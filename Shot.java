import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Shot {
    private Image shotImage;
    private int shotX;
    private int shotY;
    private boolean dead;

    public Shot() {
        shotImage = Toolkit.getDefaultToolkit().createImage("Shot.png");
        shotX = 130;
        shotY = 400;
        dead = false;
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
}