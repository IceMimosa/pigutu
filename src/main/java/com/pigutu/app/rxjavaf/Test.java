package com.pigutu.app.rxjavaf;

import javax.management.ObjectName;

public class Test {
    public static void main(String args[]){
        Observable.create(new OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for(int i=0;i<20;i++){
                    subscriber.onNext(i);
                }
            }
        }).map(new Transformer<Integer, String>() {
            @Override
            public String call(Integer from) {
                return from+",";
            }
        }).subscriber(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String next) {
                System.out.println(next);
            }
        });
    }
}
