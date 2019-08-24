package net.blockcade.Arcade.games.BedBattles.Events;

import net.blockcade.Arcade.Game;
import net.blockcade.Arcade.Varables.GameState;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractEvent implements Listener {

    Game game;
    public PlayerInteractEvent(Game game){this.game=game;}

    @EventHandler
    public void PlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent e){

        if(game.GameState()!= GameState.IN_GAME)return;

        if(e.getItem().getType().equals(Material.SILVERFISH_SPAWN_EGG)){

            Silverfish silverfish = (Silverfish) game.map().spawnEntity(e.getClickedBlock().getLocation(), EntityType.SILVERFISH);

        }

    }

    @EventHandler
    public void EntityTarget(EntityTargetEvent e){

    }

}
