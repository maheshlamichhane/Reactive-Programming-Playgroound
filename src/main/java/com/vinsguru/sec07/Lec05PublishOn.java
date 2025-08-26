package com.vinsguru.sec07;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05PublishOn {

    private static final Logger loggger = LoggerFactory.getLogger(Lec05PublishOn.class);

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads","true");

        var flux = Flux.create(sink -> {
                    for(int i = 1; i < 3; i++){
                        loggger.info("generating: {}",i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(v -> loggger.info("value: {}",v))
                .doFirst(() -> loggger.info("first1-{}",Thread.currentThread().isVirtual()))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> loggger.info("first2"));

        // publish on is for downstream
        // subscribe on is for upstream



        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());

        Thread.ofPlatform().start(runnable1);

        Util.sleepSeconds(2);
    }
}
