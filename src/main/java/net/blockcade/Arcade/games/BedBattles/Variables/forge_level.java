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
