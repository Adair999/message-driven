package com.txw.rabbitmq.producer;

import com.txw.rabbitmq.entity.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.UUID;
/**
 * TODO {@link RabbitSender}
 */
@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * Confirm 确认消息
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("CorrelationData: " + correlationData);
            System.out.println("ack: " + ack);
            if (!ack) {
                System.err.println("异常处理...");
            }
        }
    };
    /**
     * return 返回消息
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode,
                                    String replyText, String exchange, String routingKey) {
            System.out.println("return exchange: " + exchange + ", routingKey: " + routingKey
                    + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };
    /**
     * 发送消息
     * @param msg 消息主体
     * @param properties 消息属性
     */
    public void send (Object msg, Map<String, Object> properties) {
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        Message message = MessageBuilder.createMessage(msg, messageHeaders);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData();
        // 全局唯一 id + 时间戳
        // message id
        correlationData.setId(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("spring.boot.topic.exchange", "boot.msg", message, correlationData);
    }
    /**
     * 发送 Order 消息
     * @param order 对象
     */
    public void sendOrder (Order order) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("spring.boot.order.exchange", "order.*", order, correlationData);
    }
}