package net.blockcade.Arcade.games.BedBattles.Inventories.Assets;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Utils.Formatting.Item;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamTraps;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class traps implements Listener {

    private static Inventory shop;

    public static Inventory getShop(Game game, Player player){

        traps.shop= header.format(game, Bukkit.createInventory(null,9*6, Text.format("&cSecurity Systems")),false,0);

        BedTeam bedTeam = Main.teams.get(game.TeamManager().getTeam(player));

        Item close = new Item(Material.BARRIER, "&cBack");
        close.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                p.openInventory((new net.blockcade.Arcade.games.BedBattles.Inventories.team_shop()).getShop(game, p));
            }
        });

        Item ALERT_TRAP = new Item(TeamTraps.ALERT.getItem(), TeamTraps.ALERT.getName());
        ALERT_TRAP.setLore(new String[]{"",TeamTraps.ALERT.getDescription(),"","&7Cost: &b1 Diamond","",((player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 1) ? (bedTeam.getTraps().size()>=3)?"&aYou are fully armed":"&eClick to purchase" : "&cYou don't have enough Diamonds!"))});
        ALERT_TRAP.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (bedTeam.getTraps().size()>=3) return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.DIAMOND, 1)) {
                    Main.teams.get(team).addTrap(TeamTraps.ALERT);
                    p.openInventory(getShop(game, p));
                }
            }
        });


        Item BLINDNESS_TRAP = new Item(TeamTraps.BLINDNESS.getItem(), TeamTraps.BLINDNESS.getName());
        BLINDNESS_TRAP.setLore(new String[]{"",TeamTraps.BLINDNESS.getDescription(),"","&7Cost: &b1 Diamond","",((player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 1) ? (bedTeam.getTraps().size()>=3)?"&aYou are fully armed":"&eClick to purchase" : "&cYou don't have enough Diamonds!"))});
        BLINDNESS_TRAP.setOnClick(new Item.click() {
            @Override
            public void run(Player p) {
                TeamColors team = game.TeamManager().getTeam(p);
                if (bedTeam.getTraps().size()>=3) return;
                if (net.blockcade.Arcade.games.BedBattles.Inventories.shop.doCharge(p, Material.DIAMOND, 1)) {
                    Main.teams.get(team).addTrap(TeamTraps.BLINDNESS);
                    p.openInventory(getShop(game, p));
                }
            }
        });

        shop.setItem(0,close.spigot());
        shop.setItem(10,ALERT_TRAP.spigot());
        shop.setItem(11,BLINDNESS_TRAP.spigot());
        return traps.shop;
    }

}
