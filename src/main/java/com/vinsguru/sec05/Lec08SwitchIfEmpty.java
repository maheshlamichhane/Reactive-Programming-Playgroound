package com.vinsguru.sec05;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {

    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(i -> i > 11)
                .switchIfEmpty(falback())
                .subscribe(Util.subscriber());
    }



    private static Flux<Integer> falback(){
        return Flux.range(100,3);
    }
}
