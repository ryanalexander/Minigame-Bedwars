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

import net.blockcade.Arcade.Main;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class SpinningBlock {
    public SpinningBlock(Location location, Material material) {
        Create(location, material, 0);
    }

    public SpinningBlock(Location location, Material material, int data) {
        Create(location, material, data, true, null);
    }

    public SpinningBlock(Location location, Material material, int data, boolean small) {
        Create(location, material, data, small, null);
    }

    public SpinningBlock(Location location, Material material, int data, String text) {
        Create(location, material, data, true, text);
    }

    private ArmorStand e = null;
    private boolean deleted = false;

    private void Create(Location location, Material material, int data) {
        Create(location, material, data, true, null);
    }

    private void Create(Location location, Material material, int data, boolean is_Small, String text) {
        ItemStack skull = new ItemStack(material, 1, (short) (byte) data);
        this.e = ((ArmorStand) Bukkit.getWorld(location.getWorld().getName()).spawnEntity(location, EntityType.ARMOR_STAND));
        this.e.setVisible(false);
        this.e.setGravity(false);
        this.e.teleport(this.e.getLocation());
        this.e.setCanPickupItems(false);
        this.e.setRemoveWhenFarAway(false);
        this.e.setMarker(false);
        this.e.getEquipment().setHelmet(skull);
        this.e.setCustomName(Text.format(text));
        this.e.setCustomNameVisible(false);
        this.e.setSmall(is_Small);

        new BukkitRunnable() {
            double ticks = 0.0;
            boolean right = true;
            Location loc = SpinningBlock.this.e.getLocation();

            public void run() {
                if (SpinningBlock.this.deleted) {
                    cancel();
                }
                this.ticks += 3.0;
                double change = Math.cos(this.ticks / 10.0);

                PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook packet = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(SpinningBlock.this.e.getEntityId(), (byte) 0, (byte) (int) (change * 2.0), (byte) 0, (byte) (int) (this.loc.getYaw() + 0.0), (byte) (int) ((this.loc.getPitch() + 180.0) / 360.0), true);
                this.loc.setYaw(this.loc.getYaw() + 7);
                this.loc.setY(change);
                Player p;
                for (Iterator localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();
                     ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet)) {
                    p = (Player) localIterator.next();
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 1);
    }

    public void teleport(Location l) {
        this.e.teleport(l);
    }

    public void stop() {
        this.e.remove();
    }
}