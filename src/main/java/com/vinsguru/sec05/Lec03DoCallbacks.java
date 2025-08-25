package com.vinsguru.sec05;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    private static final Logger log = LoggerFactory.getLogger(Lec03DoCallbacks.class);

    public static void main(String[] args) {
        Flux.<Integer>create(fluxSink -> {
            log.info("producer begins");
            for(int i=0; i<4; i++){
                fluxSink.next(i);
            }
            fluxSink.complete();
            log.info("producer ends");
        })
                .doOnComplete(() -> log.info("doOnComplete-1"))
                .doFirst(() -> log.info("doFirst-1"))
                .doOnNext(item -> log.info("doOnNext-1: {}",item))
                .doOnSubscribe(subscription -> log.info("doOnSubscribe-1:{}",subscription))
                .doOnRequest(request -> log.info("doOnRequest-1: {}",request))
                .doOnError(error -> log.info("doOnError-1: {}",error.getMessage()))
                .doOnTerminate(() -> log.info("doOnTerminate-1"))
                .doOnCancel(() -> log.info("doOnCancel-1"))
                .doOnDiscard(Object.class,o -> log.info("doOnDiscard-1:{}",o))
                .doFinally(signal -> log.info("doFinally-1:{}",signal))
                .subscribe(Util.subscriber());
    }

}
