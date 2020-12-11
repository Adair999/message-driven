package com.txw.log.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
/**
 * MQ消费者
 */
@Slf4j
@EnableBinding(Processor.class)
public class MqConsumer {
    // 基于注解
    @StreamListener(Processor.INPUT)
    public void receiver(Message mesage) {
        if (mesage == null) {
            return;
        }
        String payLoad = (String) mesage.getPayload();
        if (payLoad.contains("success")) {
            log.info("收到MQ消息={}，记录下单成功日志", payLoad);
        } else {
            log.info("收到MQ消息={}，记录下单失败日志", payLoad);
        }
    }
    @Autowired
    private Processor processor;
    // 基于Spring Message API监听数据
    //@Bean
    public ApplicationRunner runner() {
        return args -> {
            processor.input().subscribe(new MessageHandler() {
                @Override
                public void handleMessage(Message<?> message) throws MessagingException {
                    String payLoad = new String((byte[]) message.getPayload());
                    if (payLoad.contains("success")) {
                        log.info("收到MQ消息={}，记录下单成功日志", payLoad);
                    } else {
                        log.info("收到MQ消息={}，记录下单失败日志", payLoad);
                    }
                }
            });
        };
    }
}