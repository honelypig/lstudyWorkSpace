package com.zda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@Configuration
public class TestController {
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@GetMapping("/router")
	public String router(){
		RestTemplate restTemplate=getRestTemplate();
		//这个url的格式是 http://  +服务名称，是来自服务提供者的配置文件   +接口地址
		String jso=restTemplate.getForObject("http://first-police/call/1", String.class);
		return jso;
	}
	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/list")
	@ResponseBody
	public String serviceCount() {
		List<String> names = discoveryClient.getServices();
		for(String serviceId : names) {
			List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
			System.out.println(serviceId + ": " + instances.size());
		}
		return "";
	}
	
	@GetMapping("/meta")
	@ResponseBody
	public String getMetadata() {
		List<ServiceInstance> instances = discoveryClient.getInstances("EK_Provider");
		for(ServiceInstance ins : instances) {
			String name = ins.getMetadata().get("company-name");
			System.out.println(ins.getPort() + "---" + name);
		}
		return "";
	}
}
