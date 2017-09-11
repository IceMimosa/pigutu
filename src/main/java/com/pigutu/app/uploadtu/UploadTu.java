package com.pigutu.app.uploadtu;

import com.pigutu.app.entity.ImageSetListEntity;

import java.io.File;
import java.util.Calendar;
import java.util.List;

public class UploadTu {
    private static int start = 10000;
    private static ImageSetListEntity imageSetListEntity=new ImageSetListEntity();
    public static void main(String args[]) {
        findDirectory();
    }

    private static void findDirectory() {
        File file = new File("C://app//tu");
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println( file2.getName()+"文件夹:" + file2.getAbsolutePath());
                        imageSetListEntity.setId(Integer.parseInt(file2.getName().split(",")[0]));
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
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
