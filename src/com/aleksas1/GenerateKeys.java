package com.aleksas1;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class GenerateKeys {
    private KeyPairGenerator keyGenerator;
    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public GenerateKeys (int length){
        try {
            keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(length);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createKeys (){
        keyPair = keyGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    public void saveKeysToFile (){
        try {
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(new File("C:\\Users\\Alex\\Desktop\\publicKey.txt")));
            publicKeyOS.writeObject(publicKey);
            publicKeyOS.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(new File("C:\\Users\\Alex\\Desktop\\privateKey.txt")));
            publicKeyOS.writeObject(privateKey);
            publicKeyOS.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
