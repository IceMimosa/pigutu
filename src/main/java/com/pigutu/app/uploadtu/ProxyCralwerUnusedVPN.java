package com.pigutu.app.uploadtu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;  
  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
  
/** 
 *  
 * ProxyCralwerUnusedVPN 
 * @Description:TODO 免费代理IP获取 不需要使用VPN 国外代理IP网站抓取需要使用VPN 
 * 在使用之前，请配置自己的Redis工具，如果不想使用，直接在代码中注释掉即可 
 * @author:千界云 http://qianjieyun.com 
 * @email:fhg1225@126.com 
 * @date 2016年9月14日 
 */  
public class ProxyCralwerUnusedVPN {  
  
    public static void main(String[] args) {  
        ProxyCralwerUnusedVPN proxyCrawler = new ProxyCralwerUnusedVPN();  
          
        proxyCrawler.startCrawler();  
    }  
      
    public void startCrawler(){  
        //13 http://www.kuaidaili.com  
        kuaidailiCom("http://www.kuaidaili.com/free/inha/", 15);  
        kuaidailiCom("http://www.kuaidaili.com/free/intr/", 15);  
        kuaidailiCom("http://www.kuaidaili.com/free/outha/", 15);  
        kuaidailiCom("http://www.kuaidaili.com/free/outtr/", 15);  
    }  
      
    private void kuaidailiCom(String baseUrl,int totalPage){  
        String ipReg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{1,6}";  
        Pattern ipPtn = Pattern.compile(ipReg);  
          
        for (int i = 1; i < totalPage; i++) {  
            try {  
                Document doc = Jsoup.connect(baseUrl + i + "/")  
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")  
                        .header("Accept-Encoding", "gzip, deflate, sdch")  
                        .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")  
                        .header("Cache-Control", "max-age=0")  
                        .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36")  
                        .header("Cookie", "Hm_lvt_7ed65b1cc4b810e9fd37959c9bb51b31=1462812244; _gat=1; _ga=GA1.2.1061361785.1462812244")  
                        .header("Host", "www.kuaidaili.com")  
                        .header("Referer", "http://www.kuaidaili.com/free/outha/")  
                        .timeout(30*1000)  
                        .get();  
                System.out.println(doc.text());  
                Matcher m = ipPtn.matcher(doc.text());  
                  
                while(m.find()){  
                    String[] strs = m.group().split(" ");  
                    if(ProxyUtil.checkProxy(strs[0],Integer.parseInt(strs[1]))){
                        System.out.println(strs[0]+":"+strs[1]);
                    }  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }     
}  