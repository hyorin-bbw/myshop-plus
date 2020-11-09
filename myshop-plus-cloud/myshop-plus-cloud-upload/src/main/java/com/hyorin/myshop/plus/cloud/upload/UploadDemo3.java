package com.hyorin.myshop.plus.cloud.upload;

import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 七牛云文件上传 断点续传
 *
 * @author hyorin
 * @date 2020.10.20
 */
public class UploadDemo3 {
    public static void main(String[] args) {

        //region2 对应华南地区机房
        Configuration configuration = new Configuration(Region.region2());
        String accessKey = "-2NbFtE0oRgeOYxyVIzUxxkiv8FgE7c7WWgi9ghe";
        String secretKey = "fQuvvQKM5IMwIc8NgLOgdtQkXC5rUgKmQpe_UlG8";
        //本地文件路径 绝对路径
        String localFilePath = "E:\\code\\learning\\myshop-plus\\myshop-plus-cloud\\myshop-plus-cloud-upload\\src\\main\\resources\\temp\\伊藤由奈 - I’m Here.mp3";
        String bucket = "hyorin-test";
        //不指定key的情况下 以文件的hash值作为文件名
        String key = "i am here.mp3";
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket);
        String localTempDir = Paths.get(System.getenv("java.io.tempdir"), bucket).toString();

        try {
            //设置断点续传文件进度保存目录
            FileRecorder fileRecorder = new FileRecorder(localTempDir);
            UploadManager uploadManager = new UploadManager(configuration, fileRecorder);
            Response response = uploadManager.put(localFilePath, key, token);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(defaultPutRet.hash);
            System.out.println(defaultPutRet.key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
