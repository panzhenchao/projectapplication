package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.AnnouncementManagerDao;
import com.it.projectapplication.domain.AnnouncementManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementManagerService {
    @Autowired
    AnnouncementManagerDao announcementManagerDao;

    public void save(AnnouncementManager announcementManager){
        if(announcementManager!=null){
            announcementManagerDao.save(announcementManager);
        }
    }
    public List<AnnouncementManager> findAll(){
        List<AnnouncementManager> list=(List<AnnouncementManager>)announcementManagerDao.findAll();

        return list;
    }
}
