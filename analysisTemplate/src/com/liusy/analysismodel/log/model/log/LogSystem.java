package com.liusy.analysismodel.log.model.log;

import java.sql.Date;
/**
 * ������־����
 * @author andy
 *
 */
public class LogSystem {
    private Integer id;   		//id
    private String logLevel;    //��־����
    private Date logTime;     //��־����ʱ��
    private String logClass;   //��־��
    private String logMessage; //��־��Ϣ
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getLogClass() {
		return logClass;
	}
	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
   
	
    
}
