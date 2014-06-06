package com.liusy.analysismodel.log.dao.formBean;

import org.eclipse.swt.widgets.Text;

public class LogImportBean2 {
	private Text fromDate;
	private Text toDate;
	private Text filePath;
	private Text fileName;
	public Text getFromDate() {
		return fromDate;
	}
	public void setFromDate(Text fromDate) {
		this.fromDate = fromDate;
	}
	public Text getToDate() {
		return toDate;
	}
	public void setToDate(Text toDate) {
		this.toDate = toDate;
	}
	public Text getFilePath() {
		return filePath;
	}
	public void setFilePath(Text filePath) {
		this.filePath = filePath;
	}
	public Text getFileName() {
		return fileName;
	}
	public void setFileName(Text fileName) {
		this.fileName = fileName;
	}
	public LogImportBean2(Text fromDate, Text toDate, Text filePath,
			Text fileName) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.filePath = filePath;
		this.fileName = fileName;
	}
	

	
}
