package com.retailer.ordermanagement.config;

import com.retailer.ordermanagement.harness.OrderManagementHarness;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class HarnessConfiguration {

    @Bean
    public OrderManagementHarness orderManagementHarness() {
        return new OrderManagementHarness();
    }
}






