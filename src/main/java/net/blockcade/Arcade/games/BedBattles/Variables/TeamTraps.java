package net.blockcade.Arcade.games.BedBattles.Variables;

import org.bukkit.Material;

public enum TeamTraps {
    ALERT(Material.STONE_BUTTON,"Doorbell","&7Reveals invisible players as well as their name and team."),
    BLINDNESS(Material.LEVER,"Cut the lights","&7Inflicts Blindness and Slowness for 5 seconds.");

    Material item;
    String name;
    String description;
    TeamTraps(Material item, String name, String description){
        this.item=item;
        this.name=name;
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Material getItem() {
        return item;
    }
}
