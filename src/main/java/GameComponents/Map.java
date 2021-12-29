package GameComponents;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Spritesheet class, which we create for using textures
 */

public class Map {
    private String path;
    public final int SIZE;
    public int[] pixels;

    public static Map tiles = new Map("res/textures/spritesheetTest.png", 256);

    public Map(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        load(path);
    }

    private void load(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0,0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
