import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public abstract class Zombie implements Creature, Cloneable {
    protected int hp;
    protected boolean dead;
    protected Image zombImage;
    protected int zombieX;
    protected int zombieY;
    protected int attCounter = 30;
    protected int modAttCounter = 45;
    protected int damage;

    public boolean isDead() {
        return dead;
    }

    public int giveDamage() {
        return damage;
    }

    public void draw(Graphics2D g) {
        g.drawImage(zombImage, zombieX, zombieY, null);
    }

    public void zombieWalk() {
        zombieX -= 5;
    }

    public void zombieHold() {
        zombieX += 5;
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

    public void takeDamage(int damageTaken) {
        hp -= damageTaken;
    }

    public int getHp() {
        return hp;
    }

    public void incAttCounter() {
        attCounter++;
    }

    public int getAttCounter() {
        return attCounter;
    }

    public int getModAttCounter() {
        return modAttCounter;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}