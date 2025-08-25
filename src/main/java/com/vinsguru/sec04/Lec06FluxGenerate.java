package com.vinsguru.sec04;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {

    public static void main(String[] args) {

        //using synchronous sink we can emit only one value
        // with flux create we developer have complete control
        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);
            synchronousSink.complete();
        }).subscribe(Util.subscriber());
    }
}
