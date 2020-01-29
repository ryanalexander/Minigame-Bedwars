
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

package net.blockcade.Arcade.games.BedBattles;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import net.blockcade.Arcade.Commands.GameCommand;
import net.blockcade.Arcade.Commands.debug;
import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Managers.GamePlayer;
import net.blockcade.Arcade.Managers.ScoreboardManager;
import net.blockcade.Arcade.Varables.*;
import net.blockcade.Arcade.games.BedBattles.Events.*;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import net.blockcade.Arcade.games.BedBattles.Misc.Forge;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public static HashMap<TeamColors, BedTeam> teams = new HashMap<>();
    public static HashMap<ScoreboardManager, Integer> scoreboard_upgrade_offsets = new HashMap<>();
    public static ArrayList<Player> trap_immunity = new ArrayList<>();
    public static HashMap<Material, ArrayList<Forge>> forges = new HashMap<>();
    public static HashMap<Block, BedTeam> beds = new HashMap<>();
    public static HashMap<Chest, BedTeam> chests = new HashMap<>();
    public static Game game;

    public static boolean noCamping = false;
    public static HashMap<Player,Integer> camping = new HashMap<>();

    @Override
    public void onEnable() {
        Game game = new Game("BedBattles", GameType.DESTROY, 8, 8, net.blockcade.Arcade.Main.getPlugin(net.blockcade.Arcade.Main.class), Bukkit.getWorld("world"));
        game.TeamManager().setMaxTeams(8);
        getCommand("game").setExecutor(new GameCommand(this, game));
        net.blockcade.Arcade.Main.getPlugin(net.blockcade.Arcade.Main.class).getCommand("debug").setExecutor(new debug(game));
        game.setMaxDamageHeight(512);
        game.setGameName(GameName.BEDBATTLES);
        Main.game=game;

        /*
         * Register Handlers
         */
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GameStartEvent(game), this);
        pm.registerEvents(new GameRegisterEvent(), this);
        pm.registerEvents(new PlayerRespawnEvent(game), this);
        pm.registerEvents(new PlayerMoveEvent(game), this);
        pm.registerEvents(new PlayerInteractEvent(game),this);
        pm.registerEvents(new BlockBreakEvent(game), this);
        pm.registerEvents(new GamePlayer(game,true), this);
        pm.registerEvents(new PotionEvent(), this);

        game.setModule(GameModule.DEATH_MANAGER,true);
        game.setModule(GameModule.VOID_DEATH,true);
        game.setModule(GameModule.BLOCK_PLACEMENT,true);
        game.setModule(GameModule.CHEST_BLOCK,true);
        game.setModule(GameModule.BLOCK_ROLLBACK,true);
        game.setModule(GameModule.NO_CRAFTING,true);
        game.setModule(GameModule.NO_HUNGER,true);
        game.setModule(GameModule.NO_SMELTING,true);
        game.setModule(GameModule.NO_TOOL_DROP,true);
        game.setModule(GameModule.START_MECHANISM,true);
        game.setModule(GameModule.TEAMS,true);
        game.setModule(GameModule.CHAT_MANAGER,true);
        game.setModule(GameModule.NO_WEATHER_CHANGE,true);
        game.setModule(GameModule.INFINITE_BUILDING,false);

        game.setModule(GameModule.ALLSTATE_JOIN,false);

        game.setModule(GameModule.NO_FALL_DAMAGE,false);

        game.map().setTime(1);
        game.map().setWeatherDuration(0);

        game.map().setGameRule(GameRule.DO_DAYLIGHT_CYCLE,false);
        game.map().setGameRule(GameRule.DO_WEATHER_CYCLE,false);

        new BukkitRunnable() {
            @Override
            public void run() {
                game.GameState(GameState.IN_LOBBY);
            }
        }.runTaskLater(this, 60L);
    }

}
