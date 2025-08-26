package com.vinsguru.sec07;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06Parallel {

    private static final Logger logger = LoggerFactory.getLogger(Lec06Parallel.class);

    public static void main(String[] args) {
        Flux.range(1,10)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .map(Lec06Parallel::process)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static int process(int i){
        logger.info("time consuming task {}",i);
        Util.sleepSeconds(1);
        return i*2;
    }
}
