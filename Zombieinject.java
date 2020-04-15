import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Object;
import javax.swing.JPanel;
import java.awt.Rectangle;


class Zombieinject extends JPanel implements Runnable {
    Gameplay gameplay;
    Graphics g;

    


  Zombieinject(Gameplay gameplay){
    this.gameplay = gameplay;
  }

  /*
    Bot akan menebak angka berkali-kali sampai game dinyatakan over
    dengan delay tiap tebakan 500 ms

    Setiap tebakan akan dibandingkan dengan nilai pada GameMaster
    Jika tebakan benar, maka tuliskan

    "Bot wins"

    Hint: Gunakan Thread.sleep(duration) untuk pause jalannya thread
    selama nilai duration 
  */
  public void run() {

    if (gameplay.zombieList.size() > 0) {
      //inject zombie
      for (int i = 0; i < gameplay.zombieList.size(); i++) {
          if (!gameplay.zombieList.get(i).isDead()) {
            gameplay.zombieList.get(i).draw((Graphics2D) g);
          }
          //zombie berjalan
          if (!gameplay.zombieList.get(i).isDead()) {
            gameplay.zombieList.get(i).zombieWalk();
          }
          try {
            Thread.sleep(1000);
          } catch(InterruptedException ex) {
            System.out.println("Bot is dead");
          }
        }
          //zombie kena tembakan
          if (gameplay.shotList.size() > 0) {
            for (int i = 0; i < gameplay.zombieList.size(); i++) {
                for (int j = 0; j < gameplay.shotList.size(); j++) {
                    // System.out.println("zombie" + i);
                    // System.out.println("shot " + j);
                    if (new Rectangle(gameplay.zombieList.get(i).zombieGetX(), gameplay.zombieList.get(i).zombieGetY(),95,45).intersects(new Rectangle(gameplay.shotList.get(j).shotGetX(), gameplay.shotList.get(j).shotGetY(),30,30))) {
                        gameplay.zombieList.get(i).takeDamage(gameplay.shotList.get(j).shotDamage());
                        gameplay.shotList.get(j).shotDead();
                        gameplay.shotList.remove(j);
                        if (gameplay.zombieList.get(i).getHp() <= 0) {
                            gameplay.zombieList.get(i).zombieDead();
                            gameplay.zombieList.remove(i);
                            break;
                        }
                    }
                }
            }
          }
      
  }  
  }
}