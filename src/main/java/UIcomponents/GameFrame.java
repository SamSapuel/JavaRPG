package UIcomponents;

import GameComponents.Enemy;
import GameComponents.Hero;
import GameComponents.KeyboardInputs;
import GameComponents.Mouse;
import Level.Level;
import Level.RandomLevel;
import Level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * GameFrame class, which draws graphics.
 * The main implementation techniques are Buffer strategy and update of game status every second
 */

public class GameFrame extends Canvas implements Runnable, ActionListener {
    private Thread thread;
    public static boolean running = false;    // running status of the game
    private MainFrame mainFrame;
    private Level level;
    private RandomLevel randomLevel;
    private Hero hero;
    private Enemy enemy;
    KeyboardInputs key;
    WorkSpace workSpace;
    ImageIcon icon;
    Image map;
    JFrame frame;
    Dimension size;
    JPanel westPanel;
    JPanel centerPanel;
    private static int width = 300;
    private static int height = 200; //width / 16 * 9; // for 16:9 monitors
    private static int scale = 3;
    int x, y = 0;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  // create the image in the buffer
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();        // get the info about pixels in image(color)

    /**
     * GameFrame class, which creates a new window and init. level, hero, action listeners
     */
    public void GameFrame() {
        //EventQueue.invokeLater(() -> {              // it will wait for others operations and just then show us the window
        //
        //});
        frame = new JFrame();  // create new window
        workSpace = new WorkSpace(width, height);
        size = new Dimension(width * scale, height * scale);
        westPanel = new JPanel();
        centerPanel = new JPanel();

        key = new KeyboardInputs();

        //level = new RandomLevel(64, 64);
        //level = new SpawnLevel("res/textures/levelTest.png");
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(22,22);
        hero = new Hero(playerSpawn.x(),playerSpawn.y(), key);
        //hero.init(level);
        level.add(hero);
        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

    }


    public void main() {

        GameFrame game = new GameFrame();
        game.GameFrame();


        game.frame.setMinimumSize(new Dimension(800, 600));  // set minimum size of window
        game.frame.setPreferredSize(size);
        game.frame.add(game);

        game.frame.pack();
        game.frame.setLayout(new BorderLayout());
        game.frame.setLocation(0, 0);
        game.frame.setLocationRelativeTo(null);
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setResizable(false);
        game.frame.setVisible(true);

        icon = new ImageIcon("res/icon.png"); // create icon of engine
        game.frame.setIconImage(icon.getImage());
        game.start();
        JOptionPane helloMsg = new JOptionPane();
        JOptionPane.showMessageDialog(helloMsg, "Greetings traveler! Your main quest is to find 2 shrines, break down the door and exit the level. Good luck!");
        JButton ok  = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helloMsg.setVisible(false);
            }
        });
        helloMsg.add(ok);


    }

    public static int getWindowWidth() {
        return width*scale;
    }

    public static int getWindowHeight() {
        return height*scale;
    }
    public synchronized void start() {

        running = true;
        thread = new Thread(this, "Display");    // create new thread
        thread.start();
        //MainFrame mainFrame = new MainFrame();
        //mainFrame.MainFrame();                               // create new MainFrame(window of program)
    }
    public synchronized void stop() {
        running = false;                                    // method to stop that thread
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     *  Run method. which calls by method start(), that creates a new thread.
     *  While game is running, this method is updating the game status and rendering a new image with new properties
     */
    @Override
    public void run() {


        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanosecs = 1000000000.0 / 60.0;     // setting const for 60 updates per sec
        double delta = 0;
        int frames = 0;
        int updates  = 0;
        requestFocus();   // method for correct movement of the map
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanosecs; // equals +- 60
            lastTime = now;
            while (delta >= 1) {
                update();           // realize the "buffer strategy" - https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html !!!
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() -  timer > 1000) {      // if currentTime - timer >= 1 sec  (for update the number of frames)
                timer += 1000;
                frame.setTitle("2SH-game, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /**
     * Update method class, which updates keyboard events and actual status of the level
     */
    public void update() {
        key.update();
        level.update();
    }


    /**
     * Render method class, which deletes last frame and renders via buffer strategy actual frame.
     */
    public void render() {
        BufferStrategy bs = getBufferStrategy();        // render new frames and add them to the buffer
        if (bs == null) {
            createBufferStrategy(3);           // render 3 frames and save them to the memory
            return;
        }

        int xScroll = hero.getX() - workSpace.width/2;
        int yScroll = hero.getY() - workSpace.height/2;      // position of player - middle of the screen
        workSpace.clear();
        level.render(xScroll, yScroll, workSpace);
        //hero.render(workSpace);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = workSpace.pixels[i];
        }


        Graphics g = bs.getDrawGraphics();              // get graphics on that frame
        g.fillRect(0,0, getWidth(),getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();                                   // draw graphics on that frame
        bs.show();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}




