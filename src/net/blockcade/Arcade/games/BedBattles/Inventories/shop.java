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

package net.blockcade.Arcade.games.BedBattles.Inventories;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Inventories.Assets.header;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class shop implements Listener {

    private Inventory shop;

    private ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public Inventory getShop(Game game, Player player) {
        this.shop = header.format(game, player, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully")), true,true,9);

        this.menus.add(this.shop);
        return this.shop;
    }


    public static boolean doCharge(Player player, Material mat, int amount) {

        if (player.getInventory().contains(mat, amount)) {
            ItemStack payload = new ItemStack(mat);
            for (int i = 0; i < amount; i++) {
                player.getInventory().removeItem(payload);
            }
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            return true;
        } else {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            return false;
        }
    }
}
