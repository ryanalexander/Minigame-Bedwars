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
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class header {

    public static Inventory format(Game game, Inventory inv, boolean header, int offset) {
        return format(game,inv,header,false,offset);
    }

    public static Inventory format(Game game, Inventory inv, boolean header, boolean hasquickbuy, int offset) {
        offset=offset-1;

        ItemStack placeholder = new Item(Material.GRAY_STAINED_GLASS_PANE, "&r").setOnClick(new Item.click() {
            @Override
            public void run(Player param1Player) {

            }
        }).spigot();
        for (int i = 0; i <= inv.getSize()-1; i++) {
            inv.setItem(i, placeholder);
        }

        if (header) {
            Item quick_buy = new Item(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "&fQuick Buy");
            quick_buy.setOnClick(new Item.click() {
                @Override
                public void run(Player p) {
                    p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,0.5f,0.5f);
                }
            });
            quick_buy.setLore(new String[]{"&7To set this &bQuick Buy&7 slot","&7just &bShift + Click&7 and click","&7any item from the menu"});

            Item Blocks = new Item(Material.OAK_PLANKS, "&6Blocks");
            Item Weapons = new Item(Material.DIAMOND_SWORD, "&6Weapons");
            Item Armor = new Item(Material.IRON_CHESTPLATE, "&6Armor");
            Item Tools = new Item(Material.GOLDEN_PICKAXE, "&6Tools");
            Item Special = new Item(Material.FIRE_CHARGE, "&6Specials");

            Blocks.setOnClick(new Item.click() {
                public void run(Player p) {
                    p.openInventory(blocks.getShop(game, p));
                }
            });
            Weapons.setOnClick(new Item.click() {
                public void run(Player p) {
                    p.openInventory(weapons.getShop(game, p));
                }
            });
            Armor.setOnClick(new Item.click() {
                public void run(Player p) {
                    p.openInventory(armor.getShop(game, p));
                }
            });
            Tools.setOnClick(new Item.click() {
                public void run(Player p) {
                    p.openInventory(tools.getShop(game, p));
                }
            });
            Special.setOnClick(new Item.click() {
                public void run(Player p) {
                    p.openInventory(specials.getShop(game, p));
                }
            });

            inv.setItem(offset+2, Blocks.spigot());
            inv.setItem(offset+3, Weapons.spigot());
            inv.setItem(offset+5, Armor.spigot());
            inv.setItem(offset+7, Tools.spigot());
            inv.setItem(offset+8, Special.spigot());

            /*
             Quick buy slots
             */
            if(hasquickbuy) {
                inv.setItem(28, quick_buy.spigot());
                inv.setItem(29, quick_buy.spigot());
                inv.setItem(30, quick_buy.spigot());
                inv.setItem(31, quick_buy.spigot());
                inv.setItem(32, quick_buy.spigot());
                inv.setItem(33, quick_buy.spigot());
                inv.setItem(34, quick_buy.spigot());

                inv.setItem(38, quick_buy.spigot());
                inv.setItem(39, quick_buy.spigot());
                inv.setItem(40, quick_buy.spigot());
                inv.setItem(41, quick_buy.spigot());
                inv.setItem(42, quick_buy.spigot());
            }
        }

        return inv;
    }

}
