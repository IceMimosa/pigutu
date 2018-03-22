package com.pigutu.app.rxjavaf;

import javax.management.ObjectName;

public class Test {
    public static void main(String args[]){
        Observable.create(new OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<20;i++){
                    subscriber.onNext(i);
                    System.out.println("name2 = "+Thread.currentThread().getName());
                }
            }
        }).map(new Transformer<Integer, String>() {
            @Override
            public String call(Integer from) {
                return from+",";
            }
        }).observeOn(Schedulers.io()).subscriber(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String next) {
                System.out.println("name = "+Thread.currentThread().getName());
                System.out.println(next);
            }
        });
    }
}
