package UIcomponents;



import Level.Level;
import Level.SpawnLevel;
import sun.applet.Main;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * MainFrame class, which creates editor window
 */
public class MainFrame extends Canvas {
    JFrame frame;
    Dimension size;
    ImageIcon icon;
    boolean running;
    Thread thread;
    JPanel westPanel;
    JPanel centerPanel;
    WorkSpace workSpace;
    private Random random = new Random();
    int[] tiles = new int[64*64];
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  // create the image in the buffer
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    public static int width = 460;
    public static int height = width / 16 * 9; // for 16:9 monitors
    public static int scale = 3;

    public static File file;

    public void mainFrame() {
        frame = new JFrame("2SH-engine");  // create new window
        size = new Dimension(width * scale, height * scale);
        workSpace = new WorkSpace(width * scale, height * scale);
    }

    public void main() {
        MainFrame editor = new MainFrame();
        editor.mainFrame();
        westPanel = new JPanel();
        centerPanel = new JPanel();
        JButton add_map = new JButton("Add map");
        JTextPane annotation = new JTextPane();
        ImageIcon image = new ImageIcon("res/mapAnnotation");
        JLabel label = new JLabel(image);
        JButton exit = new JButton("Exit");


        editor.frame.setMinimumSize(new Dimension(900, 600));  // set minimum size of window
        editor.frame.setPreferredSize(size);
        editor.frame.pack();
        editor.frame.setLayout(new BorderLayout());
        editor.frame.setLocation(0, 0);
        editor.frame.setLocationRelativeTo(null);
        editor.frame.setResizable(false);
        editor.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editor.frame.setVisible(true);

        westPanel.setBounds(0, 0, 150, 100);
        westPanel.setBackground(Color.GRAY);
        westPanel.setPreferredSize(new Dimension(150, 100));

        centerPanel.setBounds(150,0, getWidth(), getHeight());
        add_map.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser map = new JFileChooser();
                map.setPreferredSize(new Dimension(300,300));
                int ret = map.showDialog(null, "Open file");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = map.getSelectedFile();
                    file.getPath();
                    map.setVisible(false);
                    System.out.println(file);
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter("levelPath.txt", "UTF-8");
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    }
                    writer.println(file.getPath());
                    writer.close();
                }
            }
        });

        annotation.setEditable(false);
        annotation.setPreferredSize(new Dimension(100, 50));
        annotation.setText("Example of spawn level =>");
        annotation.setBackground(Color.GRAY);

        centerPanel.add(annotation);
        centerPanel.add(label);
        centerPanel.add(new JLabel(new ImageIcon("res/mapAnnotation.png")));

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        westPanel.add(add_map);
        westPanel.add(exit);

        editor.frame.add(westPanel, BorderLayout.WEST);
        editor.frame.add(centerPanel, BorderLayout.CENTER);

        icon = new ImageIcon("res/icon.png"); // create icon of engine
        editor.frame.setIconImage(icon.getImage());
    }

    public static String getLevel() {
        return file.getPath();
    }
}
