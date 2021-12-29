package GameComponents;

import UIcomponents.GameFrame;
import UIcomponents.MainMenu;
import UIcomponents.WorkSpace;
import sun.applet.Main;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Main hero class
 */

public class Hero extends Mob{
    private KeyboardInputs input;
    private MapComponent sprite;
    public boolean walking;
    private int targetX;
    private int targetY;
    protected double damage; //, hp;
    protected static double hp = 100;
    boolean exit;
    public static boolean buff1;
    public static boolean buff2;
    Logger log = Logger.getLogger(Hero.class.getName());


    private int fireRate = 0;

    public Hero(KeyboardInputs input) {
        this.input = input;
        sprite = MapComponent.hero_right;
    }
    public Hero(int x, int y, KeyboardInputs input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = MapComponent.hero_right;
        fireRate = WizardProjectile.FIRE_RATE;
        exit = WizardProjectile.exit;
    }

    /**
     * update() method of a player. First of all it checks your life status, if anyone in the range,
     * then calls method move(), which checks if player can move or not, then system will render the player on current coordinates.
     * Also this method updates fireballs status and can call shoot() method for fireball
     */
    public void update() {
        List<Entity> es = level.getEntities(this, 20);
        for ( Entity e : es) {
            System.out.println(e);
            //e.remove();
            //System.exit(0);
            GameFrame.running = false;
            JOptionPane optionPane = new JOptionPane("Game over");
            JOptionPane.showMessageDialog(optionPane, "You were killed by Skeleton King! Try again");
            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                    optionPane.setVisible(false);

                }
            });
        }
        if (fireRate > 0) fireRate--;
        int xabsolute = 0;
        int yabsolute = 0;
        if (input.up) yabsolute--;
        if (input.down) yabsolute++;
        if (input.left) xabsolute--;
        if (input.right) xabsolute++;

        if (xabsolute != 0 || yabsolute != 0){
            if ((x+xabsolute)/16 == 48 && (y+yabsolute)/16 == 3) {
                if (buff1 == false) {
                    music();
                }
                buff1 = true;
                log.info("X: " + (x+xabsolute)/16 + " Y: " + (y+yabsolute)/16 + " ,buff is " + buff1 + " You buffed by shrine#1");

            }
            if ((x+xabsolute)/16 == 3 &&(y+yabsolute)/16 == 3) {
                if (buff2 == false) {
                    music();
                }
                buff2 = true;
                log.info("X: " + (x+xabsolute)/16 + " Y: " + (y+yabsolute)/16 + " ,buff is " + buff2 + " You buffed by shrine#2");
            }

            move(xabsolute, yabsolute);

            walking = true;
        } else {
            walking = false;
        }
        clear();
        updateShooting();
    }

    /**
     * clear() method, that removes from render order fireball with status removed
     */
    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile projectile = level.getProjectiles().get(i);
            if (projectile.isRemoved()) {
                level.getProjectiles().remove(i);
            }
        }
    }

    public void updateShooting() {
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - GameFrame.getWindowWidth()/2;        // difference between new point and old point(GameFrame.getWindowWidth()/2)((position of the player))
            double dy = Mouse.getY() - GameFrame.getWindowHeight()/2;       // difference between new point and old point(GameFrame.getWindowHeight()/2)((position of the player))
            double dir = Math.atan2(dy, dx);                                // angle of direction

            shoot(x,y,dir);
            fireRate = WizardProjectile.FIRE_RATE;                          // mechanics that allows to not to draw every frame a fireball

        }
    }


    public void render(WorkSpace screen) {                      // render hero sprites(4 parts)
        if (dir == Direction.RIGHT) sprite = MapComponent.hero_right;
        if (dir == Direction.LEFT) sprite = MapComponent.hero_left;

        screen.renderMob(x-16, y-16, sprite);   // -16 because its half of the player size


    }

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("res/buff.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);

            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }

    public static double getHp() {
        return hp;
    }

    /*
    static class Inventory {
        private int capacity = 0;
        private ArrayList<Item> inventory;

        public int setCapacity(int value) {
            this.capacity = value;
            return this.capacity;
        }

        public boolean addItem(Item item) {
            try {
                this.inventory.add(item);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        public boolean removeItem(Item item) {
            try {
                this.inventory.remove(item);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
         Isnt working
        public boolean isFull() {
            if () {
                // check if list is full
                return true;
            } else return false;
        }

        public boolean useItem(Item item) {
            try {
                item.action(null, null);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        protected Inventory(int capacity) {
            this.inventory = new ArrayList<>(setCapacity(capacity));
        }
    }

    public void move(int x, int y) {
        // hero moving mechanics
    }

        public class Hero implements Character {
            private String name = null;
            private String team = null;
            private HashMap<String, Integer> stats = new HashMap<>(5);
            private int health = 0;

            @Override
            public void setName(String name) {
                this.name = name;
            }

            @Override
            @Deprecated
            public void setTeam(String team) {
                this.team = team;
            }

            @Override
            public void setStats(String statName, int value) {
                this.stats.put(statName, value);
            }

            @Override
            public void setInitialHealth(int initHealth) {
                this.health = initHealth;
            }
        }
    }*/

}
