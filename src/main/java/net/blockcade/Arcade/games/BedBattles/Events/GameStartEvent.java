/*
 *
 *
 *  © Stelch Software 2020, distribution is strictly prohibited
 *  Blockcade is a company of Stelch Software
 *
 *  Changes to this file must be documented on push.
 *  Unauthorised changes to this file are prohibited.
 *
 *  @author Ryan W
 * @since (DD/MM/YYYY) 18/1/2020
 */

/*
 *
 *  *
 *  * © Stelch Software 2019, distribution is strictly prohibited
 *  * Blockcade is a company of Stelch Software
 *  *
 *  * Changes to this file must be documented on push.
 *  * Unauthorised changes to this file are prohibited.
 *  *
 *  * @author Ryan Wood
 *  @since 18/8/2019
 */

package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Managers.GamePlayer;
import net.blockcade.Arcade.Managers.ScoreboardManager;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Utils.JavaUtils;
import net.blockcade.Arcade.Varables.GameState;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Inventories.shop;
import net.blockcade.Arcade.games.BedBattles.Inventories.team_shop;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedPlayer;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Misc.Forge;
import net.blockcade.Arcade.games.BedBattles.Misc.SpinningBlock;
import net.blockcade.Arcade.games.BedBattles.Variables.GameUpgrades;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static net.blockcade.Arcade.games.BedBattles.Main.*;

public class GameStartEvent implements Listener {

    Game game;

    public GameStartEvent(Game game) {
        this.game = game;
    }

