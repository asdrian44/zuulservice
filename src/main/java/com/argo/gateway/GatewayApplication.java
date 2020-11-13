package com.argo.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication implements CommandLineRunner {





    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String password="adrian44";





    }
}
