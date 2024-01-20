package com.example.dsj_test.checker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignatureChecker {
    private static final String TOKEN = "wx_wcl";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] tmpArr = {TOKEN, timestamp, nonce};
        Arrays.sort(tmpArr);
        String tmpStr = String.join("", tmpArr);
        String calculatedSignature = sha1(tmpStr);
        return calculatedSignature.equals(signature);
    }

    private static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
