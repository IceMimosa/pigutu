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

    public Observable<T> subscribeOn(Scheduler scheduler){
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                scheduler.createWorker().schedule(new Runnable() {
                    @Override
                    public void run() {
                        Observable.this.onSubscribe.call(subscriber);
                    }
                });
            }
        });
    }

    public Observable<T> observeOn(Scheduler scheduler){
        return Observable.create(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Scheduler.Worker worker = scheduler.createWorker();
                Observable.this.onSubscribe.call(new Subscriber<T>() {
                    @Override
                    public void onCompleted() {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onCompleted();
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onError(throwable);
                            }
                        });
                    }

                    @Override
                    public void onNext(T next) {
                        worker.schedule(new Runnable() {
                            @Override
                            public void run() {
                                subscriber.onNext(next);
                            }
                        });
                    }
                });
            }
        });
    }
}
