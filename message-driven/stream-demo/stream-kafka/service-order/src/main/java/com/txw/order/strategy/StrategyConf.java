package com.txw.order.strategy;

import org.springframework.context.annotation.Bean;
//@Configuration
public class StrategyConf {
    @Bean
    MyPartitionKeyStrategy myPartitionKeyStrategy() {
        return new MyPartitionKeyStrategy();
    }
}