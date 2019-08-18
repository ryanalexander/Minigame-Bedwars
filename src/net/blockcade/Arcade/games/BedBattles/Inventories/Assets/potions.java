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
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

import static org.bukkit.Material.EMERALD;
import static org.bukkit.Material.POTION;

public class potions implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        potions.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully's Specials")), false);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });

        Item invis = new Item(POTION, "&bInvisibility Potion");
        invis.setLore(new String[]{
                "&r",
                "&7Cost: &a2 Emeralds",
                "&r",
                "&aDuration: &630 Secs"
        });
        invis.setAmount(1);
        invis.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, EMERALD, 2)) {
                    ItemStack potion = new ItemStack(Material.POTION, 1);

                    PotionMeta potionmeta = (PotionMeta) potion.getItemMeta();
                    potionmeta.setMainEffect(PotionEffectType.INVISIBILITY);
                    PotionEffect speed = new PotionEffect(PotionEffectType.INVISIBILITY, (30 * 20), 1);
                    potionmeta.addCustomEffect(speed, true);
                    potionmeta.setDisplayName(Text.format("&bInvisibility Potion"));
                    potion.setItemMeta(potionmeta);
                    p.getInventory().addItem(potion);
                }
            }
        });


        Item jump = new Item(POTION, "&bJump Juice");
        jump.setLore(new String[]{
                "&r",
                "&7Cost: &a1 Emerald",
                "&r",
                "&aDuration: &630 Secs"
        });
        jump.setAmount(1);
        jump.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, EMERALD, 1)) {
                    ItemStack potion = new ItemStack(Material.POTION, 1);

                    PotionMeta potionmeta = (PotionMeta) potion.getItemMeta();
                    potionmeta.setMainEffect(PotionEffectType.JUMP);
                    PotionEffect speed = new PotionEffect(PotionEffectType.JUMP, (30 * 20), 3);
                    potionmeta.addCustomEffect(speed, true);
                    potionmeta.setColor(Color.LIME);
                    potionmeta.setDisplayName(Text.format("&bJump Juice"));
                    potion.setItemMeta(potionmeta);
                    p.getInventory().addItem(potion);
                }
            }
        });

        potions.shop.setItem(0, close.spigot());
        potions.shop.setItem(19, invis.spigot());
        potions.shop.setItem(20, jump.spigot());

        potions.menus.add(potions.shop);
        return potions.shop;
    }
}
