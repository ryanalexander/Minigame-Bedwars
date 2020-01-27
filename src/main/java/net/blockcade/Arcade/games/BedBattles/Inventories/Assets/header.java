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

package net.blockcade.Arcade.games.BedBattles.Inventories.Assets;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.games.BedBattles.Misc.Item;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class header {

    public static Inventory format(Game game, Player p, Inventory inv, boolean header, int offset) {
        return format(game,p,inv,header,false,offset);
    }

    public static Inventory format(Game game, Player p, Inventory inv, boolean header, boolean hasquickbuy, int offset) {
        offset=offset-1;

        ItemStack placeholder = new net.blockcade.Arcade.Utils.Formatting.Item(Material.GRAY_STAINED_GLASS_PANE, "&r").setOnClick(new net.blockcade.Arcade.Utils.Formatting.Item.click() {
            @Override
            public void run(Player param1Player) {

            }
        }).spigot();
        for (int i = 0; i <= inv.getSize()-1; i++) {
            inv.setItem(i, placeholder);
        }

        if (header) {
            net.blockcade.Arcade.Utils.Formatting.Item quick_buy = new net.blockcade.Arcade.Utils.Formatting.Item(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "&fQuick Buy");
            quick_buy.setOnClick(p16 -> p16.playSound(p16.getLocation(), Sound.ENTITY_VILLAGER_NO,0.5f,0.5f));
            quick_buy.setLore("&7To set this &bQuick Buy&7 slot","&7just &bShift + Click&7 and click","&7any item from the menu");

            net.blockcade.Arcade.Utils.Formatting.Item Blocks = new net.blockcade.Arcade.Utils.Formatting.Item(Material.OAK_PLANKS, "&6Blocks");
            net.blockcade.Arcade.Utils.Formatting.Item Weapons = new net.blockcade.Arcade.Utils.Formatting.Item(Material.DIAMOND_SWORD, "&6Weapons");
            net.blockcade.Arcade.Utils.Formatting.Item Armor = new net.blockcade.Arcade.Utils.Formatting.Item(Material.IRON_CHESTPLATE, "&6Armor");
            net.blockcade.Arcade.Utils.Formatting.Item Tools = new net.blockcade.Arcade.Utils.Formatting.Item(Material.GOLDEN_PICKAXE, "&6Tools");
            net.blockcade.Arcade.Utils.Formatting.Item Special = new net.blockcade.Arcade.Utils.Formatting.Item(Material.FIRE_CHARGE, "&6Specials");

            Blocks.setOnClick(p1 -> p1.openInventory(blocks.getShop(game, p1)));
            Weapons.setOnClick(p13 -> p13.openInventory(weapons.getShop(game, p13)));
            Armor.setOnClick(p12 -> p12.openInventory(armor.getShop(game, p12)));
            Tools.setOnClick(p14 -> p14.openInventory(tools.getShop(game, p14)));
            Special.setOnClick(p15 -> p15.openInventory(specials.getShop(game, p15)));

            inv.setItem(offset+2, Blocks.spigot());
            inv.setItem(offset+3, Weapons.spigot());
            inv.setItem(offset+5, Armor.spigot());
            inv.setItem(offset+7, Tools.spigot());
            inv.setItem(offset+8, Special.spigot());

            /*
             Quick buy slots
             */
            if(hasquickbuy) {
                inv.setItem(28, Item.valueOf("BLOCK_WOOL_"+game.TeamManager().getTeam(p).name()).getItem().spigot());
                inv.setItem(29, quick_buy.spigot());

                inv.setItem(31, Item.SPECIAL_GOLDEN_APPLE.getItem().spigot());

                inv.setItem(33, quick_buy.spigot());
                inv.setItem(34, quick_buy.spigot());


                inv.setItem(37, quick_buy.spigot());
                inv.setItem(38, quick_buy.spigot());

                inv.setItem(40, quick_buy.spigot());

                inv.setItem(42, quick_buy.spigot());
                inv.setItem(43, quick_buy.spigot());
            }
        }

        return inv;
    }

}
