package com.huateng.report.system.bean;

import java.io.Serializable;

/**
 * @author jianxue.zhang 用于approve_common.xml的数据获取bean;
 */
public class ApproveBean implements Serializable {
	private String bizSubClass;
	private String adtResult;
	private String remark;

	public String getBizSubClass() {
		return bizSubClass;
	}

	public void setBizSubClass(String bizSubClass) {
		this.bizSubClass = bizSubClass;
	}

	public String getAdtResult() {
		return adtResult;
	}

	public void setAdtResult(String adtResult) {
		this.adtResult = adtResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
