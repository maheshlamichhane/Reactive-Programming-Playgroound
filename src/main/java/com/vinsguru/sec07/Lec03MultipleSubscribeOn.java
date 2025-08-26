package com.vinsguru.sec07;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribeOn {

    private static final Logger loggger = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
                    for(int i = 1; i < 3; i++){
                        loggger.info("generating: {}",i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .subscribeOn(Schedulers.newParallel("vins"))
                .doOnNext(v -> loggger.info("value: {}",v))
                .doFirst(() -> loggger.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> loggger.info("first2"));



        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);
    }
}
