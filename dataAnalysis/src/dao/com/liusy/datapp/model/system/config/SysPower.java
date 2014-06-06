package com.liusy.datapp.model.system.config;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_POWER_INFO table.
 *
 * @hibernate.class
 *  table="T_SYS_POWER_INFO"
 */

public class SysPower implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String powerDesc;   //  

   private java.lang.String actionUrl;   //  

   private java.lang.String powerName;   //  

   private java.lang.String powerCode;   //  

   private java.lang.String remark;   //  


   // Constructors
   public SysPower() {
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
    * Return the value associated with the column: POWER_DESC
    *  
    */
   public java.lang.String getPowerDesc() { 
      return this.powerDesc; 
   }
   /**
    * Set the value related to the column: POWER_DESC
    * @param powerDesc is the 'POWER_DESC' value
    */
   public void setPowerDesc(java.lang.String powerDesc) { 
      this.powerDesc = powerDesc; 
   }

   /**
    * Return the value associated with the column: ACTION_URL
    *  
    */
   public java.lang.String getActionUrl() { 
      return this.actionUrl; 
   }
   /**
    * Set the value related to the column: ACTION_URL
    * @param actionUrl is the 'ACTION_URL' value
    */
   public void setActionUrl(java.lang.String actionUrl) { 
      this.actionUrl = actionUrl; 
   }

   /**
    * Return the value associated with the column: POWER_NAME
    *  
    */
   public java.lang.String getPowerName() { 
      return this.powerName; 
   }
   /**
    * Set the value related to the column: POWER_NAME
    * @param powerName is the 'POWER_NAME' value
    */
   public void setPowerName(java.lang.String powerName) { 
      this.powerName = powerName; 
   }

   /**
    * Return the value associated with the column: POWER_CODE
    *  
    */
   public java.lang.String getPowerCode() { 
      return this.powerCode; 
   }
   /**
    * Set the value related to the column: POWER_CODE
    * @param powerCode is the 'POWER_CODE' value
    */
   public void setPowerCode(java.lang.String powerCode) { 
      this.powerCode = powerCode; 
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


   public static String REF_CLASS = "SysPower";
   public static String PROP_POWER_DESC = "powerDesc";
   public static String PROP_ACTION_URL = "actionUrl";
   public static String PROP_POWER_NAME = "powerName";
   public static String PROP_POWER_CODE = "powerCode";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_SYS_POWER_INFO";
   public static String COL_POWER_DESC = "POWER_DESC";
   public static String COL_ACTION_URL = "ACTION_URL";
   public static String COL_POWER_NAME = "POWER_NAME";
   public static String COL_POWER_CODE = "POWER_CODE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.config.SysPower)) return false;
      else {
         com.liusy.datapp.model.system.config.SysPower o = (com.liusy.datapp.model.system.config.SysPower) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysPower:");
		buffer.append(" id:").append(id);
		buffer.append(" powerDesc:").append(dealNull(powerDesc));
		buffer.append(" actionUrl:").append(dealNull(actionUrl));
		buffer.append(" powerName:").append(dealNull(powerName));
		buffer.append(" powerCode:").append(dealNull(powerCode));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"powerDesc\":\"").append(dealNull(powerDesc)).append("\"");
		buffer.append(",\"actionUrl\":\"").append(dealNull(actionUrl)).append("\"");
		buffer.append(",\"powerName\":\"").append(dealNull(powerName)).append("\"");
		buffer.append(",\"powerCode\":\"").append(dealNull(powerCode)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
