package com.retailer.ordermanagement;

import com.retailer.ordermanagement.config.OpenApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ComponentScan(basePackages = {"com.retailer.ordermanagement.harness"})
@Import(OpenApiConfig.class)
@Profile("dev")
public class HarnessInvoker {

    public static void main(String[] args) {
        SpringApplication.run(HarnessInvoker.class, args);
    }
}