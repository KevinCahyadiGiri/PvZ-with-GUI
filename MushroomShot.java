import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class MushroomShot extends Shot{

    public MushroomShot() {
        shotImage = Toolkit.getDefaultToolkit().createImage("Mushroom_Shot.png");
        shotX = 0;
        shotY = 0;
        dead = false;
        damage = 25;
    }

    public MushroomShot(int x, int y) {
        shotImage = Toolkit.getDefaultToolkit().createImage("Mushroom_Shot.png");
        shotX = x;
        shotY = y;
        dead = false;
        damage = 25;
    }
}