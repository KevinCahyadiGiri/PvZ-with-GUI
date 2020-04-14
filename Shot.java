import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public abstract class Shot implements Cloneable {
    protected Image shotImage;
    protected int shotX;
    protected int shotY;
    protected boolean dead;
    protected int damage;

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

    public void shotAlive() {
        dead = false;
    }

    public int shotDamage() {
        return damage;
    }

    public void setPos(int x, int y) {
        shotX = x;
        shotY = y;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}