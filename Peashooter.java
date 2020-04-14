import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Peashooter {
    private Image peaImage;
    private int peaX;
    private int peaY;
    private boolean dead;
    private int hp;
    private int shotDamage; // apa nanti ganti jadi Shot aja sekalian
    private int counter;

    public Peashooter(int x, int y) {
        peaImage = Toolkit.getDefaultToolkit().createImage("Peashooter.png");
        peaX = x;
        peaY = y;
        dead = false;
        hp = 60;
        counter = 80;
    }

    public void draw(Graphics2D g) {
        g.drawImage(peaImage, peaX, peaY, null);
    }

    public boolean isDead() {
        return dead;
    }

    public int peaGetX() {
        return peaX;
    }

    public int peaGetY() {
        return peaY;
    }

    public void peaDead() {
        dead = true;
    }

    public void peaTakeDamage(int damageTaken) {
        hp -= damageTaken;
    }

    public int peaGiveDamage() {
        return shotDamage;
    }

    public void counterPlus() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}