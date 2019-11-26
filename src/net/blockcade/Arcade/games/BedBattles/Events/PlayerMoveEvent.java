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
 *  @since 25/7/2019
 */

package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.GameUtils.Spectator;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Map;

public class PlayerMoveEvent implements Listener {

    Game game;

    public PlayerMoveEvent(Game game) {
        this.game = game;
    }

    @EventHandler
    public void PlayerMove(org.bukkit.event.player.PlayerMoveEvent e) {
        if (!game.TeamManager().hasTeam(e.getPlayer())) return;
        TeamColors team = game.TeamManager().getTeam(e.getPlayer());



        BedTeam closest = closestBed(e.getTo());
        if(!(closest.getTeam().equals(team))&&closest.traps.size()>0&& (!Spectator.isSpectator(e.getPlayer())) && !Main.trap_immunity.contains(e.getPlayer())){
            if(closest.getTrap()!=null&&((closest.getBed().getLocation().distanceSquared(e.getTo()))<200)){
                switch (closest.getTrap()){
                    case ALERT:
                        e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                        break;
                    case BLINDNESS:
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,140,2),true);
                        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW,140,2),true);
                        break;
                    default:
                        System.out.println("Unknown trap exception "+closest.getTrap());
                }
                Text.sendMessage(e.getPlayer(),"&c&lTrap Triggered", Text.MessageType.TITLE);
                Text.sendMessage(e.getPlayer(),"&fYou have triggered &e"+closest.getTrap().getName()+"&f!!", Text.MessageType.SUBTITLE);
                for(Player player : game.TeamManager().getTeamPlayers(closest.getTeam())){
                    Text.sendMessage(player,"&c&lTrap Triggered", Text.MessageType.TITLE);
                    Text.sendMessage(player,"&e"+closest.getTrap().getName()+"&f has been triggered", Text.MessageType.SUBTITLE);
                }
                closest.removeTrap();
            }
        }

        if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.HEALING) && (Main.teams.get(team).getBed().getLocation().distanceSquared(e.getTo()) < 300)) {
            if (!(e.getPlayer().hasPotionEffect(PotionEffectType.REGENERATION)))
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 99999, 1));
        } else {
            if ((e.getPlayer().hasPotionEffect(PotionEffectType.REGENERATION)))
                e.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
        }
    }

    private BedTeam closestBed(Location location){
        Location l = null;
        BedTeam team = null;
        double distance = 0;
        for(Map.Entry<Block,BedTeam> payload :  Main.beds.entrySet()) {
            if(l!=null){
                Location loc = payload.getKey().getLocation();
                if(distance>loc.distanceSquared(location)){
                    l=loc;
                    distance=loc.distanceSquared(location);
                    team=payload.getValue();
                }
            }else {
                team=payload.getValue();
                l=payload.getKey().getLocation();
                distance=location.distanceSquared(l);
            }
        }
        return team;
    }

}
