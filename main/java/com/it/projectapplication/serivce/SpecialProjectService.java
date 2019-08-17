package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.SpecialProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialProjectService {
    @Autowired
    SpecialProjectDao specialProjectDao;

}
