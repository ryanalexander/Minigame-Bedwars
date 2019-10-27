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
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerRespawnEvent implements Listener {

    Game game;

    public PlayerRespawnEvent(Game game) {
        this.game = game;
    }

    @EventHandler
    public void PlayerRespawn(net.blockcade.Arcade.Managers.EventManager.PlayerRespawnEvent e) {
        if (!e.isEliminated()) {
            TeamColors team = game.TeamManager().getTeam(e.getPlayer());
            ItemStack sword = new ItemStack(Material.WOODEN_SWORD);
            if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.REINFORCED_ARMOR)) {
                for (ItemStack is : e.getPlayer().getInventory().getArmorContents()) {
                    if (is == null) continue;
                    is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Main.teams.get(team).upgrades.get(TeamUpgrades.REINFORCED_ARMOR));
                }
            }
            if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            e.getPlayer().getInventory().addItem(sword);
        }
    }

}
