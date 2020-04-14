import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Zombie {
    private Image zombImage;
    private int zombieX;
    private int zombieY;
    private boolean dead;

    public Zombie() {
        zombImage = Toolkit.getDefaultToolkit().createImage("rsz_zombie2.png");
        zombieX = 900;
        zombieY = 360;
        dead = false;
    }

    public void draw(Graphics2D g) {
        g.drawImage(zombImage, zombieX, zombieY, null);
    }

    public void zombieWalk() {
        zombieX -= 5;
    }

    public boolean isDead() {
        return dead;
    }

    public int zombieGetX() {
        return zombieX;
    }

    public int zombieGetY() {
        return zombieY;
    }

    public void zombieDead() {
        dead = true;
    }
}