package com.vinsguru.sec06.assignment;

import com.vinsguru.sec02.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private Flux<Order> orderFluix;
    
    public Flux<Order> orderStream(){
        if(Objects.isNull(orderFluix)){
            this.orderFluix = getOrderStream();
        }
        return this.orderFluix;
    }

    private Flux<Order> getOrderStream(){
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext(o -> log.info("{}",o))
                .publish()
                .refCount(2);
    }

    private Order parse(String message){
        var arr = message.split("");
        return new Order(
                arr[0],
                Integer.parseInt(arr[1]),
                Integer.parseInt(arr[2])
        );
    }


}
