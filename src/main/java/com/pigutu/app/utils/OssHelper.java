package com.pigutu.app.utils;

import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class OssHelper {
    public static void uploadImageUrl(String url,String folder,String imageIndex){
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "http://img.pigutu.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号
        String accessKeyId = "LTAIt7bz9idw4Rmp";
        String accessKeySecret = "OhzLNYoiWpkbHLhwikjPErzOL6HJx8";
// 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
// 上传网络流
        InputStream inputStream = null;
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String time = String.valueOf(System.currentTimeMillis());
        //todo 这里需要imagesetdao上报
        ossClient.putObject("hellohappy", folder+"/"+imageIndex+".jpg", inputStream);
// 关闭client
        ossClient.shutdown();
    }
    public static void createFolder(){
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "http://img.pigutu.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号
        String accessKeyId = "LTAIt7bz9idw4Rmp";
        String accessKeySecret = "OhzLNYoiWpkbHLhwikjPErzOL6HJx8";
// 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        final String keySuffixWithSlash = "background/446/";
        ossClient.putObject("hellohappy", keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
// 关闭client
        ossClient.shutdown();
    }
}
