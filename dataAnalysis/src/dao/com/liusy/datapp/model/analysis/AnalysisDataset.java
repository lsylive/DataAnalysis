package com.liusy.datapp.model.analysis;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_ANALYSIS_DATASET table.
 *
 * @hibernate.class
 *  table="T_ANALYSIS_DATASET"
 */

public class AnalysisDataset implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.Integer resourceId;   //  

   private java.lang.Integer createBy;   //  

   private java.lang.String name;   //  

   private java.lang.Integer modifyBy;   //  

   private java.lang.String resourceName;   //  

   private java.util.Date createDate;   //  

   private java.lang.Integer templateId;   //  

   private java.lang.String additionSql;   //  

   private java.lang.String sqlScript;   //  

   private java.lang.String remark;   //  


   // Constructors
   public AnalysisDataset() {
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
    * Return the value associated with the column: RESOURCE_ID
    *  
    */
   public java.lang.Integer getResourceId() { 
      return this.resourceId; 
   }
   /**
    * Set the value related to the column: RESOURCE_ID
    * @param resourceId is the 'RESOURCE_ID' value
    */
   public void setResourceId(java.lang.Integer resourceId) { 
      this.resourceId = resourceId; 
   }

   /**
    * Return the value associated with the column: CREATE_BY
    *  
    */
   public java.lang.Integer getCreateBy() { 
      return this.createBy; 
   }
   /**
    * Set the value related to the column: CREATE_BY
    * @param createBy is the 'CREATE_BY' value
    */
   public void setCreateBy(java.lang.Integer createBy) { 
      this.createBy = createBy; 
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
   public java.lang.Integer getModifyBy() { 
      return this.modifyBy; 
   }
   /**
    * Set the value related to the column: MODIFY_BY
    * @param modifyBy is the 'MODIFY_BY' value
    */
   public void setModifyBy(java.lang.Integer modifyBy) { 
      this.modifyBy = modifyBy; 
   }

   /**
    * Return the value associated with the column: RESOURCE_NAME
    *  
    */
   public java.lang.String getResourceName() { 
      return this.resourceName; 
   }
   /**
    * Set the value related to the column: RESOURCE_NAME
    * @param resourceName is the 'RESOURCE_NAME' value
    */
   public void setResourceName(java.lang.String resourceName) { 
      this.resourceName = resourceName; 
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
    * Return the value associated with the column: ADDITION_SQL
    *  
    */
   public java.lang.String getAdditionSql() { 
      return this.additionSql; 
   }
   /**
    * Set the value related to the column: ADDITION_SQL
    * @param additionSql is the 'ADDITION_SQL' value
    */
   public void setAdditionSql(java.lang.String additionSql) { 
      this.additionSql = additionSql; 
   }

   /**
    * Return the value associated with the column: SQL_SCRIPT
    *  
    */
   public java.lang.String getSqlScript() { 
      return this.sqlScript; 
   }
   /**
    * Set the value related to the column: SQL_SCRIPT
    * @param sqlScript is the 'SQL_SCRIPT' value
    */
   public void setSqlScript(java.lang.String sqlScript) { 
      this.sqlScript = sqlScript; 
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


   public static String REF_CLASS = "AnalysisDataset";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_RESOURCE_ID = "resourceId";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_NAME = "name";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_RESOURCE_NAME = "resourceName";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_TEMPLATE_ID = "templateId";
   public static String PROP_ADDITION_SQL = "additionSql";
   public static String PROP_SQL_SCRIPT = "sqlScript";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_ANALYSIS_DATASET";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_RESOURCE_ID = "RESOURCE_ID";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_NAME = "NAME";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_RESOURCE_NAME = "RESOURCE_NAME";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_TEMPLATE_ID = "TEMPLATE_ID";
   public static String COL_ADDITION_SQL = "ADDITION_SQL";
   public static String COL_SQL_SCRIPT = "SQL_SCRIPT";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.analysis.AnalysisDataset)) return false;
      else {
         com.liusy.datapp.model.analysis.AnalysisDataset o = (com.liusy.datapp.model.analysis.AnalysisDataset) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AnalysisDataset:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" resourceId:").append(dealNull(resourceId));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" resourceName:").append(dealNull(resourceName));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" templateId:").append(dealNull(templateId));
		buffer.append(" additionSql:").append(dealNull(additionSql));
		buffer.append(" sqlScript:").append(dealNull(sqlScript));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"resourceId\":\"").append(dealNull(resourceId)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"resourceName\":\"").append(dealNull(resourceName)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"templateId\":\"").append(dealNull(templateId)).append("\"");
		buffer.append(",\"additionSql\":\"").append(dealNull(additionSql)).append("\"");
		buffer.append(",\"sqlScript\":\"").append(dealNull(sqlScript)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}