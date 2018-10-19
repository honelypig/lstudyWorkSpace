package org.rpc.common.rpc;

/**
 * @Description 服务审核状态
 * @author Zhangdada
 * @version v1.0
 */
public enum ServiceReviewState {
	HAS_NOT_REVIEWED, //未审核
	PASS_REVIEW,      //通过审核
	NOT_PASS_REVIEW,  //未通过审核
	FORBIDDEN         //禁用
	

}
