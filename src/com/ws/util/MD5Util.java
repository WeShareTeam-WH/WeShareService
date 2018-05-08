package com.ws.util;

import java.security.MessageDigest;

import com.ws.Constant;

/**
 * MD5 encrypts the content into 32-bit characters.
 * */
public class MD5Util {
    /**
     * Gets the encrypted 32-character password.
     * @return Encrypted password.
     * */
    public static String getEncryptPassword(String password) {
        String result = Constant.BLANK;
        if (!StringUtil.isEmpty(password)) {
            try {
                MessageDigest md = MessageDigest.getInstance(Constant.MD5);
                byte[] bytes = md.digest(password.getBytes());
                for (int i=0; i < bytes.length; i++) {
                    String str = Integer.toHexString(bytes[i] & 0xFF);
                    if (str.length() == 1) {
                        str += Constant.F;
                    }
                    result += str;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
        return result.toUpperCase();
    }
}
