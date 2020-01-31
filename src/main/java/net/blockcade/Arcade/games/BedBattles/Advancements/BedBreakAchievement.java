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
 * @since (DD/MM/YYYY) 31/1/2020
 */

package net.blockcade.Arcade.games.BedBattles.Advancements;

import net.blockcade.Arcade.Managers.AdvancementManager.Achievement;
import net.blockcade.Arcade.Managers.GamePlayer;
import net.blockcade.Arcade.Varables.RewardType;
import net.blockcade.Arcade.games.BedBattles.Main;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class BedBreakAchievement implements Achievement {
    @Override
    public RewardType getRewardType() {
        return RewardType.EXPERIENCE;
    }

    @Override
    public Integer getRewardCount() {
        return 30;
    }

    @Override
    public boolean hasAchieved(GamePlayer player) {
        return false;
    }

    @Override
    public boolean hasAchieved(GamePlayer player, Block block) {
        return Main.beds.containsKey(block)&&Main.beds.get(block).getTeam()!=player.getTeam();
    }

    @Override
    public boolean hasAchieved(GamePlayer player, Entity entity) {
        return false;
    }

    @Override
    public String getTitle() {
        return "Master insomniac";
    }

    @Override
    public String getDescription() {
        return "Achieved by destroying another teams bed";
    }

    @Override
    public InitiatedBy getInitiator() {
        return InitiatedBy.BLOCK_BREAK;
    }
}
