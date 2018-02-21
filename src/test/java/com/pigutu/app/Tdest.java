package com.pigutu.app;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tdest {
    public static void main(String args[]) {
        /*int max=20;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
        System.out.println(s);*/
        int a[] = {1, 2, 5, 4, 3, 9, 8, 78, 65, 57};
        sort(a,0,9);
        for(int i:a){
            System.out.println(i);
        }
    }

    public static int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }
}
