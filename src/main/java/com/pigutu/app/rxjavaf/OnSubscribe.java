package com.pigutu.app.rxjavaf;

public interface OnSubscribe<T> {
        void call(Subscriber<? super T> subscriber);
    }