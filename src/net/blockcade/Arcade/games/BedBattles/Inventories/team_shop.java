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

package net.blockcade.Arcade.games.BedBattles.Inventories;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Item;
import net.blockcade.Arcade.Utils.Text;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Inventories.Assets.traps;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Misc.Forge;
import net.blockcade.Arcade.games.BedBattles.Variables.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class team_shop implements Listener {

    private Inventory shop;

    private ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public Inventory getShop(Game game, Player player) {
        this.shop = Bukkit.createInventory(null, 9 * 5, Text.format("&7Team Shop"));

        ItemStack placeholder = new Item(Material.BLACK_STAINED_GLASS_PANE, "&r").setOnClick(new Item.click() {
            @Override
            public void run(Player param1Player) {
            }
        }).spigot();
        for (int i = 18; i < 8; i++) {
            shop.setItem(i, placeholder);
        }
        TeamColors team = game.TeamManager().getTeam(player);

        Item sharp_swords = new Item(Material.IRON_SWORD, (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD) ? "&a" : "&c") + "Sharpened Swords");
        sharp_swords.setLore(new String[]{
                Text.format("&7Your team permanently gains"),
                Text.format("&7Sharpness I on all swords and"),
                Text.format("&7axes!"),
                "",
                Text.format("&7Cost: &b4 Diamonds"),
                "",
                (!Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD) ? (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4) ? "&eClick to purchase" : "&cYou don't have enough Diamonds!") : "&aAlready purchased")
        });
        sharp_swords.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.SHARP_SWORD)) return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.DIAMOND, 4)) {
                    Main.teams.get(team).upgrades.put(TeamUpgrades.SHARP_SWORD, 1);
                    for (Player player : game.TeamManager().getTeamPlayers(team)) {
                        for (ItemStack is : player.getInventory().getContents()) {
                            if (is == null) continue;
                            if (
                                    is.getType() == (Material.WOODEN_SWORD) ||
                                            is.getType() == (Material.STONE_SWORD) ||
                                            is.getType() == (Material.GOLDEN_SWORD) ||
                                            is.getType() == (Material.IRON_SWORD) ||
                                            is.getType() == (Material.DIAMOND_SWORD)
                            ) {
                                is.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                            }
                        }
                    }
                    p.openInventory((new team_shop()).getShop(game, p));
                }
            }
        });

        Item reinforced_armor = new Item(Material.IRON_CHESTPLATE, (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.REINFORCED_ARMOR) ? "&a" : "&c") + "Reinforced Armor " + RomanNumerals.valueOf("_" + getOrZero(team, TeamUpgrades.REINFORCED_ARMOR)).getNumeral());

        reinforced_armor.setLore(new String[]{
                Text.format("&7Your team permanently gains"),
                Text.format("&7Protection on all armor pieces!"),
                "",
                Text.format(((getOrZero(team, TeamUpgrades.REINFORCED_ARMOR)) >= 1 ? "&a" : "&7") + "Tier 1&7: Protection I, &b2 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.REINFORCED_ARMOR)) >= 2 ? "&a" : "&7") + "Tier 2&7: Protection II, &b4 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.REINFORCED_ARMOR)) >= 3 ? "&a" : "&7") + "Tier 3&7: Protection III, &b8 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.REINFORCED_ARMOR)) >= 4 ? "&a" : "&7") + "Tier 4&7: Protection IV, &b16 Diamonds"),
                "",
                (!((getOrZero(team, TeamUpgrades.REINFORCED_ARMOR) + 1) >= 4) ? (player.getInventory().containsAtLeast(armor_level.valueOf("PROT" + (getOrZero(team, TeamUpgrades.REINFORCED_ARMOR) + 1)).getPrice(), armor_level.valueOf("PROT" + (getOrZero(team, TeamUpgrades.REINFORCED_ARMOR) + 1)).getPrice().getAmount()) ? "&eClick to purchase" : "&cYou don't have enough Diamonds!") : "&aFully Upgraded")
        });

        reinforced_armor.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.REINFORCED_ARMOR) && Main.teams.get(team).upgrades.get(TeamUpgrades.REINFORCED_ARMOR) >= 4)
                    return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, (armor_level.valueOf("PROT" + (getOrZero(team, TeamUpgrades.REINFORCED_ARMOR) + 1)).getPrice().getType()), armor_level.valueOf("PROT" + (getOrZero(team, TeamUpgrades.REINFORCED_ARMOR) + 1)).getPrice().getAmount())) {
                    Main.teams.get(team).upgrades.put(TeamUpgrades.REINFORCED_ARMOR, (getOrZero(team, TeamUpgrades.REINFORCED_ARMOR) + 1));
                    p.openInventory((new team_shop()).getShop(game, p));
                }
            }
        });

        Item ManiacMiner = new Item(Material.GOLDEN_PICKAXE, (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.MANIAC_MINER) ? "&a" : "&c") + "Maniac Miner" + RomanNumerals.valueOf("_" + getOrZero(team, TeamUpgrades.MANIAC_MINER)).getNumeral());

        ManiacMiner.setLore(new String[]{
                Text.format("&7All players on your team"),
                Text.format("&7permanently gain Haste"),
                "",
                Text.format(((getOrZero(team, TeamUpgrades.MANIAC_MINER)) >= 1 ? "&a" : "&7") + "Tier 1&7: Haste I, &b2 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.MANIAC_MINER)) >= 2 ? "&a" : "&7") + "Tier 2&7: Haste II, &b4 Diamonds"),
                "",
                (!((getOrZero(team, TeamUpgrades.MANIAC_MINER) + 1) >= 2) ? (player.getInventory().containsAtLeast(haste_level.valueOf("HASTE" + (getOrZero(team, TeamUpgrades.MANIAC_MINER) + 1)).getPrice(), haste_level.valueOf("HASTE" + (getOrZero(team, TeamUpgrades.MANIAC_MINER) + 1)).getPrice().getAmount()) ? "&eClick to purchase" : "&cYou don't have enough Diamonds!") : "&aFully Upgraded")
        });

        ManiacMiner.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.MANIAC_MINER) && Main.teams.get(team).upgrades.get(TeamUpgrades.MANIAC_MINER) >= 2)
                    return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, (haste_level.valueOf("HASTE" + (getOrZero(team, TeamUpgrades.MANIAC_MINER) + 1)).getPrice().getType()), haste_level.valueOf("HASTE" + (getOrZero(team, TeamUpgrades.MANIAC_MINER) + 1)).getPrice().getAmount())) {
                    Main.teams.get(team).upgrades.put(TeamUpgrades.MANIAC_MINER, (getOrZero(team, TeamUpgrades.MANIAC_MINER) + 1));
                    p.openInventory((new team_shop()).getShop(game, p));
                }
            }
        });

        Item forge = new Item(Material.FURNACE, (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.FORGE) ? "&a" : "&c") + "%s Forge");

        forge.setLore(new String[]{
                Text.format("&7Upgrade resources spawning on"),
                Text.format("&7you island."),
                "",
                Text.format(((getOrZero(team, TeamUpgrades.FORGE)) >= 1 ? "&a" : "&7") + "Tier 1&7: +50% Resources, &b2 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.FORGE)) >= 2 ? "&a" : "&7") + "Tier 2&7: +100% Resources, &b4 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.FORGE)) >= 3 ? "&a" : "&7") + "Tier 3&7: Spawn emeralds, &b6 Diamonds"),
                Text.format(((getOrZero(team, TeamUpgrades.FORGE)) >= 4 ? "&a" : "&7") + "Tier 4&7: +200% Resources, &b8 Diamonds"),
                "",
                (!((getOrZero(team, TeamUpgrades.FORGE) + 1) >= 4) ? (player.getInventory().containsAtLeast(forge_level.valueOf("LVL" + (getOrZero(team, TeamUpgrades.FORGE) + 1)).getPrice(), forge_level.valueOf("LVL" + (getOrZero(team, TeamUpgrades.FORGE) + 1)).getPrice().getAmount()) ? "&eClick to purchase" : "&cYou don't have enough Diamonds!") : "&aFully Upgraded")
        });

        forge.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.FORGE) && Main.teams.get(team).upgrades.get(TeamUpgrades.FORGE) >= 4)
                    return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, (forge_level.valueOf("LVL" + (getOrZero(team, TeamUpgrades.FORGE) + 1)).getPrice().getType()), forge_level.valueOf("LVL" + (getOrZero(team, TeamUpgrades.FORGE) + 1)).getPrice().getAmount())) {
                    Main.teams.get(team).upgrades.put(TeamUpgrades.FORGE, (getOrZero(team, TeamUpgrades.FORGE) + 1));
                    switch (getOrZero(team, TeamUpgrades.FORGE)){
                        case 1:
                            Main.teams.get(team).getIron_forge().incresePercent(25);
                            Main.teams.get(team).getGold_forge().incresePercent(25);
                            break;
                        case 2:
                            Main.teams.get(team).getIron_forge().incresePercent(50);
                            Main.teams.get(team).getGold_forge().incresePercent(50);
                            break;
                        case 3:
                            Forge forge = new Forge(game,Main.teams.get(team).getIron_forge().getLocation(),Material.EMERALD,(30 * 20),false,2);
                            Main.teams.get(team).setEmerald_forge(forge);
                            break;
                        case 4:
                            Main.teams.get(team).getIron_forge().incresePercent(80);
                            Main.teams.get(team).getGold_forge().incresePercent(80);
                            Main.teams.get(team).getEmerald_forge().incresePercent(25);
                            break;
                        default:
                            break;
                    }
                    p.openInventory((new team_shop()).getShop(game, p));
                }
            }
        });



        Item healing = new Item(Material.BEACON, (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.HEALING) ? "&a" : "&c") + "Heal Pool");
        healing.setLore(new String[]{
                Text.format("&7Creates a Regeneration field"),
                Text.format("&7around your base!"),
                "",
                Text.format("&7Cost: &b1 Diamond"),
                "",
                (!Main.teams.get(team).upgrades.containsKey(TeamUpgrades.HEALING) ? (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 1) ? "&eClick to purchase" : "&cYou don't have enough Diamonds!") : "&aAlready purchased")
        });
        healing.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (Main.teams.get(team).upgrades.containsKey(TeamUpgrades.HEALING)) return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.DIAMOND, 1)) {
                    Main.teams.get(team).upgrades.put(TeamUpgrades.HEALING, 1);
                    p.openInventory((new team_shop()).getShop(game, p));
                }
            }
        });
        BedTeam bedTeam = Main.teams.get(team);
        int trapCount = bedTeam.traps.size();

        Item trap_1 = new Item((trapCount>=1?bedTeam.getTrap(0).getItem():Material.RED_STAINED_GLASS_PANE),trapCount>=1?bedTeam.getTrap(0).getName():"&cNo trap set");
        Item trap_2 = new Item((trapCount>=2?bedTeam.getTrap(1).getItem():Material.RED_STAINED_GLASS_PANE),trapCount>=2?bedTeam.getTrap(1).getName():"&cNo trap set");
        Item trap_3 = new Item((trapCount>=3?bedTeam.getTrap(2).getItem():Material.RED_STAINED_GLASS_PANE),trapCount>=3?bedTeam.getTrap(2).getName():"&cNo trap set");

        trap_1.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                if(bedTeam.traps.size()>=1){return;}
                p.openInventory(traps.getShop(game,p));
            }
        });
        trap_2.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                if(bedTeam.traps.size()>=2){return;}
                p.openInventory(traps.getShop(game,p));
            }
        });
        trap_3.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                if(bedTeam.traps.size()>=3){return;}
                p.openInventory(traps.getShop(game,p));
            }
        });

        this.shop.setItem(10, sharp_swords.spigot());
        this.shop.setItem(11, reinforced_armor.spigot());
        this.shop.setItem(12, ManiacMiner.spigot());
        this.shop.setItem(13, forge.spigot());
        this.shop.setItem(14, healing.spigot());
        this.shop.setItem(30, trap_1.spigot());
        this.shop.setItem(31, trap_2.spigot());
        this.shop.setItem(32, trap_3.spigot());

        this.menus.add(this.shop);
        return this.shop;
    }

    public int getOrZero(TeamColors team, TeamUpgrades key) {
        if (Main.teams.get(team).upgrades.containsKey(key))
            return Main.teams.get(team).upgrades.get(key);
        return 0;
    }
}
