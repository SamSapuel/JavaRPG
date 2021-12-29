import UIcomponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

import UIcomponents.MainFrame;
import UIcomponents.WorkSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/*
 * Tile - eto plitka
 * BufferImage - image v bafferu
 * Canvas - prosto prjamougolnik v kotorom mozhno risovat grafiku
 * Label - kak naklejka, podhodit dlja fona
 * Panel - razlichnyje paneli
 */
/**
 * @author shevcdmi a.k.a. Shevchenko Dmitriy
 */

/**
 * Main class, which starts application
 */

public class Main extends Canvas {
    private Thread thread;
    private boolean running = false;    // running status of the game
    private MainFrame mainFrame;
    WorkSpace workSpace;
    ImageIcon icon;
    Image map;
    JFrame frame;
    Dimension size;
    JPanel westPanel;
    JPanel centerPanel;
    Main game;
    public static int width = 460;
    public static int height = width / 16 * 9; // for 16:9 monitors
    public static int scale = 3;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  // create the image in the buffer
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();        // get the info about pixels in image(color)

    public Main() {
        size = new Dimension(width*scale, height*scale);
        workSpace = new WorkSpace(width, height);
        frame = new JFrame();  // create new window

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {              // it will wait for others operations and just then show us the window
            MainMenu mainMenu = new MainMenu();
            mainMenu.mainMenu();
        });

    }

}

