package com.imooc.utils;


import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptUtil {
    public static String getEncryptedString(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(string.getBytes("UTF-8"));

            String hexString = bytesToHexString(bytes);
            return hexString;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHexString(byte[] bytes) {
        String encodeHexString = Hex.encodeHexString(bytes);
        return encodeHexString;

    }
}
