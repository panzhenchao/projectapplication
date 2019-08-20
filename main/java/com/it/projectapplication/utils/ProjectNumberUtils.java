package com.it.projectapplication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectNumberUtils {
    public static Long getProjectNumber(Long maxNumber){
        SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
        String date=format.format(new Date(System.currentTimeMillis()));
        System.out.println(date);
        if(null!=maxNumber&&maxNumber.toString().contains(date)){
            maxNumber+=1L;
        }else {
            System.out.println(Long.parseLong(date));
            maxNumber=Long.parseLong(date+"00001");
        }
        return maxNumber;
    }
}
