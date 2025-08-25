package com.vinsguru.sec05;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec09Timeout {

    private static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);

    public static void main(String[] args) {
        var mono = getProductName()
                .timeout(Duration.ofSeconds(3),fallback());

        Util.sleepSeconds(5);
    }


    private static Mono<String> getProductName(){
        return Mono.fromSupplier(() -> "service-"+Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(1));
    }

    private static Mono<String> fallback(){
        return Mono.fromSupplier(() -> "fallback-"+Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(300))
                .doFirst(() -> System.out.println("do first"));
    }
}
