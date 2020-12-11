package com.txw.log.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
/**
 * MQ消费者
 */
@Slf4j
@EnableBinding(Processor.class)
public class MqConsumer {
    /**
     * 监听队列，完成消费
     */
    @StreamListener(Processor.INPUT)
    public void receiver(Message message) throws Exception {
        if (null == message) {
            return;
        }
        try {
            String payload = (String) message.getPayload();
            if (payload.contains("success")) {
                log.info("收到MQ消息= {}，记录下单成功日志", payload);
            } else {
                log.info("收到MQ消息= {}，记录下单失败日志", payload);
            }
        } catch (Exception e) {
            log.error("接收消息出现异常:{}", e);
            throw new RuntimeException(e);
        }
    }
}