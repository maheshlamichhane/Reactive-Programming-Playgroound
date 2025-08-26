package com.vinsguru.sec06;

import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublihserAutoConnect {

    private static final Logger log = LoggerFactory.getLogger(Lec03HotPublihserAutoConnect.class);

    public static void main(String[] args) {

        var movieFlux = movieStream().publish().autoConnect(0);
//        var nivueFkyx = movieStream().publish().refCount(2);
        Util.sleepSeconds(2);
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);

    }


    //movie theater
    private static Flux<String> movieStream(){
        return Flux.generate(
                () -> {
                    log.info("received the reqeust");
                    return 1;
                },
                (state,sink) -> {
                    var scene = "movie scene"+state;
                    log.info("playing {}",scene);
                    sink.next(scene);
                    return ++state;
                }
        ).take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
