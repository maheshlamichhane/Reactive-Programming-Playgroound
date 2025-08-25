package com.vinsguru.sec04;

import com.vinsguru.sec01.publisher.SubscriptionImpl;
import com.vinsguru.sec01.subscriber.SubscriberImpl;
import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        produceOnDemand();
    }


    public static void produceOnDemand(){
        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for(int i=0; i<request && !fluxSink.isCancelled(); i++){
                    var name = Util.faker().name().firstName();
                    log.info("generated:{}",name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);

        subscriber.getSubscription().request(4);
    }

    public static void produceByFixedSize(){
        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxSink -> {
            for(int i=0; i<10; i++){
                var name = Util.faker().name().firstName();
                log.info("generated:{}",name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);

        subscriber.getSubscription().request(15);
    }

}
