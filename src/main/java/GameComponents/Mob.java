package GameComponents;

import UIcomponents.GameFrame;
import UIcomponents.WorkSpace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Subclass of entity for mobs
 */
public abstract class Mob extends Entity{

    protected MapComponent sprite;
    //protected int dir = -1;  // direction of the mob
    protected  boolean moving = false;
    Logger log = Logger.getLogger(Mob.class.getName());

    protected enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

    }

    protected Direction dir;

    //protected List<Projectile> projectiles = new ArrayList<>();

    /**
     * move() method, that changes coordinates of every mob on the map if there arent collisions
     * @param xabsolute - new coordinates of mob OX
     * @param yabsolute - new coordinates of mob OY
     */
    public void move(int xabsolute, int yabsolute) {
        if (xabsolute != 0 && yabsolute != 0) {
            move(xabsolute,0);
            move(0,yabsolute);
            return;
        }
        if (xabsolute > 0) dir = Direction.RIGHT;
        if (xabsolute < 0) dir = Direction.LEFT;

        //if (yabsolute > 0) dir = 2;
        //if (yabsolute < 0) dir = 0;

        // x = 1, 0, 1
        if (!collision(xabsolute, yabsolute)) {   // colliders
            if ((x+xabsolute)/16 == 60 && (y+yabsolute)/16 ==  60 && WizardProjectile.exit == true && Hero.buff1 == true && Hero.buff2 == true) {
                GameFrame.running = false;
                //System.out.println((x+xabsolute)/16 +" " + (y+yabsolute)/16);
                log.info("X: " + (x+xabsolute)/16 + " Y: " + (y+yabsolute)/16 + " Exited spawn level!");
                JOptionPane optionPane = new JOptionPane("End!");
                JOptionPane.showMessageDialog(optionPane, "You exited the level! Congratulation");
                JButton ok = new JButton("Ok");
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        optionPane.setVisible(false);
                        System.exit(0);
                    }
                });

            }
            x += xabsolute;
            y += yabsolute;


        }

    }

    public abstract void update();

    /**
     * shoot() method for creating and shooting fireballs
     * @param x - coordinate of shoot OX
     * @param y - coordinate of shoot OY
     * @param dir - direction
     */
    protected void shoot(int x, int y, double dir) {
        //dir *= 180/Math.PI;
       Projectile projectile = new WizardProjectile(x, y, dir);
       //projectiles.add(projectile);
       level.add(projectile);

    }

    /**
     * collision() method checks for collision
     * @param xabsolute - new possible coordinates of mob OX
     * @param yabsolute - new  possible coordinates of mob OY
     * @return solid
     */

    private boolean collision(int xabsolute, int yabsolute) {
        boolean solid = false;

        //System.out.println(((x+xabsolute)/16) + " " +  ((y+yabsolute)/16));

        if (level.getTile((x+xabsolute)/16,  (y+yabsolute)/16).solid()) {       // cheking if new position has the colliders
            solid = true;
        }
        return solid;
    }

    public abstract void render(WorkSpace screen);


}
