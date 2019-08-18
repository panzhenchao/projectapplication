package com.it.projectapplication.utils;

public class StringSplit {
    public static Integer stringSplit(String string){
        String sb=string.substring(0,string.length()-1);
        String [] split=sb.split(",");
        Integer number=0;
        for(String s:split){
            number+=Integer.parseInt(s);
        }
        return number;
    }
}
