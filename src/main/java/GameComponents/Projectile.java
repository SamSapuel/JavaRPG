package GameComponents;

public class Projectile extends Entity{

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected MapComponent sprite;
    protected double x, y;
    protected double newx, newy;
    protected double distance;
    protected double speed, range, damage;


    public Projectile(int x, int y, double dir) {
        this.x = x;
        this.y = y;
        xOrigin = x;
        yOrigin = y;
        angle = dir;
    }

    public MapComponent getSprite() {
        return sprite;
    }

    protected void move() {

    }
}
