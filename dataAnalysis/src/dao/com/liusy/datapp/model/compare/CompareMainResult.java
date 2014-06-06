package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_MAINRESULT table.
 *
 * @hibernate.class
 *  table="T_COMPARE_MAINRESULT"
 */

public class CompareMainResult implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String slaveTableName;   //  

   private java.lang.Integer processPkId;   //  

   private java.lang.String status;   //  

   private java.lang.Integer runId;   //  


   // Constructors
   public CompareMainResult() {
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
    * Return the value associated with the column: SLAVETABLE_NAME
    *  
    */
   public java.lang.String getSlaveTableName() { 
      return this.slaveTableName; 
   }
   /**
    * Set the value related to the column: SLAVETABLE_NAME
    * @param slaveTableName is the 'SLAVETABLE_NAME' value
    */
   public void setSlaveTableName(java.lang.String slaveTableName) { 
      this.slaveTableName = slaveTableName; 
   }

   /**
    * Return the value associated with the column: PROCESSED_PKID
    *  
    */
   public java.lang.Integer getProcessPkId() { 
      return this.processPkId; 
   }
   /**
    * Set the value related to the column: PROCESSED_PKID
    * @param processPkId is the 'PROCESSED_PKID' value
    */
   public void setProcessPkId(java.lang.Integer processPkId) { 
      this.processPkId = processPkId; 
   }

   /**
    * Return the value associated with the column: STATUS
    *  
    */
   public java.lang.String getStatus() { 
      return this.status; 
   }
   /**
    * Set the value related to the column: STATUS
    * @param status is the 'STATUS' value
    */
   public void setStatus(java.lang.String status) { 
      this.status = status; 
   }

   /**
    * Return the value associated with the column: RUN_ID
    *  
    */
   public java.lang.Integer getRunId() { 
      return this.runId; 
   }
   /**
    * Set the value related to the column: RUN_ID
    * @param runId is the 'RUN_ID' value
    */
   public void setRunId(java.lang.Integer runId) { 
      this.runId = runId; 
   }


   public static String REF_CLASS = "CompareMainResult";
   public static String PROP_SLAVETABLE_NAME = "slaveTableName";
   public static String PROP_PROCESSED_PKID = "processPkId";
   public static String PROP_STATUS = "status";
   public static String PROP_RUN_ID = "runId";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_COMPARE_MAINRESULT";
   public static String COL_SLAVETABLE_NAME = "SLAVETABLE_NAME";
   public static String COL_PROCESSED_PKID = "PROCESSED_PKID";
   public static String COL_STATUS = "STATUS";
   public static String COL_RUN_ID = "RUN_ID";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareMainResult)) return false;
      else {
         com.liusy.datapp.model.compare.CompareMainResult o = (com.liusy.datapp.model.compare.CompareMainResult) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareMainResult:");
		buffer.append(" id:").append(id);
		buffer.append(" slaveTableName:").append(dealNull(slaveTableName));
		buffer.append(" processPkId:").append(dealNull(processPkId));
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" runId:").append(dealNull(runId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"slaveTableName\":\"").append(dealNull(slaveTableName)).append("\"");
		buffer.append(",\"processPkId\":\"").append(dealNull(processPkId)).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"runId\":\"").append(dealNull(runId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
