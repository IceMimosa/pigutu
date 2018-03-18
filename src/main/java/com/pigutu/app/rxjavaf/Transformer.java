package com.pigutu.app.rxjavaf;

public interface Transformer<T,R> {
    R call(T from);
}
