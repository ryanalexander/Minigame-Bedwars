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

package net.blockcade.Arcade.games.BedBattles;

import net.blockcade.Arcade.Commands.GameCommand;
import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Varables.GameState;
import net.blockcade.Arcade.Varables.GameType;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Events.*;
import net.blockcade.Arcade.games.BedBattles.Misc.BedTeam;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Main extends JavaPlugin {

    public static HashMap<TeamColors, BedTeam> teams = new HashMap<>();
    public static HashMap<Block, BedTeam> beds = new HashMap<>();

    @Override
    public void onEnable() {
        Game game = new Game("BedBattles", GameType.DESTROY, 8, 16, net.blockcade.Arcade.Main.getPlugin(net.blockcade.Arcade.Main.class), Bukkit.getWorld("world"));
        getCommand("game").setExecutor(new GameCommand(this, game));

        /*
         * Register Handlers
         */
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GameStartEvent(game), this);
        pm.registerEvents(new GameRegisterEvent(), this);
        pm.registerEvents(new PlayerRespawnEvent(game), this);
        pm.registerEvents(new PlayerMoveEvent(game), this);
        pm.registerEvents(new BlockBreakEvent(game), this);

        new BukkitRunnable() {
            @Override
            public void run() {
                game.GameState(GameState.IN_LOBBY);
            }
        }.runTaskLater(this, 60L);
    }

}
