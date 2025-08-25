package com.vinsguru.sec05;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {

        Flux.range(1,10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorContinue((ex,obj) -> log.error("=>",obj,ex))
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    public static void errors(){
        Mono.just(5)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorResume(ArithmeticException.class,ex -> fallback())
                .onErrorResume(ex -> fallback())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn(){
        Flux.range(1,10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorReturn(-1)
                .onErrorReturn(ArithmeticException.class,-1)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback(){
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10,100));
    }
}
