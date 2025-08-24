package com.vinsguru.sec02;

import com.vinsguru.sec02.client.ExternalServiceClient;
import com.vinsguru.sec02.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec10NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec10NonBlockingIO.class);

    public static void main(String args[]){
        var client = new ExternalServiceClient();
        log.info("starting");

        for(int i=1; i<5;i++){
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        System.out.println("HEllo");
        Util.sleepSeconds(2);
    }
}
