package com.shanejim.myweb.personalmodel.utils;

import java.security.MessageDigest;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 13:53
 **/
public class DigestUtil {
    private static String DEFAULT_ENCODING = "UTF-8";
    private static String SHA_256 = "SHA-256";

    public static String sha256Digest(String str) {
        return digest(str, SHA_256, DEFAULT_ENCODING);
    }

    private static String digest(String str, String alg, String charencoding) {
        try {
            byte[] data = str.getBytes(charencoding);
            MessageDigest md = MessageDigest.getInstance(alg);
            return byteToHexString(md.digest(data));
        } catch (Exception var5) {
            throw new RuntimeException("digest fail!", var5);
        }
    }

    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }
}
