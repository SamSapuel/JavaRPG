package GameComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.Collection;
//import java.util.Map;
import java.util.Set;

/**
 * Sprites class, which inits different textures
 */
public class MapComponent  {
    public final int SIZE;
    private int x, y;
    public  int[] pixels;
    boolean movable;
    public Map sheet;

    // sprite of level
    public static MapComponent grass = new MapComponent(16, 0,0, Map.tiles);
    public static MapComponent grass2 = new MapComponent(16, 1,0, Map.tiles);
    public static MapComponent wall = new MapComponent(16, 0,2, Map.tiles);
    //public static MapComponent voidSprite = new MapComponent(16,0x000000);
    public static MapComponent voidSprite = new MapComponent(16, 2, 1, Map.tiles);
    public static MapComponent ground = new MapComponent(16,0, 3, Map.tiles);
    public static MapComponent exit_ground = new MapComponent(16, 1, 1, Map.tiles);
    public static MapComponent shrine = new MapComponent(16, 3, 0, Map.tiles);

    // projectile sprite
    public static MapComponent projectile_wizard = new MapComponent(16, 4 ,4, Map.tiles);

    // sprite of enemy heroes
    public static MapComponent enemy_right = new MapComponent(32, 0, 3, Map.tiles);
    public static MapComponent enemy_left = new MapComponent(32, 1, 3, Map.tiles);

    // sprite of players hero
    public static MapComponent hero_right = new MapComponent(32, 0, 2, Map.tiles);
    public static MapComponent hero_left = new MapComponent(32, 1, 2, Map.tiles);


    public MapComponent(int size, int x, int y, Map sheet) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        this.x = x*size;    // in pixels
        this.y = y*size;
        this.sheet = sheet;
        load();
    }

    public MapComponent(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColor(color);
    }
    private void setColor(int color) {
        for (int i = 0; i < SIZE*SIZE; i++) {
            pixels[i] = color;
        }
    }

    /**
     *  load() method reads pixels from spritesheet and writes them to the new pixels array for 1 sprite
     */
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];       // draw the sprite at the spritesheet
            }
        }
    }

}
