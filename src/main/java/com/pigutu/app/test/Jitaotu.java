package com.pigutu.app.test;

public class Jitaotu {
    public static void main(String args[]) {
        AAA aa=new AAA();
        Object cc ="bb";
        aa.aaa("bb");
    }
    static class AAA{
        public void aaa(String a){
            System.out.println(a+"aaa");
        }
        public void aaa(Object a){
            System.out.println(a+"bbb");
        }
    }
}
