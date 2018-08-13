package com.zda;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PersonAPP {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PersonAPP.class).web(true).run(args);

	}

}
