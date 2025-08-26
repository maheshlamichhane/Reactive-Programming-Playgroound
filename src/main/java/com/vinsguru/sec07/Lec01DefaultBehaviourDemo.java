package com.vinsguru.sec07;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehaviourDemo {

    private static final Logger loggger = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    public static void main(String args[]){
        var flux = Flux.create(sink -> {
            for(int i = 1; i < 3; i++){
                loggger.info("generating: {}",i);
                sink.next(i);
            }
            sink.complete();
        })
                .doOnNext(v -> loggger.info("value: {}",v));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));

        Thread.ofPlatform().start(runnable);
    }
}
