package com.liusy.datapp.model.query;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_QUERY_TABLEPARAM_CFG table.
 *
 * @hibernate.class
 *  table="T_QUERY_TABLEPARAM_CFG"
 */

public class QueryParamCfg implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer columnId;   //  

   private java.lang.String stQuery;   //  

   private java.lang.String filterOperator;   //  

   private java.lang.String batchQuery;   //  

   private java.lang.String homonymQuery;   //  

   private java.lang.String isFilter;   //  

   private java.lang.Integer tableId;   //  

   private java.lang.Integer seqNo;   //  


   private java.lang.String fuzzyQuery;   //  

   // Constructors
   public QueryParamCfg() {
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
    * Return the value associated with the column: COLUMN_ID
    *  
    */
   public java.lang.Integer getColumnId() { 
      return this.columnId; 
   }
   /**
    * Set the value related to the column: COLUMN_ID
    * @param columnId is the 'COLUMN_ID' value
    */
   public void setColumnId(java.lang.Integer columnId) { 
      this.columnId = columnId; 
   }

   /**
    * Return the value associated with the column: ST_QUERY
    *  
    */
   public java.lang.String getStQuery() { 
      return this.stQuery; 
   }
   /**
    * Set the value related to the column: ST_QUERY
    * @param stQuery is the 'ST_QUERY' value
    */
   public void setStQuery(java.lang.String stQuery) { 
      this.stQuery = stQuery; 
   }

   /**
    * Return the value associated with the column: FILTER_OPERATOR
    *  
    */
   public java.lang.String getFilterOperator() { 
      return this.filterOperator; 
   }
   /**
    * Set the value related to the column: FILTER_OPERATOR
    * @param filterOperator is the 'FILTER_OPERATOR' value
    */
   public void setFilterOperator(java.lang.String filterOperator) { 
      this.filterOperator = filterOperator; 
   }

   /**
    * Return the value associated with the column: BATCH_QUERY
    *  
    */
   public java.lang.String getBatchQuery() { 
      return this.batchQuery; 
   }
   /**
    * Set the value related to the column: BATCH_QUERY
    * @param batchQuery is the 'BATCH_QUERY' value
    */
   public void setBatchQuery(java.lang.String batchQuery) { 
      this.batchQuery = batchQuery; 
   }

   /**
    * Return the value associated with the column: HOMONYM_QUERY
    *  
    */
   public java.lang.String getHomonymQuery() { 
      return this.homonymQuery; 
   }
   /**
    * Set the value related to the column: HOMONYM_QUERY
    * @param homonymQuery is the 'HOMONYM_QUERY' value
    */
   public void setHomonymQuery(java.lang.String homonymQuery) { 
      this.homonymQuery = homonymQuery; 
   }

   /**
    * Return the value associated with the column: IS_FILTER
    *  
    */
   public java.lang.String getIsFilter() { 
      return this.isFilter; 
   }
   /**
    * Set the value related to the column: IS_FILTER
    * @param isFilter is the 'IS_FILTER' value
    */
   public void setIsFilter(java.lang.String isFilter) { 
      this.isFilter = isFilter; 
   }

   /**
    * Return the value associated with the column: TABLE_ID
    *  
    */
   public java.lang.Integer getTableId() { 
      return this.tableId; 
   }
   /**
    * Set the value related to the column: TABLE_ID
    * @param tableId is the 'TABLE_ID' value
    */
   public void setTableId(java.lang.Integer tableId) { 
      this.tableId = tableId; 
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

   /**
    * Return the value associated with the column: FUZZY_QUERY
    *  
    */
   public java.lang.String getFuzzyQuery() { 
      return this.fuzzyQuery; 
   }
   /**
    * Set the value related to the column: FUZZY_QUERY
    * @param fuzzyQuery is the 'FUZZY_QUERY' value
    */
   public void setFuzzyQuery(java.lang.String fuzzyQuery) { 
      this.fuzzyQuery = fuzzyQuery; 
   }


   public static String REF_CLASS = "QueryParamCfg";
   public static String PROP_COLUMN_ID = "columnId";
   public static String PROP_ST_QUERY = "stQuery";
   public static String PROP_FILTER_OPERATOR = "filterOperator";
   public static String PROP_BATCH_QUERY = "batchQuery";
   public static String PROP_HOMONYM_QUERY = "homonymQuery";
   public static String PROP_IS_FILTER = "isFilter";
   public static String PROP_TABLE_ID = "tableId";
   public static String PROP_SEQ_NO = "seqNo";
   public static String PROP_ID = "id";
   public static String PROP_FUZZY_QUERY = "fuzzyQuery";

   public static String REF_TABLE = "T_QUERY_TABLEPARAM_CFG";
   public static String COL_COLUMN_ID = "COLUMN_ID";
   public static String COL_ST_QUERY = "ST_QUERY";
   public static String COL_FILTER_OPERATOR = "FILTER_OPERATOR";
   public static String COL_BATCH_QUERY = "BATCH_QUERY";
   public static String COL_HOMONYM_QUERY = "HOMONYM_QUERY";
   public static String COL_IS_FILTER = "IS_FILTER";
   public static String COL_TABLE_ID = "TABLE_ID";
   public static String COL_SEQ_NO = "SEQ_NO";
   public static String COL_ID = "ID";
   public static String COL_FUZZY_QUERY = "FUZZY_QUERY";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.query.QueryParamCfg)) return false;
      else {
         com.liusy.datapp.model.query.QueryParamCfg o = (com.liusy.datapp.model.query.QueryParamCfg) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[QueryParamCfg:");
		buffer.append(" id:").append(id);
		buffer.append(" columnId:").append(dealNull(columnId));
		buffer.append(" stQuery:").append(dealNull(stQuery));
		buffer.append(" filterOperator:").append(dealNull(filterOperator));
		buffer.append(" batchQuery:").append(dealNull(batchQuery));
		buffer.append(" homonymQuery:").append(dealNull(homonymQuery));
		buffer.append(" isFilter:").append(dealNull(isFilter));
		buffer.append(" tableId:").append(dealNull(tableId));
		buffer.append(" seqNo:").append(dealNull(seqNo));
		buffer.append(" fuzzyQuery:").append(dealNull(fuzzyQuery));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"columnId\":\"").append(dealNull(columnId)).append("\"");
		buffer.append(",\"stQuery\":\"").append(dealNull(stQuery)).append("\"");
		buffer.append(",\"filterOperator\":\"").append(dealNull(filterOperator)).append("\"");
		buffer.append(",\"batchQuery\":\"").append(dealNull(batchQuery)).append("\"");
		buffer.append(",\"homonymQuery\":\"").append(dealNull(homonymQuery)).append("\"");
		buffer.append(",\"isFilter\":\"").append(dealNull(isFilter)).append("\"");
		buffer.append(",\"tableId\":\"").append(dealNull(tableId)).append("\"");
		buffer.append(",\"seqNo\":\"").append(dealNull(seqNo)).append("\"");
		buffer.append(",\"fuzzyQuery\":\"").append(dealNull(fuzzyQuery)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
