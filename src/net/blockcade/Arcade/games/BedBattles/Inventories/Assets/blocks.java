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

public class blocks implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        blocks.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully's blocks")), false);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });

        Item wool = new Item(Material.valueOf(game.TeamManager().getTeam(player).getTranslation().toUpperCase() + "_WOOL"), "&bWool");
        wool.setLore(new String[]{
                "&r",
                "&7Cost: &f4 Iron"
        });
        wool.setAmount(16);
        wool.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 4))
                    p.getInventory().addItem(new ItemStack(Material.valueOf(game.TeamManager().getTeam(player).getTranslation().toUpperCase() + "_WOOL"), 16));
            }
        });

        Item wooden_planks = new Item(Material.OAK_PLANKS, "&bWooden Planks");
        wooden_planks.setLore(new String[]{
                "&r",
                "&7Cost: &64 Gold"
        });
        wooden_planks.setAmount(8);
        wooden_planks.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.GOLD_INGOT, 4))
                    p.getInventory().addItem(new ItemStack(Material.OAK_PLANKS, 8));
            }
        });

        Item clay = new Item(Material.valueOf(game.TeamManager().getTeam(player).getTranslation().toUpperCase() + "_TERRACOTTA"), "&bClay");
        clay.setLore(new String[]{
                "&r",
                "&7Cost: &f12 Iron",
        });
        clay.setAmount(12);
        clay.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 12))
                    p.getInventory().addItem(new ItemStack(Material.valueOf(game.TeamManager().getTeam(player).getTranslation().toUpperCase() + "_TERRACOTTA"), 12));
            }
        });

        Item sand = new Item(Material.SAND, "&bSand");
        sand.setLore(new String[]{
                "&r",
                "&7Cost: &f24 Iron",
                "&r",
                "&dFalls"
        });
        sand.setAmount(4);
        sand.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 24))
                    p.getInventory().addItem(new ItemStack(Material.SAND, 4));
            }
        });

        Item glass = new Item(Material.GLASS, "&bGlass");
        glass.setLore(new String[]{
                "&r",
                "&7Cost: &f12 Iron",
                "&r",
                "&dBlast Proof"
        });
        glass.setAmount(4);
        glass.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 12))
                    p.getInventory().addItem(new ItemStack(Material.GLASS, 4));
            }
        });

        Item end_stone = new Item(Material.END_STONE, "&bEnd Stone");
        end_stone.setLore(new String[]{
                "&r",
                "&7Cost: &f24 Iron",
        });
        end_stone.setAmount(12);
        end_stone.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 24))
                    p.getInventory().addItem(new ItemStack(Material.END_STONE, 12));
            }
        });

        Item obsidian = new Item(Material.OBSIDIAN, "&bObsidian");
        obsidian.setLore(new String[]{
                "&r",
                "&7Cost: &a4 Emeralds",
                "&r",
                "&bBlast Proof"
        });
        obsidian.setAmount(4);
        obsidian.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.EMERALD, 4))
                    p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
            }
        });

        blocks.shop.setItem(0, close.spigot());
        blocks.shop.setItem(19, wool.spigot());
        blocks.shop.setItem(20, wooden_planks.spigot());
        blocks.shop.setItem(21, clay.spigot());
        blocks.shop.setItem(22, sand.spigot());
        blocks.shop.setItem(23, glass.spigot());
        blocks.shop.setItem(24, end_stone.spigot());
        blocks.shop.setItem(25, obsidian.spigot());

        blocks.menus.add(blocks.shop);
        return blocks.shop;
    }
}
