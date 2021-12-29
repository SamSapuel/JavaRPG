package Level;

import GameComponents.MapComponent;
import UIcomponents.WorkSpace;

public class VoidTile extends Tile {
    public VoidTile(MapComponent sprite) {
        super(sprite);
    }
    public void render(int x, int y, WorkSpace screen) {
        screen.renderTile(x * 16, y * 16, this);
    }
}
