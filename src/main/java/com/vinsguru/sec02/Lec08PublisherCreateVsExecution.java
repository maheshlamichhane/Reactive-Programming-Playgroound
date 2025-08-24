package com.vinsguru.sec02;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec08PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec08PublisherCreateVsExecution.class);

    public static void main(String args[]){
        getName()
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getName(){
        return Mono.fromSupplier(() -> {
            log.info("generating name");
            Util.sleepSeconds(3);
            return Util.faker().name().firstName();
        });
    }
}
