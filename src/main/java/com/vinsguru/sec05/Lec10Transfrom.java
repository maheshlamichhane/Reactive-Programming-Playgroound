package com.vinsguru.sec05;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Lec10Transfrom {

    private static final Logger logger = LoggerFactory.getLogger(Lec10Transfrom.class);

    record Customer(int id,String name){}
    record PurchaseOrder(String productName,int price,int quantity){}

    public static void main(String[] args) {

        getCustomers()
                .transform(addDebugger())
                .subscribe();

        getCustomers()
                .transform(addDebugger())
                .doOnNext(i -> logger.info("received: {}",i))
                .doOnComplete(() -> logger.info("completed"))
                .doOnError(err -> logger.error("error",err))
                .subscribe();
    }


    private static Flux<Customer> getCustomers(){
        return Flux.range(1,3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders(){
        return Flux.range(1,5)
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(),i,i*10));
    }

    private static <T>UnaryOperator<Flux<T>> addDebugger(){

        return flux -> flux
                .doOnNext(i -> logger.info("received: {}",i))
                .doOnComplete(() -> logger.info("completed"))
                .doOnError(err -> logger.error("error",err));

    }
}
