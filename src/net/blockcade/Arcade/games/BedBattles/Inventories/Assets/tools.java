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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static net.blockcade.Arcade.games.BedBattles.Inventories.Assets.itemUpgrades.*;
import static org.bukkit.Material.*;

public class tools implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        tools.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully's Tools")), false);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });

        Item shears = new Item(Material.SHEARS, "&bShears");
        shears.setLore(new String[]{
                "&r",
                "&7Cost: &f20 Iron"
        });
        shears.setAmount(1);
        shears.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 20))
                    p.getInventory().addItem(new ItemStack(Material.SHEARS, 1));
            }
        });


        Item pickaxe = new Item(nextUpgrade(player, PICAXE), "&bPickaxe");
        ItemStack pickaxe_cost = getPrice(player, PICAXE);
        pickaxe.setLore(new String[]{
                "&r",
                "&7Cost: &f" + ((pickaxe_cost != null) ? pickaxe_cost.getAmount() + " " + pickaxe_cost.getType().name().replaceAll("_", " ").toLowerCase() : "&cFully Upgraded")
        });
        pickaxe.setAmount(1);
        pickaxe.setOnClick(new Item.click() {
            public void run(Player p) {
                if (pickaxe_cost != null) {
                    if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, pickaxe_cost.getType(), pickaxe_cost.getAmount())) {
                        Material last = lastUpgrade(player, PICAXE);
                        Material next = nextUpgrade(player, PICAXE);
                        if (last != null) {
                            p.getInventory().remove(last);
                        }
                            ItemStack is = new ItemStack(next, 1);
                            is.addEnchantment(Enchantment.DIG_SPEED,1);
                        p.getInventory().addItem(new ItemStack(is));
                        p.openInventory(tools.getShop(game, p));
                    }
                }
            }
        });

        Item axe = new Item(nextUpgrade(player, AXE), "&bAXE");
        ItemStack axe_cost = getPrice(player, AXE);
        axe.setLore(new String[]{
                "&r",
                "&7Cost: &f" + ((axe_cost != null) ? axe_cost.getAmount() + " " + axe_cost.getType().name().replaceAll("_", " ").toLowerCase() : "&cFully Upgraded")
        });
        axe.setAmount(1);
        axe.setOnClick(new Item.click() {
            public void run(Player p) {
                if (axe_cost != null) {
                    if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, axe_cost.getType(), axe_cost.getAmount())) {
                        Material last = lastUpgrade(player, AXE);
                        Material next = nextUpgrade(player, AXE);
                        if (last != null) {
                            p.getInventory().remove(last);
                        }
                        ItemStack is = new ItemStack(next, 1);
                        is.addEnchantment(Enchantment.DIG_SPEED,1);
                        p.getInventory().addItem(new ItemStack(is));
                        p.openInventory(tools.getShop(game, p));
                    }
                }
            }
        });

        Item shovel = new Item(nextUpgrade(player, SHOVEL), "&bSHOVEL");
        ItemStack shovel_cost = getPrice(player, SHOVEL);
        shovel.setLore(new String[]{
                "&r",
                "&7Cost: &f" + ((shovel_cost != null) ? shovel_cost.getAmount() + " " + shovel_cost.getType().name().replaceAll("_", " ").toLowerCase() : "&cFully Upgraded")
        });
        shovel.setAmount(1);
        shovel.setOnClick(new Item.click() {
            public void run(Player p) {
                if (shovel_cost != null) {
                    if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, shovel_cost.getType(), shovel_cost.getAmount())) {
                        Material last = lastUpgrade(player, SHOVEL);
                        Material next = nextUpgrade(player, SHOVEL);
                        if (last != null) {
                            p.getInventory().remove(last);
                        }
                        ItemStack is = new ItemStack(next, 1);
                        is.addEnchantment(Enchantment.DIG_SPEED,1);
                        p.getInventory().addItem(new ItemStack(is));
                        p.openInventory(tools.getShop(game, p));
                    }
                }
            }
        });

        tools.shop.setItem(0, close.spigot());
        if (!(player.getInventory().contains(Material.SHEARS)))
            tools.shop.setItem(19, shears.spigot());
        tools.shop.setItem(20, pickaxe.spigot());
        tools.shop.setItem(21, axe.spigot());
        tools.shop.setItem(22, shovel.spigot());

        tools.menus.add(tools.shop);
        return tools.shop;
    }

    public static Material nextUpgrade(Player player, itemUpgrades upgrade) {
        switch (upgrade) {
            case PICAXE:
                if (player.getInventory().contains(DIAMOND_PICKAXE)) {
                    return DIAMOND_PICKAXE;
                }
                if (player.getInventory().contains(GOLDEN_PICKAXE)) {
                    return DIAMOND_PICKAXE;
                }
                if (player.getInventory().contains(IRON_PICKAXE)) {
                    return GOLDEN_PICKAXE;
                }
                if (player.getInventory().contains(WOODEN_PICKAXE)) {
                    return IRON_PICKAXE;
                }
                return WOODEN_PICKAXE;
            case AXE:
                if (player.getInventory().contains(DIAMOND_AXE)) {
                    return DIAMOND_AXE;
                }
                if (player.getInventory().contains(GOLDEN_AXE)) {
                    return DIAMOND_AXE;
                }
                if (player.getInventory().contains(IRON_AXE)) {
                    return GOLDEN_AXE;
                }
                if (player.getInventory().contains(WOODEN_AXE)) {
                    return IRON_AXE;
                }
                return WOODEN_AXE;
            case SHOVEL:
                if (player.getInventory().contains(DIAMOND_SHOVEL)) {
                    return DIAMOND_SHOVEL;
                }
                if (player.getInventory().contains(GOLDEN_SHOVEL)) {
                    return DIAMOND_SHOVEL;
                }
                if (player.getInventory().contains(IRON_SHOVEL)) {
                    return GOLDEN_SHOVEL;
                }
                if (player.getInventory().contains(WOODEN_SHOVEL)) {
                    return IRON_SHOVEL;
                }
                return WOODEN_SHOVEL;
            default:
                break;
        }
        return null;
    }

    public static Material lastUpgrade(Player player, itemUpgrades upgrade) {
        switch (upgrade) {
            case PICAXE:
                if (player.getInventory().contains(DIAMOND_PICKAXE)) {
                    return DIAMOND_PICKAXE;
                }
                if (player.getInventory().contains(GOLDEN_PICKAXE)) {
                    return GOLDEN_PICKAXE;
                }
                if (player.getInventory().contains(IRON_PICKAXE)) {
                    return IRON_PICKAXE;
                }
                if (player.getInventory().contains(WOODEN_PICKAXE)) {
                    return WOODEN_PICKAXE;
                }
                return null;
            case AXE:
                if (player.getInventory().contains(DIAMOND_AXE)) {
                    return DIAMOND_AXE;
                }
                if (player.getInventory().contains(GOLDEN_AXE)) {
                    return GOLDEN_AXE;
                }
                if (player.getInventory().contains(IRON_AXE)) {
                    return IRON_AXE;
                }
                if (player.getInventory().contains(WOODEN_AXE)) {
                    return WOODEN_AXE;
                }
                return null;
            case SHOVEL:
                if (player.getInventory().contains(DIAMOND_SHOVEL)) {
                    return DIAMOND_SHOVEL;
                }
                if (player.getInventory().contains(GOLDEN_SHOVEL)) {
                    return GOLDEN_SHOVEL;
                }
                if (player.getInventory().contains(IRON_SHOVEL)) {
                    return IRON_SHOVEL;
                }
                if (player.getInventory().contains(WOODEN_SHOVEL)) {
                    return WOODEN_SHOVEL;
                }
                return null;
            default:
                break;
        }
        return null;
    }

    public static ItemStack getPrice(Player player, itemUpgrades upgrade) {
        switch (upgrade) {
            case PICAXE:
                if (player.getInventory().contains(DIAMOND_PICKAXE)) {
                    return null;
                }
                if (player.getInventory().contains(GOLDEN_PICKAXE)) {
                    return new ItemStack(Material.GOLD_INGOT, 6);
                } // DIAMOND PICKAXE
                if (player.getInventory().contains(IRON_PICKAXE)) {
                    return new ItemStack(Material.GOLD_INGOT, 3);
                } // GOLDEN PICKAXE
                if (player.getInventory().contains(WOODEN_PICKAXE)) {
                    return new ItemStack(Material.IRON_INGOT, 10);
                } // IRON PICKAXE
                return new ItemStack(IRON_INGOT, 10); // TO WOODEN PICKAXE
            case AXE:
                if (player.getInventory().contains(DIAMOND_AXE)) {
                    return null;
                }
                if (player.getInventory().contains(GOLDEN_AXE)) {
                    return new ItemStack(Material.GOLD_INGOT, 16);
                }
                if (player.getInventory().contains(IRON_AXE)) {
                    return new ItemStack(Material.GOLD_INGOT, 8);
                }
                if (player.getInventory().contains(WOODEN_AXE)) {
                    return new ItemStack(Material.IRON_INGOT, 20);
                }
                return new ItemStack(IRON_INGOT, 10);
            case SHOVEL:
                if (player.getInventory().contains(DIAMOND_SHOVEL)) {
                    return null;
                }
                if (player.getInventory().contains(GOLDEN_SHOVEL)) {
                    return new ItemStack(Material.GOLD_INGOT, 16);
                }
                if (player.getInventory().contains(IRON_SHOVEL)) {
                    return new ItemStack(Material.GOLD_INGOT, 8);
                }
                if (player.getInventory().contains(WOODEN_SHOVEL)) {
                    return new ItemStack(Material.IRON_INGOT, 20);
                }
                return new ItemStack(IRON_INGOT, 10);
            default:
                break;
        }
        return null;
    }
}
