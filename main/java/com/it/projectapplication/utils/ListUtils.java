package com.it.projectapplication.utils;

import java.util.List;

public class ListUtils {
    public static List<?>getList(List<Enum> list,Integer page,Integer size){
        Integer startPage=(page-1)*size+1;
        Integer endPage;
        if(list.size()>=page*size){
            endPage=page*size;
        }else {
            endPage=list.size();
        }
        list=list.subList(startPage,endPage);

        return list;
    }
}
