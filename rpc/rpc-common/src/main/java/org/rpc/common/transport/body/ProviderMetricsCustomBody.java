package org.rpc.common.transport.body;

import java.util.List;

import org.rpc.common.exception.remoting.RemotingCommmonCustomException;
import org.rpc.common.rpc.MetricsReporter;

/**
 * @Description 管理员发送给监控中心的信息
 * @author Zhangdada
 * @version v1.0
 */
public class ProviderMetricsCustomBody implements CommonCustomBody{
	private List<MetricsReporter> metricsReporter;

	@Override
	public void checkFields() throws RemotingCommmonCustomException {
	}

	public List<MetricsReporter> getMetricsReporter() {
		return metricsReporter;
	}

	public void setMetricsReporter(List<MetricsReporter> metricsReporter) {
		this.metricsReporter = metricsReporter;
	}
	
}
