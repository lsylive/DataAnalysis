package com.liusy.analysismodel.log.listener;

import com.liusy.analysismodel.log.dialog.LogonLogDetailDlg;
import com.liusy.analysismodel.log.model.log.LogonLog;
import com.liusy.analysis.template.model.util.StringUtil;

public class LogonLogDetailListener {
	private LogonLogDetailDlg dlg ;
	private LogonLog logBean;

	public LogonLogDetailListener(LogonLogDetailDlg dlg,LogonLog logBean) {
		super();
		this.dlg = dlg;
		this.logBean = logBean;
	}
	public void addListener() {
		
		
	}
	public void init() {
		dlg.getUserAcount().setText(StringUtil.getStringNotNull(logBean.getUserAccount()));
		dlg.getUserName().setText(StringUtil.getStringNotNull(logBean.getUserAccount()));
		dlg.getOrgName().setText(StringUtil.getStringNotNull(logBean.getOrgName()));
		dlg.getDeptName().setText(StringUtil.getStringNotNull(logBean.getDeptName()));
		
		dlg.getIpAdress().setText(StringUtil.getStringNotNull(logBean.getIpAddress()));
		dlg.getLogonTime().setText(StringUtil.getStringNotNull(logBean.getLogonTime()));
		dlg.getLogonResult().setText(StringUtil.getStringNotNull(logBean.getResult()));
		dlg.getLogoutTime().setText("");
		
		dlg.getLogoutResult().setText("");
	}
	
}
