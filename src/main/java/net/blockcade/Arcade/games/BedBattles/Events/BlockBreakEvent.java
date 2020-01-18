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

package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Managers.GamePlayer;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Varables.TeamColors;
import net.blockcade.Arcade.games.BedBattles.Main;
import net.blockcade.Arcade.games.BedBattles.Misc.BedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Objects;
import java.util.Random;

public class BlockBreakEvent implements Listener {

    Game game;

    public BlockBreakEvent(Game game) {
        this.game = game;
    }

    private static String[] bed_message = new String[]{
            "%s&7's bed has been broken by %s",
            "&7Looks like %s&7's bed was shown the exit by %s",
            "&7%s&7 team couldn't protect their bed from %s",
            "Oh no! %s&7 was shown the way to insomnia by %s"
    };

    @EventHandler(priority = EventPriority.HIGHEST)
    public void BlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        if (!(e.getBlock().getType().name().toUpperCase().contains("BED")))
            return;
        if (Main.beds.containsKey(e.getBlock())) {
            e.setCancelled(true);
            handleBedBreak(e.getPlayer(), e.getBlock());
        } else if (Main.beds.containsKey(getOtherBedBlock(e.getBlock()))) {
            e.setCancelled(true);
            handleBedBreak(e.getPlayer(), getOtherBedBlock(e.getBlock()));
        }

    }

    private void handleBedBreak(Player blockBreaker, Block bed) {
        TeamColors teamBed = Main.beds.getOrDefault(bed, null).getTeam();
        TeamColors breaker = game.TeamManager().getTeam(blockBreaker);
        GamePlayer breaker_gp = GamePlayer.getGamePlayer(blockBreaker);
        BedPlayer breaker_bp = BedPlayer.getBedPlayer(breaker_gp);

        if (teamBed.equals(breaker)) {
            blockBreaker.sendMessage(Text.format("&cYou may not break your own bed!"));
        } else {
            Objects.requireNonNull(getOtherBedBlock(bed)).setType(Material.AIR);
            bed.setType(Material.AIR);
            Random rand = new Random();
            game.TeamManager().setCantRespawn(teamBed, true);

            breaker_bp.setBedDestroys(breaker_bp.getBedDestroys()+1);
            Bukkit.broadcastMessage(Text.format(String.format(bed_message[rand.nextInt(bed_message.length)], teamBed.getChatColor() + teamBed.name(), breaker.getChatColor() + blockBreaker.getDisplayName())));
            try {
                bed.getLocation().getWorld().strikeLightningEffect(bed.getLocation());
            } catch (Exception e) {
                // Failed to strike lightning!
            }
        }
    }

    private Block getOtherBedBlock(Block b1) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                Block b2 = b1.getRelative(x, 0, z);
                if (!(b1.getLocation().equals(b2.getLocation()))) {
                    if (b2.getType().equals(Material.RED_BED) || b2.getType().equals(Material.LEGACY_BED_BLOCK)) {
                        return b2;
                    }
                }
            }
        }
        return null;
    }

}
