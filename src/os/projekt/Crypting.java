/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.projekt;

import com.sun.xml.internal.messaging.saaj.util.Base64;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author marin
 */
public class Crypting {
    


    public static String encrypt(String text) {

        SecretKey secKey = generateKey.secretKey;
        Cipher AesCipher;
        byte[] byteCipherText = null;
        String encrypte = "";
        File f = new File("C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke\\tajni_kljuc.txt");
        String tk = Reader.read(f);
        
        try {
            AesCipher = Cipher.getInstance("AES");
            byte[] k = text.getBytes();
            AesCipher.init(Cipher.ENCRYPT_MODE, secKey);
            byteCipherText = AesCipher.doFinal(k);
            Writing.writeByte("kriptirani_text_aes.txt", byteCipherText);
            encrypte = new String(byteCipherText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }

        return encrypte;
    }

    public static String decrypt(String p) {
        byte[] cipherText = null;
        Cipher AesCipher;
        SecretKey secKey = generateKey.secretKey;
        String encryptText = "";
        File f = new File("C:\\Users\\marin\\Documents\\NetBeansProjects\\OS-projekt\\datoteke\\tajni_kljuc.txt");
        String tk = Reader.read(f);
        
        try {
            AesCipher = Cipher.getInstance("AES");
            cipherText = Files.readAllBytes(Paths.get(p));

            AesCipher.init(Cipher.DECRYPT_MODE, secKey);
            byte[] bytePlainText = AesCipher.doFinal(cipherText);
            encryptText = new String(bytePlainText);

        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }

        return encryptText;
    }

    public static String encriptyRSA(String s, boolean c, PublicKey key) {
        String encrypt = "";

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptByte = cipher.doFinal(s.getBytes());
            if (c) {
                Writing.writeByte("kriptirani_text_rsa.txt", encryptByte);
            }
            encrypt = new String(encryptByte);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }

        return encrypt;
    }

    

    public static String decryptRSA(String p, PrivateKey key) {
        String decrypt = "";
        byte[] cipherText = null;
        Cipher cipher;
        
        try {
            cipher = Cipher.getInstance("RSA");
            cipherText = Files.readAllBytes(Paths.get(p));
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptByte = cipher.doFinal(cipherText);
            decrypt = new String(decryptByte);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Došlo je do pogreške");
        }

        return decrypt;
    }
}
