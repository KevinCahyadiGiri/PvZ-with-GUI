// Index Injector
import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Object;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class IndexInject implements Runnable {
    private volatile int value;
    private boolean stop = false;

    public IndexInject(int a){
        this.value = a ;
    }

    @Override
    public void run() {
        int random;
        while (!stop) {
            random = ((int) (Math.random() * (10)));
            setValue(random);
            try {
                Thread.sleep(1000);
                System.out.println(this.value);
            } catch (Exception e) {
                System.out.println("Zombie habis");
            }
        }
    }

    public void setValue(int value) {
        this.value = value;
    }

    // public void paint2D(Graphics g){
    //     if (!zombieList.get(take).isDead()) {
    //         zombieList.get(take).draw((Graphics2D) g);
    // }

    public int getValue() {
        return this.value;
    }
}