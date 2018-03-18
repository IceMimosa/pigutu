package com.pigutu.app.rxjavaf;

public class MapSubscriber<T,R> extends Subscriber<R> {
    Subscriber<? super T> subscriber;
    Transformer<? super R,? extends T> transformer;
    public MapSubscriber(Subscriber<? super T> subscriber,Transformer<? super R,? extends T> transformer){
        this.subscriber=subscriber;
        this.transformer=transformer;
    }
    @Override
    public void onCompleted() {
        subscriber.onCompleted();
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onNext(R next) {
        subscriber.onNext(transformer.call(next));
    }
}
