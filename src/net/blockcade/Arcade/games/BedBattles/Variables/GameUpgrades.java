package net.blockcade.Arcade.games.BedBattles.Variables;

import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Misc.Forge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;

import java.util.Map;

public class GameUpgrades {

    public static Event[] events = new Event[]{
            new Event("&b&lDiamond &bII", 3600,0),
            new Event("&a&lEmerald &aII", 3600,1),
            new Event("&b&lDiamond &bIII", 2400, 2),
            new Event("&a&lEmerald &aIII", 2400, 3),
            new Event("&b&lDiamond &bIV", 1800, 4),
            new Event("&c&lBeds Destroyed", 2400,5),
            new Event("&4&lSudden Death", 6000,6),
            new Event("&7&lGame Ends", 6000,7)
    };

    public static Runnable[] actions = new Runnable[]{
            ()-> {
                for(Forge f : Main.forges.get(Material.DIAMOND)){
                    f.setSpeed(f.getSpeed()-100);
                }
            },
            ()-> {
                for(Forge f : Main.forges.get(Material.EMERALD)){
                    f.setSpeed(f.getSpeed()-300);
                }
            },
            ()-> {
                for(Forge f : Main.forges.get(Material.DIAMOND)){
                    f.setSpeed(f.getSpeed()-100);
                }
            },
            ()-> {
                for(Forge f : Main.forges.get(Material.EMERALD)){
                    f.setSpeed(f.getSpeed()-300);
                }
            },
            ()-> {
                for(Forge f : Main.forges.get(Material.DIAMOND)){
                    f.setSpeed(f.getSpeed()-200);
                }
            },
            () -> {
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
            },
            ()-> {
                Bukkit.broadcastMessage("Sudden Death");
            },
            () -> {
                Main.game.stop(true);
            }
    };

    public static class Event {

        String name;
        int time;
        int id;

        public Event(String name, int time, int id){
            this.name=name;
            this.time=time;
            this.id=id;
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
    }

}