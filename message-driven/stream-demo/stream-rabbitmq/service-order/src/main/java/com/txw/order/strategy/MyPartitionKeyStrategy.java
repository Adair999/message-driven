package com.txw.order.strategy;

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;
import java.util.Random;
public class MyPartitionKeyStrategy implements PartitionKeyExtractorStrategy {
    @Override
    public Object extractKey(Message<?> message) {
        Random random =new Random();
        final int r = random.nextInt(2);
        System.out.println("r:" + r);
        return r;
    }
}