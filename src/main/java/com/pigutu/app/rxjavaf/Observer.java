package com.pigutu.app.rxjavaf;

public interface Observer<T> {
    void onCompleted();
    void onError(Throwable throwable);
    void onNext(T next);
}
