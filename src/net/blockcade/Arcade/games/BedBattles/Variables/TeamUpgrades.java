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
 *  @since 25/7/2019
 */

package net.blockcade.Arcade.games.BedBattles.Variables;

public enum TeamUpgrades {
    SHARP_SWORD(1),
    HEALING(1),
    REINFORCED_ARMOR(0),
    MANIAC_MINER(1),
    FORGE(0);

    int level;

    public int getLevel() {
        return this.level;
    }

    TeamUpgrades(int level) {
        this.level = level;
    }
}
