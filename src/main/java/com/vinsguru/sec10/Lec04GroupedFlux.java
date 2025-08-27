package com.vinsguru.sec10;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec04GroupedFlux {

    public static final Logger log = LoggerFactory.getLogger(Lec04GroupedFlux.class);

    public static void main(String[] args) {
        Flux.range(1,30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i %2)
                .flatMap(Lec04GroupedFlux::processEvents)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<Integer,Integer> groupedFlux){
        log.info("received flux for {}",groupedFlux.key());
        return groupedFlux.doOnNext(i -> log.info("key: {}, item: {}",groupedFlux.key(),i))
                .then();
    }
}
