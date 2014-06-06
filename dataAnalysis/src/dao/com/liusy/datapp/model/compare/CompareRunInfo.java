package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_RUNINFO table.
 *
 * @hibernate.class
 *  table="T_COMPARE_RUNINFO"
 */

public class CompareRunInfo implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer compId;   //  

   private java.lang.String memo;   //  

   private java.lang.String status;   //  

   private java.sql.Timestamp startTime;   //  

   private java.sql.Timestamp finishTime;   //  


   private java.lang.String ptableName;   //  
   
   private int pmatchCount;



// Constructors
   public CompareRunInfo() {
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
    * Return the value associated with the column: MEMO
    *  
    */
   public java.lang.String getMemo() { 
      return this.memo; 
   }
   /**
    * Set the value related to the column: MEMO
    * @param memo is the 'MEMO' value
    */
   public void setMemo(java.lang.String memo) { 
      this.memo = memo; 
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
    * Return the value associated with the column: START_TIME
    *  
    */
   public java.sql.Timestamp getStartTime() { 
      return this.startTime; 
   }
   /**
    * Set the value related to the column: START_TIME
    * @param startTime is the 'START_TIME' value
    */
   public void setStartTime(java.sql.Timestamp startTime) { 
      this.startTime = startTime; 
   }

   /**
    * Return the value associated with the column: FINISH_TIME
    *  
    */
   public java.sql.Timestamp getFinishTime() { 
      return this.finishTime; 
   }
   /**
    * Set the value related to the column: FINISH_TIME
    * @param finishTime is the 'FINISH_TIME' value
    */
   public void setFinishTime(java.sql.Timestamp finishTime) { 
      this.finishTime = finishTime; 
   }

   /**
    * Return the value associated with the column: PTABLE_NAME
    *  
    */
   public java.lang.String getPtableName() { 
      return this.ptableName; 
   }
   /**
    * Set the value related to the column: PTABLE_NAME
    * @param ptableName is the 'PTABLE_NAME' value
    */
   public void setPtableName(java.lang.String ptableName) { 
      this.ptableName = ptableName; 
   }
   
   public int getPmatchCount() {
		return pmatchCount;
	}

	public void setPmatchCount(int pmatchCount) {
		this.pmatchCount = pmatchCount;
	}


   public static String REF_CLASS = "CompareRunInfo";
   public static String PROP_COMP_ID = "compId";
   public static String PROP_MEMO = "memo";
   public static String PROP_STATUS = "status";
   public static String PROP_START_TIME = "startTime";
   public static String PROP_FINISH_TIME = "finishTime";
   public static String PROP_ID = "id";
   public static String PROP_PTABLE_NAME = "ptableName";
   public static String PROP_PMATCH_COUNT = "pmatchCount";

   public static String REF_TABLE = "T_COMPARE_RUNINFO";
   public static String COL_COMP_ID = "COMP_ID";
   public static String COL_MEMO = "MEMO";
   public static String COL_STATUS = "STATUS";
   public static String COL_START_TIME = "START_TIME";
   public static String COL_FINISH_TIME = "FINISH_TIME";
   public static String COL_ID = "ID";
   public static String COL_PTABLE_NAME = "PTABLE_NAME";
   public static String COL_PMATCH_COUNT = "PMATCH_COUNT";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareRunInfo)) return false;
      else {
         com.liusy.datapp.model.compare.CompareRunInfo o = (com.liusy.datapp.model.compare.CompareRunInfo) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareRunInfo:");
		buffer.append(" id:").append(id);
		buffer.append(" compId:").append(dealNull(compId));
		buffer.append(" memo:").append(dealNull(memo));
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" startTime:").append(dealNull(startTime));
		buffer.append(" finishTime:").append(dealNull(finishTime));
		buffer.append(" ptableName:").append(dealNull(ptableName));
		buffer.append(" pmatchCount:").append(dealNull(pmatchCount));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"compId\":\"").append(dealNull(compId)).append("\"");
		buffer.append(",\"memo\":\"").append(dealNull(memo)).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"startTime\":\"").append(dealNull(startTime)).append("\"");
		buffer.append(",\"finishTime\":\"").append(dealNull(finishTime)).append("\"");
		buffer.append(",\"ptableName\":\"").append(dealNull(ptableName)).append("\"");
		buffer.append(",\"pmatchCount\":\"").append(dealNull(pmatchCount)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
