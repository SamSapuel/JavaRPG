package Level;

import GameComponents.MapComponent;
import UIcomponents.WorkSpace;

/**
 * Tile class, which creates tiles with different properties
 */
public class Tile {

    public int x, y;
    public MapComponent sprite;

    public static Tile grass = new GrassTile(MapComponent.grass);  // sprite grass => tile grass
    public static Tile grass2 = new Grass2Tile(MapComponent.grass2);
    public static Tile wall = new WallTile(MapComponent.wall);
    public static  Tile voidTile = new VoidTile(MapComponent.voidSprite);
    public static Tile ground = new GroundTile(MapComponent.ground);
    public static Tile exit_ground = new ExitGroundTile(MapComponent.exit_ground);
    public static Tile shrine = new ShrineTile(MapComponent.shrine);


    public static final int color_spawn_grass = 0xFF08FF00;
    public static final int color_spawn_grass2 = 0xFFC5FF60;
    public static final int color_spawn_wall = 0xFF000000;
    public static final int color_spawn_ground = 0xFF404040;
    public static final int color_exit_ground = 0xFFFF1900;
    public static final int color_shrine = 0xFF0509FF;

    public Tile(MapComponent sprite) {
        this.sprite = sprite;
    }

    public void render(int x , int y, WorkSpace screen) {

    }

    public boolean solid() {
        return false;
    }
}
