package com.it.projectapplication.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtils {
    public static  String  sendPostRequest(String url, String requestJson){
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpMethod method=HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity =new HttpEntity<String>(requestJson,headers);
        String token=restTemplate.exchange(url, method, entity,String.class).getBody();
        System.out.println(token);
        return token;
    }
}
