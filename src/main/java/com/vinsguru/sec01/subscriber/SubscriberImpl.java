package com.vinsguru.sec01.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubscriberImpl implements Subscriber {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;


    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onNext(Object email) {
        logger.info("{}",email);

    }

    @Override
    public void onError(Throwable throwable) {
        logger.error("error",throwable);
    }

    @Override
    public void onComplete() {
        logger.info("completed");
    }
}
