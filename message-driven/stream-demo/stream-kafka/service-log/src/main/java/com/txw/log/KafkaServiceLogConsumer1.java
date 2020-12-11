package com.txw.log;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class KafkaServiceLogConsumer1 {
   public static void main(String[] args) {
        new SpringApplicationBuilder().profiles("consumer1")
                .sources(KafkaServiceLogConsumer1.class)
                .run(args);
    }
}