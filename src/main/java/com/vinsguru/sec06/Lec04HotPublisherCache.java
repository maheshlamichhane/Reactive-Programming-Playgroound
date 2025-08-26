package com.vinsguru.sec06;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {

        var stockFlux = stockStream().replay().autoConnect(0);
        Util.sleepSeconds(4);
        log.info("sam joining");
        stockFlux
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(4);
        log.info("mike joining");

        Util.sleepSeconds(3);
        stockFlux
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);

    }


    //stock price changes
    private static Flux<Integer> stockStream(){
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10,100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emiting price: {}",price))
                .cast(Integer.class);
    }
}
