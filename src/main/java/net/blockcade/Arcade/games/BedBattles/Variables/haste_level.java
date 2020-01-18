
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

package net.blockcade.Arcade.games.BedBattles.Variables;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public enum haste_level {
    HASTE0(new ItemStack(Material.DIAMOND, 0), Enchantment.DIG_SPEED, 0),
    HASTE1(new ItemStack(Material.DIAMOND, 2), Enchantment.DIG_SPEED, 1),
    HASTE2(new ItemStack(Material.DIAMOND, 4), Enchantment.DIG_SPEED, 2);

    ItemStack price;
    Enchantment enchantment;
    int level;

    public ItemStack getPrice() {
        return this.price;
    }

    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    public int getLevel() {
        return this.level;
    }

    haste_level(ItemStack price, Enchantment enchantment, int level) {
        this.price = price;
        this.enchantment = enchantment;
        this.level = level;
    }
}
