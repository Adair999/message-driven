package com.txw.order.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import java.util.Map;
/**
 * MQ生产者
 */
@Slf4j
@Component
@EnableBinding(Processor.class)
public class MqProducer {
    @Autowired
    private Processor processor;
    public String sendMessage(Object message, Map<String, Object> properties) {
        try {
            MessageHeaders messageHeaders = new MessageHeaders(properties);
            Message msg = MessageBuilder.createMessage(message, messageHeaders);
            boolean sendStatus = processor.output().send(msg);
            System.out.println("发送数据：" + message + ",sendStatus：" + sendStatus);
        } catch (Exception e) {
            System.out.println("----------error--------");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
}