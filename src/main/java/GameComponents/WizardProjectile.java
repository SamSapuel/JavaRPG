package GameComponents;

import UIcomponents.WorkSpace;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Fireball class
 */
public class WizardProjectile extends Projectile{

    public static final int FIRE_RATE = 10;
    public static boolean exit = false;
    Logger log = Logger.getLogger(WizardProjectile.class.getName());

    /**
     * WizardProjectile() constructor for creating fireball and fixation his origin coordinates, direction, new coordinates
     * @param x - xOrigin
     * @param y - yOrigin
     * @param dir - direction
     */
    public WizardProjectile(int x, int y, double dir) {
        super(x, y, dir);
        range = 100;
        damage = 20;
        speed = 3;
        sprite = MapComponent.projectile_wizard;
        newx = speed * Math.cos(angle);
        newy = speed * Math.sin(angle);
    }

    public void update() {
        move();
    }

    protected void move() {
        if (!level.tileCollision(x, y, newx, newy, 64)) {

            x += newx;
            y += newy;
            if ((int)(x+newx)/16 == 60 || (x+newy)/16 == 60) {
                if (exit == false) {
                    music();
                }
                exit = true;
                log.info("exit damaged by fireball " + exit);
                //System.out.println(exit);
            }
        } else {
            remove();
        }

        if (distance() > range) remove();
    }

    private double distance() {
        double distance = 0;
        distance = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y)));   // new point - old point and after that define the distance between them
        return distance;
    }

    public void render(WorkSpace screen) {
        screen.renderProjectile((int)x, (int)y, this);
    }


    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("res/door.wav");
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
}
