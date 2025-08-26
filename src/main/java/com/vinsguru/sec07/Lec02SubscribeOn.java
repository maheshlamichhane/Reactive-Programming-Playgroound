package com.vinsguru.sec07;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {

    private static final Logger loggger = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
                    for(int i = 1; i < 3; i++){
                        loggger.info("generating: {}",i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> loggger.info("value: {}",v))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> loggger.info("first2"))
                .doFirst(() -> loggger.info("first1"));


        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber());

        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);

        Util.sleepSeconds(2);
    }
}
