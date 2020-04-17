import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{

    // JButton plant1;

    // public JButtonwithImages(){
    //     ImageIcon peashooterIcon = new ImageIcon("peashooterCard.png");
    //     plant1 = new JButton(peashooterIcon);
        
    //     //add buttons on jframe
    //     setLayout(new FlowLayout());
    //     add(plant1);
    // }
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        Sound.bgmusic.loop();
        //Try 1
        // JButton b=new JButton(new ImageIcon("peashooterCard.png"));
        // b.setBounds(0,0,80,120);
        

        // Try 2
        // JButton buttonBrowse = new JButton();
        // buttonBrowse.setIcon(new javax.swing.ImageIcon("peashooterCard.png"));
        // buttonBrowse.setBorderPainted(false);
        // buttonBrowse.setFocusPainted(false);
        // buttonBrowse.setContentAreaFilled(false);
        // obj.add(b);
        obj.setBounds(10,10,997,808);
        obj.setTitle("Plants vs Zombie");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
        //cara tahu index yang didapat
        (new Thread(new IndexInject(10))).start();
    }
}