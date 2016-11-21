/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Writing {

    public static void clear() {
        File directory = new File("C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke");
        for (File f : directory.listFiles()) {
            f.delete();
        }
    }

    public static boolean write(String dat, String key) {
        
        PrintWriter writer;
        String file = "C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke\\" + dat;
        File f = new File(file);
        if (!f.exists() && !f.isDirectory()) {
            try {
                writer = new PrintWriter("C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke\\" + dat, "UTF-8");
                writer.println(key);
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean writeByte(String dat, byte[] key) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke\\" + dat);
            fos.write(key);
            fos.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
            return false;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
            return false;
        }
        return true;
    }

}
