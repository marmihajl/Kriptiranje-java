/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.projekt;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Reader {
    
    public static String read(File dat){
        
        String content = null;
        FileReader reader = null;
        try {
            reader = new FileReader(dat);
            char[] chars = new char[(int) dat.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
                }
            }
        }
        return content;
    }
    
    
    
}
