package Level;

import GameComponents.Entity;
import GameComponents.Hero;
import GameComponents.Projectile;
import UIcomponents.WorkSpace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for game levels; int[]  tiles means that at index 0  can be tile grass, at 1 can be tile stone etc.
 */
public abstract class Level {
    public boolean tileCollision;
    protected int[] tiles;
    protected int width, height;
    protected int[] tilesInt;   // method to use tiles without path of the map
    public static String value;

    //static String path = new MainFrame().getLevel();
    //public static Level spawn = new SpawnLevel("res/textures/spawnLevel2.png");
    public static Level spawn = new SpawnLevel(getLevelPath());

    private List<Entity> entities = new ArrayList<>(); // list of entities on level
    private List<Projectile> projectiles = new ArrayList<>(); // list of projectiles
    private List<Hero> heroes = new ArrayList<>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width*height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    protected void loadLevel(String path) {

    }


    protected void generateLevel() {

    }

    public static String getLevelPath() {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("levelPath.txt"))) {
            value = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * update() method updates the status of every entity, fireball and hero on that level
     */
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }
        for (int i = 0; i < heroes.size(); i++) {
            heroes.get(i).update();
        }

    }

    public void time() {

    }

    /**
     * tileCollision method checks if the next tile has collision or not.
     * @param x - current pos of entity on map OX
     * @param y - current pos of entity on map OY
     * @param xabsolute - new pos of entity on map OX
     * @param yabsolute - new pos of entity on map OX
     * @param size -  ...
     * @return solid - if the next tile has a collision(wall for ex.)
     */
    public boolean tileCollision(double x, double y, double xabsolute,  double yabsolute, int size) {  // x, y - pos of entity, xa, ya - direction
        boolean solid = false;
        double xt = (x+xabsolute)/16;
        double yt = (y+yabsolute)/16;
        if (getTile((int)xt,  (int)yt).solid()) {       // cheking if new position has the colliders
            solid = true;
        }
        //System.out.println(getTile((int)xt,  (int)yt).solid());
        return solid;
    }

    /**
     * render() method for a level which uses tiles
     * @param xScroll - the beginning of the coordinates OX
     * @param yScroll - the beginning of the coordinates OX
     * @param screen - workspace class
     */
    // def. the region of render on screen!!!
    public void render(int xScroll, int yScroll, WorkSpace screen) {
        screen.setOffset(xScroll, yScroll);
        // generate +1 tile, for reason that we dont wanna see black shit on the left and the right sides
        int x0 = (xScroll)/16 - 1;
        int x1  = (xScroll + screen.width)/16 + 1;
        int y0 = (yScroll)/16 - 1;
        int y1 = (yScroll + screen.height)/16 + 1;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                //getTile(x, y).render(x, y, screen);
                if (x + y*64 < 0 || x + y*64 >= 4096 ) {
                    Tile.voidTile.render(x, y, screen);
                    continue;
                }

                getTile(x, y).render(x, y, screen);

            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }
        for (int i = 0; i < heroes.size(); i++) {
            heroes.get(i).render(screen);
        }
    }

    /**
     * add() method, which adds entities on map
     * @param e - represents different entities
     */
    public void add(Entity e) {
        e.init(this);
        if (e instanceof Hero) {
            heroes.add((Hero)e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        }
        else entities.add(e);

    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public Hero getHeroAt(int index) {
        return heroes.get(index);
    }

    /**
     * getEntitites() method, which works for getting information about enemies around. If size of result will be greater than 0 game will be over
     * @param e - entity
     * @param radius - radius in which enemies should not be
     * @return result - arraylist of entities in radius
     */
    public List<Entity> getEntities(Entity e, int radius) {
        List<Entity> result = new ArrayList<>();
        int ex = e.getX();
        int ey = e.getY();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            int x = entity.getX();
            int y = entity.getY();

            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx*dx) + (dy*dy));
            if (distance <= radius) {
                result.add(entity);
            }
        }
        return result;
    }


    /**
     * getTile() the method is for reading colors from a 64x64 pixels drawn map and returning a specific tile that matches a specific color on the drawn map
     * @param x - tile coordinate on map OX
     * @param y - tile coordinate on map OY
     * @return tile
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        //System.out.println(new File("res/textures/spawnLevel.png").exists());
        if (tiles[x+y*width] == Tile.color_spawn_grass) return Tile.grass;
        if (tiles[x+y*width] == Tile.color_spawn_grass2) return Tile.grass2;
        if (tiles[x+y*width] == Tile.color_spawn_wall) return Tile.wall;
        if (tiles[x+y*width] == Tile.color_spawn_ground) return Tile.ground;
        if (tiles[x+y*width] == Tile.color_exit_ground) return Tile.exit_ground;
        if (tiles[x+y*width] == Tile.color_shrine) return Tile.shrine;
        return Tile.voidTile;
    }

}
