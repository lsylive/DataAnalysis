package com.liusy.datapp.model.datastandard;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_STANDARD_CATEGORY table.
 *
 * @hibernate.class
 *  table="T_STANDARD_CATEGORY"
 */

public class StandardCategory implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.Integer squenceNo;   //  

   private java.lang.String cityCode;   //  

   private java.lang.String createBy;   //  

   private java.lang.String code;   //  

   private java.lang.String name;   //  

   private java.lang.String modifyBy;   //  

   private java.util.Date createDate;   //  

   private java.lang.String remark;   //  


   private java.lang.Integer parentId;   //  

   // Constructors
   public StandardCategory() {
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
    * Return the value associated with the column: SQUENCE_NO
    *  
    */
   public java.lang.Integer getSquenceNo() { 
      return this.squenceNo; 
   }
   /**
    * Set the value related to the column: SQUENCE_NO
    * @param squenceNo is the 'SQUENCE_NO' value
    */
   public void setSquenceNo(java.lang.Integer squenceNo) { 
      this.squenceNo = squenceNo; 
   }

   /**
    * Return the value associated with the column: CITY_CODE
    *  
    */
   public java.lang.String getCityCode() { 
      return this.cityCode; 
   }
   /**
    * Set the value related to the column: CITY_CODE
    * @param cityCode is the 'CITY_CODE' value
    */
   public void setCityCode(java.lang.String cityCode) { 
      this.cityCode = cityCode; 
   }

   /**
    * Return the value associated with the column: CREATE_BY
    *  
    */
   public java.lang.String getCreateBy() { 
      return this.createBy; 
   }
   /**
    * Set the value related to the column: CREATE_BY
    * @param createBy is the 'CREATE_BY' value
    */
   public void setCreateBy(java.lang.String createBy) { 
      this.createBy = createBy; 
   }

   /**
    * Return the value associated with the column: CODE
    *  
    */
   public java.lang.String getCode() { 
      return this.code; 
   }
   /**
    * Set the value related to the column: CODE
    * @param code is the 'CODE' value
    */
   public void setCode(java.lang.String code) { 
      this.code = code; 
   }

   /**
    * Return the value associated with the column: NAME
    *  
    */
   public java.lang.String getName() { 
      return this.name; 
   }
   /**
    * Set the value related to the column: NAME
    * @param name is the 'NAME' value
    */
   public void setName(java.lang.String name) { 
      this.name = name; 
   }

   /**
    * Return the value associated with the column: MODIFY_BY
    *  
    */
   public java.lang.String getModifyBy() { 
      return this.modifyBy; 
   }
   /**
    * Set the value related to the column: MODIFY_BY
    * @param modifyBy is the 'MODIFY_BY' value
    */
   public void setModifyBy(java.lang.String modifyBy) { 
      this.modifyBy = modifyBy; 
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
    * Return the value associated with the column: PARENT_ID
    *  
    */
   public java.lang.Integer getParentId() { 
      return this.parentId; 
   }
   /**
    * Set the value related to the column: PARENT_ID
    * @param parentId is the 'PARENT_ID' value
    */
   public void setParentId(java.lang.Integer parentId) { 
      this.parentId = parentId; 
   }


   public static String REF_CLASS = "StandardCategory";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_SQUENCE_NO = "squenceNo";
   public static String PROP_CITY_CODE = "cityCode";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_CODE = "code";
   public static String PROP_NAME = "name";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";
   public static String PROP_PARENT_ID = "parentId";

   public static String REF_TABLE = "T_STANDARD_CATEGORY";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_SQUENCE_NO = "SQUENCE_NO";
   public static String COL_CITY_CODE = "CITY_CODE";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_CODE = "CODE";
   public static String COL_NAME = "NAME";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   public static String COL_PARENT_ID = "PARENT_ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.datastandard.StandardCategory)) return false;
      else {
         com.liusy.datapp.model.datastandard.StandardCategory o = (com.liusy.datapp.model.datastandard.StandardCategory) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[StandardCategory:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" squenceNo:").append(dealNull(squenceNo));
		buffer.append(" cityCode:").append(dealNull(cityCode));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" code:").append(dealNull(code));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" parentId:").append(dealNull(parentId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"squenceNo\":\"").append(dealNull(squenceNo)).append("\"");
		buffer.append(",\"cityCode\":\"").append(dealNull(cityCode)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"code\":\"").append(dealNull(code)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"parentId\":\"").append(dealNull(parentId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
