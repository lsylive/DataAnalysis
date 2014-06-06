package com.liusy.datapp.model.blacklist;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_BLACKLIST_DECLARATION table.
 *
 * @hibernate.class
 *  table="T_BLACKLIST_DECLARATION"
 */

public class BlacklistDeclaration implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer appId;   //  

   private java.lang.String indCode;   //  

   private java.lang.String queryValue;   //  

   private java.util.Date decTime;   //  

   private java.lang.Integer userId;   //  

   private java.util.Date startDate;   //  

   private java.lang.String decCode;   //  

   private java.lang.String remark;   //  

   private java.util.Date endDate;   //  


   // Constructors
   public BlacklistDeclaration() {
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
    * Return the value associated with the column: APP_ID
    *  
    */
   public java.lang.Integer getAppId() { 
      return this.appId; 
   }
   /**
    * Set the value related to the column: APP_ID
    * @param appId is the 'APP_ID' value
    */
   public void setAppId(java.lang.Integer appId) { 
      this.appId = appId; 
   }



   /**
    * Return the value associated with the column: QUERY_VALUE
    *  
    */
   public java.lang.String getQueryValue() { 
      return this.queryValue; 
   }
   /**
    * Set the value related to the column: QUERY_VALUE
    * @param queryValue is the 'QUERY_VALUE' value
    */
   public void setQueryValue(java.lang.String queryValue) { 
      this.queryValue = queryValue; 
   }

   /**
    * Return the value associated with the column: DEC_TIME
    *  
    */
   public java.util.Date getDecTime() { 
      return this.decTime; 
   }
   /**
    * Set the value related to the column: DEC_TIME
    * @param decTime is the 'DEC_TIME' value
    */
   public void setDecTime(java.util.Date decTime) { 
      this.decTime = decTime; 
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
    * Return the value associated with the column: START_DATE
    *  
    */
   public java.util.Date getStartDate() { 
      return this.startDate; 
   }
   /**
    * Set the value related to the column: START_DATE
    * @param startDate is the 'START_DATE' value
    */
   public void setStartDate(java.util.Date startDate) { 
      this.startDate = startDate; 
   }

   /**
    * Return the value associated with the column: DEC_CODE
    *  
    */
   public java.lang.String getDecCode() { 
      return this.decCode; 
   }
   /**
    * Set the value related to the column: DEC_CODE
    * @param decCode is the 'DEC_CODE' value
    */
   public void setDecCode(java.lang.String decCode) { 
      this.decCode = decCode; 
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
    * Return the value associated with the column: END_DATE
    *  
    */
   public java.util.Date getEndDate() { 
      return this.endDate; 
   }
   /**
    * Set the value related to the column: END_DATE
    * @param endDate is the 'END_DATE' value
    */
   public void setEndDate(java.util.Date endDate) { 
      this.endDate = endDate; 
   }


   public static String REF_CLASS = "BlacklistDeclaration";
   public static String PROP_APP_ID = "appId";
   public static String PROP_IND_CODE = "indCode";
   public static String PROP_QUERY_VALUE = "queryValue";
   public static String PROP_DEC_TIME = "decTime";
   public static String PROP_USER_ID = "userId";
   public static String PROP_START_DATE = "startDate";
   public static String PROP_DEC_CODE = "decCode";
   public static String PROP_REMARK = "remark";
   public static String PROP_END_DATE = "endDate";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_BLACKLIST_DECLARATION";
   public static String COL_APP_ID = "APP_ID";
   public static String COL_IND_CODE = "IND_CODE";
   public static String COL_QUERY_VALUE = "QUERY_VALUE";
   public static String COL_DEC_TIME = "DEC_TIME";
   public static String COL_USER_ID = "USER_ID";
   public static String COL_START_DATE = "START_DATE";
   public static String COL_DEC_CODE = "DEC_CODE";
   public static String COL_REMARK = "REMARK";
   public static String COL_END_DATE = "END_DATE";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.blacklist.BlacklistDeclaration)) return false;
      else {
         com.liusy.datapp.model.blacklist.BlacklistDeclaration o = (com.liusy.datapp.model.blacklist.BlacklistDeclaration) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[BlacklistDeclaration:");
		buffer.append(" id:").append(id);
		buffer.append(" appId:").append(dealNull(appId));
		buffer.append(" indCode:").append(dealNull(indCode));
		buffer.append(" queryValue:").append(dealNull(queryValue));
		buffer.append(" decTime:").append(dealNull(decTime));
		buffer.append(" userId:").append(dealNull(userId));
		buffer.append(" startDate:").append(dealNull(startDate));
		buffer.append(" decCode:").append(dealNull(decCode));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" endDate:").append(dealNull(endDate));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"appId\":\"").append(dealNull(appId)).append("\"");
		buffer.append(",\"indCode\":\"").append(dealNull(indCode)).append("\"");
		buffer.append(",\"queryValue\":\"").append(dealNull(queryValue)).append("\"");
		buffer.append(",\"decTime\":\"").append(dealNull(decTime)).append("\"");
		buffer.append(",\"userId\":\"").append(dealNull(userId)).append("\"");
		buffer.append(",\"startDate\":\"").append(dealNull(startDate)).append("\"");
		buffer.append(",\"decCode\":\"").append(dealNull(decCode)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"endDate\":\"").append(dealNull(endDate)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}

	public java.lang.String getIndCode() {
		return indCode;
	}

	public void setIndCode(java.lang.String indCode) {
		this.indCode = indCode;
	}
}
