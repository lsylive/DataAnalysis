package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_INFO table.
 *
 * @hibernate.class
 *  table="T_COMPARE_INFO"
 */

public class CompareInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String AUTO = "0";
    public static final String MANUAL = "1";

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.util.Date endTime;   //  

   private java.util.Date startTime;   //  

   private java.lang.String compareType;   //  

   private java.lang.Integer userId;   //  

   private java.lang.String comapreStatus;   //  

   private java.util.Date createDate;   //  

   private java.lang.String compareName;   //  

   private java.lang.String runCycle;   //  

   private java.lang.String remark;   //  


   private java.lang.String runStatus;   //  
   private java.lang.Integer runMinute;
   private java.lang.Integer runHour;
   private java.lang.Integer runDay;
   private java.lang.Integer runDate;
   private java.lang.Integer dtId;
   private java.lang.String colList; 
   

   // Constructors
   public CompareInfo() {
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
    * Return the value associated with the column: MODIFY_DATE
    *  
    */
   public java.util.Date getModifyDate() { 
      return this.modifyDate; 
   }
   /**
    * Set the value related to the column: MODIFY_DATE
    * @param modifyDate is the 'MODIFY_DATE' value
    */
   public void setModifyDate(java.util.Date modifyDate) { 
      this.modifyDate = modifyDate; 
   }

   /**
    * Return the value associated with the column: END_TIME
    *  
    */
   public java.util.Date getEndTime() { 
      return this.endTime; 
   }
   /**
    * Set the value related to the column: END_TIME
    * @param endTime is the 'END_TIME' value
    */
   public void setEndTime(java.util.Date endTime) { 
      this.endTime = endTime; 
   }

   /**
    * Return the value associated with the column: START_TIME
    *  
    */
   public java.util.Date getStartTime() { 
      return this.startTime; 
   }
   /**
    * Set the value related to the column: START_TIME
    * @param startTime is the 'START_TIME' value
    */
   public void setStartTime(java.util.Date startTime) { 
      this.startTime = startTime; 
   }

   /**
    * Return the value associated with the column: COMPARE_TYPE
    *  
    */
   public java.lang.String getCompareType() { 
      return this.compareType; 
   }
   /**
    * Set the value related to the column: COMPARE_TYPE
    * @param compareType is the 'COMPARE_TYPE' value
    */
   public void setCompareType(java.lang.String compareType) { 
      this.compareType = compareType; 
   }

   /**
    * Return the value associated with the column: USER_ID
    *  
    */
   public java.lang.Integer getUserId() { 
      return this.userId; 
   }
   /**
    * Set the value related to the column: USER_ID
    * @param userId is the 'USER_ID' value
    */
   public void setUserId(java.lang.Integer userId) { 
      this.userId = userId; 
   }

   /**
    * Return the value associated with the column: COMAPRE_STATUS
    *  
    */
   public java.lang.String getComapreStatus() { 
      return this.comapreStatus; 
   }
   /**
    * Set the value related to the column: COMAPRE_STATUS
    * @param comapreStatus is the 'COMAPRE_STATUS' value
    */
   public void setComapreStatus(java.lang.String comapreStatus) { 
      this.comapreStatus = comapreStatus; 
   }

   /**
    * Return the value associated with the column: CREATE_DATE
    *  
    */
   public java.util.Date getCreateDate() { 
      return this.createDate; 
   }
   /**
    * Set the value related to the column: CREATE_DATE
    * @param createDate is the 'CREATE_DATE' value
    */
   public void setCreateDate(java.util.Date createDate) { 
      this.createDate = createDate; 
   }

   /**
    * Return the value associated with the column: COMPARE_NAME
    *  
    */
   public java.lang.String getCompareName() { 
      return this.compareName; 
   }
   /**
    * Set the value related to the column: COMPARE_NAME
    * @param compareName is the 'COMPARE_NAME' value
    */
   public void setCompareName(java.lang.String compareName) { 
      this.compareName = compareName; 
   }

   /**
    * Return the value associated with the column: RUN_CYCLE
    *  
    */
   public java.lang.String getRunCycle() { 
      return this.runCycle; 
   }
   /**
    * Set the value related to the column: RUN_CYCLE
    * @param runCycle is the 'RUN_CYCLE' value
    */
   public void setRunCycle(java.lang.String runCycle) { 
      this.runCycle = runCycle; 
   }

   /**
    * Return the value associated with the column: REMARK
    *  
    */
   public java.lang.String getRemark() { 
      return this.remark; 
   }
   /**
    * Set the value related to the column: REMARK
    * @param remark is the 'REMARK' value
    */
   public void setRemark(java.lang.String remark) { 
      this.remark = remark; 
   }

   /**
    * Return the value associated with the column: RUN_STATUS
    *  
    */
   public java.lang.String getRunStatus() { 
      return this.runStatus; 
   }
   /**
    * Set the value related to the column: RUN_STATUS
    * @param runStatus is the 'RUN_STATUS' value
    */
   public void setRunStatus(java.lang.String runStatus) { 
      this.runStatus = runStatus; 
   }


   public static String REF_CLASS = "CompareInfo";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_END_TIME = "endTime";
   public static String PROP_START_TIME = "startTime";
   public static String PROP_COMPARE_TYPE = "compareType";
   public static String PROP_USER_ID = "userId";
   public static String PROP_COMAPRE_STATUS = "comapreStatus";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_COMPARE_NAME = "compareName";
   public static String PROP_RUN_CYCLE = "runCycle";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";
   public static String PROP_RUN_STATUS = "runStatus";
   public static String PROP_RUN_DAY = "runDay";
   public static String PROP_RUN_DATE = "runDate";
   public static String PROP_DT_ID = "dtId";
   public static String PROP_COL_LIST = "colList";
   public static String PROP_RUN_MINUTE = "runMinute";

   public static String REF_TABLE = "T_COMPARE_INFO";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_END_TIME = "END_TIME";
   public static String COL_START_TIME = "START_TIME";
   public static String COL_COMPARE_TYPE = "COMPARE_TYPE";
   public static String COL_USER_ID = "USER_ID";
   public static String COL_COMAPRE_STATUS = "COMAPRE_STATUS";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_COMPARE_NAME = "COMPARE_NAME";
   public static String COL_RUN_CYCLE = "RUN_CYCLE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   public static String COL_RUN_STATUS = "RUN_STATUS";
   public static String COL_RUN_DAY = "RUN_DAY";
   public static String COL_RUN_DATE = "RUN_DATE";
   public static String COL_DT_ID = "DT_ID";
   public static String COL_COL_LIST = "COL_LIST";
   public static String COL_RUN_MINUTE = "RUN_MINUTE";
   
   public java.lang.Integer getRunMinute() {
	return runMinute;
}

public void setRunMinute(java.lang.Integer runMinute) {
	this.runMinute = runMinute;
}

public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.compare.CompareInfo)) return false;
      else {
         com.liusy.datapp.model.compare.CompareInfo o = (com.liusy.datapp.model.compare.CompareInfo) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[CompareInfo:");
		buffer.append(" id:").append(id);
		buffer.append(" compareName:").append(this.compareName);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" endTime:").append(dealNull(endTime));
		buffer.append(" startTime:").append(dealNull(startTime));
		buffer.append(" compareType:").append(dealNull(compareType));
		buffer.append(" userId:").append(dealNull(userId));
		buffer.append(" comapreStatus:").append(dealNull(comapreStatus));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" compareName:").append(dealNull(compareName));
		buffer.append(" runCycle:").append(dealNull(runCycle));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" runStatus:").append(dealNull(runStatus));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"endTime\":\"").append(dealNull(endTime)).append("\"");
		buffer.append(",\"startTime\":\"").append(dealNull(startTime)).append("\"");
		buffer.append(",\"compareType\":\"").append(dealNull(compareType)).append("\"");
		buffer.append(",\"userId\":\"").append(dealNull(userId)).append("\"");
		buffer.append(",\"comapreStatus\":\"").append(dealNull(comapreStatus)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"compareName\":\"").append(dealNull(compareName)).append("\"");
		buffer.append(",\"runCycle\":\"").append(dealNull(runCycle)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"runStatus\":\"").append(dealNull(runStatus)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}



	public java.lang.Integer getRunDay() {
		return runDay;
	}

	public void setRunDay(java.lang.Integer runDay) {
		this.runDay = runDay;
	}

	public java.lang.Integer getRunHour() {
		return runHour;
	}

	public void setRunHour(java.lang.Integer runHour) {
		this.runHour = runHour;
	}

	public java.lang.Integer getRunDate() {
		return runDate;
	}

	public void setRunDate(java.lang.Integer runDate) {
		this.runDate = runDate;
	}

	public java.lang.Integer getDtId() {
		return dtId;
	}

	public void setDtId(java.lang.Integer dtId) {
		this.dtId = dtId;
	}

	public java.lang.String getColList() {
		return colList;
	}

	public void setColList(java.lang.String colList) {
		this.colList = colList;
	}
}
