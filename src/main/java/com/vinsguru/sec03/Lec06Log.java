package com.vinsguru.sec03;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Lec06Log {
    public static void main(String[] args) {
        Flux.range(1,5)
                .log()
                .subscribe(Util.subscriber());


    }
}
