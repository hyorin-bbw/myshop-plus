package com.hyorin.myshop.plus.cloud.download;

import com.qiniu.util.Auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 七牛云私有空间文件下载
 *
 * @author hyorin
 * @date 2020.10.20
 */
public class DownloadDemo {

    public static void main(String[] args) {
       getPrivateUrl();
    }


    public static String getPublicUrl() {
        String domainOfBucket = "http://qihca0gux.hn-bkt.clouddn.com/";
        String fileName = "down.jpg";
        String finalUrl = String.format("%s/%s", domainOfBucket, fileName);
        System.out.println(finalUrl);
        return finalUrl;
    }

    public static String getPrivateUrl() {
        String finalUrl = null;
        try {
            String domainOfBucket = "http://qihca0gux.hn-bkt.clouddn.com/";
            String fileName = "down.jpg";
            String encodeFilename = URLEncoder.encode(fileName, "UTF-8");
            String publicUrl = String.format("%s/%s", domainOfBucket, encodeFilename);
            String accessKey = "-2NbFtE0oRgeOYxyVIzUxxkiv8FgE7c7WWgi9ghe";
            String secretKey = "fQuvvQKM5IMwIc8NgLOgdtQkXC5rUgKmQpe_UlG8";
            Auth auth = Auth.create(accessKey, secretKey);
            //自定义外链超时时间 单位秒
            Long expireInSecond = 3600L;
            finalUrl = auth.privateDownloadUrl(publicUrl, expireInSecond);
            System.out.println(finalUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return finalUrl;
    }
}
