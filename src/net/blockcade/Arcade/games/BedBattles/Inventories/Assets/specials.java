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
import net.blockcade.Arcade.Utils.Item;
import net.blockcade.Arcade.Utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static org.bukkit.Material.*;

public class specials implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        specials.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully's Specials")), false);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });

        Item tnt = new Item(Material.TNT, "&bTNT");
        tnt.setLore(new String[]{
                "&r",
                "&7Cost: &64 Gold"
        });
        tnt.setAmount(1);
        tnt.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, GOLD_INGOT, 4))
                    p.getInventory().addItem(new ItemStack(Material.TNT, 1));
            }
        });

        Item epearl = new Item(ENDER_PEARL, "&bEnder Pearl");
        epearl.setLore(new String[]{
                "&r",
                "&7Cost: &a4 Emeralds"
        });
        epearl.setAmount(1);
        epearl.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, EMERALD, 4))
                    p.getInventory().addItem(new ItemStack(ENDER_PEARL, 1));
            }
        });

        Item water = new Item(WATER_BUCKET, "&bWater");
        water.setLore(new String[]{
                "&r",
                "&7Cost: &64 Gold"
        });
        water.setAmount(1);
        water.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, GOLD_INGOT, 4))
                    p.getInventory().addItem(new ItemStack(WATER_BUCKET, 1));
            }
        });

        Item fireball = new Item(FIRE_CHARGE, "&bFireball");
        fireball.setLore(new String[]{
                "&r",
                "&7Cost: &f40 Iron"
        });
        fireball.setAmount(1);
        fireball.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, IRON_INGOT, 40))
                    p.getInventory().addItem(new ItemStack(FIRE_CHARGE, 1));
            }
        });

        Item bridge_egg = new Item(EGG, "&bBridge Egg");
        bridge_egg.setLore(new String[]{
                "&r",
                "&7Cost: &a3 Emeralds"
        });
        bridge_egg.setAmount(1);
        bridge_egg.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, EMERALD, 3))
                    p.getInventory().addItem(new ItemStack(EGG, 1));
            }
        });

        Item golden_apple = new Item(GOLDEN_APPLE, "&bGolden Apple");
        golden_apple.setLore(new String[]{
                "&r",
                "&7Cost: &63 Gold"
        });
        golden_apple.setAmount(1);
        golden_apple.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, GOLD_INGOT, 3))
                    p.getInventory().addItem(new ItemStack(GOLDEN_APPLE, 1));
            }
        });


        specials.shop.setItem(0, close.spigot());
        specials.shop.setItem(19, tnt.spigot());
        specials.shop.setItem(20, epearl.spigot());
        specials.shop.setItem(21, water.spigot());
        //specials.shop.setItem(22,fireball.spigot());
        //specials.shop.setItem(23, bridge_egg.spigot());
        specials.shop.setItem(24, golden_apple.spigot());
        return specials.shop;
    }
}
