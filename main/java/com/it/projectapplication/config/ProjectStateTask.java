package com.it.projectapplication.config;

import com.it.projectapplication.domain.SpecialProject;
import com.it.projectapplication.serivce.SpecialProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class ProjectStateTask {
    @Autowired
    SpecialProjectService specialProjectService;
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public  void checkSpecialProjectStopDateTasks() {
        try {
            SpecialProject specialProject=specialProjectService.findMinStopDate();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date spTime=sdf.parse(sdf.format(specialProject.getStopDate()));
            Date tTime=sdf.parse(sdf.format(new Date()));

            if(spTime.before(tTime)){
                specialProject.setState("0");
               specialProjectService.saveSpecialProject(specialProject);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
