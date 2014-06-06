package com.liusy.datapp.model.analysis;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_ANALYSIS_TEMPLATE_PARAMS table.
 *
 * @hibernate.class
 *  table="T_ANALYSIS_TEMPLATE_PARAMS"
 */

public class AnalysisTemplateParams implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String dataType;   //  

   private java.lang.String name;   //  

   private java.lang.Integer templateId;   //  

   private java.lang.String defaultValue;   //  

   private java.lang.String remark;   //  


   // Constructors
   public AnalysisTemplateParams() {
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
    * Return the value associated with the column: DATA_TYPE
    *  
    */
   public java.lang.String getDataType() { 
      return this.dataType; 
   }
   /**
    * Set the value related to the column: DATA_TYPE
    * @param dataType is the 'DATA_TYPE' value
    */
   public void setDataType(java.lang.String dataType) { 
      this.dataType = dataType; 
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
    * Return the value associated with the column: TEMPLATE_ID
    *  
    */
   public java.lang.Integer getTemplateId() { 
      return this.templateId; 
   }
   /**
    * Set the value related to the column: TEMPLATE_ID
    * @param templateId is the 'TEMPLATE_ID' value
    */
   public void setTemplateId(java.lang.Integer templateId) { 
      this.templateId = templateId; 
   }

   /**
    * Return the value associated with the column: DEFAULT_VALUE
    *  
    */
   public java.lang.String getDefaultValue() { 
      return this.defaultValue; 
   }
   /**
    * Set the value related to the column: DEFAULT_VALUE
    * @param defaultValue is the 'DEFAULT_VALUE' value
    */
   public void setDefaultValue(java.lang.String defaultValue) { 
      this.defaultValue = defaultValue; 
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


   public static String REF_CLASS = "AnalysisTemplateParams";
   public static String PROP_DATA_TYPE = "dataType";
   public static String PROP_NAME = "name";
   public static String PROP_TEMPLATE_ID = "templateId";
   public static String PROP_DEFAULT_VALUE = "defaultValue";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_ANALYSIS_TEMPLATE_PARAMS";
   public static String COL_DATA_TYPE = "DATA_TYPE";
   public static String COL_NAME = "NAME";
   public static String COL_TEMPLATE_ID = "TEMPLATE_ID";
   public static String COL_DEFAULT_VALUE = "DEFAULT_VALUE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.analysis.AnalysisTemplateParams)) return false;
      else {
         com.liusy.datapp.model.analysis.AnalysisTemplateParams o = (com.liusy.datapp.model.analysis.AnalysisTemplateParams) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AnalysisTemplateParams:");
		buffer.append(" id:").append(id);
		buffer.append(" dataType:").append(dealNull(dataType));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" templateId:").append(dealNull(templateId));
		buffer.append(" defaultValue:").append(dealNull(defaultValue));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"dataType\":\"").append(dealNull(dataType)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"templateId\":\"").append(dealNull(templateId)).append("\"");
		buffer.append(",\"defaultValue\":\"").append(dealNull(defaultValue)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
