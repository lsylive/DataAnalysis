package com.liusy.datapp.model.blacklist;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_BLACKLIST_DECLARATION table.
 *
 * @hibernate.class
 *  table="T_BLACKLIST_Alarm"
 */

public class BlacklistAlarm implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer decId;   //  

   private java.lang.Integer hitDtId;   //  
   
   private java.lang.String hitIndCode;   //  

   private java.lang.String hitValue;   //  

   private java.util.Date hitTime;   //  

   private java.lang.String alarmStatus;   //  

   


   // Constructors
   public BlacklistAlarm() {
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

  

   public static String REF_CLASS = "BlacklistAlarm";
   public static String PROP_ID = "id";
   public static String PROP_DEC_ID = "decId";
   public static String PROP_HIT_DT_ID = "hitDtId";
   public static String PROP_HIT_IND_CODE = "hitIndCode";
   public static String PROP_HIT_VALUE = "hitValue";
   public static String PROP_ALARM_STATUS = "alarmStatus";
  
   public static String REF_TABLE = "T_BLACKLIST_ALARM";
   public static String COL_DEC_ID = "DEC_ID";
   public static String COL_HIT_DT_ID = "HIT_DT_ID";
   public static String COL_HIT_IND_CODE = "HIT_IND_CODE";
   public static String COL_HIT_VALUE = "HIT_VALUE";
   public static String COL_ALARM_STATUS = "ALARM_STATUS";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof BlacklistAlarm)) return false;
      else {
         BlacklistAlarm o = (BlacklistAlarm) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }



	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}

	public java.lang.Integer getDecId() {
		return decId;
	}

	public void setDecId(java.lang.Integer decId) {
		this.decId = decId;
	}

	public java.lang.Integer getHitDtId() {
		return hitDtId;
	}

	public void setHitDtId(java.lang.Integer hitDtId) {
		this.hitDtId = hitDtId;
	}

	public java.lang.String getHitIndCode() {
		return hitIndCode;
	}

	public void setHitIndCode(java.lang.String hitIndCode) {
		this.hitIndCode = hitIndCode;
	}

	public java.lang.String getHitValue() {
		return hitValue;
	}

	public void setHitValue(java.lang.String hitValue) {
		this.hitValue = hitValue;
	}

	public java.util.Date getHitTime() {
		return hitTime;
	}

	public void setHitTime(java.util.Date hitTime) {
		this.hitTime = hitTime;
	}

	public java.lang.String getAlarmStatus() {
		return alarmStatus;
	}

	public void setAlarmStatus(java.lang.String alarmStatus) {
		this.alarmStatus = alarmStatus;
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
		hashCode = 31 * hashCode + (id == null ? 0 : id.hashCode());
		hashCode = 31 * hashCode + (decId == null ? 0 : decId.hashCode());
		hashCode = 31 * hashCode + (hitDtId == null ? 0 : hitDtId.hashCode());
		hashCode = 31 * hashCode + (hitIndCode == null ? 0 : hitIndCode.hashCode());
		hashCode = 31 * hashCode + (hitValue == null ? 0 : hitValue.hashCode());
		hashCode = 31 * hashCode + (hitTime == null ? 0 : hitTime.hashCode());
		hashCode = 31
			* hashCode
			+ (alarmStatus == null ? 0 : alarmStatus.hashCode());
		hashCode = 31
			* hashCode
			+ (REF_CLASS == null ? 0 : REF_CLASS.hashCode());
		hashCode = 31 * hashCode + (PROP_ID == null ? 0 : PROP_ID.hashCode());
		hashCode = 31
			* hashCode
			+ (PROP_DEC_ID == null ? 0 : PROP_DEC_ID.hashCode());
		hashCode = 31
			* hashCode
			+ (PROP_HIT_DT_ID == null ? 0 : PROP_HIT_DT_ID.hashCode());
		hashCode = 31
			* hashCode
			+ (PROP_HIT_IND_CODE == null ? 0 : PROP_HIT_IND_CODE.hashCode());
		hashCode = 31
			* hashCode
			+ (PROP_HIT_VALUE == null ? 0 : PROP_HIT_VALUE.hashCode());
		hashCode = 31
			* hashCode
			+ (PROP_ALARM_STATUS == null ? 0 : PROP_ALARM_STATUS.hashCode());
		hashCode = 31
			* hashCode
			+ (REF_TABLE == null ? 0 : REF_TABLE.hashCode());
		hashCode = 31
			* hashCode
			+ (COL_DEC_ID == null ? 0 : COL_DEC_ID.hashCode());
		hashCode = 31
			* hashCode
			+ (COL_HIT_DT_ID == null ? 0 : COL_HIT_DT_ID.hashCode());
		hashCode = 31
			* hashCode
			+ (COL_HIT_IND_CODE == null ? 0 : COL_HIT_IND_CODE.hashCode());
		hashCode = 31
			* hashCode
			+ (COL_HIT_VALUE == null ? 0 : COL_HIT_VALUE.hashCode());
		hashCode = 31
			* hashCode
			+ (COL_ALARM_STATUS == null ? 0 : COL_ALARM_STATUS.hashCode());
		hashCode = 31 * hashCode + (COL_ID == null ? 0 : COL_ID.hashCode());
		return hashCode;
	}

	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[BlacklistAlarm:");
			buffer.append(" serialVersionUID: ");
			buffer.append(serialVersionUID);
			buffer.append(" id: ");
			buffer.append(id);
			buffer.append(" decId: ");
			buffer.append(decId);
			buffer.append(" hitDtId: ");
			buffer.append(hitDtId);
			buffer.append(" hitIndId: ");
			buffer.append(hitIndCode);
			buffer.append(" hitValue: ");
			buffer.append(hitValue);
			buffer.append(" hitTime: ");
			buffer.append(hitTime);
			buffer.append(" alarmStatus: ");
			buffer.append(alarmStatus);
			buffer.append(" REF_CLASS: ");
			buffer.append(REF_CLASS);
			buffer.append(" PROP_ID: ");
			buffer.append(PROP_ID);
			buffer.append(" PROP_DEC_ID: ");
			buffer.append(PROP_DEC_ID);
			buffer.append(" PROP_HIT_DT_ID: ");
			buffer.append(PROP_HIT_DT_ID);
			buffer.append(" PROP_HIT_IND_ID: ");
			buffer.append(PROP_HIT_IND_CODE);
			buffer.append(" PROP_HIT_VALUE: ");
			buffer.append(PROP_HIT_VALUE);
			buffer.append(" PROP_ALARM_STATUS: ");
			buffer.append(PROP_ALARM_STATUS);
			buffer.append(" REF_TABLE: ");
			buffer.append(REF_TABLE);
			buffer.append(" COL_DEC_ID: ");
			buffer.append(COL_DEC_ID);
			buffer.append(" COL_HIT_DT_ID: ");
			buffer.append(COL_HIT_DT_ID);
			buffer.append(" COL_HIT_IND_ID: ");
			buffer.append(COL_HIT_IND_CODE);
			buffer.append(" COL_HIT_VALUE: ");
			buffer.append(COL_HIT_VALUE);
			buffer.append(" COL_ALARM_STATUS: ");
			buffer.append(COL_ALARM_STATUS);
			buffer.append(" COL_ID: ");
			buffer.append(COL_ID);
			buffer.append("]");
			return buffer.toString();
		}
}
