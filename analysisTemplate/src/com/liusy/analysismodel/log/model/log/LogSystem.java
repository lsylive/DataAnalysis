package com.liusy.analysismodel.log.model.log;

import java.sql.Date;
/**
 * 操作日志对象
 * @author andy
 *
 */
public class LogSystem {
    private Integer id;   		//id
    private String logLevel;    //日志级别
    private Date logTime;     //日志发生时间
    private String logClass;   //日志类
    private String logMessage; //日志消息
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
