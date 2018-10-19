package org.rpc.monitor;

import java.io.File;

/**
 * 
 * @Description monitor端的一些参数配置
 * @author Zhangdada
 * @version v1.0
 */
public class MonitorConfig {
	
	//持久化保存的位置
	private String storePathRootDir = System.getProperty("user.home") + File.separator + "test" + File.separator + "historyMetrics.json";
	
	//每个多久时间刷盘到硬盘上，默认30s
	private int persistTime = 30; 
	
	//当手动修改历史记录的时候，历史记录发生改变的时候，是否立即进行持久化操作
	private boolean changedPersistRightnow = false;

	public String getStorePathRootDir() {
		return storePathRootDir;
	}

	public void setStorePathRootDir(String storePathRootDir) {
		this.storePathRootDir = storePathRootDir;
	}

	public int getPersistTime() {
		return persistTime;
	}

	public void setPersistTime(int persistTime) {
		this.persistTime = persistTime;
	}

	public boolean isChangedPersistRightnow() {
		return changedPersistRightnow;
	}

	public void setChangedPersistRightnow(boolean changedPersistRightnow) {
		this.changedPersistRightnow = changedPersistRightnow;
	}

}
