package com.vinsguru.sec06;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec06.assignment.ExternalServiceClient;
import com.vinsguru.sec06.assignment.InventoryService;
import com.vinsguru.sec06.assignment.RevenueService;

public class Lec06Assignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueServie = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueServie::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("revenue"));

        inventoryService.stream().subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(30);

    }
}
