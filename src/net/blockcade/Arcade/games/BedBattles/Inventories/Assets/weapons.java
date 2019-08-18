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
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge;

public class weapons implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        weapons.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully's blocks")), false);
        TeamColors team = game.TeamManager().getTeam(player);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });

        Item stone_sword = new Item(Material.STONE_SWORD, "&bStone Sword");
        stone_sword.setLore(new String[]{
                "&r",
                "&7Cost: &f10 Iron"
        });
        stone_sword.setAmount(1);
        stone_sword.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, Material.IRON_INGOT, 10)) {
                    removeSword(p);
                    ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
                    if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    p.getInventory().addItem(sword);
                }
            }
        });

        Item iron_sword = new Item(Material.IRON_SWORD, "&bIron Sword");
        iron_sword.setLore(new String[]{
                "&r",
                "&7Cost: &67 Gold"
        });
        iron_sword.setAmount(1);
        iron_sword.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, Material.GOLD_INGOT, 7)) {
                    removeSword(p);
                    ItemStack sword = new ItemStack(Material.IRON_SWORD, 1);
                    if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    p.getInventory().addItem(sword);
                }
            }
        });

        Item diamond_sword = new Item(Material.DIAMOND_SWORD, "&bDiamond Sword");
        diamond_sword.setLore(new String[]{
                "&r",
                "&7Cost: &a4 Emeralds"
        });
        diamond_sword.setAmount(1);
        diamond_sword.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, Material.EMERALD, 4)) {
                    try {
                        removeSword(p);
                    } catch (Exception e) {
                        System.out.println("https://www.youtube.com/watch?v=t3otBjVZzT0");
                    }
                    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
                    if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD))
                        sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    p.getInventory().addItem(sword);
                }
            }
        });

        Item bow = new Item(Material.BOW, "&bBow");
        bow.setLore(new String[]{
                "&r",
                "&7Cost: &612 Gold"
        });
        bow.setAmount(1);
        bow.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, Material.GOLD_INGOT, 12)) p.getInventory().addItem(new ItemStack(Material.BOW, 1));
            }
        });

        Item super_bow = new Item(Material.BOW, "&dSuper Bow");
        super_bow.setEnchanted(true);
        super_bow.setLore(new String[]{
                "&r",
                "&7Cost: &a6 Emeralds"
        });
        super_bow.setAmount(1);
        ItemStack super_bowis = new ItemStack(Material.BOW);
        ItemMeta super_bowmeta = super_bowis.getItemMeta();
        super_bowmeta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        super_bowmeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 1, true);
        super_bowmeta.setDisplayName(Text.format("&d" + player.getName() + "'s Super bow"));
        super_bowis.setItemMeta(super_bowmeta);
        super_bow.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, Material.EMERALD, 6)) p.getInventory().addItem(super_bowis);
            }
        });

        Item arrows = new Item(Material.ARROW, "&bArrow");
        arrows.setLore(new String[]{
                "&r",
                "&7Cost: &62 Gold"
        });
        arrows.setAmount(8);
        arrows.setOnClick(new Item.click() {
            public void run(Player p) {
                if (doCharge(p, Material.GOLD_INGOT, 2)) p.getInventory().addItem(new ItemStack(Material.ARROW, 8));
            }
        });

        weapons.shop.setItem(0, close.spigot());
        weapons.shop.setItem(19, stone_sword.spigot());
        weapons.shop.setItem(20, iron_sword.spigot());
        weapons.shop.setItem(22, diamond_sword.spigot());
        weapons.shop.setItem(23, bow.spigot());
        weapons.shop.setItem(24, super_bow.spigot());
        weapons.shop.setItem(25, arrows.spigot());

        weapons.menus.add(weapons.shop);
        return weapons.shop;
    }

    private static void removeSword(Player player) {
        if (player.getInventory().contains(Material.WOODEN_SWORD)) player.getInventory().remove(Material.WOODEN_SWORD);
        if (player.getInventory().contains(Material.STONE_SWORD)) player.getInventory().remove(Material.STONE_SWORD);
        if (player.getInventory().contains(Material.WOODEN_SWORD)) player.getInventory().remove(Material.IRON_SWORD);
        if (player.getInventory().contains(Material.DIAMOND_SWORD))
            player.getInventory().remove(Material.DIAMOND_SWORD);

    }
}
