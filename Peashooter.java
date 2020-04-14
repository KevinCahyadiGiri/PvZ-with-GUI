import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Peashooter extends Plant {
    private int shotDamage; 

    public Peashooter(int x, int y) {
        plantImage = Toolkit.getDefaultToolkit().createImage("Peashooter.png");
        posX = x;
        posY = y;
        dead = false;
        hp = 60;
        counter = 80;
    }

    public int giveDamage() {
        return shotDamage;
    }

}