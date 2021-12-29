package Level;

import GameComponents.MapComponent;
import UIcomponents.WorkSpace;

public class GroundTile extends Tile{
    public GroundTile(MapComponent sprite) {
        super(sprite);
    }
    public void render(int x , int y, WorkSpace screen) {
        screen.renderTile(x * 16, y * 16, this);
    }

    public boolean solid() {
        return false;
    }
}
