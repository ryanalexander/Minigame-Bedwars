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
 *  @since 27/7/2019
 */

package net.blockcade.Arcade.games.BedBattles.Variables;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public enum armor_level {
    PROT0(new ItemStack(Material.DIAMOND, 0), Enchantment.PROTECTION_ENVIRONMENTAL, 0),
    PROT1(new ItemStack(Material.DIAMOND, 2), Enchantment.PROTECTION_ENVIRONMENTAL, 1),
    PROT2(new ItemStack(Material.DIAMOND, 4), Enchantment.PROTECTION_ENVIRONMENTAL, 2),
    PROT3(new ItemStack(Material.DIAMOND, 8), Enchantment.PROTECTION_ENVIRONMENTAL, 3),
    PROT4(new ItemStack(Material.DIAMOND, 16), Enchantment.PROTECTION_ENVIRONMENTAL, 4);

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

    armor_level(ItemStack price, Enchantment enchantment, int level) {
        this.price = price;
        this.enchantment = enchantment;
        this.level = level;
    }
}
