package com.vinsguru.sec05;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {
        Mono.just("mahesh")
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());
    }
}
