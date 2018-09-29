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


    /**
     * 随机生成盐值：采用了很简单的方法
     */
    public static String radomString(){
//        String result="";
//        for(int i=0;i<6;i++){
//            int intVal=(int)(Math.random()*26+97);
//            result=result+(char)intVal;
//        }
//        System.out.println(result);
//        String randomcode = "";
//        for(int i=0;i<6;i++)
//        {
//            //52个字母与6个大小写字母间的符号；范围为91~96
//            int value = (int)(Math.random()*58+65);
//            while(value>=91 && value<=96)
//                value = (int)(Math.random()*58+65);
//            randomcode = randomcode + (char)value;
//
//        }
//        System.out.println(randomcode);

        //用字符数组的方式随机
        String randomcode2 = "";
        String model = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        char[] m = model.toCharArray();

        for (int j=0;j<6 ;j++ )
        {
            char c = m[(int)(Math.random()*62)];
            randomcode2 = randomcode2 + c;
        }
        System.out.println(randomcode2);
        return randomcode2;

    }




}
