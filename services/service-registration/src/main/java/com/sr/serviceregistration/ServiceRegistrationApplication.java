package com.sr.serviceregistration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistrationApplication.class, args);
    }
    @Bean
    NewTopic nofitication(){
        return new NewTopic("notification", 1, (short) 1);
    }
}
