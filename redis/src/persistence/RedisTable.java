package persistence;

import java.util.Date;

public class RedisTable {
	  private Long redisId; //����redis������ID
	    private String redisType;//redis�������磺set/list/hash/sortedset/string
	    private String redisKey;//����redisʱʹ�õ�key
	    private String objectName;//��������Ҫ����hash���ݽṹʱ������member��
	    private String redisValue;//�洢��redis��ֵ
	    private String keyToken;//����Tokenʱ��Ϊ����ƴ�ӵ��ַ���
	    private String score;//������Ϊsortedset���ݽṹʱ�������scoreֵ
	    private Date createTime;//����ʱ��
	    private Date updateTime;//����ʱ��
	    private String macIp;//redis��IP��ַ  ��Ȼ�˴�Ҳ���Դ洢mac��ַ
	    private String port;//redisʹ�õĶ˿ں�
	    private String appCode;//Ӧ��������
	    private String remark;//��ע
	    private String isModify;//�Ƿ��޸ġ������Կ���������������ʱ������ÿ��redis�洢ʱ���Ը���key��洢һ������isModify�� ������޸ģ�����Ϊ Y,����ΪN.
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
