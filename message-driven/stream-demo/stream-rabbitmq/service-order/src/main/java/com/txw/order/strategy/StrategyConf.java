package com.txw.order.strategy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class StrategyConf {
    @Bean
    MyPartitionKeyStrategy myPartitionKeyStrategy() {
        return new MyPartitionKeyStrategy();
    }
}