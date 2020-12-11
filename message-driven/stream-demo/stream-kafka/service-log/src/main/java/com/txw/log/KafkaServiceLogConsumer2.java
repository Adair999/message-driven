package com.txw.log;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class KafkaServiceLogConsumer2 {
    public static void main(String[] args) {
        new SpringApplicationBuilder().profiles("consumer2")
                .sources(KafkaServiceLogConsumer2.class)
                .run(args);
    }
}