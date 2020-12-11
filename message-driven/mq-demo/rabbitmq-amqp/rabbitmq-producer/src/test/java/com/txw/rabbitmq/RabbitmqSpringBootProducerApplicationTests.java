package com.txw.rabbitmq;

import com.txw.rabbitmq.entity.Order;
import com.txw.rabbitmq.producer.RabbitSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@SpringBootTest
class RabbitmqSpringBootProducerApplicationTests {
    @Autowired
    private RabbitSender rabbitSender;

    //@Test
    void testSend() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("num", "1000");
        properties.put("sendTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        String msg = "Spring Boot @ yuan deng ta";
        rabbitSender.send(msg, properties);
    }
    @Test
    void testSendOrder() {
        Order order = new Order("1000", "Order-2", "Order-2 description...");
        rabbitSender.sendOrder(order);
    }
}