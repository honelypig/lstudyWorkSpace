package org.rpc.console.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.rpc.common.rpc.ServiceReviewState;
import org.rpc.console.info.kaleidoscope.KaleidoscopeInfo;
import org.rpc.console.model.ManagerRPC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @Description 管理员管理页面spring mvc的controller
 * @author Zhangdada
 * @version v1.0
 * @date 2018年10月19日
 */
@Controller
public class MonitorCoreController {

	private static final Logger logger = LoggerFactory.getLogger(MonitorCoreController.class);

	@Resource
	private KaleidoscopeInfo kaleidoscopeInfo;

	@ResponseBody
	@RequestMapping(value = "/index.do")
	// 首页初始化
	public Map<String, Object> initTable(@RequestParam Map<String, Object> requestMap) {
		int pageSize = 10;
		int offset = 1;
		try {
			pageSize = Integer.valueOf("" + requestMap.get("limit")); // 页大小
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
		}
		try {
			offset = Integer.valueOf("" + requestMap.get("offset"));
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
		}

		Map<String, Object> resultMap = kaleidoscopeInfo.findInfoByPage(pageSize, offset);
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/manager.do")
	// 管理请求
	public Map<String, Object> managerRpc(ManagerRPC managerRPC) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Boolean operationFlag = false;
		
		// 禁用
		if (managerRPC.getManagerType() == 1) {
			operationFlag = kaleidoscopeInfo.notifyReviewService(managerRPC.getHost(), managerRPC.getPort(), managerRPC.getServiceName(),ServiceReviewState.FORBIDDEN);
		}
		// 降级
		if (managerRPC.getManagerType() == 2) {
			operationFlag = kaleidoscopeInfo.notifyServiceDegrade(managerRPC.getHost(), managerRPC.getPort(), managerRPC.getServiceName());
		}
		// 审核通过
		if (managerRPC.getManagerType() == 5) {
			operationFlag = kaleidoscopeInfo.notifyReviewService(managerRPC.getHost(), managerRPC.getPort(), managerRPC.getServiceName(),ServiceReviewState.PASS_REVIEW);
		}

		resultMap.put("status", operationFlag);
		return resultMap;
	}

}
