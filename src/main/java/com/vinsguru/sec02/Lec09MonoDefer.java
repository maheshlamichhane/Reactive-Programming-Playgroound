package com.vinsguru.sec02;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec09MonoDefer {

    private static final Logger logger = LoggerFactory.getLogger(Lec09MonoDefer.class);

    public static void main(String args[]){

        Mono.defer(() -> createPublisher())
                .subscribe(Util.subscriber());
//        createPublisher();
//                .subscribe(Util.subscriber());
    }


    private static Mono<Integer> createPublisher(){
        logger.info("creating publisher");
        var list = List.of(1,2,3);
        Util.sleepSeconds(3);
        return Mono.fromSupplier(() -> sum(list));
    }


    // time-consuming task
    private static int sum(List<Integer> list){
        logger.info("finding the sum of {}",list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }
}
