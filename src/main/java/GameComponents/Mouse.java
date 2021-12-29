package GameComponents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Logger;

/**
 * simple class for registration mouse events
 */
public class Mouse implements MouseListener, MouseMotionListener {
    private static Logger log = Logger.getLogger(Mouse.class.getName());

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    public static int getX() {
        return mouseX;
    }
    public static int getY() {
        return mouseY;
    }

    public static int getButton() {
        return mouseB;
    }

    public Mouse() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();
        log.info(mouseB + " button was pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX= e.getX();
        mouseY= e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();


    }
}
