package com.pigutu.app.rxjavaf;

public class Observable<T> {
    OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> OnSubscribe){
        this.onSubscribe = OnSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe){
        return new Observable<T>(onSubscribe);
    }

    public void subscriber(Subscriber<? super T> subscribe){
        onSubscribe.call(subscribe);
    }

    public <R> Observable<R>  map(Transformer<? super T,? extends R> transformer){
        return create(new MapOnSubscribe<T,R>(this,transformer));
    }
}
