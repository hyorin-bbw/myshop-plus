package com.hyorin.myshop.plus.cloud.upload;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

/**
 * 七牛云字节流文件上传示例demo
 *
 * @author hyorin
 * @date 2020.10.20
 */
public class UploadDemo {

    public static void main(String[] args) {
        //region2 对应华南地区机房
        Configuration configuration = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "-2NbFtE0oRgeOYxyVIzUxxkiv8FgE7c7WWgi9ghe";
        String secretKey = "fQuvvQKM5IMwIc8NgLOgdtQkXC5rUgKmQpe_UlG8";
        String bucket = "hyorin-test";

        try {
            byte[] uploadBytes = "hello ,this is hyorin".getBytes("utf-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String token = auth.uploadToken(bucket);
            System.out.println("token:" + token);

            try {
                Response response = uploadManager.put(byteArrayInputStream, accessKey, token, null, null);
                //解析上传成功的结果
                DefaultPutRet PutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println("hash:" + PutRet.hash);
                System.out.println("key:" + PutRet.key);
            } catch (QiniuException e) {
                Response response = e.response;
                System.err.println(response.toString());
                try {
                    System.err.println(response.bodyString());
                } catch (QiniuException e1) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException e) {
            //ignore
        }
    }

}
