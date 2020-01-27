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

package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Utils.Formatting.Text;
import net.blockcade.Arcade.games.BedBattles.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PotionEvent implements Listener {

    public static HashMap<Player, ArmorStand> invis_players = new HashMap<>();
    private int invisible_time = 30;

    @EventHandler
    public void PotionEvent(EntityPotionEffectEvent e) {
        if (e.getCause().equals(EntityPotionEffectEvent.Cause.POTION_DRINK)) {
            if (e.getNewEffect().getType().equals(PotionEffectType.INVISIBILITY)) {
                e.setCancelled(true);
                ArmorStand as = (ArmorStand) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ARMOR_STAND);
                as.setVisible(false);
                as.setMarker(true);
                as.setItemInHand(((Player) e.getEntity()).getItemOnCursor());
                invis_players.put((Player) e.getEntity(), as);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(Main.getPlugin(Main.class), (Player) e.getEntity());
                }
                new BukkitRunnable() {
                    int counter = 1;

                    @Override
                    public void run() {
                        counter = counter + 1;
                        Player player = (Player) e.getEntity();
                        char chatColor = 'a';
                        if (counter / 20 > (invisible_time / 3)) {
                            chatColor = '6';
                        }
                        if (counter / 20 > (invisible_time / 2)) {
                            chatColor = 'c';
                        }
                        if (counter / 20 > (invisible_time)) {
                            chatColor = '4';
                        }
                        Text.sendMessage(player,String.format("&eInvisible for &" + chatColor + "%s&as.", 30 - (counter / 20)), Text.MessageType.ACTION_BAR);
                        if ((30 - (counter / 20)) <= 0 || (!invis_players.containsKey(player)))
                            cancel();
                    }
                }.runTaskTimer(Main.getPlugin(Main.class), 0, 1);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (invis_players.containsKey((Player) e.getEntity())) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.showPlayer(Main.getPlugin(Main.class), (Player) e.getEntity());
                                invis_players.get((Player) e.getEntity()).remove();
                            }
                            invis_players.remove((Player) e.getEntity());
                        }
                    }
                }.runTaskLater(Main.getPlugin(Main.class), (30 * 20));
            }
        }
    }
}
