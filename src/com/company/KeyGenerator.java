package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;

    public class KeyGenerator {

        public static void generateKeys(String fileName) {
            SecureRandom rand = new SecureRandom();
            int bitLength = 2048;

            BigInteger p = new BigInteger(bitLength / 2, 100, rand);
            BigInteger q = new BigInteger(bitLength / 2, 100, rand);
            BigInteger n = p.multiply(q);
            BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            BigInteger e = new BigInteger("3");
            while (phiN.gcd(e).intValue() > 1) {
                e = e.add(new BigInteger("2"));
            }

            BigInteger d = e.modInverse(phiN);
            KeyPair publicKey = new KeyPair(e, n);
            KeyPair privateKey = new KeyPair(d, n);
            saveKey(fileName + "_pub.key", publicKey);
            saveKey(fileName + "_priv.key", privateKey);

        }
        public static void saveKey(String fileName, KeyPair key) {
            try {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(key);
                out.close();
                System.out.println("Saved key as " + fileName);
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }

