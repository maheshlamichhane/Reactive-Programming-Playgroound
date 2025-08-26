package com.vinsguru.sec09;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec09.applications.OrderService;
import com.vinsguru.sec09.applications.User;
import com.vinsguru.sec09.applications.UserService;

public class Lec10FluxFlatMap {

    public static void main(String[] args) {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
