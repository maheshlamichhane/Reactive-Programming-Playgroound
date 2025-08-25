package com.vinsguru.sec03;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec03.assignment.StockPriceObserver;
import com.vinsguru.sec03.client.ExternalServiceClient;

public class Lec12Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();
        client.getPriceChanges()
                .subscribe(subscriber);

        Util.sleepSeconds(20);
    }
}
