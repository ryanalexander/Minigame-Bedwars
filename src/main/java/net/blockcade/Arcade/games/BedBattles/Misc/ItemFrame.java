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

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.bukkit.Bukkit.getServer;

public class ItemFrame {
    public static ItemStack getMap(URL image){
        // Generate map ItemStack
        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
        // Fetch image from url
        BufferedImage img;
        try {
            System.out.println("Loaded "+image);
            img=(BufferedImage) getImageFromURL(image);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

        MapView map = getServer().createMap(getServer().getWorlds().get(0));
        for(org.bukkit.map.MapRenderer r : map.getRenderers())
            map.removeRenderer(r);
        map.addRenderer(new MapRenderer(img,0,0,1d));
        MapMeta meta = ((MapMeta) mapItem.getItemMeta());
        meta.setMapId(map.getId());
        mapItem.setItemMeta(meta);
        return mapItem;
    }
    public static Image getImageFromURL(URL url) throws MalformedURLException{
        BufferedImage image = null;

        try {
            image = ImageIO.read(url);
        }
        catch (IOException e) {
            System.out.println("I'm not smart enough to open the file");
        }

        return image;
    }

    static class MapRenderer extends org.bukkit.map.MapRenderer {
        private int MAP_WIDTH = 128;
        private int MAP_HEIGHT = 128;
        private BufferedImage image = null;
        private boolean first = true;

        public MapRenderer(BufferedImage image, int x1, int y1, double scale)
        {
            recalculateInput(image, x1, y1, scale);
        }

        public void recalculateInput(BufferedImage input, int x1, int y1, double scale)
        {
            int x2 = MAP_WIDTH;
            int y2 = MAP_HEIGHT;

            if (x1 > input.getWidth()* scale + 0.001 || y1 > input.getHeight() * scale + 0.001)
                return;

            if (x1 + x2 >= input.getWidth() * scale)
                x2 = (int)(input.getWidth() * scale) - x1;

            if (y1 + y2 >= input.getHeight() * scale)
                y2 = (int)(input.getHeight() * scale) - y1;

            this.image = input.getSubimage((int)(x1/scale), (int)(y1/scale), (int)(x2/scale), (int)(y2/scale));
            if (scale != 1.0) {
                BufferedImage resized = new BufferedImage(MAP_WIDTH, MAP_HEIGHT, input.getType());
                AffineTransform at = new AffineTransform();
                at.scale(scale, scale);
                AffineTransformOp scaleOp =  new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
                this.image = scaleOp.filter(this.image, resized);
            }
            first = true;
        }

        @Override
        public void render(MapView view, MapCanvas canvas, Player player)
        {
            if (image != null && first)
            {
                canvas.drawImage(0, 0, image);
                first = false;
            }
        }
    }
}
