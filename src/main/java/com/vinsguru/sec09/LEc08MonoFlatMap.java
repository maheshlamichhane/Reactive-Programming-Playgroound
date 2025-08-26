package com.vinsguru.sec09;

import com.vinsguru.sec02.common.Util;
import com.vinsguru.sec09.applications.PaymentService;
import com.vinsguru.sec09.applications.UserService;

public class LEc08MonoFlatMap {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());
    }
}
