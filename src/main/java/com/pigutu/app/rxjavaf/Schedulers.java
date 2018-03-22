package com.pigutu.app.rxjavaf;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Schedulers {
    private static final Scheduler ioSchedule = new Scheduler(Executors.newSingleThreadExecutor());
    public static Scheduler io(){
        return ioSchedule;
    }
}
