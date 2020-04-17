import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics2D;

public class Sun {
    private int sunX;
    private int sunY;
    private Image sunImage;
    private boolean taken;
    private int drown;

    
    public Sun(int x, int y) {
        sunX = x;
        sunY = y;
        sunImage = Toolkit.getDefaultToolkit().createImage("rsz_sun.png");
        taken = false;
        drown = 0;
    }

    public boolean isTaken() {
        return taken;
    }

    public void sunHasTaken() {
        taken = true;
    }

    public void draw(Graphics2D g) {
        g.drawImage(sunImage, sunX, sunY, null);
    }

    public void goDown() {
        sunY += 5;
    }

    public void goDrown() {
        drown++;
    }

    public boolean isDrown() {
        return (drown > 25);
    }

    public int getSunX() {
        return sunX;
    }

    public int getSunY() {
        return sunY;
    }

    public boolean timeToFade() {
        return (drown > 200);
    }

}