package com.springcloud.weibo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class ServiceUtils {

    // 将用户密码使用MD5算法加密后存储
    public static String MessageDigest(String message) {
        String result = null;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("md5");
            byte md5[] = mDigest.digest(message.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            result = encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 产生全球唯一ID UUID算法
    public static String generateID() {
        return UUID.randomUUID().toString();
    }
}
