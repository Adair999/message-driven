package com.txw.order.controller;

import com.txw.order.mq.MqProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private MqProducer mqProducer;
    private static int orderId = 1;
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        //String storageResult = restTemplate.getForEntity("http://service-storage/storage", String.class).getBody();
        //System.out.println("扣减库存结果：" + storageResult);
        String orderResult = "order：" + orderId + " success";
        // 新增订单成功后，发送消息
        mqProducer.sendMessage(orderResult, null);
        orderId++;
        return orderResult;
    }
}