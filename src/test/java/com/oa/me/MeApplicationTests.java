package com.oa.me;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MeApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public  void radomString(){
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

    }

}
