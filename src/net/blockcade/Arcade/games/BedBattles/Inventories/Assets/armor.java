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
 *  @since 18/8/2019
 */

package net.blockcade.Arcade.games.BedBattles.Inventories.Assets;


import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Formatting.Item;
import net.blockcade.Arcade.Utils.Formatting.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge;
import static org.bukkit.Material.*;

public class armor implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        armor.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cFancy Clothing Line")), true,0);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });

        Item armor_chain = new Item(CHAINMAIL_CHESTPLATE, "&7Chainmail Armor");
        armor_chain.setLore(new String[]{
                "&r",
                "&aCost: &f40 Iron",
        });
        armor_chain.setAmount(1);
        armor_chain.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, IRON_INGOT, 40)) {
                    ItemStack armor_leggings = new ItemStack(CHAINMAIL_LEGGINGS);
                    ItemStack armor_boots = new ItemStack(CHAINMAIL_BOOTS);
                    p.getInventory().setArmorContents(new ItemStack[]{armor_boots, armor_leggings, player.getInventory().getChestplate(), player.getInventory().getHelmet()});
                }
            }
        });

        Item armor_iron = new Item(IRON_CHESTPLATE, "&fIron Armor");
        armor_iron.setLore(new String[]{
                "&r",
                "&aCost: &612 Gold",
        });
        armor_iron.setAmount(1);
        armor_iron.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, GOLD_INGOT, 12)) {
                    ItemStack armor_leggings = new ItemStack(IRON_LEGGINGS);
                    ItemStack armor_boots = new ItemStack(IRON_BOOTS);
                    p.getInventory().setArmorContents(new ItemStack[]{armor_boots, armor_leggings, player.getInventory().getChestplate(), player.getInventory().getHelmet()});
                }
            }
        });

        Item armor_diamond = new Item(DIAMOND_CHESTPLATE, "&bDiamond Armor");
        armor_diamond.setLore(new String[]{
                "&r",
                "&aCost: &a6 Emeralds",
        });
        armor_diamond.setAmount(1);
        armor_diamond.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, EMERALD, 6)) {
                    ItemStack armor_leggings = new ItemStack(DIAMOND_LEGGINGS);
                    ItemStack armor_boots = new ItemStack(DIAMOND_BOOTS);
                    p.getInventory().setArmorContents(new ItemStack[]{armor_boots, armor_leggings, player.getInventory().getChestplate(), player.getInventory().getHelmet()});
                }
            }
        });

        armor.shop.setItem(29, armor_chain.spigot());
        armor.shop.setItem(31, armor_iron.spigot());
        armor.shop.setItem(33, armor_diamond.spigot());

        armor.menus.add(armor.shop);
        return armor.shop;
    }
}
