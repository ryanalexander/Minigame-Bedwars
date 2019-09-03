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

package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Managers.EntityManager;
import net.blockcade.Arcade.Managers.ScoreboardManager;
import net.blockcade.Arcade.Utils.JavaUtils;
import net.blockcade.Arcade.Utils.Text;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Inventories.shop;
import net.blockcade.Arcade.games.BedBattles.Inventories.team_shop;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Misc.Forge;
import net.blockcade.Arcade.games.BedBattles.Misc.SpinningBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GameStartEvent implements Listener {

    Game game;

    public GameStartEvent(Game game) {
        this.game = game;
    }

    @EventHandler
    public void GameStartEvent(net.blockcade.Arcade.Managers.EventManager.GameStartEvent e) {
        for (TeamColors team : game.TeamManager().getActive_teams()) {
            BedTeam teamb = new BedTeam(game, team);

            // Create team scoreboard
            ScoreboardManager sm = new ScoreboardManager("GAME_" + team.name(), game);
            sm.enableHealthCounter();
            String name = "  BedBattles  ";
            sm.setDisplayname(name);
            sm.addBlank();
            for (TeamColors teamColor : game.TeamManager().getActive_teams()) {
                sm.addLine(teamColor.getChatColor() + teamColor.name().substring(0, 1) + "&r " + teamColor.name() + String.format(" :ELIMINATED_%s: ", teamColor.name()) + (teamColor == team ? "&7You" : ""));
            }
            sm.addBlank();
            sm.addLine(JavaUtils.center("&dblockcade.net", sm.longest_line));
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player player : game.TeamManager().getTeamPlayers(team)) {
                        if (!player.isOnline()) cancel();
                        sm.update();
                        sm.showFor(player);
                    }
                }
            }.runTaskTimer(game.handler(), 0L, 10L);


            // Spawn item shop
            Location shoploc = game.TeamManager().getConfigLocation("shop", team);
            LivingEntity _shop = (LivingEntity) shoploc.getWorld().spawnEntity(shoploc, EntityType.VILLAGER);
            _shop.setCustomName(Text.format(team.getChatColor() + team.name() + "'s Shopkeeper"));
            _shop.setCustomNameVisible(true);
            _shop.setInvulnerable(true);
            _shop.setAI(false);
            _shop.setSilent(true);
            _shop.setCollidable(false);

            game.EntityManager().AddGameEntity(_shop);
            game.EntityManager().setFunction(_shop, new EntityManager.click() {
                @Override
                public void run(Player player) {
                    if (player != null) {
                        player.openInventory((new shop()).getShop(game, player));
                    }
                }
            });

            // Spawn team shop
            Location tshoploc = game.TeamManager().getConfigLocation("team_shop", team);
            LivingEntity _tshop = (LivingEntity) tshoploc.getWorld().spawnEntity(tshoploc, EntityType.VILLAGER);
            _tshop.setCustomName(Text.format(team.getChatColor() + team.name() + "'s Team shop"));
            _tshop.setCustomNameVisible(true);
            _tshop.setInvulnerable(true);
            _tshop.setAI(false);
            _tshop.setSilent(true);
            _tshop.setCollidable(false);

            game.EntityManager().AddGameEntity(_tshop);
            game.EntityManager().setFunction(_tshop, new EntityManager.click() {
                @Override
                public void run(Player player) {
                    if (player != null) {
                        player.openInventory((new team_shop()).getShop(game, player));
                    }
                }
            });

            Block bed = game.TeamManager().getConfigLocation("bed", team).getBlock();
            BlockFace direction = JavaUtils.direction((float)game.TeamManager().getConfigInt("bed.yaw", team));
            bed.setType(Material.RED_BED);
            Bed blockData = (Bed) bed.getBlockData();
            blockData.setPart(Bed.Part.FOOT);
            blockData.setFacing(direction);
            bed.setBlockData(blockData);

            org.bukkit.block.Block relative = bed.getRelative(direction);
            relative.setType(Material.RED_BED, false);
            Bed relativeBlockData = (Bed) relative.getBlockData();
            relativeBlockData.setPart(Bed.Part.HEAD);
            relativeBlockData.setFacing(direction);
            relative.setBlockData(relativeBlockData);

            game.BlockManager().update(bed.getLocation(),Material.AIR,null);
            game.BlockManager().update(relative.getLocation(),Material.AIR,null);

            teamb.setBed(bed);

            Main.beds.put(bed,teamb);


            // Create Forges
            Forge iron_forge = new Forge(game, game.TeamManager().getConfigLocation("forge", team), Material.IRON_INGOT, (1 * 20), true,50);
            Forge gold_forge = new Forge(game, game.TeamManager().getConfigLocation("forge", team), Material.GOLD_INGOT, (5 * 20), true,30);

            teamb.setIron_forge(iron_forge);
            teamb.setGold_forge(gold_forge);

            Main.teams.put(team, teamb);
        }

        Bukkit.broadcastMessage(Text.format("&d&m&l============================="));
        Bukkit.broadcastMessage(Text.format(JavaUtils.center("&f&lBed Battles", 42 + (4))));
        Bukkit.broadcastMessage(Text.format(JavaUtils.center("&e&lProtect your bed and destroy the enemy beds.", 41 + (4))));
        Bukkit.broadcastMessage(Text.format(JavaUtils.center("&e&lUpgrade yourself and your team by collecting", 41 + (4))));
        Bukkit.broadcastMessage(Text.format(JavaUtils.center("&e&lIron, Gold, Emerald and Diamond from generators", 41 + (4))));
        Bukkit.broadcastMessage(Text.format(JavaUtils.center("&e&lto access powerful upgrades.", 41 + (4))));
        Bukkit.broadcastMessage(Text.format("&d&m&l============================="));

        // Spawn Diamond Forges
        List<String> diamond_locations = game.handler().getConfig().getStringList(String.format("maps.%s.DIAMOND_FORGE", game.map().getName()));
        for (String raw_loc : diamond_locations) {
            String[] loc_data = raw_loc.split("[:]");
            Location pos = new Location(Bukkit.getWorld(loc_data[0]), Double.parseDouble(loc_data[1]), Double.parseDouble(loc_data[2]), Double.parseDouble(loc_data[3]));
            Forge forge = new Forge(game, pos, Material.DIAMOND, (10 * 20), false,4);
            forge.setSpinningBlock(new SpinningBlock(pos.add(0, 1.5, 0), Material.DIAMOND_BLOCK, 0, "&b&lDiamond Generator"));
        }
        // Spawn Emerald Forges
        List<String> emerald_locations = game.handler().getConfig().getStringList(String.format("maps.%s.EMERALD_FORGE", game.map().getName()));
        for (String raw_loc : emerald_locations) {
            String[] loc_data = raw_loc.split("[:]");
            Location pos = new Location(Bukkit.getWorld(loc_data[0]), Double.parseDouble(loc_data[1]), Double.parseDouble(loc_data[2]), Double.parseDouble(loc_data[3]));
            Forge forge = new Forge(game, pos, Material.EMERALD, (20 * 20), false,2);
            forge.setSpinningBlock(new SpinningBlock(pos.add(0, 1.5, 0), Material.EMERALD_BLOCK, 0, "&a&lEmerald Generator"));
        }

    }


}
