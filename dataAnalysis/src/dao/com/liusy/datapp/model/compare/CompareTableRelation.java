package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_DATATABLE_R table.
 *
 * @hibernate.class
 *  table="T_COMPARE_DATATABLE_R"
 */

public class CompareTableRelation implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   
   private java.lang.Integer dtId;   //  

   private java.lang.Integer compId;   //  

   private java.lang.String colList;

   // Constructors
   public CompareTableRelation() {
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



   public java.lang.String getColList() {
	return colList;
}

public void setColList(java.lang.String colList) {
	this.colList = colList;
}

/**
    * Return the value associated with the column: DT_ID
    *  
    */
   public java.lang.Integer getDtId() { 
      return this.dtId; 
   }
   /**
    * Set the value related to the column: DT_ID
    * @param dtId is the 'DT_ID' value
    */
   public void setDtId(java.lang.Integer dtId) { 
      this.dtId = dtId; 
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


   public static String REF_CLASS = "CompareTableRelation";
   public static String PROP_COL_LIST = "colList";
   public static String PROP_DT_ID = "dtId";
   public static String PROP_COMP_ID = "compId";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_COMPARE_DATATABLE_R";
   public static String COL_COL_LIST = "colList";
   public static String COL_DT_ID = "DT_ID";
   public static String COL_COMP_ID = "COMP_ID";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareTableRelation)) return false;
      else {
         com.liusy.datapp.model.compare.CompareTableRelation o = (com.liusy.datapp.model.compare.CompareTableRelation) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareTableRelation:");
		buffer.append(" id:").append(id);
		buffer.append(" colList:").append(dealNull(colList));
		buffer.append(" dtId:").append(dealNull(dtId));
		buffer.append(" compId:").append(dealNull(compId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"colList\":\"").append(dealNull(colList)).append("\"");
		buffer.append(",\"dtId\":\"").append(dealNull(dtId)).append("\"");
		buffer.append(",\"compId\":\"").append(dealNull(compId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
