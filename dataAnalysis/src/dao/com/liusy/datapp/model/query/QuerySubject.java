package com.liusy.datapp.model.query;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_QUERY_SUBJECT table.
 *
 * @hibernate.class
 *  table="T_QUERY_SUBJECT"
 */

public class QuerySubject implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String subDesc;   //  

   private java.lang.String modifyby;   //  

   private java.lang.Integer cityId;   //  

   private java.lang.String subType;   //  

   private java.lang.Integer scId;   //  

   private java.util.Date createdate;   //  

   private java.lang.String createby;   //  

   private java.lang.String subName;   //  

   private java.util.Date modifydate;   //  

   private java.lang.String subCode;   //  

   private java.lang.String remark;   //  
   
   private java.lang.Integer orde;
   
   public static String SUBTYPE_COMM="1";
   public static String SUBTYPE_SEPERATE="2";


   // Constructors
   public QuerySubject() {
   }

   
   
   public java.lang.Integer getOrde() {
		return orde;
	}



	public void setOrde(java.lang.Integer orde) {
		this.orde = orde;
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
    * Return the value associated with the column: SUB_DESC
    *  
    */
   public java.lang.String getSubDesc() { 
      return this.subDesc; 
   }
   /**
    * Set the value related to the column: SUB_DESC
    * @param subDesc is the 'SUB_DESC' value
    */
   public void setSubDesc(java.lang.String subDesc) { 
      this.subDesc = subDesc; 
   }

   /**
    * Return the value associated with the column: MODIFYBY
    *  
    */
   public java.lang.String getModifyby() { 
      return this.modifyby; 
   }
   /**
    * Set the value related to the column: MODIFYBY
    * @param modifyby is the 'MODIFYBY' value
    */
   public void setModifyby(java.lang.String modifyby) { 
      this.modifyby = modifyby; 
   }

   /**
    * Return the value associated with the column: CITY_ID
    *  
    */
   public java.lang.Integer getCityId() { 
      return this.cityId; 
   }
   /**
    * Set the value related to the column: CITY_ID
    * @param cityId is the 'CITY_ID' value
    */
   public void setCityId(java.lang.Integer cityId) { 
      this.cityId = cityId; 
   }

   /**
    * Return the value associated with the column: SUB_TYPE
    *  
    */
   public java.lang.String getSubType() { 
      return this.subType; 
   }
   /**
    * Set the value related to the column: SUB_TYPE
    * @param subType is the 'SUB_TYPE' value
    */
   public void setSubType(java.lang.String subType) { 
      this.subType = subType; 
   }

   /**
    * Return the value associated with the column: SC_ID
    *  
    */
   public java.lang.Integer getScId() { 
      return this.scId; 
   }
   /**
    * Set the value related to the column: SC_ID
    * @param scId is the 'SC_ID' value
    */
   public void setScId(java.lang.Integer scId) { 
      this.scId = scId; 
   }

   /**
    * Return the value associated with the column: CREATEDATE
    *  
    */
   public java.util.Date getCreatedate() { 
      return this.createdate; 
   }
   /**
    * Set the value related to the column: CREATEDATE
    * @param createdate is the 'CREATEDATE' value
    */
   public void setCreatedate(java.util.Date createdate) { 
      this.createdate = createdate; 
   }

   /**
    * Return the value associated with the column: CREATEBY
    *  
    */
   public java.lang.String getCreateby() { 
      return this.createby; 
   }
   /**
    * Set the value related to the column: CREATEBY
    * @param createby is the 'CREATEBY' value
    */
   public void setCreateby(java.lang.String createby) { 
      this.createby = createby; 
   }

   /**
    * Return the value associated with the column: SUB_NAME
    *  
    */
   public java.lang.String getSubName() { 
      return this.subName; 
   }
   /**
    * Set the value related to the column: SUB_NAME
    * @param subName is the 'SUB_NAME' value
    */
   public void setSubName(java.lang.String subName) { 
      this.subName = subName; 
   }

   /**
    * Return the value associated with the column: MODIFYDATE
    *  
    */
   public java.util.Date getModifydate() { 
      return this.modifydate; 
   }
   /**
    * Set the value related to the column: MODIFYDATE
    * @param modifydate is the 'MODIFYDATE' value
    */
   public void setModifydate(java.util.Date modifydate) { 
      this.modifydate = modifydate; 
   }

   /**
    * Return the value associated with the column: SUB_CODE
    *  
    */
   public java.lang.String getSubCode() { 
      return this.subCode; 
   }
   /**
    * Set the value related to the column: SUB_CODE
    * @param subCode is the 'SUB_CODE' value
    */
   public void setSubCode(java.lang.String subCode) { 
      this.subCode = subCode; 
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


   public static String REF_CLASS = "QuerySubject";
   public static String PROP_SUB_DESC = "subDesc";
   public static String PROP_MODIFYBY = "modifyby";
   public static String PROP_CITY_ID = "cityId";
   public static String PROP_SUB_TYPE = "subType";
   public static String PROP_SC_ID = "scId";
   public static String PROP_CREATEDATE = "createdate";
   public static String PROP_CREATEBY = "createby";
   public static String PROP_SUB_NAME = "subName";
   public static String PROP_MODIFYDATE = "modifydate";
   public static String PROP_SUB_CODE = "subCode";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";
   public static String PROP_ORDE = "orde";

   public static String REF_TABLE = "T_QUERY_SUBJECT";
   public static String COL_SUB_DESC = "SUB_DESC";
   public static String COL_MODIFYBY = "MODIFYBY";
   public static String COL_CITY_ID = "CITY_ID";
   public static String COL_SUB_TYPE = "SUB_TYPE";
   public static String COL_SC_ID = "SC_ID";
   public static String COL_CREATEDATE = "CREATEDATE";
   public static String COL_CREATEBY = "CREATEBY";
   public static String COL_SUB_NAME = "SUB_NAME";
   public static String COL_MODIFYDATE = "MODIFYDATE";
   public static String COL_SUB_CODE = "SUB_CODE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   public static String COL_ORDE = "ORDE";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.query.QuerySubject)) return false;
      else {
         com.liusy.datapp.model.query.QuerySubject o = (com.liusy.datapp.model.query.QuerySubject) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[QuerySubject:");
		buffer.append(" id:").append(id);
		buffer.append(" subDesc:").append(dealNull(subDesc));
		buffer.append(" modifyby:").append(dealNull(modifyby));
		buffer.append(" cityId:").append(dealNull(cityId));
		buffer.append(" subType:").append(dealNull(subType));
		buffer.append(" scId:").append(dealNull(scId));
		buffer.append(" createdate:").append(dealNull(createdate));
		buffer.append(" createby:").append(dealNull(createby));
		buffer.append(" subName:").append(dealNull(subName));
		buffer.append(" modifydate:").append(dealNull(modifydate));
		buffer.append(" subCode:").append(dealNull(subCode));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"subDesc\":\"").append(dealNull(subDesc)).append("\"");
		buffer.append(",\"modifyby\":\"").append(dealNull(modifyby)).append("\"");
		buffer.append(",\"cityId\":\"").append(dealNull(cityId)).append("\"");
		buffer.append(",\"subType\":\"").append(dealNull(subType)).append("\"");
		buffer.append(",\"scId\":\"").append(dealNull(scId)).append("\"");
		buffer.append(",\"createdate\":\"").append(dealNull(createdate)).append("\"");
		buffer.append(",\"createby\":\"").append(dealNull(createby)).append("\"");
		buffer.append(",\"subName\":\"").append(dealNull(subName)).append("\"");
		buffer.append(",\"modifydate\":\"").append(dealNull(modifydate)).append("\"");
		buffer.append(",\"subCode\":\"").append(dealNull(subCode)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
