package com.liusy.datapp.model.analysis;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_ANALYSIS_DATASET_CONDITION table.
 *
 * @hibernate.class
 *  table="T_ANALYSIS_DATASET_CONDITION"
 */

public class AnalysisDatasetCondition implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer analysisDatasetId;   //  

   private java.lang.String resourceColumnName;   //  

   private java.lang.String conditionType;   //  

   private java.lang.String operator;   //  

   private java.lang.String value;   //  

   private java.lang.Integer resourceColumnId;   //  


   // Constructors
   public AnalysisDatasetCondition() {
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
    * Return the value associated with the column: ANALYSIS_DATASET_ID
    *  
    */
   public java.lang.Integer getAnalysisDatasetId() { 
      return this.analysisDatasetId; 
   }
   /**
    * Set the value related to the column: ANALYSIS_DATASET_ID
    * @param analysisDatasetId is the 'ANALYSIS_DATASET_ID' value
    */
   public void setAnalysisDatasetId(java.lang.Integer analysisDatasetId) { 
      this.analysisDatasetId = analysisDatasetId; 
   }

   /**
    * Return the value associated with the column: RESOURCE_COLUMN_NAME
    *  
    */
   public java.lang.String getResourceColumnName() { 
      return this.resourceColumnName; 
   }
   /**
    * Set the value related to the column: RESOURCE_COLUMN_NAME
    * @param resourceColumnName is the 'RESOURCE_COLUMN_NAME' value
    */
   public void setResourceColumnName(java.lang.String resourceColumnName) { 
      this.resourceColumnName = resourceColumnName; 
   }

   /**
    * Return the value associated with the column: CONDITION_TYPE
    *  
    */
   public java.lang.String getConditionType() { 
      return this.conditionType; 
   }
   /**
    * Set the value related to the column: CONDITION_TYPE
    * @param conditionType is the 'CONDITION_TYPE' value
    */
   public void setConditionType(java.lang.String conditionType) { 
      this.conditionType = conditionType; 
   }

   /**
    * Return the value associated with the column: OPERATOR
    *  
    */
   public java.lang.String getOperator() { 
      return this.operator; 
   }
   /**
    * Set the value related to the column: OPERATOR
    * @param operator is the 'OPERATOR' value
    */
   public void setOperator(java.lang.String operator) { 
      this.operator = operator; 
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
    * Return the value associated with the column: RESOURCE_COLUMN_ID
    *  
    */
   public java.lang.Integer getResourceColumnId() { 
      return this.resourceColumnId; 
   }
   /**
    * Set the value related to the column: RESOURCE_COLUMN_ID
    * @param resourceColumnId is the 'RESOURCE_COLUMN_ID' value
    */
   public void setResourceColumnId(java.lang.Integer resourceColumnId) { 
      this.resourceColumnId = resourceColumnId; 
   }


   public static String REF_CLASS = "AnalysisDatasetCondition";
   public static String PROP_ANALYSIS_DATASET_ID = "analysisDatasetId";
   public static String PROP_RESOURCE_COLUMN_NAME = "resourceColumnName";
   public static String PROP_CONDITION_TYPE = "conditionType";
   public static String PROP_OPERATOR = "operator";
   public static String PROP_VALUE = "value";
   public static String PROP_RESOURCE_COLUMN_ID = "resourceColumnId";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_ANALYSIS_DATASET_CONDITION";
   public static String COL_ANALYSIS_DATASET_ID = "ANALYSIS_DATASET_ID";
   public static String COL_RESOURCE_COLUMN_NAME = "RESOURCE_COLUMN_NAME";
   public static String COL_CONDITION_TYPE = "CONDITION_TYPE";
   public static String COL_OPERATOR = "OPERATOR";
   public static String COL_VALUE = "VALUE";
   public static String COL_RESOURCE_COLUMN_ID = "RESOURCE_COLUMN_ID";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.analysis.AnalysisDatasetCondition)) return false;
      else {
         com.liusy.datapp.model.analysis.AnalysisDatasetCondition o = (com.liusy.datapp.model.analysis.AnalysisDatasetCondition) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AnalysisDatasetCondition:");
		buffer.append(" id:").append(id);
		buffer.append(" analysisDatasetId:").append(dealNull(analysisDatasetId));
		buffer.append(" resourceColumnName:").append(dealNull(resourceColumnName));
		buffer.append(" conditionType:").append(dealNull(conditionType));
		buffer.append(" operator:").append(dealNull(operator));
		buffer.append(" value:").append(dealNull(value));
		buffer.append(" resourceColumnId:").append(dealNull(resourceColumnId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"analysisDatasetId\":\"").append(dealNull(analysisDatasetId)).append("\"");
		buffer.append(",\"resourceColumnName\":\"").append(dealNull(resourceColumnName)).append("\"");
		buffer.append(",\"conditionType\":\"").append(dealNull(conditionType)).append("\"");
		buffer.append(",\"operator\":\"").append(dealNull(operator)).append("\"");
		buffer.append(",\"value\":\"").append(dealNull(value)).append("\"");
		buffer.append(",\"resourceColumnId\":\"").append(dealNull(resourceColumnId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
