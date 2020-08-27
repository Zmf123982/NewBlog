package com.edu.zzc.util;

import com.alibaba.druid.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 封装主键的生成方式
 * 封装MD5加密算法
 */
public class NoteUtil {
    public static String createID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
    //非对称加密算法
    public static String md5(String src) throws Exception {
        //将字符串信息采用MD5处理
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(src.getBytes());
        //将MD5处理结果利用Base64转换成字符串
        String s = Base64.byteArrayToBase64(digest);
        return s;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(createID());
        System.out.println(md5("123456"));
    }
}
