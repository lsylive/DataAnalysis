package com.liusy.datapp.model.datastandard;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_STANDARD_CODESET table.
 *
 * @hibernate.class
 *  table="T_STANDARD_CODESET"
 */

public class StandardCodeset implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.String type;   //  

   private java.lang.Integer categoryId;   //  

   private java.lang.String codeExpression;   //  

   private java.lang.String createBy;   //  

   private java.lang.String modifyBy;   //  

   private java.util.Date createDate;   //  

   private java.lang.String standardCode;   //  

   private java.lang.String status;   //  

   private java.lang.String encodeRule;   //  

   private java.lang.String codesetNo;   //  

   private java.lang.String name;   //  

   private java.lang.String remark;   //  


   // Constructors
   public StandardCodeset() {
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
    * Return the value associated with the column: MODIFY_DATE
    *  
    */
   public java.util.Date getModifyDate() { 
      return this.modifyDate; 
   }
   /**
    * Set the value related to the column: MODIFY_DATE
    * @param modifyDate is the 'MODIFY_DATE' value
    */
   public void setModifyDate(java.util.Date modifyDate) { 
      this.modifyDate = modifyDate; 
   }

   /**
    * Return the value associated with the column: TYPE
    *  
    */
   public java.lang.String getType() { 
      return this.type; 
   }
   /**
    * Set the value related to the column: TYPE
    * @param type is the 'TYPE' value
    */
   public void setType(java.lang.String type) { 
      this.type = type; 
   }

   /**
    * Return the value associated with the column: CATEGORY_ID
    *  
    */
   public java.lang.Integer getCategoryId() { 
      return this.categoryId; 
   }
   /**
    * Set the value related to the column: CATEGORY_ID
    * @param categoryId is the 'CATEGORY_ID' value
    */
   public void setCategoryId(java.lang.Integer categoryId) { 
      this.categoryId = categoryId; 
   }

   /**
    * Return the value associated with the column: CODE_EXPRESSION
    *  
    */
   public java.lang.String getCodeExpression() { 
      return this.codeExpression; 
   }
   /**
    * Set the value related to the column: CODE_EXPRESSION
    * @param codeExpression is the 'CODE_EXPRESSION' value
    */
   public void setCodeExpression(java.lang.String codeExpression) { 
      this.codeExpression = codeExpression; 
   }

   /**
    * Return the value associated with the column: CREATE_BY
    *  
    */
   public java.lang.String getCreateBy() { 
      return this.createBy; 
   }
   /**
    * Set the value related to the column: CREATE_BY
    * @param createBy is the 'CREATE_BY' value
    */
   public void setCreateBy(java.lang.String createBy) { 
      this.createBy = createBy; 
   }

   /**
    * Return the value associated with the column: MODIFY_BY
    *  
    */
   public java.lang.String getModifyBy() { 
      return this.modifyBy; 
   }
   /**
    * Set the value related to the column: MODIFY_BY
    * @param modifyBy is the 'MODIFY_BY' value
    */
   public void setModifyBy(java.lang.String modifyBy) { 
      this.modifyBy = modifyBy; 
   }

   /**
    * Return the value associated with the column: CREATE_DATE
    *  
    */
   public java.util.Date getCreateDate() { 
      return this.createDate; 
   }
   /**
    * Set the value related to the column: CREATE_DATE
    * @param createDate is the 'CREATE_DATE' value
    */
   public void setCreateDate(java.util.Date createDate) { 
      this.createDate = createDate; 
   }

   /**
    * Return the value associated with the column: STANDARD_CODE
    *  
    */
   public java.lang.String getStandardCode() { 
      return this.standardCode; 
   }
   /**
    * Set the value related to the column: STANDARD_CODE
    * @param standardCode is the 'STANDARD_CODE' value
    */
   public void setStandardCode(java.lang.String standardCode) { 
      this.standardCode = standardCode; 
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
    * Return the value associated with the column: ENCODE_RULE
    *  
    */
   public java.lang.String getEncodeRule() { 
      return this.encodeRule; 
   }
   /**
    * Set the value related to the column: ENCODE_RULE
    * @param encodeRule is the 'ENCODE_RULE' value
    */
   public void setEncodeRule(java.lang.String encodeRule) { 
      this.encodeRule = encodeRule; 
   }

   /**
    * Return the value associated with the column: CODESET_NO
    *  
    */
   public java.lang.String getCodesetNo() { 
      return this.codesetNo; 
   }
   /**
    * Set the value related to the column: CODESET_NO
    * @param codesetNo is the 'CODESET_NO' value
    */
   public void setCodesetNo(java.lang.String codesetNo) { 
      this.codesetNo = codesetNo; 
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


   public static String REF_CLASS = "StandardCodeset";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_TYPE = "type";
   public static String PROP_CATEGORY_ID = "categoryId";
   public static String PROP_CODE_EXPRESSION = "codeExpression";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_STANDARD_CODE = "standardCode";
   public static String PROP_STATUS = "status";
   public static String PROP_ENCODE_RULE = "encodeRule";
   public static String PROP_CODESET_NO = "codesetNo";
   public static String PROP_NAME = "name";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_STANDARD_CODESET";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_TYPE = "TYPE";
   public static String COL_CATEGORY_ID = "CATEGORY_ID";
   public static String COL_CODE_EXPRESSION = "CODE_EXPRESSION";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_STANDARD_CODE = "STANDARD_CODE";
   public static String COL_STATUS = "STATUS";
   public static String COL_ENCODE_RULE = "ENCODE_RULE";
   public static String COL_CODESET_NO = "CODESET_NO";
   public static String COL_NAME = "NAME";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.datastandard.StandardCodeset)) return false;
      else {
         com.liusy.datapp.model.datastandard.StandardCodeset o = (com.liusy.datapp.model.datastandard.StandardCodeset) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[StandardCodeset:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" type:").append(dealNull(type));
		buffer.append(" categoryId:").append(dealNull(categoryId));
		buffer.append(" codeExpression:").append(dealNull(codeExpression));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" standardCode:").append(dealNull(standardCode));
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" encodeRule:").append(dealNull(encodeRule));
		buffer.append(" codesetNo:").append(dealNull(codesetNo));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"type\":\"").append(dealNull(type)).append("\"");
		buffer.append(",\"categoryId\":\"").append(dealNull(categoryId)).append("\"");
		buffer.append(",\"codeExpression\":\"").append(dealNull(codeExpression)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"standardCode\":\"").append(dealNull(standardCode)).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"encodeRule\":\"").append(dealNull(encodeRule)).append("\"");
		buffer.append(",\"codesetNo\":\"").append(dealNull(codesetNo)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
