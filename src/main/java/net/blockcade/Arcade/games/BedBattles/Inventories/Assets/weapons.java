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
 *  @since 23/7/2019
 */

/*
 *
 * *
 *  *
 *  * © Stelch Games 2019, distribution is strictly prohibited
 *  *
 *  * Changes to this file must be documented on push.
 *  * Unauthorised changes to this file are prohibited.
 *  *
 *  * @author Ryan Wood
 *  * @since 14/7/2019
 *
 */

package net.blockcade.Arcade.games.BedBattles.Inventories.Assets;


import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Misc.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class weapons implements Listener {

    private static Inventory shop;

    public static Inventory getShop(Game game, Player player) {
        weapons.shop = header.format(game,player, Bukkit.createInventory(null, 9 * 6, Text.format("&cWeapons of minimal destruction")), true,0);

        weapons.shop.setItem(28, Item.WEAPON_STONE_SWORD.spigot());
        weapons.shop.setItem(29, Item.WEAPON_IRON_SWORD.spigot());
        weapons.shop.setItem(30, Item.WEAPON_DIAMOND_SWORD.spigot());
        weapons.shop.setItem(32, Item.WEAPON_BOW.spigot());
        weapons.shop.setItem(33, Item.WEAPON_BOW_SUPER.spigot());
        weapons.shop.setItem(34, Item.WEAPON_ARROW.spigot());

        return weapons.shop;
    }

    public static void removeSword(Player player) {
        if (player.getInventory().contains(Material.WOODEN_SWORD)) player.getInventory().remove(Material.WOODEN_SWORD);
        if (player.getInventory().contains(Material.STONE_SWORD)) player.getInventory().remove(Material.STONE_SWORD);
        if (player.getInventory().contains(Material.WOODEN_SWORD)) player.getInventory().remove(Material.IRON_SWORD);
        if (player.getInventory().contains(Material.DIAMOND_SWORD))
            player.getInventory().remove(Material.DIAMOND_SWORD);

    }
}
