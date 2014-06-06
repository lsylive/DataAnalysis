package com.liusy.datapp.model.system.log;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_LOG_OPERATE table.
 *
 * @hibernate.class
 *  table="T_SYS_LOG_OPERATE"
 */

public class SysLogOperate implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String deptName;   //  

   private java.lang.String ipAddress;   //  

   private java.lang.String queryCondition;   //  

   private java.lang.Integer deptId;   //  

   private java.lang.Integer resId;   //  

   private java.lang.String optType;   //  

   private java.lang.String orgName;   //  

   private java.lang.Integer orgId;   //  

   private java.lang.String userAccount;   //  

   private java.lang.String userName;   //  


   private java.util.Date optTime;   //  

   // Constructors
   public SysLogOperate() {
   }

   /**
    * The unique identifier of this class.
    * Return the value associated with the column: ID
    *  
    */
   public java.lang.Integer getId() { 
      return this.id; 
   }
   /**
    * The unique identifier of this class.
    * Set the value related to the column: ID
    * @param id is the 'ID' value
    */
   public void setId(java.lang.Integer id) { 
      this.id = id; 
   }

   /**
    * Return the value associated with the column: DEPT_NAME
    *  
    */
   public java.lang.String getDeptName() { 
      return this.deptName; 
   }
   /**
    * Set the value related to the column: DEPT_NAME
    * @param deptName is the 'DEPT_NAME' value
    */
   public void setDeptName(java.lang.String deptName) { 
      this.deptName = deptName; 
   }

   /**
    * Return the value associated with the column: IP_ADDRESS
    *  
    */
   public java.lang.String getIpAddress() { 
      return this.ipAddress; 
   }
   /**
    * Set the value related to the column: IP_ADDRESS
    * @param ipAddress is the 'IP_ADDRESS' value
    */
   public void setIpAddress(java.lang.String ipAddress) { 
      this.ipAddress = ipAddress; 
   }

   /**
    * Return the value associated with the column: QUERY_CONDITION
    *  
    */
   public java.lang.String getQueryCondition() { 
      return this.queryCondition; 
   }
   /**
    * Set the value related to the column: QUERY_CONDITION
    * @param queryCondition is the 'QUERY_CONDITION' value
    */
   public void setQueryCondition(java.lang.String queryCondition) { 
      this.queryCondition = queryCondition; 
   }

   /**
    * Return the value associated with the column: DEPT_ID
    *  
    */
   public java.lang.Integer getDeptId() { 
      return this.deptId; 
   }
   /**
    * Set the value related to the column: DEPT_ID
    * @param deptId is the 'DEPT_ID' value
    */
   public void setDeptId(java.lang.Integer deptId) { 
      this.deptId = deptId; 
   }

   /**
    * Return the value associated with the column: RES_ID
    *  
    */
   public java.lang.Integer getResId() { 
      return this.resId; 
   }
   /**
    * Set the value related to the column: RES_ID
    * @param resId is the 'RES_ID' value
    */
   public void setResId(java.lang.Integer resId) { 
      this.resId = resId; 
   }

   /**
    * Return the value associated with the column: OPT_TYPE
    *  
    */
   public java.lang.String getOptType() { 
      return this.optType; 
   }
   /**
    * Set the value related to the column: OPT_TYPE
    * @param optType is the 'OPT_TYPE' value
    */
   public void setOptType(java.lang.String optType) { 
      this.optType = optType; 
   }

   /**
    * Return the value associated with the column: ORG_NAME
    *  
    */
   public java.lang.String getOrgName() { 
      return this.orgName; 
   }
   /**
    * Set the value related to the column: ORG_NAME
    * @param orgName is the 'ORG_NAME' value
    */
   public void setOrgName(java.lang.String orgName) { 
      this.orgName = orgName; 
   }

   /**
    * Return the value associated with the column: ORG_ID
    *  
    */
   public java.lang.Integer getOrgId() { 
      return this.orgId; 
   }
   /**
    * Set the value related to the column: ORG_ID
    * @param orgId is the 'ORG_ID' value
    */
   public void setOrgId(java.lang.Integer orgId) { 
      this.orgId = orgId; 
   }

   /**
    * Return the value associated with the column: USER_ACCOUNT
    *  
    */
   public java.lang.String getUserAccount() { 
      return this.userAccount; 
   }
   /**
    * Set the value related to the column: USER_ACCOUNT
    * @param userAccount is the 'USER_ACCOUNT' value
    */
   public void setUserAccount(java.lang.String userAccount) { 
      this.userAccount = userAccount; 
   }

   /**
    * Return the value associated with the column: USER_NAME
    *  
    */
   public java.lang.String getUserName() { 
      return this.userName; 
   }
   /**
    * Set the value related to the column: USER_NAME
    * @param userName is the 'USER_NAME' value
    */
   public void setUserName(java.lang.String userName) { 
      this.userName = userName; 
   }

   /**
    * Return the value associated with the column: OPT_TIME
    *  
    */
   public java.util.Date getOptTime() { 
      return this.optTime; 
   }
   /**
    * Set the value related to the column: OPT_TIME
    * @param optTime is the 'OPT_TIME' value
    */
   public void setOptTime(java.util.Date optTime) { 
      this.optTime = optTime; 
   }


   public static String REF_CLASS = "SysLogOperate";
   public static String PROP_DEPT_NAME = "deptName";
   public static String PROP_IP_ADDRESS = "ipAddress";
   public static String PROP_QUERY_CONDITION = "queryCondition";
   public static String PROP_DEPT_ID = "deptId";
   public static String PROP_RES_ID = "resId";
   public static String PROP_OPT_TYPE = "optType";
   public static String PROP_ORG_NAME = "orgName";
   public static String PROP_ORG_ID = "orgId";
   public static String PROP_USER_ACCOUNT = "userAccount";
   public static String PROP_USER_NAME = "userName";
   public static String PROP_ID = "id";
   public static String PROP_OPT_TIME = "optTime";

   public static String REF_TABLE = "T_SYS_LOG_OPERATE";
   public static String COL_DEPT_NAME = "DEPT_NAME";
   public static String COL_IP_ADDRESS = "IP_ADDRESS";
   public static String COL_QUERY_CONDITION = "QUERY_CONDITION";
   public static String COL_DEPT_ID = "DEPT_ID";
   public static String COL_RES_ID = "RES_ID";
   public static String COL_OPT_TYPE = "OPT_TYPE";
   public static String COL_ORG_NAME = "ORG_NAME";
   public static String COL_ORG_ID = "ORG_ID";
   public static String COL_USER_ACCOUNT = "USER_ACCOUNT";
   public static String COL_USER_NAME = "USER_NAME";
   public static String COL_ID = "ID";
   public static String COL_OPT_TIME = "OPT_TIME";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.log.SysLogOperate)) return false;
      else {
         com.liusy.datapp.model.system.log.SysLogOperate o = (com.liusy.datapp.model.system.log.SysLogOperate) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysLogOperate:");
		buffer.append(" id:").append(id);
		buffer.append(" deptName:").append(dealNull(deptName));
		buffer.append(" ipAddress:").append(dealNull(ipAddress));
		buffer.append(" queryCondition:").append(dealNull(queryCondition));
		buffer.append(" deptId:").append(dealNull(deptId));
		buffer.append(" resId:").append(dealNull(resId));
		buffer.append(" optType:").append(dealNull(optType));
		buffer.append(" orgName:").append(dealNull(orgName));
		buffer.append(" orgId:").append(dealNull(orgId));
		buffer.append(" userAccount:").append(dealNull(userAccount));
		buffer.append(" userName:").append(dealNull(userName));
		buffer.append(" optTime:").append(dealNull(optTime));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"deptName\":\"").append(dealNull(deptName)).append("\"");
		buffer.append(",\"ipAddress\":\"").append(dealNull(ipAddress)).append("\"");
		buffer.append(",\"queryCondition\":\"").append(dealNull(queryCondition)).append("\"");
		buffer.append(",\"deptId\":\"").append(dealNull(deptId)).append("\"");
		buffer.append(",\"resId\":\"").append(dealNull(resId)).append("\"");
		buffer.append(",\"optType\":\"").append(dealNull(optType)).append("\"");
		buffer.append(",\"orgName\":\"").append(dealNull(orgName)).append("\"");
		buffer.append(",\"orgId\":\"").append(dealNull(orgId)).append("\"");
		buffer.append(",\"userAccount\":\"").append(dealNull(userAccount)).append("\"");
		buffer.append(",\"userName\":\"").append(dealNull(userName)).append("\"");
		buffer.append(",\"optTime\":\"").append(dealNull(optTime)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
