package GameComponents;

import Level.Level;
import UIcomponents.WorkSpace;

import java.util.Random;

/**
 * The entity of every unit on the map
 */
public  class Entity {
    protected int x,y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update() {

    }

    public void render(WorkSpace screen) {

    }
    // Remove from level
    public void remove() {

        removed = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init (Level level) {
        this.level = level;
    }

}
