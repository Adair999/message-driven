package com.txw.order.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
public interface MySource {
    String OUTPUT = "myOutput";
    @Output("myOutput")
    MessageChannel output();
}