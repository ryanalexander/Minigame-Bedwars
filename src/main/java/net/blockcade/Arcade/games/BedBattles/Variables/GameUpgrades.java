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

package net.blockcade.Arcade.games.BedBattles.Variables;

import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Utils.JavaUtils;
import net.blockcade.Arcade.Varables.GameModule;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Misc.Forge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class GameUpgrades {

    public static Event[] events = new Event[]{
            new Event("&b&lDiamond &bII", 3600,0,()->{
                for(Forge f : Main.forges.get(Material.DIAMOND)){
                    f.setSpeed(f.getSpeed()-100);
                }
            }),
            new Event("&a&lEmerald &aII", 3600,1,()->{
                for(Forge f : Main.forges.get(Material.EMERALD)){
                    f.setSpeed(f.getSpeed()-300);
                }
            }),
            new Event("&b&lDiamond &bIII", 2400, 2,()->{
                for(Forge f : Main.forges.get(Material.DIAMOND)){
                    f.setSpeed(f.getSpeed()-100);
                }
            }),
            new Event("&a&lEmerald &aIII", 2400, 3,()->{
                for(Forge f : Main.forges.get(Material.EMERALD)){
                    f.setSpeed(f.getSpeed()-300);
                }
            }),
            new Event("&b&lDiamond &bIV", 1800, 4,()->{
                for(Forge f : Main.forges.get(Material.DIAMOND)){
                    f.setSpeed(f.getSpeed()-200);
                }
            }),
            new Event("&c&lBeds Destroyed", 2400,5,()->{
                for(Map.Entry<Block, BedTeam> payload : Main.beds.entrySet()){
                    Block bed = payload.getKey();
                    Bed blockData = (Bed) bed.getBlockData();
                    org.bukkit.block.Block relative = bed.getRelative(blockData.getFacing());
                    bed.setType(Material.AIR);
                    relative.setType(Material.AIR, false);
                    Main.game.TeamManager().setCantRespawn(payload.getValue().getTeam(),true);
                    for(Player player : Main.game.TeamManager().getTeamPlayers(payload.getValue().getTeam())){
                        Text.sendMessage(player,"&c&lBed Destroyed", Text.MessageType.TITLE);
                        Text.sendMessage(player,"&fAll beds have been destroyed!", Text.MessageType.SUBTITLE);
                        Text.sendMessage(player,"&c&lAll beds have been destroyed!", Text.MessageType.TEXT_CHAT);
                        player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM,1,1);
                    }
                }
            }),
            new Event("&4&lSudden Death", /*6000*/300,6,()->{
                Main.game.BlockManager().doRollback();

                Main.noCamping=true;

                // Remove all managed entities
                for(Entity e : (ArrayList<Entity>)Main.game.EntityManager().GameEntities().clone())
                    Main.game.EntityManager().remove(e);

                // Remove all forges
                for(Map.Entry<Material, ArrayList<Forge>> payload : Main.forges.entrySet())
                    for(Forge forge : payload.getValue())
                        forge.stop();

                for(Map.Entry<Block, BedTeam> payload : Main.beds.entrySet()){
                    if(Main.game.TeamManager().isEliminated(payload.getValue().getTeam())) continue;
                    Main.game.setModule(GameModule.INFINITE_BUILDING,true);

                    // Clear all traps & upgrades
                    payload.getValue().upgrades.clear();
                    payload.getValue().traps.clear();

                    // Disable all forges
                    if(payload.getValue().getIron_forge()!=null)payload.getValue().getIron_forge().stop();
                    if(payload.getValue().getGold_forge()!=null)payload.getValue().getGold_forge().stop();
                    if(payload.getValue().getEmerald_forge()!=null)payload.getValue().getEmerald_forge().stop();

                    // Teleport all players to mid & give 64 blocks of wool
                    for(Player player : Main.game.TeamManager().getTeamPlayers(payload.getValue().getTeam())) {
                        player.teleport(Main.game.TeamManager().getConfigLocation("mid", payload.getValue().getTeam()));
                        player.getInventory().addItem(new ItemStack(Material.valueOf(Main.game.TeamManager().getTeam(player).getTranslation()+"_WOOL"),64));
                        player.playSound(player.getLocation(),Sound.ENTITY_ENDER_DRAGON_GROWL,1L,1L);
                    }
                }
                Bukkit.broadcastMessage(Text.format("&c&lSudden Death! &7Fight to the death, last team standing wins."));
            }),
            new Event("&7&lGame Ends", 6000,7,()->Main.game.stop(true))
    };

    public static class Event {

        String name;
        int time;
        int id;
        Runnable runnable;

        public Event(String name, int time, int id, Runnable runnable){
            this.name=name;
            this.time=time;
            this.id=id;
            this.runnable=runnable;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getTime() {
            return time;
        }

        public void run() {
            runnable.run();
        }
    }

}
