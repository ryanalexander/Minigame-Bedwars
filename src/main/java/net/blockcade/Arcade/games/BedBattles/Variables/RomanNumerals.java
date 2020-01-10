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
 *  @since 27/7/2019
 */

package net.blockcade.Arcade.games.BedBattles.Variables;

public enum RomanNumerals {
    _0(""),
    _1("I"),
    _2("II"),
    _3("III"),
    _4("IV"),
    _5("V"),
    _6("VI"),
    _7("VII"),
    _8("VIII"),
    _9("IX"),
    _10("X");
    String numeral;

    public String getNumeral() {
        return this.numeral;
    }

    RomanNumerals(String value) {
        this.numeral = value;
    }
}
