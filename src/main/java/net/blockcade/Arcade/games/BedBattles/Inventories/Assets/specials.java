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
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Misc.Item;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import static org.bukkit.Material.*;

public class specials implements Listener {

    private static Inventory shop;

    public static Inventory getShop(Game game, Player player) {
        specials.shop = header.format(game,player, Bukkit.createInventory(null, 9 * 6, Text.format("&cMy little friends'")), true,0);

        ItemStack invisIS = new ItemStack(POTION);
        PotionMeta invisMeta = (PotionMeta)invisIS.getItemMeta();
        assert invisMeta != null;
        invisMeta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));
        invisIS.setItemMeta(invisMeta);
        net.blockcade.Arcade.Utils.Formatting.Item invis = new net.blockcade.Arcade.Utils.Formatting.Item(invisIS, "&bInvisibility Potion");
        invis.setLore(new String[]{
                "&r",
                "&7Cost: &a2 Emeralds",
                "&r",
                "&7Duration: &f30 Secs"
        });
        invis.setAmount(1);
        invis.setOnClick(new net.blockcade.Arcade.Utils.Formatting.Item.click() {
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
        ItemStack jumpIS = new ItemStack(POTION);
        PotionMeta jumpMeta = (PotionMeta)jumpIS.getItemMeta();
        assert jumpMeta != null;
        jumpMeta.setBasePotionData(new PotionData(PotionType.JUMP));
        jumpIS.setItemMeta(jumpMeta);
        net.blockcade.Arcade.Utils.Formatting.Item jump = new net.blockcade.Arcade.Utils.Formatting.Item(jumpIS, "&bJump Juice");
        jump.setLore(new String[]{
                "&r",
                "&7Cost: &a1 Emerald",
                "&r",
                "&7Duration: &f30 Secs"
        });
        jump.setAmount(1);
        jump.setOnClick(new net.blockcade.Arcade.Utils.Formatting.Item.click() {
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

        net.blockcade.Arcade.Utils.Formatting.Item milk = new net.blockcade.Arcade.Utils.Formatting.Item(MILK_BUCKET, "&bMilk");
        milk.setLore(new String[]{
                "&r",
                "&7Cost: &a5 Gold",
                "&r",
                "&7Duration: &f30 Secs",
                "&r",
                "&dBecome immune to traps"
        });
        milk.setOnClick(p -> {
            if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, GOLD_INGOT, 5)) {
                p.getInventory().addItem(new ItemStack(MILK_BUCKET));
            }
        });

        ItemStack speedIS = new ItemStack(POTION);
        PotionMeta speedMeta = (PotionMeta)speedIS.getItemMeta();
        assert speedMeta != null;
        speedMeta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE));
        speedIS.setItemMeta(speedMeta);
        net.blockcade.Arcade.Utils.Formatting.Item speed = new net.blockcade.Arcade.Utils.Formatting.Item(speedIS, "&bSpeed");
        speed.setLore(new String[]{
                "&r",
                "&7Cost: &a1 Emerald",
                "&r",
                "&7Duration: &f30 Secs"
        });
        speed.setAmount(1);
        speed.setOnClick(new net.blockcade.Arcade.Utils.Formatting.Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, EMERALD, 1)) {
                    ItemStack potion = new ItemStack(Material.POTION, 1);

                    PotionMeta potionmeta = (PotionMeta) potion.getItemMeta();
                    potionmeta.setMainEffect(PotionEffectType.JUMP);
                    PotionEffect speed = new PotionEffect(PotionEffectType.JUMP, (30 * 20), 2);
                    potionmeta.addCustomEffect(speed, true);
                    potionmeta.setColor(Color.LIME);
                    potionmeta.setDisplayName(Text.format("&bSpeed"));
                    potion.setItemMeta(potionmeta);
                    p.getInventory().addItem(potion);
                }
            }
        });

        net.blockcade.Arcade.Utils.Formatting.Item disabled = new net.blockcade.Arcade.Utils.Formatting.Item(BARRIER, "&cItem Disabled");
        disabled.setLore(new String[]{"&r","&fThis is has been disabled","&fin some cases this may be","&ftemporary, or due to technical issues.","&r","&6Check again later."});
        disabled.setOnClick((Player p)->player.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO,1f,1f));

        /*  X - Y */
        /*  3 - 2 */ specials.shop.setItem(19, Item.SPECIAL_TNT.spigot());
        /*  3 - 3 */ specials.shop.setItem(20, Item.SPECIAL_ENDER_PEARL.spigot());

        /*  3 - 5 */ specials.shop.setItem(22,disabled.spigot()); // SPAWN EGG

        /*  3 - 7 */ specials.shop.setItem(24,speed.spigot()); // POTION OF SPEED
        /*  3 - 8 */ specials.shop.setItem(25,milk.spigot()); // Magic Milk


        /*  4 - 2 */ specials.shop.setItem(28, Item.SPECIAL_WATER.spigot());
        /*  4 - 3 */ specials.shop.setItem(29, Item.SPECIAL_FIREBALL.spigot());

        /*  4 - 5 */ specials.shop.setItem(31,disabled.spigot()); // SPAWN EGG

        /*  4 - 7 */ specials.shop.setItem(33,jump.spigot()); // POTION OF LEAPING
        /*  4 - 8 */ specials.shop.setItem(34, Item.SPECIAL_GOLDEN_APPLE.spigot());


        /*  5 - 2 */ specials.shop.setItem(37, Item.SPECIAL_ENDER_CHEST.spigot());
        /*  5 - 3 */ specials.shop.setItem(38, Item.SPECIAL_BRIDGE_EGG.spigot());

        /*  5 - 5 */ specials.shop.setItem(40,disabled.spigot()); //SPAWN EGG

        /*  5 - 7 */ specials.shop.setItem(42,invis.spigot()); // PORTION OF INVISIBILITY
        /*  X - Y */

        return specials.shop;
    }
}
