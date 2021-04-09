package com.aleksas1;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

public class RSA {
    public boolean areKeysPresent() {

        File privateKey = new File("C:\\Users\\Alex\\Desktop\\publicKey.txt");
        File publicKey = new File("C:\\Users\\Alex\\Desktop\\privateKey.txt");

        return privateKey.exists() && publicKey.exists();
    }


    public void EncryptToFile (){
        try {
            if(areKeysPresent()) {
                System.out.println("Type in text to encrypt: ");
                Scanner scanner = new Scanner(System.in);
                String originalText = scanner.nextLine();
                ObjectInputStream inputStream = null;
                inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Alex\\Desktop\\publicKey.txt"));
                PublicKey publicKey = (PublicKey) inputStream.readObject();
                encrypt(originalText, publicKey);
            } else
                System.out.println("First create keys!");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void DecryptFromFile (){
        try{
            if(areKeysPresent()) {
                ObjectInputStream inputStream;
                inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Alex\\Desktop\\privateKey.txt"));
                PrivateKey privateKey = (PrivateKey) inputStream.readObject();
                String plainText = decrypt(privateKey);
                System.out.println(plainText);
            }else
                System.out.println("No keys found!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance("RSA");
            // encrypt the plain text using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
            //System.out.println(Arrays.toString(cipherText));

            FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\Alex\\Desktop\\encryptedText.txt"));
            fileOutputStream.write(cipherText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String decrypt(PrivateKey key ) {
        byte[] dectyptedText = null;
        try {
            // get an RSA cipher object and print the provider
            Cipher cipher = Cipher.getInstance("RSA");


            FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Alex\\Desktop\\encryptedText.txt"));
            byte [] cipherText = fileInputStream.readAllBytes();

            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(cipherText);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }

}
