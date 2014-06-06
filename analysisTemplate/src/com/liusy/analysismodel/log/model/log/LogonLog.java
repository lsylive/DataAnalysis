package com.liusy.analysismodel.log.model.log;

import java.sql.Date;

/**
 * 登录日志对象
 * @author andy
 *
 */
public class LogonLog {
   private Integer id;   //id
   private String userAccount;  //用户账号
   private String userName;     //用户姓名
   private Integer orgId;       //机构ID
   private Integer deptId;      //部门ID
   private String orgName;      //机构名称
   private String deptName;     //部门名称
   private Date logonTime;      //登录时间
   private String ipAddress;    //ip
   private Date logonOutTime;   //登出时间
   private String result;       //结果
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Date getLogonTime() {
		return logonTime;
	}
	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getLogonOutTime() {
		return logonOutTime;
	}
	public void setLogonOutTime(Date logonOutTime) {
		this.logonOutTime = logonOutTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
   
   
   
   
}
