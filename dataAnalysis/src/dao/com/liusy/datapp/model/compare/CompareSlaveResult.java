package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_SLAVERESULT table.
 *
 * @hibernate.class
 *  table="T_COMPARE_SLAVERESULT"
 */

public class CompareSlaveResult implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String slaveTableName;   //  

   private java.lang.Integer pkId;   //  

   private java.lang.String status;   //  

   private java.util.Date runTime;   //  

   private java.lang.Integer runId;   //  

   private java.lang.Integer count;   //  

   private java.util.Date finishTime;   //  


   private java.lang.String slavePk;   //  

   // Constructors
   public CompareSlaveResult() {
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
    * Return the value associated with the column: PK_ID
    *  
    */
   public java.lang.Integer getPkId() { 
      return this.pkId; 
   }
   /**
    * Set the value related to the column: PK_ID
    * @param pkId is the 'PK_ID' value
    */
   public void setPkId(java.lang.Integer pkId) { 
      this.pkId = pkId; 
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
    * Return the value associated with the column: RUN_TIME
    *  
    */
   public java.util.Date getRunTime() { 
      return this.runTime; 
   }
   /**
    * Set the value related to the column: RUN_TIME
    * @param runTime is the 'RUN_TIME' value
    */
   public void setRunTime(java.util.Date runTime) { 
      this.runTime = runTime; 
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

   /**
    * Return the value associated with the column: COUNT
    *  
    */
   public java.lang.Integer getCount() { 
      return this.count; 
   }
   /**
    * Set the value related to the column: COUNT
    * @param count is the 'COUNT' value
    */
   public void setCount(java.lang.Integer count) { 
      this.count = count; 
   }

   /**
    * Return the value associated with the column: FINISH_TIME
    *  
    */
   public java.util.Date getFinishTime() { 
      return this.finishTime; 
   }
   /**
    * Set the value related to the column: FINISH_TIME
    * @param finishTime is the 'FINISH_TIME' value
    */
   public void setFinishTime(java.util.Date finishTime) { 
      this.finishTime = finishTime; 
   }

   /**
    * Return the value associated with the column: SLAVE_PK
    *  
    */
   public java.lang.String getSlavePk() { 
      return this.slavePk; 
   }
   /**
    * Set the value related to the column: SLAVE_PK
    * @param slavePk is the 'SLAVE_PK' value
    */
   public void setSlavePk(java.lang.String slavePk) { 
      this.slavePk = slavePk; 
   }


   public static String REF_CLASS = "CompareSlaveResult";
   public static String PROP_SLAVETABLE_NAME = "slaveTableName";
   public static String PROP_PK_ID = "pkId";
   public static String PROP_STATUS = "status";
   public static String PROP_RUN_TIME = "runTime";
   public static String PROP_RUN_ID = "runId";
   public static String PROP_COUNT = "count";
   public static String PROP_FINISH_TIME = "finishTime";
   public static String PROP_ID = "id";
   public static String PROP_SLAVE_PK = "slavePk";

   public static String REF_TABLE = "T_COMPARE_SLAVERESULT";
   public static String COL_SLAVETABLE_NAME = "SLAVETABLE_NAME";
   public static String COL_PK_ID = "PK_ID";
   public static String COL_STATUS = "STATUS";
   public static String COL_RUN_TIME = "RUN_TIME";
   public static String COL_RUN_ID = "RUN_ID";
   public static String COL_COUNT = "COUNT";
   public static String COL_FINISH_TIME = "FINISH_TIME";
   public static String COL_ID = "ID";
   public static String COL_SLAVE_PK = "SLAVE_PK";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareSlaveResult)) return false;
      else {
         com.liusy.datapp.model.compare.CompareSlaveResult o = (com.liusy.datapp.model.compare.CompareSlaveResult) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareSlaveResult:");
		buffer.append(" id:").append(id);
		buffer.append(" slaveTableName:").append(dealNull(slaveTableName));
		buffer.append(" pkId:").append(dealNull(pkId));
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" runTime:").append(dealNull(runTime));
		buffer.append(" runId:").append(dealNull(runId));
		buffer.append(" count:").append(dealNull(count));
		buffer.append(" finishTime:").append(dealNull(finishTime));
		buffer.append(" slavePk:").append(dealNull(slavePk));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"slaveTableName\":\"").append(dealNull(slaveTableName)).append("\"");
		buffer.append(",\"pkId\":\"").append(dealNull(pkId)).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"runTime\":\"").append(dealNull(runTime)).append("\"");
		buffer.append(",\"runId\":\"").append(dealNull(runId)).append("\"");
		buffer.append(",\"count\":\"").append(dealNull(count)).append("\"");
		buffer.append(",\"finishTime\":\"").append(dealNull(finishTime)).append("\"");
		buffer.append(",\"slavePk\":\"").append(dealNull(slavePk)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
