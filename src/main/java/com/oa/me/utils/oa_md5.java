package com.oa.me.utils;

import com.oa.me.domain.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenjiehao on 2018/9/17
 */
public class oa_md5 {

    public static String md5_salt(String pwd,String salt){
        //单独测试时前端未加密，所以md5两次再md5
        String pwd_md5 =  md5(md5(pwd)+salt);
        return pwd_md5;
    }

    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

}
