package com.example.demo.product.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.example.demo.product.client"})
public class FeignConfiguration {
}