    @EventHandler
    public void GameStartEvent(net.blockcade.Arcade.Managers.EventManager.GameStartEvent e) {

        for(Player player : Bukkit.getOnlinePlayers()){
            // Register BedPlayer object (Also creates GamePlayer)
            BedPlayer bPlayer = new BedPlayer(player);

            // Scoreboard //
            ScoreboardManager sm = new ScoreboardManager("G" + player.getName(), game);
            sm.setGamePlayer(bPlayer);
            sm.registerPlaceholder(new ScoreboardManager.placeholder() {
                @Override
                public String String(GamePlayer player) {
                    return ""+BedPlayer.getBedPlayer(player).getBedDestroys();
                }
            },":BED_DESTROYS:");
            sm.enableHealthCounter();
            String name = "  BedBattles  ";
            sm.setDisplayname(name);
            scoreboard_upgrade_offsets.put(sm, sm.addLine("COUNTER"));
            sm.addBlank();
            for (TeamColors teamColor : TeamColors.values()) {
                sm.addLine(teamColor.getChatColor() + teamColor.name().substring(0, 1) + "&r " + teamColor.name().substring(0, 1).toUpperCase() + teamColor.name().substring(1).toLowerCase() + String.format(" :ELIMINATED_%s: ", teamColor.name()) + (teamColor == bPlayer.getTeam() ? "&7You" : ""));
            }
            sm.addBlank();
            sm.addLine("&fKills: &a:KILLS:");
            sm.addLine("&fEliminations: &a:ELIMINATIONS:");
            sm.addLine("&fBeds Destroyed: &a:BED_DESTROYS:");
            sm.addBlank();
            sm.addLine("   &dblockcade.net   ");

            sm.showFor(player);

            player.teleport(game.TeamManager().getSpawn(bPlayer.getTeam()));
            player.getInventory().setArmorContents(game.TeamManager().getArmor(bPlayer.getTeam()));

            player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
        }



        for (TeamColors team : TeamColors.values()) {
            BedTeam bedTeam = null;
            if(game.TeamManager().getActive_teams().contains(team))bedTeam=new BedTeam(game, team);

            // Spawn item shop
            Location shoploc = game.TeamManager().getConfigLocation("shop", team);
            LivingEntity _shop = (LivingEntity) Objects.requireNonNull(shoploc.getWorld()).spawnEntity(shoploc, EntityType.VILLAGER);
            _shop.setCustomName(Text.format("Shopkeeper"));
            _shop.setCustomNameVisible(true);
            _shop.setInvulnerable(true);
            _shop.setAI(false);
            _shop.setSilent(true);
            _shop.setCollidable(false);

            game.EntityManager().AddGameEntity(_shop);
            game.EntityManager().setFunction(_shop,(p)-> p.openInventory((new shop()).getShop(game, p)));

            // Spawn team shop
            Location tshoploc = game.TeamManager().getConfigLocation("team_shop", team);
            LivingEntity _tshop = (LivingEntity) tshoploc.getWorld().spawnEntity(tshoploc, EntityType.VILLAGER);
            _tshop.setCustomName(Text.format("Team shop"));
            _tshop.setCustomNameVisible(true);
            _tshop.setInvulnerable(true);
            _tshop.setAI(false);
            _tshop.setSilent(true);
            _tshop.setCollidable(false);

            game.EntityManager().AddGameEntity(_tshop);
            game.EntityManager().setFunction(_tshop, (p)->p.openInventory((new team_shop()).getShop(game, p)));

            if(bedTeam!=null) {

                // Real Team Functions

                // Spawn Chest
                Block _chest = game.TeamManager().getConfigLocation("chest", team).getBlock();
                _chest.setType(Material.CHEST);
                Chest chest = (Chest) _chest.getState();
                bedTeam.setChest(chest);
                chest.getBlockInventory().clear();

                // Spawn Bed
                Block bed = game.TeamManager().getConfigLocation("bed", team).getBlock();
                BlockFace direction = JavaUtils.direction((float)game.TeamManager().getConfigInt("bed.yaw", team));
                bed.setType(Material.RED_BED);
                Bed blockData = (Bed) bed.getBlockData();
                blockData.setPart(Bed.Part.FOOT);
                blockData.setFacing(direction);
                bed.setBlockData(blockData);

                org.bukkit.block.Block relative = bed.getRelative(direction);
                relative.setType(Material.RED_BED, false);
                Bed relativeBlockData = (Bed) relative.getBlockData();
                relativeBlockData.setPart(Bed.Part.HEAD);
                relativeBlockData.setFacing(direction);
                relative.setBlockData(relativeBlockData);

                bedTeam.setBed(bed);

                Main.chests.put(chest,bedTeam);
                Main.beds.put(bed,bedTeam);

                // Spawn Forges
                Forge iron_forge = new Forge(game, game.TeamManager().getConfigLocation("forge", team), Material.IRON_INGOT, (2 * 20), true,40,null);
                Forge gold_forge = new Forge(game, game.TeamManager().getConfigLocation("forge", team), Material.GOLD_INGOT, (8 * 20), true,20,null);

                bedTeam.setIron_forge(iron_forge);
                bedTeam.setGold_forge(gold_forge);

                Main.teams.put(team, bedTeam);
            }
        }

        forges.put(Material.DIAMOND,new ArrayList<>());
        forges.put(Material.EMERALD,new ArrayList<>());

        // Spawn Diamond Forges
        List<String> diamond_locations = game.handler().getConfig().getStringList(String.format("maps.%s.DIAMOND_FORGE", game.map().getName()));
        for (String raw_loc : diamond_locations) {
            String[] loc_data = raw_loc.split("[:]");
            Location pos = new Location(Bukkit.getWorld(loc_data[0]), Double.parseDouble(loc_data[1]), Double.parseDouble(loc_data[2]), Double.parseDouble(loc_data[3]));
            Forge forge = new Forge(game, pos, Material.DIAMOND, (30 * 20), false,4,"&b&lDiamond Generator");
            forges.get(Material.DIAMOND).add(forge);
            forge.setSpinningBlock(new SpinningBlock(pos.add(0, 1.5, 0), Material.DIAMOND_BLOCK, 0, null));
        }
        // Spawn Emerald Forges
        List<String> emerald_locations = game.handler().getConfig().getStringList(String.format("maps.%s.EMERALD_FORGE", game.map().getName()));
        for (String raw_loc : emerald_locations) {
            String[] loc_data = raw_loc.split("[:]");
            Location pos = new Location(Bukkit.getWorld(loc_data[0]), Double.parseDouble(loc_data[1]), Double.parseDouble(loc_data[2]), Double.parseDouble(loc_data[3]));
            Forge forge = new Forge(game, pos, Material.EMERALD, (60 * 20), false,2,"&a&lEmerald Generator");
            forges.get(Material.EMERALD).add(forge);
            forge.setSpinningBlock(new SpinningBlock(pos.add(0, 1.5, 0), Material.EMERALD_BLOCK, 0, null));
        }

        new BukkitRunnable(){
            int level = 6;
            long timer = 0;
            @Override
            public void run() {
                if(!game.GameState().equals(GameState.IN_GAME))cancel();
                if(Main.noCamping){
                    for(Map.Entry<Player,Integer> payload : ((HashMap<Player,Integer>)camping.clone()).entrySet()){
                        BedTeam closest = PlayerMoveEvent.closestBed(payload.getKey().getLocation());
                        if((closest.getBed().getLocation().distanceSquared(payload.getKey().getLocation()))<200) {
                            Text.sendMessage(payload.getKey(),"&6&lOUT OF BOUNDS, TELEPORTING IN &c&l"+payload.getValue()+"&6&l SECONDS", Text.MessageType.ACTION_BAR);
                            camping.put(payload.getKey(),payload.getValue()-1);
                        }
                        if(payload.getValue()<=0) {
                            Text.sendMessage(payload.getKey(),"&c&lRETURNED TO MID", Text.MessageType.ACTION_BAR);
                            payload.getKey().teleport(game.TeamManager().getConfigLocation("mid", game.TeamManager().getTeam(payload.getKey())));
                            camping.remove(payload.getKey());
                        }
                    }
                }
                if(level>=GameUpgrades.events.length)return;
                if(timer>=GameUpgrades.events[level].getTime()){
                    GameUpgrades.events[level].run();
                    level++;
                    timer=1;
                }else {
                    for(Map.Entry<ScoreboardManager, Integer> payload : scoreboard_upgrade_offsets.entrySet()){
                        ScoreboardManager sm = payload.getKey();
                        long i = ((GameUpgrades.events[level].getTime()-timer)/20)*1000;
                        long SECONDS = JavaUtils.FormatMS(i, JavaUtils.TimeUnit.SECOND);
                        long MINUTES = Math.round(SECONDS/60);
                        SECONDS=(SECONDS-((MINUTES)*60));
                        String SECONDS_FORMATTED = (SECONDS<=9?"0":"")+SECONDS;
                        String MINUTES_FORMATTED = (MINUTES<=9?"0":"")+MINUTES;

                        sm.editLine(payload.getValue()+1,GameUpgrades.events[level].getName());
                        sm.editLine(payload.getValue(),String.format("&e%s:&e%s",MINUTES_FORMATTED,SECONDS_FORMATTED));

                    }
                    timer=timer+20;
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class),0L,20L);
    }


}
