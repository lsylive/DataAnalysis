package com.liusy.datapp.model.analysis;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_ANALYSIS_TEMPLATE_CONFIG table.
 *
 * @hibernate.class
 *  table="T_ANALYSIS_TEMPLATE_CONFIG"
 */

public class AnalysisTemplateConfig implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String configValue;   //  

   private java.lang.String configType;   //  

   private java.lang.String name;   //  

   private java.lang.Integer templateId;   //  

   private java.lang.String remark;   //  


   // Constructors
   public AnalysisTemplateConfig() {
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
    * Return the value associated with the column: CONFIG_VALUE
    *  
    */
   public java.lang.String getConfigValue() { 
      return this.configValue; 
   }
   /**
    * Set the value related to the column: CONFIG_VALUE
    * @param configValue is the 'CONFIG_VALUE' value
    */
   public void setConfigValue(java.lang.String configValue) { 
      this.configValue = configValue; 
   }

   /**
    * Return the value associated with the column: CONFIG_TYPE
    *  
    */
   public java.lang.String getConfigType() { 
      return this.configType; 
   }
   /**
    * Set the value related to the column: CONFIG_TYPE
    * @param configType is the 'CONFIG_TYPE' value
    */
   public void setConfigType(java.lang.String configType) { 
      this.configType = configType; 
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


   public static String REF_CLASS = "AnalysisTemplateConfig";
   public static String PROP_CONFIG_VALUE = "configValue";
   public static String PROP_CONFIG_TYPE = "configType";
   public static String PROP_NAME = "name";
   public static String PROP_TEMPLATE_ID = "templateId";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_ANALYSIS_TEMPLATE_CONFIG";
   public static String COL_CONFIG_VALUE = "CONFIG_VALUE";
   public static String COL_CONFIG_TYPE = "CONFIG_TYPE";
   public static String COL_NAME = "NAME";
   public static String COL_TEMPLATE_ID = "TEMPLATE_ID";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.analysis.AnalysisTemplateConfig)) return false;
      else {
         com.liusy.datapp.model.analysis.AnalysisTemplateConfig o = (com.liusy.datapp.model.analysis.AnalysisTemplateConfig) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AnalysisTemplateConfig:");
		buffer.append(" id:").append(id);
		buffer.append(" configValue:").append(dealNull(configValue));
		buffer.append(" configType:").append(dealNull(configType));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" templateId:").append(dealNull(templateId));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"configValue\":\"").append(dealNull(configValue)).append("\"");
		buffer.append(",\"configType\":\"").append(dealNull(configType)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"templateId\":\"").append(dealNull(templateId)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
