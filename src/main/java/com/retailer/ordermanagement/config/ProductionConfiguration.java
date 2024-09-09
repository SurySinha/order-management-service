package com.retailer.ordermanagement.config;

import com.retailer.ordermanagement.OrderManagementServiceApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProductionConfiguration {

    @Bean
    public OrderManagementServiceApplication orderManagementServiceApplication() {
        return new OrderManagementServiceApplication();
    }
}
