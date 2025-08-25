package com.vinsguru.sec03;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec05FluxRange {

    public static void main(String[] args) {

       Flux.range(1,10)
               .subscribe(Util.subscriber());
       // assignment
        Flux.range(1,10)
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

    }
}
