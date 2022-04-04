package com.cqjtu.mindassess.util;

import org.apache.tomcat.util.security.MD5Encoder;
import org.bouncycastle.crypto.digests.MD5Digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Test {
    public static void main(String[] args) {
        String password = "ningmima..";
        String encryption = MD5Util.encryption(password);
        System.out.println(encryption);
        //99ED02009344B508FE3026EB4350C416
    }
}
