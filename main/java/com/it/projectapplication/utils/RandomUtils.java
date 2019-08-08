package com.it.projectapplication.utils;

import java.util.Random;

public class RandomUtils  {
    public static String getRandomString(){
        String str="ABCDEF";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            int number=random.nextInt(6);
            sb.append("/"+str.charAt(number));
        }
        return sb.toString();
    }
}
