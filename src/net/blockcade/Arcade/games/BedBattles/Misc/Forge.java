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

package net.blockcade.Arcade.games.BedBattles.Misc;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Forge {

    private SpinningBlock spinningBlock;
    private Location location;
    private Material material;
    private Game game;
    private boolean stopped = false;
    private int amount;
    private long speed;

    /**
     * @param game     Game object
     * @param location Where should the item drop when spawned
     * @param material Bukkit material that should drop
     * @param speed    How many ticks should pass between each drop
     */
    public Forge(Game game, Location location, Material material, long speed, boolean hasSummary) {
        this.location = location;
        this.material = material;
        this.amount = 1;
        this.speed = speed;
        this.run();
    }

    /**
     * @return has the iterator been set to stop
     */
    public boolean isStopped() {
        return stopped;
    }

    /**
     * @return Where should the item drop when spawned
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return How many of the material should be dropped per payload
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return How many ticks should pass between each drop
     */
    public long getSpeed() {
        return speed;
    }

    /**
     * @return Bukkit material that should drop
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Stop the iterator
     */
    public void stop() {
        this.stopped = true;
    }

    /**
     * @param amount How many of the material should be dropped per payload
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @param location Where should the item drop when spawned
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @param material Bukkit material that should drop
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @param spinningBlock Uses SpinningBlock module to interface with Forge
     */
    public void setSpinningBlock(SpinningBlock spinningBlock) {
        this.spinningBlock = spinningBlock;
    }

    /**
     * @param speed How many ticks should pass between each drop
     */
    public void setSpeed(long speed) {
        this.speed = speed;
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Forge.this.stopped) {
                    cancel();
                    return;
                }
                drop();
                Forge.this.run();
            }
        }.runTaskLater(Main.getPlugin(Main.class), this.speed);
    }

    private void drop() {
        location.getWorld().dropItem(location, new ItemStack(material, amount));
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
