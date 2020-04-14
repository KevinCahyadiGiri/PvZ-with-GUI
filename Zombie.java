import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Zombie {
    private Image zombImage;
    private int zombieX;
    private int zombieY;
    private boolean dead;
    private int hp;

    public Zombie() {
        zombImage = Toolkit.getDefaultToolkit().createImage("rsz_zombie.png");
        zombieX = 1060;
        zombieY = 410;
        dead = false;
        hp = 100;
    }

    public void draw(Graphics2D g) {
        g.drawImage(zombImage, zombieX, zombieY, null);
    }

    public void zombieWalk() {
        zombieX -= 1;
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

    public void zombieDamage(int damageTaken) {
        hp -= damageTaken;
    }

    public int zombiehp() {
        return hp;
    }
}