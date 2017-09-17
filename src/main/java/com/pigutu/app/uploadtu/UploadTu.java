package com.pigutu.app.uploadtu;

import com.pigutu.app.entity.ImageSetListEntity;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;

public class UploadTu {
    private static int start = 1;
    private static int id = 4969;
    private static ImageSetListEntity imageSetListEntity=new ImageSetListEntity();
    public static void main(String args[]) {
        upload(id,"翟欣欣合集六(共六集)","20170917/"+id+"/7.jpg",502,15,0,"网络","翟欣欣,苏享茂,毒妻",500,0);
        //findDirectory();
    }
    private static void upload(int id,String title,String coverUrl,int likeCount,int imgCount,int commentCount,String category,String label,int viewCount,int recommendCount){
        String coverUrlTop = "20170917/"+id+"/";
        String realUrl="";
        try {
            Jsoup.connect("http://www.pigutu.com/insertImageSetList?title="+title+"&coverUrl="+coverUrl+"&likeCount="+likeCount+"&imgCount="+imgCount+"&commentCount="+commentCount+"&category="+category+"&label="+label+"&viewCount="+viewCount+"&recommendCount="+recommendCount+"&key=26535989jidsf25").ignoreContentType(true).get();
            for(int i =1;i<=imgCount;i++){
                String url = coverUrlTop+i+".jpg";
                Jsoup.connect("http://www.pigutu.com/insertImageSet?allImagesId="+id+"&url="+url+"&key=26535989jidsf25").ignoreContentType(true).get();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void findDirectory() {
        File file = new File("C://app//20170917");
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println( file2.getName()+"文件夹:" + file2.getAbsolutePath());
                        //imageSetListEntity.setId((long)Integer.parseInt(file2.getName().split(",")[0]));
                        findFile(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private static void findFile(String directory) {
        File file = new File(directory);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    file2.renameTo(new File(directory+"//"+String.valueOf(start++)+".jpg"));
                    System.out.println(start);
                }
                start=1;
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
