package com.vinsguru.sec09;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec09.applications.OrderService;
import com.vinsguru.sec09.applications.UserService;

public class Lec09MonoFlatMapMany {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
