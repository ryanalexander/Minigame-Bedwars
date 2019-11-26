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
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class blocks implements Listener {

    private static Inventory shop;

    public static Inventory getShop(Game game, Player player) {
        blocks.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cBuilding Materials")), true,0);

        blocks.shop.setItem(20, Item.valueOf("BLOCK_WOOL_"+game.TeamManager().getTeam(player).name().toUpperCase()).spigot());
        blocks.shop.setItem(22, Item.valueOf("BLOCK_CLAY_"+game.TeamManager().getTeam(player).name().toUpperCase()).spigot());
        blocks.shop.setItem(24, Item.valueOf("BLOCK_GLASS_"+game.TeamManager().getTeam(player).name().toUpperCase()).spigot());

        blocks.shop.setItem(38, Item.BLOCK_WOODEN_PLANKS.spigot());
        blocks.shop.setItem(40, Item.BLOCK_END_STONE.spigot());
        blocks.shop.setItem(42, Item.BLOCK_OBSIDIAN.spigot());

        return blocks.shop;
    }
}
