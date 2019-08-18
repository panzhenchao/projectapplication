package com.it.projectapplication.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SaveUploadUtils {
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IDENTITY_CARD = "/img/identityCardImg"+RandomUtils.getRandomString();
    public final static String BUSINESS_LICENSE = "/img/businessLicenseImg"+RandomUtils.getRandomString();
    public final static String SPECIAL_PROJECT_PAPER= "/img/specialProjectPaper"+RandomUtils.getRandomString();

    
    public static String  getSaveDirFile(MultipartFile file,String path){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/static" + path);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        String filename=file.getOriginalFilename();
        File newFile;
        String newFilePath=fileDir.getAbsolutePath()+File.separator+filename;
        try {

            newFile =new File(newFilePath);
            file.transferTo(newFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        return path+"/"+filename;
    }
}

