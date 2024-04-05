package com.xpu.sceneryview.utils;

import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description
 * @Author lubb
 * @create 2024-04-05 16:39
 */

public class MD5Util {
    /**
     * @return String
     * @author CaoPengCheng
     * @description: 计算md5的工具方法
     * @date 2021/9/17 15:08
     * @Param String password
     */
    public static String createMD5(String password) {
        try {
            //确定md5加密算法
            MessageDigest md = MessageDigest.getInstance("md5");
            //通过md5计算摘要
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            //md5值转成可读字符串
            BASE64Encoder base64 = new BASE64Encoder();
            return base64.encode(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

