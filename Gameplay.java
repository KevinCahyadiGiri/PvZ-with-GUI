import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.Timer;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.FontUIResource;

//import org.graalvm.compiler.hotspot.replacements.NewObjectSnippets.Templates;
//import sun.awt.AWTAccessor.MouseEventAccessor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.util.Random;



public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseInputListener {
    private boolean play = false;
    private Timer timer;
    private Timer timerShot;
    private int delay = 64;
    private Image backImage;
    private Image backGameOverImage;
    private Image cardplant1;
    private Image cardplant2;
    private Image cardplant1bw;
    private Image cardplant2bw;
    // private Zombie zombie;
    private Point mouse;
    // private Shot shot;
    List<Zombie> zombieList;
    List<Shot> shotList;
    List<Sun> sunList;
    List<Zombie> zombieSelection;
    int counterGenerateZombie;
    int modGenerateZombie;
    private List<Plant> plantList;
    private ListMap<String, Shot> plantToShot;
    private int sunCounter;
    Random rand;
    private int sunfPoint;
    public static int tempClick; //1.peashooter 2. mushroom
    public static boolean clickedOnce=false;
    
    private Graphics g;
    private MapGenerator map;
    IndexInject inject;
    Thread t;

    private String gameStatus;
    public boolean isGameOver;
    public int zombiesKilled = 0;
    private boolean endsoundplayed;

    public Gameplay() {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        backImage = Toolkit.getDefaultToolkit().createImage("background.jpg");
        backGameOverImage = Toolkit.getDefaultToolkit().createImage("gameoverback.png");
        cardplant1 = Toolkit.getDefaultToolkit().createImage("peashooterCard.png");
        cardplant2 = Toolkit.getDefaultToolkit().createImage("fungusCard.png");
        cardplant1bw = Toolkit.getDefaultToolkit().createImage("peashooterCardbw.png");
        cardplant2bw = Toolkit.getDefaultToolkit().createImage("fungusCardbw.png");
        mouse = new Point();
        zombieList = new ArrayList<Zombie>();
        shotList = new ArrayList<Shot>();
        plantList = new ArrayList<Plant>();
        sunList = new ArrayList<Sun>();
        zombieSelection = new ArrayList<Zombie>();
        counterGenerateZombie = 0;
        modGenerateZombie = 120;
        map= new MapGenerator(9,5);

        // peaList = new ArrayList<Peashooter>();
        zombieSelection.add(new RegularZombie(900,140));
        zombieSelection.add(new RegularZombie(900,250));
        zombieSelection.add(new Zomboss(900,140));
        zombieSelection.add(new Zomboss(900,250));
        zombieSelection.add(new Zomboss(900,360));
        zombieSelection.add(new RegularZombie(900,360));
        zombieSelection.add(new RegularZombie(900,490));
        zombieSelection.add(new RegularZombie(900,600));
        zombieSelection.add(new Zomboss(900,490));
        zombieSelection.add(new Zomboss(900,600));
        plantToShot = new ListMap<String, Shot>();
        plantToShot.add("Peashooter", new PeaShot());
        plantToShot.add("Mushroom", new MushroomShot());
        sunCounter = 30;
        rand = new Random();
        sunfPoint = 200;
        
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouse = e.getPoint();
            }
        });
        // count = 0;
        gameStatus = "playing";
        isGameOver = false;
        endsoundplayed = false;
        timer.start();
        inject = new IndexInject(10);
        t = new Thread(inject);
        t.start();
    }

    // public void plantCardFree(int PlantType){
    //     if (PlantType==1){
    //         g.drawImage(cardplant1, 120, 16, null);
    //     }else if (PlantType==2){
    //         g.drawImage(cardplant2, 200, 16, null);
    //     }
    // }

    // public void plantCardLock(int PlantType){
    //     if (PlantType==1){
    //         g.drawImage(cardplant1bw, 120, 16, null);
    //     }else if (PlantType==2){
    //         g.drawImage(cardplant2bw, 200, 16, null);
    //     }
    // }

    public void paint(Graphics g) {
        // background
        g.drawImage(backImage, 0, 0, null);

        // kartu
        if (clickedOnce){
            if (tempClick==1){
                g.drawImage(cardplant1bw, 120, 16, null);
                g.drawImage(cardplant2, 200, 16, null);
            }else if (tempClick==2){
                g.drawImage(cardplant1, 120, 16, null);
                g.drawImage(cardplant2bw, 200, 16, null);
            }
        } else {
            g.drawImage(cardplant1, 120, 16, null);
            g.drawImage(cardplant2, 200, 16, null);
        }

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

        // plant
        if (plantList.size() > 0) {
            for (int i = 0; i < plantList.size(); i++) {
                if (!plantList.get(i).isDead()) {
                    plantList.get(i).draw((Graphics2D) g);
                }
            }
        }

        // sun
        if (sunList.size() > 0) {
            for (int i = 0; i < sunList.size(); i++) {
                if (!sunList.get(i).isTaken()) {
                    sunList.get(i).draw((Graphics2D) g);
                }
            }
        }

        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g3d = (Graphics2D) g;
        // game over
        
        if (gameStatus.equals("gameOver")) {
            // g2d.drawString("game over", 800, 20);
            // g.setColor(Color.BLACK);
            // g.fillRect(0,0,997,808);
            g.drawImage(backGameOverImage, 0, 0, null);
            Font  fgameover  = new Font(Font.SANS_SERIF,  Font.BOLD, 25);
            g2d.setFont(fgameover);
            g2d.drawString("Zombies killed: "+String.valueOf(zombiesKilled), 35, 280);
            if (!endsoundplayed){
                Sound.endmusic.loop();
                endsoundplayed=true;
            }
        }

        g2d.drawString("mouse berada pada (" + mouse.x + "," + mouse.y + ")", 800, 10);
        Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 25);
        g3d.setFont(f3);
        g3d.drawString(String.valueOf(sunfPoint), 35, 110);
        g.dispose();
    }

    public int translatex(int x){       //translasi posisi x mouse untuk menentukan user klik di kolom berapa
        if (x>100 && x<190){            //hanya dipakai untuk menanam tanaman
            return 0;
        }else if (x>190 && x<270){
            return 1;
        }else if (x>270 && x<370){
            return 2;
        }else if (x>370 && x<470){
            return 3;
        }else if (x>470 && x<570){
            return 4;
        }else if (x>570 && x<660){
            return 5;
        }else if (x>660 && x<750){
            return 6;
        }else if (x>750 && x<850){
            return 7;
        }else if (x>850 && x<940){
            return 8;
        }else{
            return x;
        }
    }

    public int translatey(int y){       //translasi posisi y mouse untuk menentukan user klik di baris berapa
        if (y>160 && y<270){            //hanya dipakai untuk menanam tanaman
            return 0;
        }else if (y>270 && y<380){
            return 1;
        }else if (y>380 && y<500){
            return 2;
        }else if (y>500 && y<610){
            return 3;
        }else if (y>610 && y<720){
            return 4;
        }else{
            return y;
        }
    }

    public int translatePlantX(int x){  //translasi posisi baris dan kolom menjadi lokasi penanaman
        if (x==0){
            return 95;
        }else if (x==1){
            return 190;
        }else if (x==2){
            return 280;
        }else if (x==3){
            return 380;
        }else if (x==4){
            return 470;
        }else if (x==5){
            return 560;
        }else if (x==6){
            return 660;
        }else if (x==7){
            return 750;
        }else if (x==8){
            return 850;
        }else{
            return -1;
        }
    }

    public int translatePlantY(int y){
        if (y==0){
            return 170;
        }else if (y==1){
            return 280; 
        }else if (y==2){
            return 390;
        }else if (y==3){
            return 510;
        }else if (y==4){
            return 630;
        }else{
            return -1;
        }
    }

    public void put(int plant, int mousex, int mousey){
        //translasi x dan y dari mouse position jadi square
        int x = translatex(mousex);
        int y = translatey(mousey);
        
        if (map.map[x][y]==0){
            //buat Plant P di posisi x dan y
            if(plant == 1){
                plantList.add(new Peashooter(translatePlantX(x),translatePlantY(y)));
                sunfPoint-=100;
                //bikin square jadi terisi
                // int tempx = translatePlantX(x);
                // int tempy = translatePlantY(y);
                // System.out.println(tempx +" , "+ tempy);
                map.putPlant(x, y);
                clickedOnce=false;
            }else{ //plant==2
                plantList.add(new Mushroom(translatePlantX(x),translatePlantY(y)));
                sunfPoint-=50;
                //bikin square jadi terisi
                // int tempx = translatePlantX(x);
                // int tempy = translatePlantY(y);
                // System.out.println(tempx +" , "+ tempy);
                map.putPlant(x, y);
                clickedOnce=false;
            }
        }else {
            System.out.println("Di sana udah ada tumbuhan, cari tempat lain");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){} 
    @Override
    public void mouseReleased(MouseEvent e){} 
    @Override
    public void mouseDragged(MouseEvent e){} 
    @Override
    public void mouseMoved(MouseEvent e){}

    
    public void mouseClicked(MouseEvent e){
        if(!clickedOnce){
            if(mouse.x>120 && mouse.x<184 && mouse.y>16 && mouse.y<110){    //menekan plant1
                //System.out.println("PLANT1");
                if (sunfPoint>=100){
                    clickedOnce=true;
                    tempClick = 1; 
                }
            }else if (mouse.x>200 && mouse.x<264 && mouse.y>16 && mouse.y<110){  //memilih plant2
                //System.out.println("PLANT2");
                if (sunfPoint>=50){
                    clickedOnce=true;
                    tempClick = 2;
                }
            } else { // dia ngeklik area Field, nanti diganti aja kalo elsenya bikin repot
                if (sunList.size() > 0) {
                    for (int i = sunList.size()-1; i >= 0; i--) {
                        if (mouse.x>=sunList.get(i).getSunX() && mouse.x<=sunList.get(i).getSunX()+90 && mouse.y>=sunList.get(i).getSunY() && mouse.y<=sunList.get(i).getSunY()+90) {
                            sunList.get(i).sunHasTaken();
                            sunList.remove(sunList.get(i));
                            sunfPoint += 25;
                            break;
                        }
                    }
                }
            } 
        } else { //clickedOnce = true (user sudah memilih tanaman, sedang memilih mau ditanam di mana)
            if(mouse.x>100 && mouse.x<940 && mouse.y>160 && mouse.y<720){  //kliknya di dalam field
                put(tempClick,mouse.x,mouse.y);
            }else{      //kliknya di luar field == cancel
                if (tempClick==1){
                    clickedOnce = false;
                }else if (tempClick==2){
                    clickedOnce= false;
                }
            } 
        }
    }

    @Override 
    public void actionPerformed(ActionEvent e) {
        timer.start();

        // memajukan zombie
        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size() ; i++) {
                if (!zombieList.get(i).isDead()) {
                    zombieList.get(i).zombieWalk();
                }
            }
        }

        // memajukan tembakan
        if (shotList.size() > 0) {
            for (int i = 0; i < shotList.size() ; i++) {
                if (!shotList.get(i).isDead()) {
                    shotList.get(i).shotGo();
                }
            }
        }

        // menggerakkan dan menghilangkan sun
        if (sunList.size() > 0) {
            boolean adaTaken = false;
            for (int i = 0; i < sunList.size(); i++) {
                sunList.get(i).goDrown();
                if (!sunList.get(i).isTaken() && !sunList.get(i).isDrown()) {
                    sunList.get(i).goDown();
                } else if (sunList.get(i).timeToFade()) {
                    sunList.remove(i);
                    // i--;
                }
            }
        }

        // jika zombie terkena tembakan
        if (zombieList.size() > 0 && shotList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                for (int j = 0; j < shotList.size(); j++) {
                    // System.out.println("zombie" + i);
                    // System.out.println("shot " + j);
                    if (new Rectangle(zombieList.get(i).zombieGetX(), zombieList.get(i).zombieGetY(),95,45).intersects(new Rectangle(shotList.get(j).shotGetX(), shotList.get(j).shotGetY(),30,30))) {
                        zombieList.get(i).takeDamage(shotList.get(j).shotDamage());
                        shotList.get(j).shotDead();
                        shotList.remove(j);
                        if (zombieList.get(i).getHp() <= 0) {
                            zombieList.get(i).zombieDead();
                            zombieList.remove(i);
                            if(!isGameOver){
                                zombiesKilled++;
                            }
                            break;
                        }
                    }
                }
            }
        }

        // zombie terkena tumbuhan
        if (zombieList.size() > 0 && plantList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                for (int j = 0; j < plantList.size(); j++) {
                    if (new Rectangle(zombieList.get(i).zombieGetX(), zombieList.get(i).zombieGetY(),95,45).intersects(new Rectangle(plantList.get(j).plantGetX(), plantList.get(j).plantGetY(),30,30))) {
                        zombieList.get(i).zombieHold();
                        zombieList.get(i).incAttCounter();
                        if (zombieList.get(i).getAttCounter() % zombieList.get(i).getModAttCounter() == 0) {
                            plantList.get(j).takeDamage(zombieList.get(i).giveDamage());
                            if (plantList.get(j).getHp() <= 0) {
                                plantList.get(j).plantDead();
                                plantList.remove(j);
                                break;
                            }
                        }
                    }
                }
            }
        }

        // generate shot
        if (plantList.size() > 0) {
            for (int i = 0; i < plantList.size(); i++) {
                if (!plantList.get(i).isDead()) {
                    plantList.get(i).counterPlus();
                    if (plantList.get(i).getCounter() % 25 == 0) {
                        try {
                            String plantName = plantList.get(i).getClass().getName();
                            Shot newShot = (Shot) plantToShot.get(plantName).clone();
                            newShot.setPos(plantList.get(i).plantGetX()+50, plantList.get(i).plantGetY()-5);
                            shotList.add(newShot);
                        }
                        catch (CloneNotSupportedException err) {
                            String plantName = plantList.get(i).getClass().getName();
                            Shot newShot = plantToShot.get(plantName);
                            newShot.shotAlive();
                            newShot.setPos(plantList.get(i).plantGetX()+50, plantList.get(i).plantGetY()-5);
                            shotList.add(newShot);
                        }
                    }
                }
            }
        }

        // generate Sun
        sunCounter++;
        if (sunCounter % 80 == 0) {
            int posX = rand.nextInt(675) + 125;
            int posY = rand.nextInt(310) + 190;
            sunList.add(new Sun(posX,posY));
        }

        // generate zombie
        int take = inject.getValue();
        take = take % zombieSelection.size();
        try {
            counterGenerateZombie++;
            if (counterGenerateZombie % modGenerateZombie == 0) {
                if (modGenerateZombie > 2) {
                    modGenerateZombie--;
                }
                Zombie newZombie = (Zombie) zombieSelection.get(take).clone();
                zombieList.add(newZombie);
                System.out.println("take" + take);
                java.lang.Thread.sleep(100);
            }
        } catch (Exception ex) {
            System.out.println("Zombie habis");
        }

        // cek apakah zombie sudah sampe ujung
        if (zombieList.size() > 0) {
            for (int i = 0; i < zombieList.size(); i++) {
                if (zombieList.get(i).zombieGetX() < 90) {
                    gameStatus = "gameOver";
                    isGameOver = true;
                    Sound.bgmusic.stop();
                }
            }
        }

        repaint();
    }

    
}