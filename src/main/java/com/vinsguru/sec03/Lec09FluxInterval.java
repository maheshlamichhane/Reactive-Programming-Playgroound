package com.vinsguru.sec03;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {

    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
