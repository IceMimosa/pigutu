package com.pigutu.app.uploadtu;

import org.jsoup.Jsoup;
  
/** 
 *  
 * ProxyUtil 
 * @Description:TODO 代理IP工具 
 * @author:千界云 http://qianjieyun.com 
 * @email:fhg1225@126.com 
 * @date 2016年9月14日 
 */  
public class ProxyUtil {  

      
    public static boolean checkProxy(String ip, Integer port){  
        try {  
            //http://1212.ip138.com/ic.asp 可以换成任何比较快的网页  
            Jsoup.connect("http://json.cn/")  
                    .timeout(3*1000)  
                    .proxy(ip, port)
                    .get();  
            System.out.println(ip+":"+port);  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
}  