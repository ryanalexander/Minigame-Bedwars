package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Spectator;
import net.blockcade.Arcade.Utils.Text;
import net.blockcade.Arcade.Varables.GameState;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapFont;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;
import org.jcp.xml.dsig.internal.dom.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

import static org.bukkit.Bukkit.getServer;

public class PlayerInteractEvent implements Listener {

    Game game;
    public PlayerInteractEvent(Game game){this.game=game;}

    @EventHandler
    public void PlayerInteract(org.bukkit.event.player.PlayerInteractEvent e){
        if(Spectator.isSpectator(e.getPlayer())){e.setCancelled(true);return;}
        if(!game.GameState().equals(GameState.IN_GAME))return;
        if(e.getClickedBlock()!=null&&e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
            e.getPlayer().openInventory(Main.teams.get(game.TeamManager().getTeam(e.getPlayer())).getEnderchest());
            e.setUseItemInHand(Event.Result.DENY);
            e.setUseInteractedBlock(Event.Result.DENY);
        }
        if(e.getItem()!=null){
            switch(e.getItem().getType()){
                case ENDER_CHEST:
                    e.getPlayer().openInventory(Main.teams.get(game.TeamManager().getTeam(e.getPlayer())).getEnderchest());
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                    e.setUseItemInHand(Event.Result.DENY);
                    e.setUseInteractedBlock(Event.Result.DENY);
                    break;
                case FIRE_CHARGE:
                    Fireball fireball = e.getPlayer().getWorld().spawn(e.getPlayer().getLocation(), Fireball.class);
                    fireball.setDirection(e.getPlayer().getEyeLocation().getDirection());
                    fireball.setVelocity(fireball.getDirection().multiply(0.1));
                    e.getItem().setAmount(e.getItem().getAmount()-1);
                    e.setUseItemInHand(Event.Result.DENY);
                    e.setUseInteractedBlock(Event.Result.DENY);
                    break;
                case SILVERFISH_SPAWN_EGG:
                    Silverfish silverfish = e.getPlayer().getWorld().spawn(e.getClickedBlock().getLocation().add(0,1,0),Silverfish.class);
                    TeamColors team = game.TeamManager().getTeam(e.getPlayer());
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            for(Entity entity:silverfish.getLocation().getWorld().getNearbyEntities(silverfish.getLocation(),5,5,5)){
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
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent event){
        event.setCancelled(true);
        if(event.getRightClicked() instanceof ItemFrame){
            ItemFrame frame = (ItemFrame)event.getRightClicked();
            frame.setRotation(Rotation.NONE);
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
