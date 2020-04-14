import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class RegularZombie extends Zombie {

    public RegularZombie() {
        zombImage = Toolkit.getDefaultToolkit().createImage("rsz_zombie2.png");
        zombieX = 900;
        zombieY = 360;
        dead = false;
        hp = 100;
    }

}