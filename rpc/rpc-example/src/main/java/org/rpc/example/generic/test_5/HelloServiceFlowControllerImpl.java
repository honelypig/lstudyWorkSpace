package org.rpc.example.generic.test_5;

import org.rpc.client.annotation.RPCService;
import org.rpc.example.demo.service.HelloSerivce;

public class HelloServiceFlowControllerImpl implements HelloSerivce {

	@Override
	@RPCService(responsibilityName="xiaoy",serviceName="LAOPOPO.TEST.SAYHELLO",maxCallCountInMinute = 40)
	public String sayHello(String str) {
		return "hello "+ str;
	}

}
