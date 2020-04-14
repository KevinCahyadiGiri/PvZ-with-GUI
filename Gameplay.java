import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.List;
import java.util.ArrayList;



public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private Timer timer;
    private Timer timerShot;
    private int delay = 64;
    private Image backImage;
    // private Zombie zombie;
    private Point mouse;
    // private Shot shot;
    private List<Zombie> zombieList;
    private List<Shot> shotList;
    private List<Peashooter> peaList;
    // private int count;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        backImage = Toolkit.getDefaultToolkit().createImage("background2.png");
        mouse = new Point();
        // zombie = new Zombie();
        // shot = new Shot();
        zombieList = new ArrayList<Zombie>();
        shotList = new ArrayList<Shot>();
        peaList = new ArrayList<Peashooter>();
        zombieList.add(new Zombie());
        shotList.add(new Shot(617,450));
        shotList.add(new Shot(800,450));
        peaList.add(new Peashooter(600,325));
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouse = e.getPoint();
            }
        });
        // count = 0;
        timer.start();
    }

    public void paint(Graphics g) {
        // background
        g.drawImage(backImage, 0, 0, null);

        // zombie
        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                if (!zombieList.get(i).isDead()) {
                    zombieList.get(i).draw((Graphics2D) g);
                }
            }
        }
        
        // shot
        if (shotList.size() > 0) {
            for (int i = 0; i < shotList.size() ; i++) {
                if (!shotList.get(i).isDead()) {
                    shotList.get(i).draw((Graphics2D) g);
                }
            }
        }

        // peashooter
        if (peaList.size() > 0) {
            for (int i = 0; i < peaList.size(); i++) {
                if (!peaList.get(i).isDead()) {
                    peaList.get(i).draw((Graphics2D) g);
                }
            }
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("mouse berada pada (" + mouse.x + "," + mouse.y + ")", 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}

    @Override 
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size() ; i++) {
                if (!zombieList.get(i).isDead()) {
                    zombieList.get(i).zombieWalk();
                }
            }
        }

        if (shotList.size() > 0) {
            for (int i = 0; i < shotList.size() ; i++) {
                if (!shotList.get(i).isDead()) {
                    shotList.get(i).shotGo();
                }
            }
        }

        // if (!zombie.isDead()) {
        //     zombie.zombieWalk();
        // }
        // if (!shot.isDead()) {
        //     shot.shotGo();
        // }
        // if(new Rectangle(zombie.zombieGetX(), zombie.zombieGetY(),95,45).intersects(new Rectangle(shot.shotGetX(), shot.shotGetY(), 30,30))) {
        //     zombie.zombieDead();
        //     shot.shotDead();
        // }

        // jika zombie terkena tembakan
        if (zombieList.size() > 0 && shotList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                for (int j = 0; j < shotList.size(); j++) {
                    // System.out.println("zombie" + i);
                    // System.out.println("shot " + j);
                    if (new Rectangle(zombieList.get(i).zombieGetX(), zombieList.get(i).zombieGetY(),95,45).intersects(new Rectangle(shotList.get(j).shotGetX(), shotList.get(j).shotGetY(),30,30))) {
                        zombieList.get(i).zombieDamage(shotList.get(j).shotDamage());
                        shotList.get(j).shotDead();
                        shotList.remove(j);
                        if (zombieList.get(i).zombiehp() <= 0) {
                            zombieList.get(i).zombieDead();
                            zombieList.remove(i);
                            break;
                        }
                    }
                }
            }
        }

        // generate shot
        if (peaList.size() > 0) {
            for (int i = 0; i < peaList.size(); i++) {
                if (!peaList.get(i).isDead()) {
                    peaList.get(i).counterPlus();
                    if (peaList.get(i).getCounter() % 100 == 0) {
                        shotList.add(new Shot(peaList.get(i).peaGetX()+50, peaList.get(i).peaGetY()-5));
                    }
                }
            }
        }

        // generate shot
        // count++;
        // if (count % 100 == 0) {
        //     shotList.add(new Shot(617,450));
        // }

        repaint();
    }
}