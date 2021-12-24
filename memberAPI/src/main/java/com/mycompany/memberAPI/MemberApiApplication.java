package com.mycompany.memberAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class MemberApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberApiApplication.class, args);
	}

    @GetMapping("/info")
    public String info(@Value("${server.port}") String port) {
        return "User 서비스의 기본 동작 Port: {" + port + "}";
    }
}
