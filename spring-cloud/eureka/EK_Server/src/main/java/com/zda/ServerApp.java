package com.zda;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
//用来作为服务器
@SpringBootApplication
@EnableEurekaServer
public class ServerApp {  //访问地址  http://localhost:8761/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
new SpringApplicationBuilder(ServerApp.class).web(true).run(args);
	}

}
