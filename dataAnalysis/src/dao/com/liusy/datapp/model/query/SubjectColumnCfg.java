package com.liusy.datapp.model.query;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_QUERY_SUBJECT_COLUMN_CFG table.
 *
 * @hibernate.class
 *  table="T_QUERY_SUBJECT_COLUMN_CFG"
 */

public class SubjectColumnCfg implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String stQuery;   //  

   private java.lang.String filterOperator;   //  

   private java.lang.String batchQuery;   //  

   private java.lang.Integer indId;   //  
   private java.lang.String codeSetNo;
   private java.lang.String name;
   private java.lang.String cnName;
   private java.lang.String dataType;
   private java.lang.String homonymQuery;   //  

   private java.lang.String filterValue;   //  

   private java.lang.String isFilter;   //  

   private java.lang.Integer subjectId;   //  

   private java.lang.Integer orderNo;   //  


   private java.lang.String fuzzyQuery;   //  

   // Constructors
   public SubjectColumnCfg() {
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
    * Return the value associated with the column: IND_ID
    *  
    */
   public java.lang.Integer getIndId() { 
      return this.indId; 
   }
   /**
    * Set the value related to the column: IND_ID
    * @param indId is the 'IND_ID' value
    */
   public void setIndId(java.lang.Integer indId) { 
      this.indId = indId; 
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
    * Return the value associated with the column: FILTER_VALUE
    *  
    */
   public java.lang.String getFilterValue() { 
      return this.filterValue; 
   }
   /**
    * Set the value related to the column: FILTER_VALUE
    * @param filterValue is the 'FILTER_VALUE' value
    */
   public void setFilterValue(java.lang.String filterValue) { 
      this.filterValue = filterValue; 
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
    * Return the value associated with the column: SUBJECT_ID
    *  
    */
   public java.lang.Integer getSubjectId() { 
      return this.subjectId; 
   }
   /**
    * Set the value related to the column: SUBJECT_ID
    * @param subjectId is the 'SUBJECT_ID' value
    */
   public void setSubjectId(java.lang.Integer subjectId) { 
      this.subjectId = subjectId; 
   }

   /**
    * Return the value associated with the column: ORDER_NO
    *  
    */
   public java.lang.Integer getOrderNo() { 
      return this.orderNo; 
   }
   /**
    * Set the value related to the column: ORDER_NO
    * @param orderNo is the 'ORDER_NO' value
    */
   public void setOrderNo(java.lang.Integer orderNo) { 
      this.orderNo = orderNo; 
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
   public java.lang.String getCnName() {
		return cnName;
	}

	public void setCnName(java.lang.String cnName) {
		this.cnName = cnName;
	}

	public java.lang.String getCodeSetNo() {
		return codeSetNo;
	}

	public void setCodeSetNo(java.lang.String codeSetNo) {
		this.codeSetNo = codeSetNo;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getDataType() {
		return dataType;
	}

	public void setDataType(java.lang.String dataType) {
		this.dataType = dataType;
	}
	
   public static String REF_CLASS = "SubjectColumnCfg";
   public static String PROP_ST_QUERY = "stQuery";
   public static String PROP_FILTER_OPERATOR = "filterOperator";
   public static String PROP_BATCH_QUERY = "batchQuery";
   public static String PROP_IND_ID = "indId";
   public static String PROP_HOMONYM_QUERY = "homonymQuery";
   public static String PROP_FILTER_VALUE = "filterValue";
   public static String PROP_IS_FILTER = "isFilter";
   public static String PROP_SUBJECT_ID = "subjectId";
   public static String PROP_ORDER_NO = "orderNo";
   public static String PROP_ID = "id";
   public static String PROP_FUZZY_QUERY = "fuzzyQuery";
   public static String PROP_CODESET_NO = "codeSetNo";
   public static String PROP_NAME = "name";
   public static String PROP_CNNAME="cnName";
   public static String PROP_DATA_TYPE="dataType";
   public static String PROP_DISPLAY_NUM="displayNum";

   public static String REF_TABLE = "T_QUERY_SUBJECT_COLUMN_CFG";
   public static String COL_ST_QUERY = "ST_QUERY";
   public static String COL_FILTER_OPERATOR = "FILTER_OPERATOR";
   public static String COL_BATCH_QUERY = "BATCH_QUERY";
   public static String COL_IND_ID = "IND_ID";
   public static String COL_HOMONYM_QUERY = "HOMONYM_QUERY";
   public static String COL_FILTER_VALUE = "FILTER_VALUE";
   public static String COL_IS_FILTER = "IS_FILTER";
   public static String COL_SUBJECT_ID = "SUBJECT_ID";
   public static String COL_ORDER_NO = "ORDER_NO";
   public static String COL_ID = "ID";
   public static String COL_FUZZY_QUERY = "FUZZY_QUERY";
   public static String COL_CODESET_NO = "CODESET_NO";
   public static String COL_NAME = "NAME";
   public static String COL_CNNAME = "CN_NAME";
   public static String COL_DATA_TYPE = "DATA_TYPE";
   public static String COL_DISPLAY_NUM = "DISPLAY_NUM";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.query.SubjectColumnCfg)) return false;
      else {
         com.liusy.datapp.model.query.SubjectColumnCfg o = (com.liusy.datapp.model.query.SubjectColumnCfg) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SubjectColumnCfg:");
		buffer.append(" id:").append(id);
		buffer.append(" stQuery:").append(dealNull(stQuery));
		buffer.append(" filterOperator:").append(dealNull(filterOperator));
		buffer.append(" batchQuery:").append(dealNull(batchQuery));
		buffer.append(" indId:").append(dealNull(indId));
		buffer.append(" codeSetNo:").append(dealNull(codeSetNo));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" cnName:").append(dealNull(cnName));
		buffer.append(" dataType:").append(dealNull(dataType));
		buffer.append(" homonymQuery:").append(dealNull(homonymQuery));
		buffer.append(" filterValue:").append(dealNull(filterValue));
		buffer.append(" isFilter:").append(dealNull(isFilter));
		buffer.append(" subjectId:").append(dealNull(subjectId));
		buffer.append(" orderNo:").append(dealNull(orderNo));
		buffer.append(" fuzzyQuery:").append(dealNull(fuzzyQuery));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"stQuery\":\"").append(dealNull(stQuery)).append("\"");
		buffer.append(",\"filterOperator\":\"").append(dealNull(filterOperator)).append("\"");
		buffer.append(",\"batchQuery\":\"").append(dealNull(batchQuery)).append("\"");
		buffer.append(",\"indId\":\"").append(dealNull(indId)).append("\"");
		buffer.append(",\"homonymQuery\":\"").append(dealNull(homonymQuery)).append("\"");
		buffer.append(",\"filterValue\":\"").append(dealNull(filterValue)).append("\"");
		buffer.append(",\"isFilter\":\"").append(dealNull(isFilter)).append("\"");
		buffer.append(",\"subjectId\":\"").append(dealNull(subjectId)).append("\"");
		buffer.append(",\"orderNo\":\"").append(dealNull(orderNo)).append("\"");
		buffer.append(",\"fuzzyQuery\":\"").append(dealNull(fuzzyQuery)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}

	

	

	
}
