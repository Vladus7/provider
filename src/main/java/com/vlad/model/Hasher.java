package com.vlad.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

    private Hasher(){}

    public static String getHash(String input, String algorithm) {
        byte[] hash = null;
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(input.getBytes());
            hash = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        if (hash != null) {
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
        }
        return sb.toString();
    }
}
