package com.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by haizhi on 2017/2/8.
 */
@Slf4j
@Component
public class FileUpload {
    public static final String ACCESS_KEY = "UMxeaDoDItmq2AcnxWES8ATFc35wyW-KCpoWaL8h";
    public static final String SECRET_KEY = "QUvij44S5DO5yuspjeDZSbO86JTHCAeTsx-fF1CK";
    public static final String BUCKET_NAME = "im-xinhongyang";
    Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);
    UploadManager uploadManager = new UploadManager(c);
    public String getUpToken(){
        return auth.uploadToken(BUCKET_NAME);
    }
    public void upload(File file, String key)throws IOException{
        try{
            Response res = uploadManager.put(file,key,getUpToken());
            log.info("upload file to qiniu success,{}",res.bodyString());
        }catch (QiniuException e ){
            log.error("Error in qiniu error:{},response:{}",e.response.toString(),e.response.bodyString());
        }
    }
    public void upload(byte[] data, String key)throws IOException{
        try{
            Response res = uploadManager.put(data,key,getUpToken());
            log.info("upload file to qiniu success,{}",res.bodyString());
        }catch (QiniuException e ){
            log.error("Error in qiniu error:{},response:{}",e.response.toString(),e.response.bodyString());
        }
    }
}
