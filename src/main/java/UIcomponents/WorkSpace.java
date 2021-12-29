package UIcomponents;

import GameComponents.Map;
import GameComponents.MapComponent;
import GameComponents.Projectile;
import Level.Tile;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * WorkSpace class is one of the main render classes, which creates pixels array with different properties
 */

public class WorkSpace extends JPanel{
    public int width;
    public int height;
    public int[] pixels;
    private Random random = new Random();
    int xtime = 0;
    int ytime = 0;
    int counter = 0;
    int[] tiles = new int[64*64];

    public int xOffset, yOffset;
    public WorkSpace(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];

        for (int i = 0; i < 64*64; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    /**
     * renderTile method, which adds to main pixels array different tiles
     *
     * @param xpixel - current map pos OX
     * @param ypixel - current map pos OY
     * @param tile - texture with parameters
     */
    // Offsets
    public void renderTile(int xpixel, int ypixel, Tile tile) {
        // represents distance from old to new point on the map
        xpixel -= xOffset;
        ypixel -= yOffset;
        // render the differences between old and new point and whole map of course
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yabsolute = ypixel + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xabsolute = xpixel + x;
                if (xabsolute < -tile.sprite.SIZE || xabsolute >= width ||  yabsolute < 0 || yabsolute >= height) break;
                if (xabsolute < 0) xabsolute = 0;                   // remove black line from the left

                pixels[xabsolute + yabsolute*width] = tile.sprite.pixels[x + y*tile.sprite.SIZE];

            }
        }
    }

    /**
     * renderMob method, which adds to main pixels array different mobs(for ex. Hero)
     * @param xpixel - current map pos OX
     * @param ypixel - current map pos OY
     * @param sprite - texture
     */
    public void renderMob(int xpixel, int ypixel, MapComponent sprite) {
        // represents distance from old to new point on the map
        xpixel -= xOffset;
        ypixel -= yOffset;
        // render the differences between old and new point and whole map of course
        for (int y = 0; y < 32; y++) {
            int yabsolute = ypixel + y;
            for (int x = 0; x < 32; x++) {
                int xabsolute = xpixel + x;
                if (xabsolute < -32 || xabsolute >= width ||  yabsolute < 0 || yabsolute >= height) break;
                if (xabsolute < 0) xabsolute = 0;                   // remove black line from the left
                int color = sprite.pixels[x + y*32];
                if (color != 0xFF71664F) pixels[xabsolute + yabsolute*width] = color;
            }
        }
    }

    /**
     * renderProjectile method, which adds to main pixels array different fireballs
     * @param xpixel - current map pos OX
     * @param ypixel - current map pos OY
     * @param projectile - texture
     */
    public void renderProjectile(int xpixel, int ypixel, Projectile projectile) {
        // represents distance from old to new point on the map
        xpixel -= xOffset;
        ypixel -= yOffset;
        // render the differences between old and new point and whole map of course
        for (int y = 0; y < projectile.getSprite().SIZE; y++) {
            int yabsolute = ypixel + y;
            for (int x = 0; x < projectile.getSprite().SIZE; x++) {
                int xabsolute = xpixel + x;
                if (xabsolute < -projectile.getSprite().SIZE || xabsolute >= width ||  yabsolute < 0 || yabsolute >= height) break;
                if (xabsolute < 0) xabsolute = 0;                   // remove black line from the left
                int color = projectile.getSprite().pixels[x + y*projectile.getSprite().SIZE];
                if (color != 0xFF71664F) pixels[xabsolute + yabsolute*width] = color;
            }
        }
    }

    /**
     * Setting new pos of map
     * @param xOffset - new map pos OX
     * @param yOffset - new map pos OY
     */
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}
