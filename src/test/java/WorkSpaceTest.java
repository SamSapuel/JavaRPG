import GameComponents.Map;
import GameComponents.MapComponent;
import Level.GrassTile;
import Level.Tile;
import UIcomponents.WorkSpace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Random;

public class WorkSpaceTest {
    private int width = 900;
    private int height = 600;
    public int[] pixels;
    private Random random = new Random();
    int xtime = 0;
    int ytime = 0;
    int counter = 0;
    int[] tiles = new int[64*64];
    public static Map tilesArray = new Map("res/textures/spritesheetTest.png", 256);
    Tile tile = new GrassTile(MapComponent.grass);
    //private WorkSpace workSpace;
    //private Mockito Mock;
    @Test
    public void init() {
        WorkSpace workSpace = new WorkSpace(width, height);
        tile.render(48,48, workSpace);




    }

    @Test
    public void renderTileTest() {
        //int [] expected = new int[16*16];

        pixels = new int[width*height];
        //xpixel -= xOffset;
            //ypixel -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yabsolute = 800 + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xabsolute = 500 + x;
                if (xabsolute < -tile.sprite.SIZE || xabsolute >= width ||  yabsolute < 0 || yabsolute >= height) break;
                if (xabsolute < 0) xabsolute = 0;                   // removing black line from the left

                pixels[xabsolute + yabsolute*width] = tile.sprite.pixels[x + y*tile.sprite.SIZE];
            }
        }
        int result = pixels.length;
        int expected = tile.sprite.pixels.length;
        //int Expected = expected.length;
        Assertions.assertEquals(expected, result);


    }


}
