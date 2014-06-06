package com.liusy.datapp.model.system.config;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_CODESET table.
 *
 * @hibernate.class
 *  table="T_SYS_CODESET"
 */

public class SysCodeSet implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String modifiedBy;   //  

   private java.lang.String status;   //  

   private java.util.Date createDate;   //  

   private java.lang.String ename;   //  

   private java.lang.String createdBy;   //  

   private java.util.Date modifyDate;   //  

   private java.lang.String cname;   //  

   private java.lang.String description;   //  


   // Constructors
   public SysCodeSet() {
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
    * Return the value associated with the column: MODIFYBY
    *  
    */
   public java.lang.String getModifiedBy() { 
      return this.modifiedBy; 
   }
   /**
    * Set the value related to the column: MODIFYBY
    * @param modifiedBy is the 'MODIFYBY' value
    */
   public void setModifiedBy(java.lang.String modifiedBy) { 
      this.modifiedBy = modifiedBy; 
   }

   /**
    * Return the value associated with the column: CS_STATUS
    *  
    */
   public java.lang.String getStatus() { 
      return this.status; 
   }
   /**
    * Set the value related to the column: CS_STATUS
    * @param status is the 'CS_STATUS' value
    */
   public void setStatus(java.lang.String status) { 
      this.status = status; 
   }

   /**
    * Return the value associated with the column: CREATEDATE
    *  
    */
   public java.util.Date getCreateDate() { 
      return this.createDate; 
   }
   /**
    * Set the value related to the column: CREATEDATE
    * @param createDate is the 'CREATEDATE' value
    */
   public void setCreateDate(java.util.Date createDate) { 
      this.createDate = createDate; 
   }

   /**
    * Return the value associated with the column: EN_NAME
    *  
    */
   public java.lang.String getEname() { 
      return this.ename; 
   }
   /**
    * Set the value related to the column: EN_NAME
    * @param ename is the 'EN_NAME' value
    */
   public void setEname(java.lang.String ename) { 
      this.ename = ename; 
   }

   /**
    * Return the value associated with the column: CREATEBY
    *  
    */
   public java.lang.String getCreatedBy() { 
      return this.createdBy; 
   }
   /**
    * Set the value related to the column: CREATEBY
    * @param createdBy is the 'CREATEBY' value
    */
   public void setCreatedBy(java.lang.String createdBy) { 
      this.createdBy = createdBy; 
   }

   /**
    * Return the value associated with the column: MODIFYDATE
    *  
    */
   public java.util.Date getModifyDate() { 
      return this.modifyDate; 
   }
   /**
    * Set the value related to the column: MODIFYDATE
    * @param modifyDate is the 'MODIFYDATE' value
    */
   public void setModifyDate(java.util.Date modifyDate) { 
      this.modifyDate = modifyDate; 
   }

   /**
    * Return the value associated with the column: CN_NAME
    *  
    */
   public java.lang.String getCname() { 
      return this.cname; 
   }
   /**
    * Set the value related to the column: CN_NAME
    * @param cname is the 'CN_NAME' value
    */
   public void setCname(java.lang.String cname) { 
      this.cname = cname; 
   }

   /**
    * Return the value associated with the column: REMARK
    *  
    */
   public java.lang.String getDescription() { 
      return this.description; 
   }
   /**
    * Set the value related to the column: REMARK
    * @param description is the 'REMARK' value
    */
   public void setDescription(java.lang.String description) { 
      this.description = description; 
   }


   public static String REF_CLASS = "SysCodeSet";
   public static String PROP_MODIFYBY = "modifiedBy";
   public static String PROP_CS_STATUS = "status";
   public static String PROP_CREATEDATE = "createDate";
   public static String PROP_EN_NAME = "ename";
   public static String PROP_CREATEBY = "createdBy";
   public static String PROP_MODIFYDATE = "modifyDate";
   public static String PROP_CN_NAME = "cname";
   public static String PROP_REMARK = "description";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_SYS_CODESET";
   public static String COL_MODIFYBY = "MODIFYBY";
   public static String COL_CS_STATUS = "CS_STATUS";
   public static String COL_CREATEDATE = "CREATEDATE";
   public static String COL_EN_NAME = "EN_NAME";
   public static String COL_CREATEBY = "CREATEBY";
   public static String COL_MODIFYDATE = "MODIFYDATE";
   public static String COL_CN_NAME = "CN_NAME";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.config.SysCodeSet)) return false;
      else {
         com.liusy.datapp.model.system.config.SysCodeSet o = (com.liusy.datapp.model.system.config.SysCodeSet) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysCodeSet:");
		buffer.append(" id:").append(id);
		buffer.append(" modifiedBy:").append(dealNull(modifiedBy));
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" ename:").append(dealNull(ename));
		buffer.append(" createdBy:").append(dealNull(createdBy));
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" cname:").append(dealNull(cname));
		buffer.append(" description:").append(dealNull(description));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifiedBy\":\"").append(dealNull(modifiedBy)).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"ename\":\"").append(dealNull(ename)).append("\"");
		buffer.append(",\"createdBy\":\"").append(dealNull(createdBy)).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"cname\":\"").append(dealNull(cname)).append("\"");
		buffer.append(",\"description\":\"").append(dealNull(description)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
