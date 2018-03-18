package com.pigutu.app.rxjavaf;

public class MapOnSubscribe<T,R> implements OnSubscribe<R> {
    Observable<T> source;
    Transformer<? super T,?extends R> transformer;
    public MapOnSubscribe(Observable<T> source,Transformer<? super T,?extends R> transformer){
        this.source=source;
        this.transformer=transformer;
    }
    @Override
    public void call(Subscriber<? super R> subscriber) {
        source.subscriber(new MapSubscriber<>(subscriber,transformer));
    }
}
