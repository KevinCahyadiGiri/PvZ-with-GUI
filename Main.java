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
        JFrame mainMenu = new JFrame();
        
        Sound.intromusic.loop();
        
        //Try 1
        // JButton b=new JButton(new ImageIcon("peashooterCard.png"));
        // b.setBounds(0,0,80,120);
        
        //MAIN MENU
        JButton playButton = new JButton("START GAME");
        mainMenu.add(playButton);
        
        mainMenu.setBounds(10,10,997,808);
        mainMenu.setTitle("Plants vs Zombie");
        mainMenu.setResizable(false);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playButton.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){  
                mainMenu.setVisible(false);
                Sound.intromusic.stop();
                //GAME DIMULAI
                JFrame obj = new JFrame();
                Gameplay gameplay = new Gameplay();
                Sound.bgmusic.loop();
                obj.setBounds(10,10,997,808);
                obj.setTitle("Plants vs Zombie");
                obj.setResizable(false);
                obj.setVisible(true);
                obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                obj.add(gameplay); 
            }
        });


        // Try 2
        // JButton buttonBrowse = new JButton();
        // buttonBrowse.setIcon(new javax.swing.ImageIcon("peashooterCard.png"));
        // buttonBrowse.setBorderPainted(false);
        // buttonBrowse.setFocusPainted(false);
        // buttonBrowse.setContentAreaFilled(false);
        // obj.add(b);
        
        //cara tahu index yang didapat
        // (new Thread(new IndexInject(10))).start();
    }
}