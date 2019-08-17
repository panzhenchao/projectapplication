package com.it.projectapplication.utils;

import java.util.List;

public class TypeConvert {
    public final static Integer ENTERPRISE_NUMBER=1;
    public final static Integer COOPERATION_NUMBER=2;
    public final static Integer HOME_FARM_NUMBER=4;
    public final static Integer VILLAGE_NUMBER=8;
    public final static Integer TOWN_NUMBER=16;
    public final static Integer MANGER_NUMBER=32;
    public final static Integer PERSONAL_NUMBER=64;
    public final static Integer ELSE_NUMBER=128;
    public static Integer findTypeNumber(String type){
        switch (type){
            case "企业":
                return ENTERPRISE_NUMBER;
            case "农业专业合作社":
                return COOPERATION_NUMBER;
            case "家庭农场":
                return HOME_FARM_NUMBER;
            case "行政村":
                return VILLAGE_NUMBER;
            case "乡镇部门":
                return TOWN_NUMBER;
            case "主管部门":
                return MANGER_NUMBER;
            case "个人":
                return PERSONAL_NUMBER;
            default:
                return ELSE_NUMBER;

        }
    }
    public static Integer fundTypeNumber(List<String> list){
        Integer number=0;
        for(String type:list){
            number+=findTypeNumber(type);
        }
        return number;

    }


}
