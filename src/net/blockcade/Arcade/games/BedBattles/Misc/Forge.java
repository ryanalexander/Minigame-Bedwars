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
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Utils.GameUtils.Hologram;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Forge {

    private SpinningBlock spinningBlock;
    private Location location;
    private Material material;
    private Game game;
    private boolean stopped = false;
    private boolean hasSummary = false;
    private Hologram hologram;
    private int max_material=0;
    private int amount;
    private long speed;
    private long base_speed;

    private String title="";

    /**
     * @param game     Game object
     * @param location Where should the item drop when spawned
     * @param material Bukkit material that should drop
     * @param speed    How many ticks should pass between each drop
     */
    public Forge(Game game, Location location, Material material, long speed, boolean hasSummary, int max_material, String title) {
        this.game=game;
        this.location = location;
        this.material = material;
        this.amount = 1;
        this.max_material = max_material-1;
        this.speed = speed;
        this.base_speed = speed;
        this.hasSummary = hasSummary;
        this.title=title!=null?title:"";
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

    public void incresePercent(long percent) {
        setSpeed(base_speed-(base_speed*percent)/100);
    }

    private void run() {
        if(!hasSummary) this.hologram = new Hologram(game,location.clone().add(0,3.25,0),new String[]{Text.format(title)});
        if(!hasSummary) this.hologram = new Hologram(game,location.clone().add(0,3,0),new String[]{"&fDropping in &e%s&f second%s"});
        Forge f = this;
        new BukkitRunnable() {
            Forge forge = f;
            int i = 0;
            @Override
            public void run() {
                if (forge.stopped) {
                    cancel();
                    return;
                }
                i=i+5;
                int time = (int) ((forge.speed-i)/20);
                if(!hasSummary) if(hologram!=null)hologram.editLine(0,String.format(hologram.getLines()[0],time,time>1?"s":""));
                if(i>=forge.speed){
                    if(getEntitiesAroundPoint(location,16,new ItemStack(material))<=max_material)drop();
                    i=0;
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0,5);
    }

    private static Integer getEntitiesAroundPoint(Location location, double radius, ItemStack type) {
        int count = 0;
        for (Entity e : location.getWorld().getNearbyEntities(location, radius, radius, radius)) {
            if (e instanceof Item) {
                if(((Item)e).getItemStack().getType().equals(type.getType()))
                    count++;
            }
        }
        return count;
    }

    private void drop() {
        Item i = location.getWorld().dropItem(location.clone(),new ItemStack(material,amount));
        i.setVelocity(new Vector());
        i.setPickupDelay(0);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
