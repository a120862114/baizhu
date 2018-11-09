package com.bzdepot.communal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableAutoConfiguration
@MapperScan("com.bzdepot.communal.mapper")
public class CommunalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunalApplication.class, args);
	}
}
