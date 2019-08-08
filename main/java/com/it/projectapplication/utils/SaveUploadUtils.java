package com.it.projectapplication.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SaveUploadUtils {
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IDENTITY_CARD = "static/img/identityCardImg"+RandomUtils.getRandomString();
    public final static String BUSINESS_LICENSE = "static/img/businessLicenseImg"+RandomUtils.getRandomString();
    
    public static String  getIdentityCardImgDirFile(MultipartFile file,String path){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/" + path);

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
        return newFilePath;
    }
}

