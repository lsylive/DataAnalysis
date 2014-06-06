package com.liusy.analysismodel.log.dao.formBean;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

public class LogOperateBean {
	private Text userAcount;
	private Text userName;
	private Combo depName;
	private Combo orgName;
	private Text startDate;
	private Text endDate;
	public Text getUserAcount() {
		return userAcount;
	}
	public void setUserAcount(Text userAcount) {
		this.userAcount = userAcount;
	}
	public Text getUserName() {
		return userName;
	}
	public void setUserName(Text userName) {
		this.userName = userName;
	}
	public Text getStartDate() {
		return startDate;
	}
	public void setStartDate(Text startDate) {
		this.startDate = startDate;
	}
	public Text getEndDate() {
		return endDate;
	}
	public void setEndDate(Text endDate) {
		this.endDate = endDate;
	}
	public LogOperateBean(Text userAcount, Text userName, Combo depName,
			Combo orgName, Text startDate, Text endDate) {
		super();
		this.userAcount = userAcount;
		this.userName = userName;
		this.depName = depName;
		this.orgName = orgName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Combo getDepName() {
		return depName;
	}
	public void setDepName(Combo depName) {
		this.depName = depName;
	}
	public Combo getOrgName() {
		return orgName;
	}
	public void setOrgName(Combo orgName) {
		this.orgName = orgName;
	}
	

}
