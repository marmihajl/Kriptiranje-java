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
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;

/**
 *
 * @author marin
 */
public class Compose {
    static Signature sig;

    public static String sha256(String t, boolean x) {
        String sazetak = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(t.getBytes("UTF-8"));
            byte[] digest = md.digest();
            if (x) {
                Writing.writeByte("sazetak.txt", digest);
            }
            sazetak = new String(digest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }
        return sazetak;
    }

    public static String dPotpis(String t, PrivateKey pk) {
        String potpis = "";
        byte[] data;
        try {
            data = t.getBytes("UTF8");
            sig = Signature.getInstance("SHA256WithRSA");
            sig.initSign(pk);
            sig.update(data);
            byte[] signatureBytes = sig.sign();
            potpis = new BASE64Encoder().encode(signatureBytes);
            FileOutputStream fos = new FileOutputStream("C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke\\digitalni_potpis.txt");
            fos.write(signatureBytes);
            fos.close();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }

        

        return potpis;
    }

    public static boolean checkPotpis(String potpis, String poruka, PublicKey pk) {
        boolean c = false;

        try {
            byte[] p = Files.readAllBytes(Paths.get(potpis));
            byte[] p2 = Files.readAllBytes(Paths.get(poruka));
            sig.initVerify(pk);
            sig.update(p2);
            if(sig.verify(p))
                c = true;
        } catch (IOException | SignatureException | InvalidKeyException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }
        
        return c;
    }
}
