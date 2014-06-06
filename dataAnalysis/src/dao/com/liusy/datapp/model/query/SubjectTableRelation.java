package com.liusy.datapp.model.query;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_QUERY_SUBJECT_DATATABLE_R table.
 *
 * @hibernate.class
 *  table="T_QUERY_SUBJECT_DATATABLE_R"
 */

public class SubjectTableRelation implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer dtId;   //  

   private java.lang.Integer subjectId;   //  

   private java.lang.Integer dtOrde;

   // Constructors
   public SubjectTableRelation() {
   }

   
   
   public java.lang.Integer getDtOrde() {
		return dtOrde;
	}

	public void setDtOrde(java.lang.Integer dtOrde) {
		dtOrde = dtOrde;
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


   public static String REF_CLASS = "SubjectTableRelation";
   public static String PROP_DT_ID = "dtId";
   public static String PROP_SUBJECT_ID = "subjectId";
   public static String PROP_ID = "id";
   public static String PROP_DT_ORDE = "dtOrde";

   public static String REF_TABLE = "T_QUERY_SUBJECT_DATATABLE_R";
   public static String COL_DT_ID = "DT_ID";
   public static String COL_SUBJECT_ID = "SUBJECT_ID";
   public static String COL_ID = "ID";
   public static String COL_DT_ORDE = "DT_ORDE";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.query.SubjectTableRelation)) return false;
      else {
         com.liusy.datapp.model.query.SubjectTableRelation o = (com.liusy.datapp.model.query.SubjectTableRelation) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SubjectTableRelation:");
		buffer.append(" id:").append(id);
		buffer.append(" dtId:").append(dealNull(dtId));
		buffer.append(" subjectId:").append(dealNull(subjectId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"dtId\":\"").append(dealNull(dtId)).append("\"");
		buffer.append(",\"subjectId\":\"").append(dealNull(subjectId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
