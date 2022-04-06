package com.cqjtu.mindassess.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MD5Util {


    private static final char[] chars = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String encryption(String source) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] bytes = source.getBytes();
            byte[] digest = md5.digest(bytes);
            for (byte b : digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b & 15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
