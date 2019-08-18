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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

import static org.bukkit.Material.GHAST_SPAWN_EGG;
import static org.bukkit.Material.GOLD_INGOT;

public class eggs implements Listener {

    private static Inventory shop;

    private static ArrayList<Inventory> menus = new ArrayList<>();

    // 11,12,13,14,15,16,17,29,30,31,33,34,35,38,39

    public static Inventory getShop(Game game, Player player) {
        eggs.shop = header.format(game, Bukkit.createInventory(null, 9 * 6, Text.format("&cSkully's Little Friends")), false);

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.shop()).getShop(game, p));
            }
        });


        Item IRON_GOLEM_SPAWN = new Item(GHAST_SPAWN_EGG, "&bIsland Bouncer");
        IRON_GOLEM_SPAWN.setLore(new String[]{
                "&r",
                "&7Cost: &630 Gold",
                "&r",
                "&7Spawns: &aIron Golem",
                "&7Duration: &6120 Secs"
        });
        IRON_GOLEM_SPAWN.setAmount(1);
        IRON_GOLEM_SPAWN.setOnClick(new Item.click() {
            public void run(Player p) {
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, GOLD_INGOT, 30)) {
                    IRON_GOLEM_SPAWN.setLore(new String[]{String.format("&aYou are tha sneaky mayn")});
                    p.getInventory().addItem(IRON_GOLEM_SPAWN.spigot());
                }
            }
        });

        eggs.shop.setItem(0, close.spigot());
        eggs.shop.setItem(19, IRON_GOLEM_SPAWN.spigot());

        eggs.menus.add(eggs.shop);
        return eggs.shop;
    }
}
