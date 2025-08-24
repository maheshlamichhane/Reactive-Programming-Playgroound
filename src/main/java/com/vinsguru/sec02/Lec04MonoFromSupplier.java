package com.vinsguru.sec02;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec04MonoFromSupplier {

    private static final Logger logger = LoggerFactory.getLogger(Lec04MonoFromSupplier.class);

    public static void main(String args[]){

        var list = List.of(1,2,3);
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());
    }


    private static int sum(List<Integer> list){
        logger.info("finding the sum of {}",list);
        return list.stream().mapToInt(a -> a).sum();
    }

    // use mono only when there already data in memory.
    // you can't use mono when cpu intensive task is performed like Mono.just(sum(list))
    // for these scenarios use Mono.fromSupplier(() -> sum(list)).subscribe(Util.subscriber)
}
