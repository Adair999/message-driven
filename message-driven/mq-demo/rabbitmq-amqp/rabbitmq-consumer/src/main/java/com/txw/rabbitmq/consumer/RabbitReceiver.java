package com.txw.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.txw.rabbitmq.entity.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
/**
 * 消费者 {@link RabbitReceiver}
 */
@Component
public class RabbitReceiver {

    /**
     * @RabbitListener 通过注解直接创建队列、交换机、绑定、路由key
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "spring.boot.topic.queue", durable = "true"),
            exchange = @Exchange(value = "spring.boot.topic.exchange", type = "topic",
                    durable = "true", ignoreDeclarationExceptions = "true"),
            key = "boot.#"
        )
    )
    @RabbitHandler
    public void onMessage (Message message, Channel channel) throws IOException {
        System.err.println("消息内容：" + message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 手工ACK
        channel.basicAck(deliveryTag, false);
    }
    /**
     * 从配置文件获取队列、交换机、绑定、路由key等配置信息
     * @param order
     * @param channel
     * @param headers
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
                    durable = "${spring.rabbitmq.listener.order.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
                    type = "${spring.rabbitmq.listener.order.exchange.type}",
                    durable = "${spring.rabbitmq.listener.order.exchange.durable}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
            key = "${spring.rabbitmq.listener.order.key}"
        )
    )
    @RabbitHandler
    public void onMessage (@Payload Order order,
                           Channel channel,
                           @Headers Map<String, Object> headers) throws IOException {
        System.err.println("Order 消息内容：" + order);
        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手工ACK
        channel.basicAck(deliveryTag, false);
    }
}