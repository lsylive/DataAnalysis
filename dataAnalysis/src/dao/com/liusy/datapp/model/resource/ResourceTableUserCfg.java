package com.liusy.datapp.model.resource;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_RESOURCE_USER_R table.
 *
 * @hibernate.class
 *  table="T_RESOURCE_USER_R"
 */

public class ResourceTableUserCfg implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String securityLevel;   //  

   private java.lang.Integer userId;   //  

   private java.lang.Integer tableId;   //  


   // Constructors
   public ResourceTableUserCfg() {
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
    * Return the value associated with the column: SECURITY_LEVEL
    *  
    */
   public java.lang.String getSecurityLevel() { 
      return this.securityLevel; 
   }
   /**
    * Set the value related to the column: SECURITY_LEVEL
    * @param securityLevel is the 'SECURITY_LEVEL' value
    */
   public void setSecurityLevel(java.lang.String securityLevel) { 
      this.securityLevel = securityLevel; 
   }

   /**
    * Return the value associated with the column: USER_ID
    *  
    */
   public java.lang.Integer getUserId() { 
      return this.userId; 
   }
   /**
    * Set the value related to the column: USER_ID
    * @param userId is the 'USER_ID' value
    */
   public void setUserId(java.lang.Integer userId) { 
      this.userId = userId; 
   }

   /**
    * Return the value associated with the column: TABLE_ID
    *  
    */
   public java.lang.Integer getTableId() { 
      return this.tableId; 
   }
   /**
    * Set the value related to the column: TABLE_ID
    * @param tableId is the 'TABLE_ID' value
    */
   public void setTableId(java.lang.Integer tableId) { 
      this.tableId = tableId; 
   }


   public static String REF_CLASS = "ResourceTableUserCfg";
   public static String PROP_SECURITY_LEVEL = "securityLevel";
   public static String PROP_USER_ID = "userId";
   public static String PROP_TABLE_ID = "tableId";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_RESOURCE_USER_R";
   public static String COL_SECURITY_LEVEL = "SECURITY_LEVEL";
   public static String COL_USER_ID = "USER_ID";
   public static String COL_TABLE_ID = "TABLE_ID";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.resource.ResourceTableUserCfg)) return false;
      else {
         com.liusy.datapp.model.resource.ResourceTableUserCfg o = (com.liusy.datapp.model.resource.ResourceTableUserCfg) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ResourceTableUserCfg:");
		buffer.append(" id:").append(id);
		buffer.append(" securityLevel:").append(dealNull(securityLevel));
		buffer.append(" userId:").append(dealNull(userId));
		buffer.append(" tableId:").append(dealNull(tableId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"securityLevel\":\"").append(dealNull(securityLevel)).append("\"");
		buffer.append(",\"userId\":\"").append(dealNull(userId)).append("\"");
		buffer.append(",\"tableId\":\"").append(dealNull(tableId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
