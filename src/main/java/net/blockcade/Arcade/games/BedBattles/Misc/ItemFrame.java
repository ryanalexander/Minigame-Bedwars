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
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.bukkit.Bukkit.getServer;

public class ItemFrame {

    public static String[] sprays = new String[]{
            "data:image/png;base64,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"
    };

    public static ItemStack getMap(BufferedImage img){
        // Generate map ItemStack
        ItemStack mapItem = new ItemStack(Material.FILLED_MAP);
        // Fetch image from url

        MapView map = getServer().createMap(getServer().getWorlds().get(0));
        for(org.bukkit.map.MapRenderer r : map.getRenderers())
            map.removeRenderer(r);
        map.addRenderer(new MapRenderer(img,0,0,1d));
        MapMeta meta = ((MapMeta) mapItem.getItemMeta());
        meta.setMapId(map.getId());
        mapItem.setItemMeta(meta);
        return mapItem;
    }
    public static Image getImageFromURL(URL url) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(url);
        }
        catch (IOException e) {
            System.out.println("I'm not smart enough to open the file");
        }

        return image;
    }
    public static BufferedImage getImageFromBase64(String base64){
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(base64.split(",")[1]);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
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
