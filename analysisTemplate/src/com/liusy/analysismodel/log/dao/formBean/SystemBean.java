package com.liusy.analysismodel.log.dao.formBean;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

public class SystemBean {
	private Combo logLevel;
	private Text startDate;
	private Text endDate;
	public Combo getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(Combo logLevel) {
		this.logLevel = logLevel;
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
	public SystemBean(Combo logLevel, Text startDate, Text endDate) {
		super();
		this.logLevel = logLevel;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	

}
