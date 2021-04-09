package com.aleksas1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        RSA rsa = new RSA();

        while(true){
            System.out.println("What would you like to do?\n1.Encryption\n2.Decryption\n3.Generate keys");
            Scanner scanner = new Scanner(System.in);
            int modeOfOperation = scanner.nextInt();
            if(modeOfOperation == 1){

                rsa.EncryptToFile();
            }
            else if (modeOfOperation == 2){
                System.out.println("Decrypted text:");
               rsa.DecryptFromFile();
            } else if ( modeOfOperation == 3 ){
                if(rsa.areKeysPresent()) {
                    System.out.println("Seems like you already have keys? Are you sure you want to continue creating?\n1.Yes\n2.No");
                    int keysSafeCheck = scanner.nextInt();
                    if (keysSafeCheck == 1) {
                        GenerateKeys generateKeys = new GenerateKeys(1024);
                        generateKeys.createKeys();
                        generateKeys.saveKeysToFile();
                    }
                } else {
                    GenerateKeys generateKeys = new GenerateKeys(1024);
                    generateKeys.createKeys();
                    generateKeys.saveKeysToFile();
                }
            }
            else {
                return;
            }
        }
    }
}
