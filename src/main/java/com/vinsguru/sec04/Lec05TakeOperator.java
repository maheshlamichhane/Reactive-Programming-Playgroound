package com.vinsguru.sec04;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {


    public static void main(String[] args) {
        takeUntil();
    }

    private static void take(){
        Flux.range(1,10)
                .take(3)
                .subscribe(Util.subscriber());
    }

    private static void takeWhile(){
        Flux.range(1,10)
                .log("take")
                .takeWhile(i -> i<5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil(){
        Flux.range(1,10)
                .log("take")
                .takeUntil( i -> i<5)
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
