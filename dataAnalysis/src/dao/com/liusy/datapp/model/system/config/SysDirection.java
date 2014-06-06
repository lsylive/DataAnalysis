package com.liusy.datapp.model.system.config;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_DIRECTION_INFO table.
 *
 * @hibernate.class
 *  table="T_SYS_DIRECTION_INFO"
 */

public class SysDirection implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String dirName;   //  

   private java.lang.String dirCode;   //  

   private java.lang.String dirDesc;   //  

   private java.lang.String remark;   //  


   // Constructors
   public SysDirection() {
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
    * Return the value associated with the column: DIR_NAME
    *  
    */
   public java.lang.String getDirName() { 
      return this.dirName; 
   }
   /**
    * Set the value related to the column: DIR_NAME
    * @param dirName is the 'DIR_NAME' value
    */
   public void setDirName(java.lang.String dirName) { 
      this.dirName = dirName; 
   }

   /**
    * Return the value associated with the column: DIR_CODE
    *  
    */
   public java.lang.String getDirCode() { 
      return this.dirCode; 
   }
   /**
    * Set the value related to the column: DIR_CODE
    * @param dirCode is the 'DIR_CODE' value
    */
   public void setDirCode(java.lang.String dirCode) { 
      this.dirCode = dirCode; 
   }

   /**
    * Return the value associated with the column: DIR_DESC
    *  
    */
   public java.lang.String getDirDesc() { 
      return this.dirDesc; 
   }
   /**
    * Set the value related to the column: DIR_DESC
    * @param dirDesc is the 'DIR_DESC' value
    */
   public void setDirDesc(java.lang.String dirDesc) { 
      this.dirDesc = dirDesc; 
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


   public static String REF_CLASS = "SysDirection";
   public static String PROP_DIR_NAME = "dirName";
   public static String PROP_DIR_CODE = "dirCode";
   public static String PROP_DIR_DESC = "dirDesc";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_SYS_DIRECTION_INFO";
   public static String COL_DIR_NAME = "DIR_NAME";
   public static String COL_DIR_CODE = "DIR_CODE";
   public static String COL_DIR_DESC = "DIR_DESC";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.config.SysDirection)) return false;
      else {
         com.liusy.datapp.model.system.config.SysDirection o = (com.liusy.datapp.model.system.config.SysDirection) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysDirection:");
		buffer.append(" id: ").append(id);
		buffer.append(" dirName: ").append(dealNull(dirName));
		buffer.append(" dirCode: ").append(dealNull(dirCode));
		buffer.append(" dirDesc: ").append(dealNull(dirDesc));
		buffer.append(" remark: ").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"dirName\":\"").append(dealNull(dirName)).append("\"");
		buffer.append(",\"dirCode\":\"").append(dealNull(dirCode)).append("\"");
		buffer.append(",\"dirDesc\":\"").append(dealNull(dirDesc)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
