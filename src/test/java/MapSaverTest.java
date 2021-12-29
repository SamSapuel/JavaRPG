import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapSaverTest {
    private File fileTest1;
    private File fileTest2;
    @Test
    public void SaverTest() {
        //
        JFileChooser map = new JFileChooser();
        map.setPreferredSize(new Dimension(300,300));
        int ret = map.showDialog(null, "Open file");
        PrintWriter writer = null;
        PrintWriter writer2 = null;
        String value1 = new String();
        String value2 = new String();

        if (ret == JFileChooser.APPROVE_OPTION) {
            fileTest1 = map.getSelectedFile();
            fileTest1.getPath();
            map.setVisible(false);
            System.out.println(fileTest1);
            //PrintWriter writer = null;
            try {
                writer = new PrintWriter("levelPathTest1.txt", "UTF-8");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }
            writer.println(fileTest1.getPath());
            writer.close();
        }


        //PrintWriter writer2 = null;
        fileTest2 = new File("D:/documents/2 semestr/PJV/shevcdmi/res/textures/spawnLevel2.png");
        try {
            writer2 = new PrintWriter("levelPathTest2.txt", "UTF-8");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        writer2.println("D:/documents/2 semestr/PJV/shevcdmi/res/textures/spawnLevel2.png");
        writer2.close();



        try (BufferedReader reader = Files.newBufferedReader(Paths.get("levelPathTest1.txt"))) {
            value1 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("levelPathTest2.txt"))) {
            value2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(value1, value2);

    }
}
