package com.vinsguru.sec03;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec03.client.ExternalServiceClient;

public class Lec08nonBlockingStreamingMessages {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for(int i=1;i<50;i++){
            client.getNames().subscribe(Util.subscriber("Dummy "+i));
        }
        Util.sleepSeconds(6);
    }

}
