package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_FILTER table.
 *
 * @hibernate.class
 *  table="T_COMPARE_FILTER"
 */

public class CompareFilter implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String filterOperator;   //  

   private java.lang.Integer compId;   //  

   private java.lang.Integer columnId;   //  

   private java.lang.String filterValue;   //  


   // Constructors
   public CompareFilter() {
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
    * Return the value associated with the column: COMP_ID
    *  
    */
   public java.lang.Integer getCompId() { 
      return this.compId; 
   }
   /**
    * Set the value related to the column: COMP_ID
    * @param compId is the 'COMP_ID' value
    */
   public void setCompId(java.lang.Integer compId) { 
      this.compId = compId; 
   }

   /**
    * Return the value associated with the column: IND_ID
    *  
    */
   public java.lang.Integer getColumnId() { 
      return this.columnId; 
   }
   /**
    * Set the value related to the column: IND_ID
    * @param indId is the 'IND_ID' value
    */
   public void setColumnId(java.lang.Integer indId) { 
      this.columnId = indId; 
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


   public static String REF_CLASS = "CompareFilter";
   public static String PROP_FILTER_OPERATOR = "filterOperator";
   public static String PROP_COMP_ID = "compId";
   public static String PROP_COLUMN_ID = "columnId";
   public static String PROP_FILTER_VALUE = "filterValue";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_COMPARE_FILTER";
   public static String COL_FILTER_OPERATOR = "FILTER_OPERATOR";
   public static String COL_COMP_ID = "COMP_ID";
   public static String COL_COLUMN_ID = "COLUMN_ID";
   public static String COL_FILTER_VALUE = "FILTER_VALUE";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareFilter)) return false;
      else {
         com.liusy.datapp.model.compare.CompareFilter o = (com.liusy.datapp.model.compare.CompareFilter) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareFilter:");
		buffer.append(" id:").append(id);
		buffer.append(" filterOperator:").append(dealNull(filterOperator));
		buffer.append(" compId:").append(dealNull(compId));
		buffer.append(" columnId:").append(dealNull(columnId));
		buffer.append(" filterValue:").append(dealNull(filterValue));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"filterOperator\":\"").append(dealNull(filterOperator)).append("\"");
		buffer.append(",\"compId\":\"").append(dealNull(compId)).append("\"");
		buffer.append(",\"columnId\":\"").append(dealNull(columnId)).append("\"");
		buffer.append(",\"filterValue\":\"").append(dealNull(filterValue)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
