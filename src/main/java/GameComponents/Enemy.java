package GameComponents;

import Level.Level;
import UIcomponents.WorkSpace;

public class Enemy extends Mob {

    private static double damage = 20;
    private int time = 0;
    private int xabsolute = 1;
    private int yabsolute = 0;
    private boolean fighting;


    public void setDamage(int value) {
        this.damage = value;
    }

    public Enemy(int x, int y) {
        this.x = x*16;
        this.y = y*16;
        sprite = MapComponent.enemy_right;
    }




    public void update() {
        // realize basic AI

        time++;    // 60 per second

        if (time % (random.nextInt(50) + 30) == 0) {
            xabsolute = random.nextInt(3) - 1;
            yabsolute = random.nextInt(3) - 1;
            if (random.nextInt(4) == 0) {
                xabsolute = 0;
                yabsolute = 0;
            }
        }



        if (xabsolute > 0 ) {
            dir = Direction.RIGHT;
            sprite = MapComponent.enemy_right;
        }
        if (xabsolute < 0) {
            dir = Direction.LEFT;
            sprite = MapComponent.enemy_left;
        }


        boolean walking;
        if (xabsolute != 0 || yabsolute != 0){
            move(xabsolute, yabsolute);
            walking = true;
        } else {
            walking = false;
        }
    }

    public static double getDamage() {
        return damage;
    }


    public void render(WorkSpace screen) {
        //screen.renderMob(x, y, sprite);

        screen.renderMob(x-16, y-16, sprite);
    }


}
