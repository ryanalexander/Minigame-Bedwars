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
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveEvent implements Listener {

    Game game;

    public PlayerMoveEvent(Game game) {
        this.game = game;
    }

    @EventHandler
    public void PlayerMove(org.bukkit.event.player.PlayerMoveEvent e) {
        if (!game.TeamManager().hasTeam(e.getPlayer())) return;
        TeamColors team = game.TeamManager().getTeam(e.getPlayer());

        if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.HEALING) && (Main.teams.get(team).getBed().getLocation().distanceSquared(e.getTo()) < 300)) {
            if (!(e.getPlayer().hasPotionEffect(PotionEffectType.HEAL)))
                e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 99999, 1));
        } else {
            if ((e.getPlayer().hasPotionEffect(PotionEffectType.HEAL)))
                e.getPlayer().removePotionEffect(PotionEffectType.HEAL);
        }
    }

}
