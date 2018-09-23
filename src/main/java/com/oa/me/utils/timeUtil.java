package com.oa.me.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenjiehao on 2018/9/19
 */
public class timeUtil {
    /**
     * 获取精确到秒的时间戳
     * @param
     * @return
     */
    public static int getSecondTimeNow(){

        String timestamp = String.valueOf(new Date().getTime()/1000);
        return Integer.valueOf(timestamp);
    }


    public static String dateTimeStamp(String date_str,String format){
               try {
                        SimpleDateFormat sdf = new SimpleDateFormat(format);
                        return String.valueOf(sdf.parse(date_str).getTime()/1000);
                  } catch (Exception e) {
                       e.printStackTrace();
                  }
                 return "";
         }
}
