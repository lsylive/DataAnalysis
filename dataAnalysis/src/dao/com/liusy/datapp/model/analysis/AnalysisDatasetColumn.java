package com.liusy.datapp.model.analysis;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_ANALYSIS_DATASET_COLUMN table.
 *
 * @hibernate.class
 *  table="T_ANALYSIS_DATASET_COLUMN"
 */

public class AnalysisDatasetColumn implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String orderType;   //  

   private java.lang.String resourceColumnName;   //  

   private java.lang.String groupBy;   //  

   private java.lang.String cnName;   //  

   private java.lang.Integer orderSeq;   //  

   private java.lang.String orderBy;   //  

   private java.lang.String aliasName;   //  

   private java.lang.String aggregateFunction;   //  

   private java.lang.Integer analysisDatasetId;   //  

   private java.lang.Integer resourceColumnId;   //  

   private java.lang.String selected;   //  

   private java.lang.Integer seqNo;   //  


   // Constructors
   public AnalysisDatasetColumn() {
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
    * Return the value associated with the column: ORDER_TYPE
    *  
    */
   public java.lang.String getOrderType() { 
      return this.orderType; 
   }
   /**
    * Set the value related to the column: ORDER_TYPE
    * @param orderType is the 'ORDER_TYPE' value
    */
   public void setOrderType(java.lang.String orderType) { 
      this.orderType = orderType; 
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
    * Return the value associated with the column: GROUP_BY
    *  
    */
   public java.lang.String getGroupBy() { 
      return this.groupBy; 
   }
   /**
    * Set the value related to the column: GROUP_BY
    * @param groupBy is the 'GROUP_BY' value
    */
   public void setGroupBy(java.lang.String groupBy) { 
      this.groupBy = groupBy; 
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
    * Return the value associated with the column: ORDER_SEQ
    *  
    */
   public java.lang.Integer getOrderSeq() { 
      return this.orderSeq; 
   }
   /**
    * Set the value related to the column: ORDER_SEQ
    * @param orderSeq is the 'ORDER_SEQ' value
    */
   public void setOrderSeq(java.lang.Integer orderSeq) { 
      this.orderSeq = orderSeq; 
   }

   /**
    * Return the value associated with the column: ORDER_BY
    *  
    */
   public java.lang.String getOrderBy() { 
      return this.orderBy; 
   }
   /**
    * Set the value related to the column: ORDER_BY
    * @param orderBy is the 'ORDER_BY' value
    */
   public void setOrderBy(java.lang.String orderBy) { 
      this.orderBy = orderBy; 
   }

   /**
    * Return the value associated with the column: ALIAS_NAME
    *  
    */
   public java.lang.String getAliasName() { 
      return this.aliasName; 
   }
   /**
    * Set the value related to the column: ALIAS_NAME
    * @param aliasName is the 'ALIAS_NAME' value
    */
   public void setAliasName(java.lang.String aliasName) { 
      this.aliasName = aliasName; 
   }

   /**
    * Return the value associated with the column: AGGREGATE_FUNCTION
    *  
    */
   public java.lang.String getAggregateFunction() { 
      return this.aggregateFunction; 
   }
   /**
    * Set the value related to the column: AGGREGATE_FUNCTION
    * @param aggregateFunction is the 'AGGREGATE_FUNCTION' value
    */
   public void setAggregateFunction(java.lang.String aggregateFunction) { 
      this.aggregateFunction = aggregateFunction; 
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

   /**
    * Return the value associated with the column: SELECTED
    *  
    */
   public java.lang.String getSelected() { 
      return this.selected; 
   }
   /**
    * Set the value related to the column: SELECTED
    * @param selected is the 'SELECTED' value
    */
   public void setSelected(java.lang.String selected) { 
      this.selected = selected; 
   }

   /**
    * Return the value associated with the column: SEQ_NO
    *  
    */
   public java.lang.Integer getSeqNo() { 
      return this.seqNo; 
   }
   /**
    * Set the value related to the column: SEQ_NO
    * @param seqNo is the 'SEQ_NO' value
    */
   public void setSeqNo(java.lang.Integer seqNo) { 
      this.seqNo = seqNo; 
   }


   public static String REF_CLASS = "AnalysisDatasetColumn";
   public static String PROP_ORDER_TYPE = "orderType";
   public static String PROP_RESOURCE_COLUMN_NAME = "resourceColumnName";
   public static String PROP_GROUP_BY = "groupBy";
   public static String PROP_CN_NAME = "cnName";
   public static String PROP_ORDER_SEQ = "orderSeq";
   public static String PROP_ORDER_BY = "orderBy";
   public static String PROP_ALIAS_NAME = "aliasName";
   public static String PROP_AGGREGATE_FUNCTION = "aggregateFunction";
   public static String PROP_ANALYSIS_DATASET_ID = "analysisDatasetId";
   public static String PROP_RESOURCE_COLUMN_ID = "resourceColumnId";
   public static String PROP_SELECTED = "selected";
   public static String PROP_SEQ_NO = "seqNo";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_ANALYSIS_DATASET_COLUMN";
   public static String COL_ORDER_TYPE = "ORDER_TYPE";
   public static String COL_RESOURCE_COLUMN_NAME = "RESOURCE_COLUMN_NAME";
   public static String COL_GROUP_BY = "GROUP_BY";
   public static String COL_CN_NAME = "CN_NAME";
   public static String COL_ORDER_SEQ = "ORDER_SEQ";
   public static String COL_ORDER_BY = "ORDER_BY";
   public static String COL_ALIAS_NAME = "ALIAS_NAME";
   public static String COL_AGGREGATE_FUNCTION = "AGGREGATE_FUNCTION";
   public static String COL_ANALYSIS_DATASET_ID = "ANALYSIS_DATASET_ID";
   public static String COL_RESOURCE_COLUMN_ID = "RESOURCE_COLUMN_ID";
   public static String COL_SELECTED = "SELECTED";
   public static String COL_SEQ_NO = "SEQ_NO";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.analysis.AnalysisDatasetColumn)) return false;
      else {
         com.liusy.datapp.model.analysis.AnalysisDatasetColumn o = (com.liusy.datapp.model.analysis.AnalysisDatasetColumn) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[AnalysisDatasetColumn:");
		buffer.append(" id:").append(id);
		buffer.append(" orderType:").append(dealNull(orderType));
		buffer.append(" resourceColumnName:").append(dealNull(resourceColumnName));
		buffer.append(" groupBy:").append(dealNull(groupBy));
		buffer.append(" cnName:").append(dealNull(cnName));
		buffer.append(" orderSeq:").append(dealNull(orderSeq));
		buffer.append(" orderBy:").append(dealNull(orderBy));
		buffer.append(" aliasName:").append(dealNull(aliasName));
		buffer.append(" aggregateFunction:").append(dealNull(aggregateFunction));
		buffer.append(" analysisDatasetId:").append(dealNull(analysisDatasetId));
		buffer.append(" resourceColumnId:").append(dealNull(resourceColumnId));
		buffer.append(" selected:").append(dealNull(selected));
		buffer.append(" seqNo:").append(dealNull(seqNo));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"orderType\":\"").append(dealNull(orderType)).append("\"");
		buffer.append(",\"resourceColumnName\":\"").append(dealNull(resourceColumnName)).append("\"");
		buffer.append(",\"groupBy\":\"").append(dealNull(groupBy)).append("\"");
		buffer.append(",\"cnName\":\"").append(dealNull(cnName)).append("\"");
		buffer.append(",\"orderSeq\":\"").append(dealNull(orderSeq)).append("\"");
		buffer.append(",\"orderBy\":\"").append(dealNull(orderBy)).append("\"");
		buffer.append(",\"aliasName\":\"").append(dealNull(aliasName)).append("\"");
		buffer.append(",\"aggregateFunction\":\"").append(dealNull(aggregateFunction)).append("\"");
		buffer.append(",\"analysisDatasetId\":\"").append(dealNull(analysisDatasetId)).append("\"");
		buffer.append(",\"resourceColumnId\":\"").append(dealNull(resourceColumnId)).append("\"");
		buffer.append(",\"selected\":\"").append(dealNull(selected)).append("\"");
		buffer.append(",\"seqNo\":\"").append(dealNull(seqNo)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
