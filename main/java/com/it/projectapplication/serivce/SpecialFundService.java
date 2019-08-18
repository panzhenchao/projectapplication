package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.SpecialFundDao;
import com.it.projectapplication.domain.SpecialFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SpecialFundService {
    @Autowired
    SpecialFundDao specialFundDao;
    public Page<SpecialFund> findSpecialFunds(Pageable pageable){
        Page<SpecialFund> page=specialFundDao.findAll(pageable);
        return page;
    }
    public void save(SpecialFund specialFund){
        specialFundDao.save(specialFund);
    }
    public SpecialFund findSpecialFundByName(String name){
        SpecialFund specialFund=specialFundDao.findSpecialFundByName(name);
        return specialFund;
    }
}
