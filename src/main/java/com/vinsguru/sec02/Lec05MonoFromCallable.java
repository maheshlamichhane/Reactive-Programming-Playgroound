package com.vinsguru.sec02;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromCallable {

    private static final Logger logger = LoggerFactory.getLogger(Lec05MonoFromCallable.class);

    public static void main(String args[]){

        var list = List.of(1,2,3);
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());
    }

    // callable provides exception handling


    private static int sum(List<Integer> list){
        logger.info("finding the sum of {}",list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
