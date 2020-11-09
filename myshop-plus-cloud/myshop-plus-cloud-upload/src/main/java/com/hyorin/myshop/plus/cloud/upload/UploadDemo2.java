package com.hyorin.myshop.plus.cloud.upload;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云本地文件上传示例demo
 *
 * @author hyorin
 * @date 2020.10.20
 */
public class UploadDemo2 {

    public static void main(String[] args) {

        //region2 对应华南地区机房
        Configuration configuration = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(configuration);
        String accessKey = "-2NbFtE0oRgeOYxyVIzUxxkiv8FgE7c7WWgi9ghe";
        String secretKey = "fQuvvQKM5IMwIc8NgLOgdtQkXC5rUgKmQpe_UlG8";
        //本地文件路径 绝对路径
        String localFilePath = "E:\\code\\learning\\myshop-plus\\myshop-plus-cloud\\myshop-plus-cloud-upload\\src\\main\\resources\\temp\\down.jpg";
        String bucket = "hyorin-test";
        //不指定key的情况下 以文件的hash值作为文件名
        String key = "down.jpg";
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, token);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(defaultPutRet.key);
            System.out.println(defaultPutRet.hash);
        } catch (QiniuException e) {
            System.out.println(e.getCause());
            Response response = e.response;
            System.out.println(response.toString());
            try {
                System.out.println(response.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
