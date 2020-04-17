import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Zomboss extends Zombie {

    public Zomboss(int x, int y) {
        zombImage = Toolkit.getDefaultToolkit().createImage("Zomboss.png");
        zombieX = x;
        zombieY = y;
        dead = false;
        hp = 200;
        damage = 30;
    }

}