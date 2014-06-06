package com.liusy.analysismodel.log.dao.formBean;

import org.eclipse.swt.widgets.Text;

public class LogExportBean2 {
	private Text txtFilePath;

	public LogExportBean2(Text txtFilePath) {
		super();
		this.txtFilePath = txtFilePath;
	}

	public Text getTxtFilePath() {
		return txtFilePath;
	}

	public void setTxtFilePath(Text txtFilePath) {
		this.txtFilePath = txtFilePath;
	}
	
}
