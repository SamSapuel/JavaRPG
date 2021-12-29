package Level;

import GameComponents.Enemy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SpawnLevel extends Level{

    Random random = new Random();

    public SpawnLevel(String path) {
        super(path);


    }

    /**
     * loadLevel() method for upload drawn map
     * @param path - path to the map
     */
    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            //tiles = new Tile[width*height];    // size of map
            tiles = new int[w*h];
            image.getRGB(0,0,w,h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error! Could not load the level file!");
        }

        // Add enemies to the game
        add(new Enemy(30, 30));
        add(new Enemy(40, 40));
        add(new Enemy(20, 40));
        add(new Enemy(30, 50));
        add(new Enemy(10, 5));
        add(new Enemy(30, 30));
        add(new Enemy(24,24));
        add(new Enemy(50,50));
        add(new Enemy(20,10));
        add(new Enemy(50,10));
        add(new Enemy(50,5));
        add(new Enemy(60,46));
        add(new Enemy(46,46));
    }

    protected void generateLevel() {

    }
}
