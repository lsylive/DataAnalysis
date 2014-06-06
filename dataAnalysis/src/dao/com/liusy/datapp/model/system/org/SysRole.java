package com.liusy.datapp.model.system.org;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_ROLE_INFO table.
 *
 * @hibernate.class
 *  table="T_SYS_ROLE_INFO"
 */

public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String roleName;   //  

   private java.lang.String roleType;   //  

   private java.lang.String roleStatus;   //  

   private java.lang.String roleCode;   //  

   private java.util.Date appTime;   //  

   private java.lang.String roleDesc;   //  

   private java.lang.String appNote;   //  

   private java.lang.String remark;   //  


   // Constructors
   public SysRole() {
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
    * Return the value associated with the column: ROLE_NAME
    *  
    */
   public java.lang.String getRoleName() { 
      return this.roleName; 
   }
   /**
    * Set the value related to the column: ROLE_NAME
    * @param roleName is the 'ROLE_NAME' value
    */
   public void setRoleName(java.lang.String roleName) { 
      this.roleName = roleName; 
   }

   /**
    * Return the value associated with the column: ROLE_TYPE
    *  
    */
   public java.lang.String getRoleType() { 
      return this.roleType; 
   }
   /**
    * Set the value related to the column: ROLE_TYPE
    * @param roleType is the 'ROLE_TYPE' value
    */
   public void setRoleType(java.lang.String roleType) { 
      this.roleType = roleType; 
   }

   /**
    * Return the value associated with the column: ROLE_STATUS
    *  
    */
   public java.lang.String getRoleStatus() { 
      return this.roleStatus; 
   }
   /**
    * Set the value related to the column: ROLE_STATUS
    * @param roleStatus is the 'ROLE_STATUS' value
    */
   public void setRoleStatus(java.lang.String roleStatus) { 
      this.roleStatus = roleStatus; 
   }

   /**
    * Return the value associated with the column: ROLE_CODE
    *  
    */
   public java.lang.String getRoleCode() { 
      return this.roleCode; 
   }
   /**
    * Set the value related to the column: ROLE_CODE
    * @param roleCode is the 'ROLE_CODE' value
    */
   public void setRoleCode(java.lang.String roleCode) { 
      this.roleCode = roleCode; 
   }

   /**
    * Return the value associated with the column: APP_TIME
    *  
    */
   public java.util.Date getAppTime() { 
      return this.appTime; 
   }
   /**
    * Set the value related to the column: APP_TIME
    * @param appTime is the 'APP_TIME' value
    */
   public void setAppTime(java.util.Date appTime) { 
      this.appTime = appTime; 
   }

   /**
    * Return the value associated with the column: ROLE_DESC
    *  
    */
   public java.lang.String getRoleDesc() { 
      return this.roleDesc; 
   }
   /**
    * Set the value related to the column: ROLE_DESC
    * @param roleDesc is the 'ROLE_DESC' value
    */
   public void setRoleDesc(java.lang.String roleDesc) { 
      this.roleDesc = roleDesc; 
   }

   /**
    * Return the value associated with the column: APP_NOTE
    *  
    */
   public java.lang.String getAppNote() { 
      return this.appNote; 
   }
   /**
    * Set the value related to the column: APP_NOTE
    * @param appNote is the 'APP_NOTE' value
    */
   public void setAppNote(java.lang.String appNote) { 
      this.appNote = appNote; 
   }

   /**
    * Return the value associated with the column: REMARK
    *  
    */
   public java.lang.String getRemark() { 
      return this.remark; 
   }
   /**
    * Set the value related to the column: REMARK
    * @param remark is the 'REMARK' value
    */
   public void setRemark(java.lang.String remark) { 
      this.remark = remark; 
   }


   public static String REF_CLASS = "SysRole";
   public static String PROP_ROLE_NAME = "roleName";
   public static String PROP_ROLE_TYPE = "roleType";
   public static String PROP_ROLE_STATUS = "roleStatus";
   public static String PROP_ROLE_CODE = "roleCode";
   public static String PROP_APP_TIME = "appTime";
   public static String PROP_ROLE_DESC = "roleDesc";
   public static String PROP_APP_NOTE = "appNote";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_SYS_ROLE_INFO";
   public static String COL_ROLE_NAME = "ROLE_NAME";
   public static String COL_ROLE_TYPE = "ROLE_TYPE";
   public static String COL_ROLE_STATUS = "ROLE_STATUS";
   public static String COL_ROLE_CODE = "ROLE_CODE";
   public static String COL_APP_TIME = "APP_TIME";
   public static String COL_ROLE_DESC = "ROLE_DESC";
   public static String COL_APP_NOTE = "APP_NOTE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.org.SysRole)) return false;
      else {
         com.liusy.datapp.model.system.org.SysRole o = (com.liusy.datapp.model.system.org.SysRole) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysRole:");
		buffer.append(" id:").append(id);
		buffer.append(" roleName:").append(dealNull(roleName));
		buffer.append(" roleType:").append(dealNull(roleType));
		buffer.append(" roleStatus:").append(dealNull(roleStatus));
		buffer.append(" roleCode:").append(dealNull(roleCode));
		buffer.append(" appTime:").append(dealNull(appTime));
		buffer.append(" roleDesc:").append(dealNull(roleDesc));
		buffer.append(" appNote:").append(dealNull(appNote));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"roleName\":\"").append(dealNull(roleName)).append("\"");
		buffer.append(",\"roleType\":\"").append(dealNull(roleType)).append("\"");
		buffer.append(",\"roleStatus\":\"").append(dealNull(roleStatus)).append("\"");
		buffer.append(",\"roleCode\":\"").append(dealNull(roleCode)).append("\"");
		buffer.append(",\"appTime\":\"").append(dealNull(appTime)).append("\"");
		buffer.append(",\"roleDesc\":\"").append(dealNull(roleDesc)).append("\"");
		buffer.append(",\"appNote\":\"").append(dealNull(appNote)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
