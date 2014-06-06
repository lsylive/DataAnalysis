package com.liusy.datapp.model.resource;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_RESOURCE_TABLE table.
 *
 * @hibernate.class
 *  table="T_RESOURCE_TABLE"
 */

public class ResourceTable implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.String type;   //  

   private java.lang.Integer categoryId;   //  

   private java.lang.String cityCode;   //  

   private java.lang.String createBy;   //  

   private java.lang.String securityLevel;   //  

   private java.lang.String modifyBy;   //  

   private java.lang.String cnName;   //  

   private java.util.Date createDate;   //  

   private java.lang.String code;   //  

   private java.lang.String name;   //  

   private java.lang.String remark;   //  
   
   private java.lang.String isSpaceTable;
   
   private java.lang.Integer spaceId;
   
   private java.lang.Integer refTableId;
   
   private java.lang.Integer orde;

   private java.lang.String recordeCount;
   // Constructors
   public ResourceTable() {
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
    * Return the value associated with the column: TYPE
    *  
    */
   public java.lang.String getType() { 
      return this.type; 
   }
   /**
    * Set the value related to the column: TYPE
    * @param type is the 'TYPE' value
    */
   public void setType(java.lang.String type) { 
      this.type = type; 
   }

   /**
    * Return the value associated with the column: CATEGORY_ID
    *  
    */
   public java.lang.Integer getCategoryId() { 
      return this.categoryId; 
   }
   /**
    * Set the value related to the column: CATEGORY_ID
    * @param categoryId is the 'CATEGORY_ID' value
    */
   public void setCategoryId(java.lang.Integer categoryId) { 
      this.categoryId = categoryId; 
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
    * Return the value associated with the column: SECURITY_LEVEL
    *  
    */
   public java.lang.String getSecurityLevel() { 
      return this.securityLevel; 
   }
   /**
    * Set the value related to the column: SECURITY_LEVEL
    * @param securityLevel is the 'SECURITY_LEVEL' value
    */
   public void setSecurityLevel(java.lang.String securityLevel) { 
      this.securityLevel = securityLevel; 
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
    * Return the value associated with the column: CN_NAME
    *  
    */
   public java.lang.String getCnName() { 
      return this.cnName; 
   }
   /**
    * Set the value related to the column: CN_NAME
    * @param cnName is the 'CN_NAME' value
    */
   public void setCnName(java.lang.String cnName) { 
      this.cnName = cnName; 
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
   public java.lang.String getIsSpaceTable() {
		return isSpaceTable;
	}

	public void setIsSpaceTable(java.lang.String isSpaceTable) {
		this.isSpaceTable = isSpaceTable;
	}

	public java.lang.Integer getRefTableId() {
		return refTableId;
	}

	public void setRefTableId(java.lang.Integer refTableId) {
		this.refTableId = refTableId;
	}
	
	public java.lang.Integer getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(java.lang.Integer spaceId) {
		this.spaceId = spaceId;
	}
	
	
	
   public java.lang.Integer getOrde() {
		return orde;
	}

	public void setOrde(java.lang.Integer orde) {
		this.orde = orde;
	}

	public java.lang.String getRecordeCount() {
		return recordeCount;
	}

	public void setRecordeCount(java.lang.String recordeCount) {
		this.recordeCount = recordeCount;
	}



	public static String REF_CLASS = "ResourceTable";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_TYPE = "type";
   public static String PROP_CATEGORY_ID = "categoryId";
   public static String PROP_CITY_CODE = "cityCode";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_SECURITY_LEVEL = "securityLevel";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_CN_NAME = "cnName";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_CODE = "code";
   public static String PROP_NAME = "name";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";
   public static String PROP_ISSPACETABLE="isSpaceTable";
   public static String PROP_REFTABLEID="refTableId";
   public static String PROP_SPACEID="spaceId";
   public static String PROP_ORDE="orde";
   public static String PROP_RECORD_COUNT="recordeCount";

   public static String REF_TABLE = "T_RESOURCE_TABLE";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_TYPE = "TYPE";
   public static String COL_CATEGORY_ID = "CATEGORY_ID";
   public static String COL_CITY_CODE = "CITY_CODE";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_SECURITY_LEVEL = "SECURITY_LEVEL";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_CN_NAME = "CN_NAME";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_CODE = "CODE";
   public static String COL_NAME = "NAME";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   public static String COL_ISSPACETABLE="IS_SPACETABLE";
   public static String COL_REFTABLEID="REF_TABLEID";
   public static String COL_SPACEID="SPACE_ID";
   public static String COL_ORDE="ORDE";
   public static String COL_RECORD_COUNT="RECORDE_COUNT";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.resource.ResourceTable)) return false;
      else {
         com.liusy.datapp.model.resource.ResourceTable o = (com.liusy.datapp.model.resource.ResourceTable) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ResourceTable:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" type:").append(dealNull(type));
		buffer.append(" categoryId:").append(dealNull(categoryId));
		buffer.append(" cityCode:").append(dealNull(cityCode));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" securityLevel:").append(dealNull(securityLevel));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" cnName:").append(dealNull(cnName));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" code:").append(dealNull(code));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"type\":\"").append(dealNull(type)).append("\"");
		buffer.append(",\"categoryId\":\"").append(dealNull(categoryId)).append("\"");
		buffer.append(",\"cityCode\":\"").append(dealNull(cityCode)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"securityLevel\":\"").append(dealNull(securityLevel)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"cnName\":\"").append(dealNull(cnName)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"code\":\"").append(dealNull(code)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}

	

	

}
