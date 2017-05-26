package com.emin.igwmp.skm.core.msmanage.validate;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/23.
 */
public class SessionGenerator {

    private static final int SESSION_LEN = 8;

    public static  String getSesssion(){
        return  getRandomString(SESSION_LEN);
    }



    private static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
