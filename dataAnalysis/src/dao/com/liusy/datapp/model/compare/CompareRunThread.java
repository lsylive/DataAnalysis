package com.liusy.datapp.model.compare;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_COMPARE_RUN_THREAD table.
 *
 * @hibernate.class
 *  table="T_COMPARE_RUN_THREAD"
 */

public class CompareRunThread implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String THREAD_STOPPED = "0";
    public static final String THREAD_RUNNING = "1";
    public static final String THREAD_FINISHED = "2";
    public static final String THREAD_FAILED = "3";

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String thCode;   //  

   private java.lang.Integer runId;   //  

   private java.sql.Timestamp startTime;   //  
   
   private java.sql.Timestamp finishTime;   //
   
   private java.lang.Integer mtPage;   //  
   
   private java.lang.Integer mtLine;   //  
   
   private java.lang.Integer stId;   //  
   
   private java.lang.Integer stPage;   //  
   
   private java.lang.Integer stLine;   //  
   
   private java.lang.String thStatus;   //  

   
   public static String REF_CLASS = "CompareRunThread";
   public static String PROP_TH_CODE = "thCode";
   public static String PROP_RUN_ID = "runId";
   public static String PROP_MT_PAGE = "mtPage";
   public static String PROP_ST_ID = "stId";
   public static String PROP_ST_PAGE = "stPage";
   public static String PROP_TH_STATUS = "thStatus";

   public static String REF_TABLE = "T_COMPARE_RUN_THREAD";
   public static String COL_TH_CODE = "TH_CODE";
   public static String COL_RUN_ID = "RUN_ID";
   public static String COL_MT_PAGE = "MT_PAGE";
   public static String COL_ST_ID = "ST_ID";
   public static String COL_ST_PAGE = "ST_PAGE";
   public static String COL_TH_STATUS = "TH_STATUS";
  
   
   // Constructors
   public CompareRunThread() {
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


	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}

	public java.lang.Integer getRunId() {
		return runId;
	}

	public void setRunId(java.lang.Integer runId) {
		this.runId = runId;
	}

	public java.sql.Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(java.sql.Timestamp startTime) {
		this.startTime = startTime;
	}

	public java.sql.Timestamp getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(java.sql.Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	public java.lang.Integer getMtPage() {
		return mtPage;
	}

	public void setMtPage(java.lang.Integer mtPage) {
		this.mtPage = mtPage;
	}

	public java.lang.Integer getMtLine() {
		return mtLine;
	}

	public void setMtLine(java.lang.Integer mtLine) {
		this.mtLine = mtLine;
	}

	public java.lang.Integer getStId() {
		return stId;
	}

	public void setStId(java.lang.Integer stId) {
		this.stId = stId;
	}

	public java.lang.Integer getStPage() {
		return stPage;
	}

	public void setStPage(java.lang.Integer stPage) {
		this.stPage = stPage;
	}

	public java.lang.Integer getStLine() {
		return stLine;
	}

	public java.lang.String getThStatus() {
		return thStatus;
	}

	public void setThStatus(java.lang.String thStatus) {
		this.thStatus = thStatus;
	}

	public void setStLine(java.lang.Integer stLine) {
		this.stLine = stLine;
	}

	/**
	 * Override hashCode.
	 *
	 * @return the Objects hashcode.
	 */
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31
			* hashCode
			+ (int) (+serialVersionUID ^ (serialVersionUID >>> 32));
		hashCode = 31
			* hashCode
			+ (THREAD_STOPPED == null ? 0 : THREAD_STOPPED.hashCode());
		hashCode = 31
			* hashCode
			+ (THREAD_RUNNING == null ? 0 : THREAD_RUNNING.hashCode());
		hashCode = 31
			* hashCode
			+ (THREAD_FINISHED == null ? 0 : THREAD_FINISHED.hashCode());
		hashCode = 31 * hashCode + (id == null ? 0 : id.hashCode());
		hashCode = 31 * hashCode + (thCode == null ? 0 : thCode.hashCode());
		hashCode = 31 * hashCode + (runId == null ? 0 : runId.hashCode());
		hashCode = 31
			* hashCode
			+ (startTime == null ? 0 : startTime.hashCode());
		hashCode = 31
			* hashCode
			+ (finishTime == null ? 0 : finishTime.hashCode());
		hashCode = 31 * hashCode + (mtPage == null ? 0 : mtPage.hashCode());
		hashCode = 31 * hashCode + (mtLine == null ? 0 : mtLine.hashCode());
		hashCode = 31 * hashCode + (stId == null ? 0 : stId.hashCode());
		hashCode = 31 * hashCode + (stPage == null ? 0 : stPage.hashCode());
		hashCode = 31 * hashCode + (stLine == null ? 0 : stLine.hashCode());
		hashCode = 31 * hashCode + (thStatus == null ? 0 : thStatus.hashCode());
		hashCode = 31
			* hashCode
			+ (REF_CLASS == null ? 0 : REF_CLASS.hashCode());
		hashCode = 31
			* hashCode
			+ (PROP_TH_CODE == null ? 0 : PROP_TH_CODE.hashCode());
		hashCode = 31
			* hashCode
			+ (REF_TABLE == null ? 0 : REF_TABLE.hashCode());
		hashCode = 31
			* hashCode
			+ (COL_TH_CODE == null ? 0 : COL_TH_CODE.hashCode());
		return hashCode;
	}

	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[CompareRunThread:");
			buffer.append(" serialVersionUID: ");
			buffer.append(serialVersionUID);
			buffer.append(" THREAD_PENDING: ");
			buffer.append(THREAD_STOPPED);
			buffer.append(" THREAD_RUNNING: ");
			buffer.append(THREAD_RUNNING);
			buffer.append(" THREAD_FINISHED: ");
			buffer.append(THREAD_FINISHED);
			buffer.append(" id: ");
			buffer.append(id);
			buffer.append(" thCode: ");
			buffer.append(thCode);
			buffer.append(" runId: ");
			buffer.append(runId);
			buffer.append(" startTime: ");
			buffer.append(startTime);
			buffer.append(" finishTime: ");
			buffer.append(finishTime);
			buffer.append(" mtPage: ");
			buffer.append(mtPage);
			buffer.append(" mtLine: ");
			buffer.append(mtLine);
			buffer.append(" stId: ");
			buffer.append(stId);
			buffer.append(" stPage: ");
			buffer.append(stPage);
			buffer.append(" stLine: ");
			buffer.append(stLine);
			buffer.append(" thStatus: ");
			buffer.append(thStatus);
			buffer.append(" REF_CLASS: ");
			buffer.append(REF_CLASS);
			buffer.append(" PROP_TH_CODE: ");
			buffer.append(PROP_TH_CODE);
			buffer.append(" REF_TABLE: ");
			buffer.append(REF_TABLE);
			buffer.append(" COL_TH_CODE: ");
			buffer.append(COL_TH_CODE);
			buffer.append("]");
			return buffer.toString();
		}

	/**
	 * Returns <code>true</code> if this <code>CompareRunThread</code> is the same as the o argument.
	 *
	 * @return <code>true</code> if this <code>CompareRunThread</code> is the same as the o argument.
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (o.getClass() != getClass()) {
			return false;
		}
		CompareRunThread castedObj = (CompareRunThread) o;
		return ((this.id == null ? castedObj.id == null : this.id
			.equals(castedObj.id))
			&& (this.thCode == null ? castedObj.thCode == null : this.thCode
				.equals(castedObj.thCode))
			&& (this.runId == null ? castedObj.runId == null : this.runId
				.equals(castedObj.runId))
			&& (this.startTime == null
				? castedObj.startTime == null
				: this.startTime.equals(castedObj.startTime))
			&& (this.finishTime == null
				? castedObj.finishTime == null
				: this.finishTime.equals(castedObj.finishTime))
			&& (this.mtPage == null ? castedObj.mtPage == null : this.mtPage
				.equals(castedObj.mtPage))
			&& (this.mtLine == null ? castedObj.mtLine == null : this.mtLine
				.equals(castedObj.mtLine))
			&& (this.stId == null ? castedObj.stId == null : this.stId
				.equals(castedObj.stId))
			&& (this.stPage == null ? castedObj.stPage == null : this.stPage
				.equals(castedObj.stPage))
			&& (this.stLine == null ? castedObj.stLine == null : this.stLine
				.equals(castedObj.stLine)) && (this.thStatus == null
			? castedObj.thStatus == null
			: this.thStatus.equals(castedObj.thStatus)));
	}

	public java.lang.String getThCode() {
		return thCode;
	}

	public void setThCode(java.lang.String thCode) {
		this.thCode = thCode;
	}
}
