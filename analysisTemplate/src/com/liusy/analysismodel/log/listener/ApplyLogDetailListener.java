package com.liusy.analysismodel.log.listener;

import com.liusy.analysismodel.log.dialog.ApllyLogDetailDlg;
import com.liusy.analysismodel.log.model.log.LogOperate;
import com.liusy.analysis.template.model.util.StringUtil;

public class ApplyLogDetailListener {
	private ApllyLogDetailDlg dlg ;
	private LogOperate logBean;

	public ApplyLogDetailListener(ApllyLogDetailDlg dlg,LogOperate logBean) {
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
		
		dlg.getCurryCondi().setText(StringUtil.getStringNotNull(logBean.getQueryCondi()));
	}
	
}
