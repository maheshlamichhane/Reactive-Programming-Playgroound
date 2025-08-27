package com.vinsguru.sec10;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    public static void main(String[] args) {
        demo4();
        Util.sleepSeconds(5);
    }

    private static void demo1(){
        eventStream()
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo2(){
        eventStream()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo3(){
        eventStream()
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }

    private static void demo4(){
        eventStream()
                .bufferTimeout(3,Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "event-"+(i+1));
    }
}
