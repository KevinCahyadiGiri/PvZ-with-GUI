import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Mushroom extends Plant {
    private int shotDamage; 

    public Mushroom(int x, int y) {
        plantImage = Toolkit.getDefaultToolkit().createImage("Mushroom.png");
        posX = x;
        posY = y;
        dead = false;
        hp = 30;
        counter = 80;
    }

    public int giveDamage() {
        return shotDamage;
    }

}