package com.liusy.analysismodel.log.listener;

import com.liusy.analysismodel.log.dialog.UserAudioLogDetailDlg;
import com.liusy.analysismodel.log.model.log.ImportLog2;
import com.liusy.analysis.template.model.util.StringUtil;

public class UserAudioLogDetailListener {
	private UserAudioLogDetailDlg dlg ;
	private ImportLog2 logBean;

	public UserAudioLogDetailListener(UserAudioLogDetailDlg dlg,ImportLog2 logBean) {
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
		dlg.getResName().setText(StringUtil.getStringNotNull(logBean.getResName()));
		dlg.getOptType().setText(StringUtil.getStringNotNull(logBean.getOptType()));
		dlg.getOptTime().setText(StringUtil.timeStampToString(logBean.getOptTime()));
		
		dlg.getList().setText(StringUtil.getStringNotNull(logBean.getQueryCondi()));
	}
	
}
