package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Utils.GameUtils.Spectator;
import net.blockcade.Arcade.Varables.GameState;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Rotation;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static net.blockcade.Arcade.games.BedBattles.Main.chests;

public class PlayerInteractEvent implements Listener {

    Game game;
    public PlayerInteractEvent(Game game){this.game=game;}

    @EventHandler
    public void PlayerInteract(org.bukkit.event.player.PlayerInteractEvent e){
        e.getPlayer().getInventory().getItemInMainHand().setDurability((short)0);
        if(Spectator.isSpectator(e.getPlayer())){e.setCancelled(true);return;}
        if(!game.GameState().equals(GameState.IN_GAME))return;
        if(e.getClickedBlock()!=null&&e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
            e.getPlayer().openInventory(Main.teams.get(game.TeamManager().getTeam(e.getPlayer())).getEnderchest());
            e.setUseItemInHand(Event.Result.DENY);
            e.setUseInteractedBlock(Event.Result.DENY);
        }
        if(e.getClickedBlock()!=null&&e.getClickedBlock().getType().equals(Material.CHEST)){
            BedTeam team = chests.get((Chest)e.getClickedBlock().getState());
            if(team!=null){
                if(!(game.TeamManager().isEliminated(team.getTeam())||team.getTeam().equals(game.TeamManager().getTeam(e.getPlayer())))){
                    e.getPlayer().sendMessage(Text.format("&cYou may not open this chest until %s team is eliminated.",team.getTeam().name()));
                    e.setCancelled(true);
                }
            }
        }
        if(e.getItem()!=null){
            TeamColors team = game.TeamManager().getTeam(e.getPlayer());
            switch(e.getItem().getType()){
                case ENDER_CHEST:
                    e.getPlayer().openInventory(Main.teams.get(game.TeamManager().getTeam(e.getPlayer())).getEnderchest());
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                    e.setUseItemInHand(Event.Result.DENY);
                    e.setUseInteractedBlock(Event.Result.DENY);
                    break;
                case FIRE_CHARGE:
                    e.getPlayer().launchProjectile(Fireball.class,e.getPlayer().getLocation().getDirection());
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                    e.setUseItemInHand(Event.Result.DENY);
                    e.setUseInteractedBlock(Event.Result.DENY);
                    break;
                case SILVERFISH_SPAWN_EGG:
                    Silverfish silverfish = e.getPlayer().getWorld().spawn(e.getClickedBlock().getLocation().add(0,1,0),Silverfish.class);
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            for(Entity entity: Objects.requireNonNull(silverfish.getLocation().getWorld()).getNearbyEntities(silverfish.getLocation(),2,2,2)){
                                if(!(entity instanceof Player)){continue;}
                                if(!game.TeamManager().getTeamPlayers(team).contains((Player)entity)){
                                    silverfish.setTarget((LivingEntity) entity);
                                    return;
                                }
                            }
                        }
                    }.runTaskTimer(Main.getPlugin(Main.class),0L,1L);
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                    e.setUseItemInHand(Event.Result.DENY);
                    e.setUseInteractedBlock(Event.Result.DENY);
                    break;
                case EGG:
                    e.setCancelled(true);
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                    Egg egg = e.getPlayer().launchProjectile(Egg.class);
                    egg.teleport(egg.getLocation().add(1,-1,1));
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            if(!egg.isDead()){
                                Location l = egg.getLocation().clone();
                                new BukkitRunnable(){
                                    @Override
                                    public void run() {
                                        for(int i=0;i<2;i++){
                                            Material team_wool = Material.valueOf((team.getTranslation()+"_WOOL").toUpperCase());
                                            game.BlockManager().update(l.clone().add(i,0,0),l.clone().add(i,0,0).getBlock().getType(),l.clone().add(i,0,0).getBlock().getBlockData());
                                            game.BlockManager().update(l.clone().add(0,i,0),l.clone().add(0,i,0).getBlock().getType(),l.clone().add(0,i,0).getBlock().getBlockData());
                                            game.BlockManager().update(l.clone().add(0,0,i),l.clone().add(0,0,i).getBlock().getType(),l.clone().add(0,0,i).getBlock().getBlockData());
                                            l.clone().add(i,0,0).getBlock().setType(team_wool);
                                            l.clone().add(0,i,0).getBlock().setType(team_wool);
                                            l.clone().add(0,0,i).getBlock().setType(team_wool);
                                        }
                                    }
                                }.runTaskLater(Main.getPlugin(Main.class),2L);
                            }else {
                                cancel();
                            }
                        }
                    }.runTaskTimer(Main.getPlugin(Main.class),0L,0L);
                    break;
            }
        }
    }

    @EventHandler
    public void ProjectileHitEvent(ProjectileHitEvent e){
        switch (e.getEntity().getType()){
            case FIREBALL:
                Objects.requireNonNull(e.getHitBlock()).getLocation().getWorld().createExplosion(e.getHitBlock().getLocation(),0,true);
                TNTPrimed tnt = (TNTPrimed)Objects.requireNonNull(e.getHitBlock()).getLocation().getWorld().spawnEntity(e.getHitBlock().getLocation(),EntityType.PRIMED_TNT);
                tnt.setFuseTicks(0);
                break;
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event){
        event.getPlayer().getInventory().getItemInMainHand().setDurability((short)0);
        event.setCancelled(true);
        if(event.getRightClicked() instanceof ItemFrame){
            ItemFrame frame = (ItemFrame)event.getRightClicked();
            frame.setRotation(Rotation.NONE);
            event.getPlayer().sendMessage(Text.format("&cSprays has been disabled. Now added to to-do"));
            /*
            try {
                String logo = "blockcade.png";
                switch(event.getPlayer().getName().toLowerCase()){
                    case "aspytheaussie":
                        logo="stelch.png";
                        break;
                    case "imservs":
                        logo="frostystudios.png";
                        break;
                }
                frame.setItem(net.blockcade.Arcade.games.BedBattles.Misc.ItemFrame.getMap(new URL("https://mc2.stelch.gg/assets/sprays/"+logo)));
                event.getPlayer().playSound(frame.getLocation(), Sound.ENTITY_SPIDER_AMBIENT,1f,1f);
            }catch (Exception e){
                e.printStackTrace();;
            }
            */
        }
    }

    /**
     * Fix Targeting for silverfish
     */
    @EventHandler
    public void SilverfishTarget(EntityTargetEvent e){
        if(e.getEntityType().equals(EntityType.SILVERFISH))e.setCancelled(true);
    }
}
