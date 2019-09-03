package com.entity;

public class HeadManage {
	private String systemName;//外部系统名称
	private String billDefine;//实物资产固定字段
	private String userId;//用户id
	private String operaterType;//信息操作标示
	private String dataId;//报文唯一标识
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getBillDefine() {
		return billDefine;
	}
	public void setBillDefine(String billDefine) {
		this.billDefine = billDefine;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperaterType() {
		return operaterType;
	}
	public void setOperaterType(String operaterType) {
		this.operaterType = operaterType;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
}
