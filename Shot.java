import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Shot {
    private Image shotImage;
    private int shotX;
    private int shotY;
    private boolean dead;
    private int damage;

    public Shot(int x, int y) {
        shotImage = Toolkit.getDefaultToolkit().createImage("Shot.png");
        shotX = x;
        shotY = y;
        dead = false;
        damage = 50;
    }

    public void draw(Graphics2D g) {
        g.drawImage(shotImage, shotX, shotY, null);
    }

    public void shotGo() {
        shotX += 10;
    }

    public boolean isDead() {
        return dead;
    }

    public int shotGetX() {
        return shotX;
    }

    public int shotGetY() {
        return shotY;
    }

    public void shotDead() {
        dead = true;
    }

    public int shotDamage() {
        return damage;
    }
}