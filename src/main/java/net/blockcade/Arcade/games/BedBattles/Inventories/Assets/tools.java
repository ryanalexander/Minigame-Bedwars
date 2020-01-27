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
import net.blockcade.Arcade.Utils.Formatting.Item;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;

import static net.blockcade.Arcade.games.BedBattles.Inventories.Assets.ItemUpgrades.AXE;
import static net.blockcade.Arcade.games.BedBattles.Inventories.Assets.ItemUpgrades.PICKAXE;
import static org.bukkit.Material.*;

public class tools implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        tools.shop = header.format(game,player, Bukkit.createInventory(null, 9 * 6, Text.format("&cHard Worker Utilities")), true,0);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(p -> p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p)));

        Item shears = new Item(Material.SHEARS, "&bShears");
        shears.setLore("&r",
                "&7Cost: &f20 Iron");
        shears.setAmount(1);
        shears.setOnClick(p -> {
            if(p.getInventory().contains(SHEARS)){
                return;
            }
            if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.IRON_INGOT, 20))
                p.getInventory().addItem(new ItemStack(Material.SHEARS, 1));
        });


        Item pickaxe = new Item(Objects.requireNonNull(nextUpgrade(player, PICKAXE)), "&bPickaxe");
        ItemStack pickaxe_cost = getPrice(player, PICKAXE);
        pickaxe.setLore("&r",
                "&7Cost: &f" + ((pickaxe_cost != null) ? pickaxe_cost.getAmount() + " " + pickaxe_cost.getType().name().replaceAll("_", " ").toLowerCase() : "&cFully Upgraded"));
        pickaxe.setAmount(1);
        pickaxe.setOnClick(p -> {
            if (pickaxe_cost != null) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, pickaxe_cost.getType(), pickaxe_cost.getAmount())) {
                    Material last = currentUpgrade(p, PICKAXE);
                    Material next = nextUpgrade(p, PICKAXE);
                    ItemStack is = new ItemStack(next, 1);
                    is.addEnchantment(Enchantment.DIG_SPEED,1);
                    if (last != null&&p.getInventory().first(last)>=0) {
                        p.getInventory().setItem(p.getInventory().first(last),new ItemStack(is));
                        p.getInventory().remove(last);
                    }else {
                        p.getInventory().addItem(new ItemStack(is));
                    }
                    p.openInventory(tools.getShop(game, p));
                }
            }
        });

        Item axe = new Item(Objects.requireNonNull(nextUpgrade(player, AXE)), "&bAXE");
        ItemStack axe_cost = getPrice(player, AXE);
        axe.setLore("&r",
                "&7Cost: &f" + ((axe_cost != null) ? axe_cost.getAmount() + " " + axe_cost.getType().name().replaceAll("_", " ").toLowerCase() : "&cFully Upgraded"));
        axe.setAmount(1);
        axe.setOnClick(p -> {
            if (axe_cost != null) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, axe_cost.getType(), axe_cost.getAmount())) {
                    Material last = currentUpgrade(p, AXE);
                    Material next = nextUpgrade(p, AXE);
                    assert next != null;
                    ItemStack is = new ItemStack(next, 1);
                    is.addEnchantment(Enchantment.DIG_SPEED,1);
                    if(Main.teams.get(game.TeamManager().getTeam(p)).upgrades.getOrDefault(TeamUpgrades.SHARP_SWORD,0)>0) {
                        is.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    }
                    if (last != null) {
                        p.getInventory().setItem(p.getInventory().first(last),new ItemStack(is));
                        p.getInventory().remove(last);
                    }else {
                        p.getInventory().addItem(new ItemStack(is));
                    }
                    p.openInventory(tools.getShop(game, p));
                }
            }
        });

        tools.shop.setItem(29,shears.spigot());
        tools.shop.setItem(31,axe.spigot());
        tools.shop.setItem(33,pickaxe.spigot());

        tools.menus.add(tools.shop);
        return tools.shop;
    }


    public static Material nextUpgrade(Player player, ItemUpgrades upgrade) {
        switch (upgrade) {
            case PICKAXE:
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
            default:
                break;
        }
        return null;
    }

    public static Material currentUpgrade(Player player, ItemUpgrades upgrade) {
        switch (upgrade) {
            case PICKAXE:
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
            default:
                System.out.println("Invalid item requested "+upgrade.name());
                return null;
        }
    }

    public static Material previousUpgrade(ArrayList<Material> inventory, ItemUpgrades upgrade) {
        switch (upgrade) {
            case PICKAXE:
                if (inventory.contains((DIAMOND_PICKAXE))) {
                    return GOLDEN_PICKAXE;
                }
                if (inventory.contains((GOLDEN_PICKAXE))) {
                    return IRON_PICKAXE;
                }
                if (inventory.contains((IRON_PICKAXE))) {
                    return WOODEN_PICKAXE;
                }
                if (inventory.contains((WOODEN_PICKAXE))) {
                    return WOODEN_PICKAXE;
                }
                return null;
            case AXE:
                if (inventory.contains((DIAMOND_AXE))) {
                    return GOLDEN_AXE;
                }
                if (inventory.contains((GOLDEN_AXE))) {
                    return IRON_AXE;
                }
                if (inventory.contains((IRON_AXE))) {
                    return WOODEN_AXE;
                }
                if (inventory.contains((WOODEN_AXE))) {
                    return WOODEN_AXE;
                }
                return null;
            default:
                System.out.println("Invalid item requested "+upgrade.name());
                return null;
        }
    }

    public static ItemStack getPrice(Player player, ItemUpgrades upgrade) {
        switch (upgrade) {
            case PICKAXE:
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
                    return new ItemStack(Material.GOLD_INGOT, 6);
                }
                if (player.getInventory().contains(IRON_AXE)) {
                    return new ItemStack(Material.GOLD_INGOT, 3);
                }
                if (player.getInventory().contains(WOODEN_AXE)) {
                    return new ItemStack(Material.IRON_INGOT, 10);
                }
                return new ItemStack(IRON_INGOT, 10);
            default:
                break;
        }
        return null;
    }
}
