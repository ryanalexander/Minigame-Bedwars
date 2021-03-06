
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

package net.blockcade.Arcade.games.BedBattles.Misc;

import net.blockcade.Arcade.Main;
import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.Utils.ReflectionUtil;
import net.blockcade.Arcade.Varables.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

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
                if (!Main.game.GameState().equals(GameState.IN_GAME))
                    stop();
                if (SpinningBlock.this.deleted)
                    cancel();
                this.ticks += 3.0;
                double change = Math.cos(this.ticks / 10.0);


                try {
                    Constructor<?> constructor = ReflectionUtil.getNMSClass("PacketPlayOutEntity$PacketPlayOutRelEntityMoveLook").getConstructor(Integer.TYPE, Short.TYPE, Short.TYPE, Short.TYPE, Byte.TYPE, Byte.TYPE, Boolean.TYPE);
                    Object packet = constructor.newInstance(SpinningBlock.this.e.getEntityId(), (short) 0, (short) (change * 2.0), (short) 0, (byte) (this.loc.getYaw() + 0.0), (byte) ((this.loc.getPitch() + 180.0) / 360.0), true);
                    this.loc.setYaw(this.loc.getYaw() + 7);
                    this.loc.setY(change);
                    for(Player player : Bukkit.getOnlinePlayers())
                        ReflectionUtil.sendPacket(player,packet);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 0, 1);
    }

    public void stop() {
        this.e.remove();
        this.deleted=true;
    }
}