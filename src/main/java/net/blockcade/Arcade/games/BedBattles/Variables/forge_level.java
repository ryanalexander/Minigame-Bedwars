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
import org.bukkit.inventory.ItemStack;

public enum forge_level {
    LVL0(new ItemStack(Material.DIAMOND, 0)),
    LVL1(new ItemStack(Material.DIAMOND, 2)),
    LVL2(new ItemStack(Material.DIAMOND, 4)),
    LVL3(new ItemStack(Material.DIAMOND, 6)),
    LVL4(new ItemStack(Material.DIAMOND, 8));

    ItemStack price;

    public ItemStack getPrice() {
        return this.price;
    }

    forge_level(ItemStack price) {
        this.price = price;
    }
}
