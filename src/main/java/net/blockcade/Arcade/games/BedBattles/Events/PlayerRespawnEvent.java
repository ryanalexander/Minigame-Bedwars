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

import com.google.common.collect.Lists;
import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Utils.JavaUtils;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Inventories.Assets.ItemUpgrades;
import net.blockcade.Arcade.games.BedBattles.Inventories.Assets.tools;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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

            if (Main.teams.get(team).upgrades!=null&&Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            e.getPlayer().getInventory().addItem(sword);

            ArrayList<Material> materials = JavaUtils.getMatFromItemstack(Lists.newArrayList(e.getInventoryContents()));

            Material axe = tools.previousUpgrade(materials, ItemUpgrades.AXE);
            Material pickaxe = tools.previousUpgrade(materials, ItemUpgrades.PICKAXE);

            // Teleport to team spawn
            e.getPlayer().teleport(game.TeamManager().getSpawn(game.TeamManager().getTeam(e.getPlayer())));

            if(e.getKiller()!=null){
                int emeralds = 0;
                int diamonds = 0;
                for(ItemStack is : e.getInventoryContents()){
                    if(is==null)continue;
                    if(is.getType().equals(Material.EMERALD))emeralds=emeralds+is.getAmount();
                    if(is.getType().equals(Material.DIAMOND))diamonds=diamonds+is.getAmount();
                }

                if(emeralds>0||diamonds>0) {
                    Text.sendMessage(e.getKiller(), Text.format("&eFor killing %s&e you received:",e.getPlayer().getDisplayName()), Text.MessageType.TEXT_CHAT);
                    if(emeralds>0)Text.sendMessage(e.getKiller(), Text.format("&a+ %s Emeralds",emeralds+""), Text.MessageType.TEXT_CHAT);
                    if(diamonds>0)Text.sendMessage(e.getKiller(), Text.format("&b+ %s Diamonds",diamonds+""), Text.MessageType.TEXT_CHAT);

                    e.getKiller().getInventory().addItem(new ItemStack(Material.EMERALD,emeralds));
                    e.getKiller().getInventory().addItem(new ItemStack(Material.DIAMOND,diamonds));
                }
            }

            if(axe!=null){
                ItemStack is = new ItemStack(axe);
                if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                    is.addEnchantment(Enchantment.DAMAGE_ALL,1);
                is.addEnchantment(Enchantment.DIG_SPEED,1);
                e.getPlayer().getInventory().addItem(new ItemStack(axe));
            }
            if(pickaxe!=null){
                ItemStack is = new ItemStack(pickaxe);
                is.addEnchantment(Enchantment.DIG_SPEED,1);
                e.getPlayer().getInventory().addItem(new ItemStack(pickaxe));
            }

            if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.REINFORCED_ARMOR)) {
                for (ItemStack is : e.getPlayer().getInventory().getArmorContents()) {
                    if (is == null) continue;
                    is.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, Main.teams.get(team).upgrades.get(TeamUpgrades.REINFORCED_ARMOR));
                }
            }
        }
    }

}
