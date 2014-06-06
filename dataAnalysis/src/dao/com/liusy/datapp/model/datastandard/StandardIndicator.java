package com.liusy.datapp.model.datastandard;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_STANDARD_INDICATOR table.
 *
 * @hibernate.class
 *  table="T_STANDARD_INDICATOR"
 */

public class StandardIndicator implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.Integer categoryId;   //  

   private java.lang.String createBy;   //  

   private java.lang.String indicatorCode;   //  

   private java.lang.String name;   //  

   private java.lang.String modifyBy;   //  

   private java.util.Date createDate;   //  

   private java.lang.String cnName;   //  

   private java.lang.String remark;   //  


   // Constructors
   public StandardIndicator() {
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
    * Return the value associated with the column: INDICATOR_CODE
    *  
    */
   public java.lang.String getIndicatorCode() { 
      return this.indicatorCode; 
   }
   /**
    * Set the value related to the column: INDICATOR_CODE
    * @param indicatorCode is the 'INDICATOR_CODE' value
    */
   public void setIndicatorCode(java.lang.String indicatorCode) { 
      this.indicatorCode = indicatorCode; 
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
    * Return the value associated with the column: CN_NAME
    *  
    */
   public java.lang.String getCnName() { 
      return this.cnName; 
   }
   /**
    * Set the value related to the column: CN_NAME
    * @param cnName is the 'CN_NAME' value
    */
   public void setCnName(java.lang.String cnName) { 
      this.cnName = cnName; 
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


   public static String REF_CLASS = "StandardIndicator";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_CATEGORY_ID = "categoryId";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_INDICATOR_CODE = "indicatorCode";
   public static String PROP_NAME = "name";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_CN_NAME = "cnName";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_STANDARD_INDICATOR";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_CATEGORY_ID = "CATEGORY_ID";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_INDICATOR_CODE = "INDICATOR_CODE";
   public static String COL_NAME = "NAME";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_CN_NAME = "CN_NAME";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.datastandard.StandardIndicator)) return false;
      else {
         com.liusy.datapp.model.datastandard.StandardIndicator o = (com.liusy.datapp.model.datastandard.StandardIndicator) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[StandardIndicator:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" categoryId:").append(dealNull(categoryId));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" indicatorCode:").append(dealNull(indicatorCode));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" cnName:").append(dealNull(cnName));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"categoryId\":\"").append(dealNull(categoryId)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"indicatorCode\":\"").append(dealNull(indicatorCode)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"cnName\":\"").append(dealNull(cnName)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
