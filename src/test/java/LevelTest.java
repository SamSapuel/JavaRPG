import GameComponents.Enemy;
import GameComponents.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class LevelTest {

    @Test
    public void getTileTest() throws IOException {
        // Testing the method for getting the colors of the spawnLevel
        boolean color_grass2 = false;
        boolean color_grass = false;
        boolean expected = true;
        boolean result = false;
        int red;
        int green;
        int blue;
        int alpha;
        BufferedImage image = ImageIO.read(new File("res/textures/spawnLevel.png"));
        int w = image.getWidth();
        int h = image.getHeight();
        int [] tiles = new int[w*h];
        int[] imagergb = image.getRGB(0,0,w,h, tiles, 0, w);
        List<Integer> list = new ArrayList<>();


        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                // Convert from decimal system to RGBA
                alpha = (imagergb[x] >> 24) & 0xFF;
                red = (imagergb[x] >> 16) & 0xFF;
                green = (imagergb[x] >> 8) & 0xFF;
                blue = (imagergb[x]) & 0xFF;
                list.add(alpha);
                list.add(red);
                list.add(green);
                list.add(blue);
                if (alpha + red + green + blue == 255 + 8 + 255 + 0) {
                    color_grass = true;
                } else if (alpha + red + green + blue == 255 + 197 + 255 + 96) {
                    color_grass2 = true;
                }
                if (color_grass &&  color_grass2 ) {
                    result = true;
                }
            }
        }

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getEntitiesTest() {
        // check for mobs in a range area
        int radius = 30;
        Enemy enemy = new Enemy(22, 55);
        List<Entity> entities = new ArrayList<>();
        entities.add(enemy);
        int expected = 1;
        List<Entity> result = new ArrayList<>();
        // Heroes coordinates
        int exX = 21*16;
        int exY = 54*16;
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            // Enemies coordinates
            int x = entity.getX();
            int y = entity.getY();
            int dx = Math.abs(x - exX);
            int dy = Math.abs(y - exY);
            double distance = Math.sqrt((dx*dx) + (dy*dy));
            if (distance <= radius) {
                result.add(entity);
            }
        }
        Assertions.assertEquals(expected, result.size());
    }
}
