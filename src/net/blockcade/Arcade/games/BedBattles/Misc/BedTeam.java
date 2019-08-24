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
 *  @since 24/7/2019
 */

package net.blockcade.Arcade.games.BedBattles.Misc;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamTraps;
import net.blockcade.Arcade.games.BedBattles.Variables.TeamUpgrades;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class BedTeam {

    private Game game;
    private TeamColors team;

    public HashMap<TeamUpgrades, Integer> upgrades = new HashMap<>();
    public ArrayList<TeamTraps> traps = new ArrayList<>();

    Forge iron_forge;
    Forge gold_forge;
    Forge emerald_forge;

    private Block bed;


    public BedTeam(Game game, TeamColors team) {
        this.game = game;
        this.team = team;
    }

    public Block getBed() {
        return this.bed;
    }

    public void setBed(Block bed) {
        this.bed = bed;
    }

    public void setIron_forge(Forge forge) {
        this.iron_forge = forge;
    }

    public void setGold_forge(Forge forge) {
        this.gold_forge = forge;
    }

    public void setEmerald_forge(Forge forge) {
        this.emerald_forge = forge;
    }

    public Forge getIron_forge() {
        return this.iron_forge;
    }

    public Forge getGold_forge() {
        return this.gold_forge;
    }

    public Forge getEmerald_forge() {
        return this.emerald_forge;
    }

    public ArrayList<TeamTraps> getTraps() {
        return traps;
    }

    public void setTraps(ArrayList<TeamTraps> traps) {
        this.traps = traps;
    }
    public void addTrap(TeamTraps trap){this.traps.add(trap);}
    public void removeTrap(TeamTraps trap){this.traps.remove(trap);}
    public void removeTrap(){this.traps.remove(0);}
    public TeamTraps getTrap(){return this.traps.get(0);}
    public TeamTraps getTrap(int i){return this.traps.get(i);}

    public TeamColors getTeam() {
        return this.team;
    }

    public boolean canRespawn() {
        return game.TeamManager().getCanRespawn(this.team);
    }
}
