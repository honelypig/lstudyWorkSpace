package persistence;

import java.util.Date;

public class RedisTable {
	  private Long redisId; //保存redis的主键ID
	    private String redisType;//redis的类型如：set/list/hash/sortedset/string
	    private String redisKey;//保存redis时使用的key
	    private String objectName;//此属性主要用于hash数据结构时，保存member的
	    private String redisValue;//存储的redis的值
	    private String keyToken;//保存Token时，为区分拼接的字符串
	    private String score;//此属性为sortedset数据结构时，保存的score值
	    private Date createTime;//创建时间
	    private Date updateTime;//更新时间
	    private String macIp;//redis的IP地址  当然此处也可以存储mac地址
	    private String port;//redis使用的端口号
	    private String appCode;//应用区分码
	    private String remark;//备注
	    private String isModify;//是否修改。此属性可以用于增量备份时，即在每个redis存储时可以更具key多存储一个属性isModify。 如果有修改，则置为 Y,否则为N.
		public Long getRedisId() {
			return redisId;
		}
		public void setRedisId(Long redisId) {
			this.redisId = redisId;
		}
		public String getRedisType() {
			return redisType;
		}
		public void setRedisType(String redisType) {
			this.redisType = redisType;
		}
		public String getRedisKey() {
			return redisKey;
		}
		public void setRedisKey(String redisKey) {
			this.redisKey = redisKey;
		}
		public String getObjectName() {
			return objectName;
		}
		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}
		public String getRedisValue() {
			return redisValue;
		}
		public void setRedisValue(String redisValue) {
			this.redisValue = redisValue;
		}
		public String getKeyToken() {
			return keyToken;
		}
		public void setKeyToken(String keyToken) {
			this.keyToken = keyToken;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public String getMacIp() {
			return macIp;
		}
		public void setMacIp(String macIp) {
			this.macIp = macIp;
		}
		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		public String getAppCode() {
			return appCode;
		}
		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getIsModify() {
			return isModify;
		}
		public void setIsModify(String isModify) {
			this.isModify = isModify;
		}
}
