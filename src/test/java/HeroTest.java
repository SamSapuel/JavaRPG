import GameComponents.Projectile;
import GameComponents.WizardProjectile;
import Level.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HeroTest {

    @Test
    public void clearTest() {
        int x1 = 20;
        int y1 = 20;
        double newX = (x1 + Math.cos(x1));
        double newX2 = (x1 + Math.cos(x1));
        double newY = (y1 + Math.sin(y1));
        double newY2 = (y1 + Math.sin(y1));
        int speed = 3;
        for (int i = 0; i < 4; i++) {
            newX *= 3;
            newY *= 3;
        }
        for (int i = 0; i < 5; i++) {
            newX2 *= 3;
            newY2 *= 3;
        }
        Projectile p = new WizardProjectile((int)newX,(int)newY, 1);
        Projectile p2 = new WizardProjectile((int)newX2,(int)newY2, 1);
        List<Projectile> projectiles = new ArrayList<>();
        projectiles.add(p);
        projectiles.add(p2);

        // Imagine that we have a wall on  coordinates (1653,1693). It means we need to remove ans stop render a fireball
        if ((int)newX == 1653 && (int)newY == 1693) p.remove();
        if ((int)newX2 == 1653 && (int)newY2 == 1693) p2.remove();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            if (projectile.isRemoved()) {
                projectiles.remove(i);
            }
        }
        int res = projectiles.size();

        Assertions.assertEquals(1, res);
    }
}
