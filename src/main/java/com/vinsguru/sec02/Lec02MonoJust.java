package com.vinsguru.sec02;

import com.vinsguru.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String args[]){
        Mono<String> mono = Mono.just("vins");
        var subscriber = new SubscriberImpl();
        mono.subscribe((value) -> System.out.println(value));
        System.out.println("hello");
//        subscriber.getSubscription().request(3);
//        subscriber.getSubscription().request(3);
//        subscriber.getSubscription().cancel();
    }
}
