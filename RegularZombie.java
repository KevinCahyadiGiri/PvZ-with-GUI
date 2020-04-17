import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class RegularZombie extends Zombie {

    public RegularZombie(int x, int y) {
        zombImage = Toolkit.getDefaultToolkit().createImage("rsz_zombie.png");
        zombieX = x;
        zombieY = y;
        dead = false;
        hp = 100;
        damage = 50;
    }

}