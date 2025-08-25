package com.vinsguru.sec04;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    public static void main(String[] args) {
    demo1();
    }


    public static void demo1(){
        Flux.generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if(country.equalsIgnoreCase("canada")) {
                        synchronousSink.complete();
                    }
                }).subscribe(Util.subscriber());
        }
    
    
    public static void demo2() {
        Flux.generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .takeUntil(c -> c.toString().equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }
    
}