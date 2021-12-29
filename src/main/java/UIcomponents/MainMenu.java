package UIcomponents;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * MainMenu class, which creates main menu
 */

public class MainMenu implements ActionListener {
    JFrame menuFrame = new JFrame("2SH");
    JButton Edit;
    JButton Play;
    JPanel panel;
    ImageIcon icon;
    BufferedImage image;
    public void mainMenu() {

        //menuFrame = new JFrame("2SH-engine");
        menuFrame.setPreferredSize(new Dimension(900, 600));
        menuFrame.setMinimumSize(new Dimension(900, 600));
        menuFrame.setResizable(false);
        //menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);

        icon = new ImageIcon("res/icon.png"); // create icon of engine
        menuFrame.setIconImage(icon.getImage());
        music();
        try {
            image = ImageIO.read(new File("res/MainMenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));

        JButton Edit = new JButton("Edit");
        JButton Play = new JButton("Play");
        JButton Exit = new JButton("Exit");
        JPanel panel = new JPanel();
        Edit.setBounds(450, 200, 100 ,50);
        Edit.addActionListener(this);
        Play.addActionListener(this);
        Exit.addActionListener(this);
        Play.setBounds(450, 300, 100 ,50);
        Exit.setBounds(450, 400, 100, 50);
        label.add(Edit);
        label.add(Play);
        label.add(Exit);
        panel.add(label);
        menuFrame.add(panel);


    }

    public static void music() {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;

        ContinuousAudioDataStream loop = null;

        try {
            InputStream test = new FileInputStream("res/theme++.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
            
            //MD = BGM.getData();
            //loop = new ContinuousAudioDataStream(MD);

        } catch (FileNotFoundException e) {
            System.out.print(e.toString());
        } catch (IOException error) {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Edit")) {
            menuFrame.dispose();
            MainFrame mainFrame = new MainFrame();
            mainFrame.main();
            mainFrame.setVisible(true);
        }
        if (action.equals("Play")) {
            menuFrame.dispose();
            //menuFrame.setVisible(false);
            //menuFrame.setDefaultCloseOperation(JFrame);
            GameFrame gameFrame = new GameFrame();
            gameFrame.main();
            gameFrame.setVisible(true);
            //System.exit(0);

        }
        if (action.equals("Exit")) {
            System.exit(0);
        }
    }
}
