package Level;

import GameComponents.MapComponent;
import UIcomponents.GameFrame;
import UIcomponents.WorkSpace;

public class GrassTile extends Tile{

    public GrassTile(MapComponent sprite) {
        super(sprite);
    }
    public void render(int x , int y, WorkSpace screen) {
        screen.renderTile(x * 16, y * 16, this);
    }
}
