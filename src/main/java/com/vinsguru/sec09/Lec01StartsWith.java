package com.vinsguru.sec09;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec09.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Lec01StartsWith {

    private static final Logger log = LoggerFactory.getLogger(Lec01StartsWith.class);

    public static void main(String[] args) {
        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                        .subscribe(Util.subscriber());

//        Util.sleepSeconds(3);
    }

    private static void demo1(){
        producer1()
                .startWith(List.of(-1,-2))
                .subscribe(Util.subscriber());
    }

    private static void demo2(){
        producer1()
                .startWith(List.of())
                .subscribe(Util.subscriber());
    }

    private static void demo3(){
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    private static void demo4(){
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber());
    }


    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .doOnSubscribe(s -> log.info("subscribing to publisher one"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(51,52,53)
                .doOnSubscribe(s -> log.info("subscribing to publisher two"))
                .delayElements(Duration.ofMillis(10));
    }
}
