package com.liusy.analysismodel.log.model.log;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * ������־����
 * @author andy
 *
 */
public class LogOperate {
    private Integer id;   //id
    private String userAccount;  //�û��˺�
    private String userName;     //�û�����
    private Integer orgId;       //����Id
    private Integer deptId;      //����Id
    private String orgName;      //��������
    private String deptName;     //��������
    private String resName;       //��ԴId
    private String optType;      //��������
    private String ipAddress;    //ip
    private Timestamp   optTime;      //����ʱ��
    private String queryCondi;    //��ѯ����
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
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getOptType() {
		return optType;
	}
	public void setOptType(String optType) {
		this.optType = optType;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Timestamp getOptTime() {
		return optTime;
	}
	public void setOptTime(Timestamp optTime) {
		this.optTime = optTime;
	}
	public String getQueryCondi() {
		return queryCondi;
	}
	public void setQueryCondi(String queryCondi) {
		this.queryCondi = queryCondi;
	}
    
    
    
}
