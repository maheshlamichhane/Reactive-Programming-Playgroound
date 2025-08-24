package com.vinsguru.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    private static final Logger logger = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String args[]){
        Mono<Integer> mono = Mono.just(1);
        mono.subscribe(i -> logger.info("received: {}",i),
                    err -> logger.error("error",err),
                              () -> logger.info("completed")
                );
        System.out.println("Hello");
    }
}
