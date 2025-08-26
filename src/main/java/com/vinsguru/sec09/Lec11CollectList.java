package com.vinsguru.sec09;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

public class Lec11CollectList {

    public static void main(String args[]){

        Flux.range(1,10)
                .collectList()
                .subscribe(Util.subscriber());
    }
}
