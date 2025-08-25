package com.vinsguru.sec04.helper;

import com.vinsguru.sec02.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> fluxSink) {
        this.sink = fluxSink;
    }


    public void generate(){
        this.sink.next(Util.faker().name().firstName());
    }
}
