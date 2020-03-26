package com.google.util;

import java.util.Random;

public class StringUtil {
    public static  boolean isEmpty(String str){

        return str == null || str.trim().equals("");
    }
    public static  boolean isNotEmpty(String str){


        return str != null && !str.trim().equals("");
    }



    public static  String getSixRandom(){
        Random random = new Random();
        String result="";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
