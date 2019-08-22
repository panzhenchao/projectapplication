package com.it.projectapplication.utils;

import com.it.projectapplication.domain.Project;

import java.util.Comparator;

public class MyCompartor  implements Comparator {
    public int compare(Object o1,Object o2){
        Project r1=(Project)o1;
        Project r2=(Project)o2;
        return r1.getId().compareTo(r2.getId());
    }
}
