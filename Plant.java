import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public abstract class Plant implements Creature {
    protected int hp;
    protected boolean dead;
    protected Image plantImage;
    protected int posX;
    protected int posY;
    protected int counter;

    public boolean isDead() {
        return dead;
    }

    public int getHp() {
        return hp;
    }

    public void takeDamage(int damageTaken) {
        hp -= damageTaken;
    }

    // public void giveDamage();

    public void draw(Graphics2D g) {
        g.drawImage(plantImage, posX, posY, null);
    }

    public int plantGetX() {
        return posX;
    }

    public int plantGetY() {
        return posY;
    }

    public void plantDead() {
        dead = true;
    }

    public void counterPlus() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}