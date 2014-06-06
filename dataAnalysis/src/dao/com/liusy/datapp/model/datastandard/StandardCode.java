package com.liusy.datapp.model.datastandard;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_STANDARD_CODE table.
 *
 * @hibernate.class
 *  table="T_STANDARD_CODE"
 */

public class StandardCode implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String status;   //  

   private java.lang.String name;   //  

   private java.lang.String value;   //  

   private java.lang.String remark;   //  


   private java.lang.Integer codesetId;   //  

   // Constructors
   public StandardCode() {
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
    * Return the value associated with the column: STATUS
    *  
    */
   public java.lang.String getStatus() { 
      return this.status; 
   }
   /**
    * Set the value related to the column: STATUS
    * @param status is the 'STATUS' value
    */
   public void setStatus(java.lang.String status) { 
      this.status = status; 
   }

   /**
    * Return the value associated with the column: NAME
    *  
    */
   public java.lang.String getName() { 
      return this.name; 
   }
   /**
    * Set the value related to the column: NAME
    * @param name is the 'NAME' value
    */
   public void setName(java.lang.String name) { 
      this.name = name; 
   }

   /**
    * Return the value associated with the column: VALUE
    *  
    */
   public java.lang.String getValue() { 
      return this.value; 
   }
   /**
    * Set the value related to the column: VALUE
    * @param value is the 'VALUE' value
    */
   public void setValue(java.lang.String value) { 
      this.value = value; 
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

   /**
    * Return the value associated with the column: CODESET_ID
    *  
    */
   public java.lang.Integer getCodesetId() { 
      return this.codesetId; 
   }
   /**
    * Set the value related to the column: CODESET_ID
    * @param codesetId is the 'CODESET_ID' value
    */
   public void setCodesetId(java.lang.Integer codesetId) { 
      this.codesetId = codesetId; 
   }


   public static String REF_CLASS = "StandardCode";
   public static String PROP_STATUS = "status";
   public static String PROP_NAME = "name";
   public static String PROP_VALUE = "value";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";
   public static String PROP_CODESET_ID = "codesetId";

   public static String REF_TABLE = "T_STANDARD_CODE";
   public static String COL_STATUS = "STATUS";
   public static String COL_NAME = "NAME";
   public static String COL_VALUE = "VALUE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   public static String COL_CODESET_ID = "CODESET_ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.datastandard.StandardCode)) return false;
      else {
         com.liusy.datapp.model.datastandard.StandardCode o = (com.liusy.datapp.model.datastandard.StandardCode) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[StandardCode:");
		buffer.append(" id:").append(id);
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" value:").append(dealNull(value));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" codesetId:").append(dealNull(codesetId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"value\":\"").append(dealNull(value)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"codesetId\":\"").append(dealNull(codesetId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
