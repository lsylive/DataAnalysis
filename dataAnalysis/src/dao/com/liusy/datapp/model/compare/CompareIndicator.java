package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_INDICATOR table.
 *
 * @hibernate.class
 *  table="T_COMPARE_INDICATOR"
 */

public class CompareIndicator implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer compId;   //  

   private java.lang.Integer indId;   //  


   // Constructors
   public CompareIndicator() {
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


   public static String REF_CLASS = "CompareIndicator";
   public static String PROP_COMP_ID = "compId";
   public static String PROP_IND_ID = "indId";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_COMPARE_INDICATOR";
   public static String COL_COMP_ID = "COMP_ID";
   public static String COL_IND_ID = "IND_ID";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareIndicator)) return false;
      else {
         com.liusy.datapp.model.compare.CompareIndicator o = (com.liusy.datapp.model.compare.CompareIndicator) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareIndicator:");
		buffer.append(" id:").append(id);
		buffer.append(" compId:").append(dealNull(compId));
		buffer.append(" indId:").append(dealNull(indId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"compId\":\"").append(dealNull(compId)).append("\"");
		buffer.append(",\"indId\":\"").append(dealNull(indId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
