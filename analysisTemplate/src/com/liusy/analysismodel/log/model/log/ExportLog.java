package com.liusy.analysismodel.log.model.log;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * ��¼��־����
 * 
 * @author andy
 */
public class ExportLog {
   private Integer   id;          //id
   private String    userAccount; //�û��˺�
   private String    userName;    //�û�����
   private Integer   orgId;       //����ID
   private Integer   deptId;      //����ID
   private String    orgName;     //��������
   private String    deptName;    //��������
   private Date      logonTime;   //��¼ʱ��
   private String    ipAddress;   //ip
   private Date      logonOutTime; //�ǳ�ʱ��
   private String    result;      //���
   private Integer   resId;       //��ԴId
   private String    optType;     //��������
   private Timestamp optTime;     //����ʱ��
   private String    queryCondi;  //��ѯ����
   private String    original;    //������Դ

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

   public Integer getResId() {
      return resId;
   }

   public void setResId(Integer resId) {
      this.resId = resId;
   }

   public String getOptType() {
      return optType;
   }

   public void setOptType(String optType) {
      this.optType = optType;
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

   public String getOriginal() {
      return original;
   }

   public void setOriginal(String original) {
      this.original = original;
   }

}
