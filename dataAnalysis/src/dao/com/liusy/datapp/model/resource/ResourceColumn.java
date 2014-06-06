package com.liusy.datapp.model.resource;

import java.io.Serializable;

import com.liusy.dataapplatform.base.model.BaseVO;

/**
 * This is an object that contains data related to the T_RESOURCE_COLUMN table.
 *
 * @hibernate.class
 *  table="T_RESOURCE_COLUMN"
 */

public class ResourceColumn extends BaseVO {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.String isForeign;   //  

   private java.lang.String createBy;   //  

   private java.lang.String securityLevel;   //  

   private java.lang.String dataType;   //  

   private java.lang.String isNull;   //  

   private java.lang.String maxValue;   //  

   private java.lang.String modifyBy;   //  

   private java.lang.Integer tableId;   //  

   private java.util.Date createDate;   //  

   private java.lang.String cnName;   //  

   private java.lang.Integer dataPercise;   //  

   private java.lang.Integer squenceNo;   //  

   private java.lang.Integer indicatorId;   //  

   private java.lang.Integer dataLength;   //  

   private java.lang.String isPrimary;   //  

   private java.lang.String minValue;   //  

   private java.lang.String code;   //  

   private java.lang.String name;   //  

   private java.lang.Integer datametaId;   //  

   private java.lang.String remark;   //  

   private java.lang.String defaultValue;   //  


   private java.lang.Integer codesetId;   //  

   // Constructors
   public ResourceColumn() {
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
    * Return the value associated with the column: IS_FOREIGN
    *  
    */
   public java.lang.String getIsForeign() { 
      return this.isForeign; 
   }
   /**
    * Set the value related to the column: IS_FOREIGN
    * @param isForeign is the 'IS_FOREIGN' value
    */
   public void setIsForeign(java.lang.String isForeign) { 
      this.isForeign = isForeign; 
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
    * Return the value associated with the column: DATA_TYPE
    *  
    */
   public java.lang.String getDataType() { 
      return this.dataType; 
   }
   /**
    * Set the value related to the column: DATA_TYPE
    * @param dataType is the 'DATA_TYPE' value
    */
   public void setDataType(java.lang.String dataType) { 
      this.dataType = dataType; 
   }

   /**
    * Return the value associated with the column: IS_NULL
    *  
    */
   public java.lang.String getIsNull() { 
      return this.isNull; 
   }
   /**
    * Set the value related to the column: IS_NULL
    * @param isNull is the 'IS_NULL' value
    */
   public void setIsNull(java.lang.String isNull) { 
      this.isNull = isNull; 
   }

   /**
    * Return the value associated with the column: MAX_VALUE
    *  
    */
   public java.lang.String getMaxValue() { 
      return this.maxValue; 
   }
   /**
    * Set the value related to the column: MAX_VALUE
    * @param maxValue is the 'MAX_VALUE' value
    */
   public void setMaxValue(java.lang.String maxValue) { 
      this.maxValue = maxValue; 
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
    * Return the value associated with the column: TABLE_ID
    *  
    */
   public java.lang.Integer getTableId() { 
      return this.tableId; 
   }
   /**
    * Set the value related to the column: TABLE_ID
    * @param tableId is the 'TABLE_ID' value
    */
   public void setTableId(java.lang.Integer tableId) { 
      this.tableId = tableId; 
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
    * Return the value associated with the column: DATA_PERCISE
    *  
    */
   public java.lang.Integer getDataPercise() { 
      return this.dataPercise; 
   }
   /**
    * Set the value related to the column: DATA_PERCISE
    * @param dataPercise is the 'DATA_PERCISE' value
    */
   public void setDataPercise(java.lang.Integer dataPercise) { 
      this.dataPercise = dataPercise; 
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
    * Return the value associated with the column: INDICATOR_ID
    *  
    */
   public java.lang.Integer getIndicatorId() { 
      return this.indicatorId; 
   }
   /**
    * Set the value related to the column: INDICATOR_ID
    * @param indicatorId is the 'INDICATOR_ID' value
    */
   public void setIndicatorId(java.lang.Integer indicatorId) { 
      this.indicatorId = indicatorId; 
   }

   /**
    * Return the value associated with the column: DATA_LENGTH
    *  
    */
   public java.lang.Integer getDataLength() { 
      return this.dataLength; 
   }
   /**
    * Set the value related to the column: DATA_LENGTH
    * @param dataLength is the 'DATA_LENGTH' value
    */
   public void setDataLength(java.lang.Integer dataLength) { 
      this.dataLength = dataLength; 
   }

   /**
    * Return the value associated with the column: IS_PRIMARY
    *  
    */
   public java.lang.String getIsPrimary() { 
      return this.isPrimary; 
   }
   /**
    * Set the value related to the column: IS_PRIMARY
    * @param isPrimary is the 'IS_PRIMARY' value
    */
   public void setIsPrimary(java.lang.String isPrimary) { 
      this.isPrimary = isPrimary; 
   }

   /**
    * Return the value associated with the column: MIN_VALUE
    *  
    */
   public java.lang.String getMinValue() { 
      return this.minValue; 
   }
   /**
    * Set the value related to the column: MIN_VALUE
    * @param minValue is the 'MIN_VALUE' value
    */
   public void setMinValue(java.lang.String minValue) { 
      this.minValue = minValue; 
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
    * Return the value associated with the column: DATAMETA_ID
    *  
    */
   public java.lang.Integer getDatametaId() { 
      return this.datametaId; 
   }
   /**
    * Set the value related to the column: DATAMETA_ID
    * @param datametaId is the 'DATAMETA_ID' value
    */
   public void setDatametaId(java.lang.Integer datametaId) { 
      this.datametaId = datametaId; 
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
    * Return the value associated with the column: DEFAULT_VALUE
    *  
    */
   public java.lang.String getDefaultValue() { 
      return this.defaultValue; 
   }
   /**
    * Set the value related to the column: DEFAULT_VALUE
    * @param defaultValue is the 'DEFAULT_VALUE' value
    */
   public void setDefaultValue(java.lang.String defaultValue) { 
      this.defaultValue = defaultValue; 
   }

   /**
    * Return the value associated with the column: CODESET_ID
    *  
    */
   public java.lang.Integer getCodesetId() { 
      return this.codesetId; 
   }
   /**
    * Set the value related to the column: CODESET_ID
    * @param codesetId is the 'CODESET_ID' value
    */
   public void setCodesetId(java.lang.Integer codesetId) { 
      this.codesetId = codesetId; 
   }


   public static String REF_CLASS = "ResourceColumn";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_IS_FOREIGN = "isForeign";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_SECURITY_LEVEL = "securityLevel";
   public static String PROP_DATA_TYPE = "dataType";
   public static String PROP_IS_NULL = "isNull";
   public static String PROP_MAX_VALUE = "maxValue";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_TABLE_ID = "tableId";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_CN_NAME = "cnName";
   public static String PROP_DATA_PERCISE = "dataPercise";
   public static String PROP_SQUENCE_NO = "squenceNo";
   public static String PROP_INDICATOR_ID = "indicatorId";
   public static String PROP_DATA_LENGTH = "dataLength";
   public static String PROP_IS_PRIMARY = "isPrimary";
   public static String PROP_MIN_VALUE = "minValue";
   public static String PROP_CODE = "code";
   public static String PROP_NAME = "name";
   public static String PROP_DATAMETA_ID = "datametaId";
   public static String PROP_REMARK = "remark";
   public static String PROP_DEFAULT_VALUE = "defaultValue";
   public static String PROP_ID = "id";
   public static String PROP_CODESET_ID = "codesetId";

   public static String REF_TABLE = "T_RESOURCE_COLUMN";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_IS_FOREIGN = "IS_FOREIGN";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_SECURITY_LEVEL = "SECURITY_LEVEL";
   public static String COL_DATA_TYPE = "DATA_TYPE";
   public static String COL_IS_NULL = "IS_NULL";
   public static String COL_MAX_VALUE = "MAX_VALUE";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_TABLE_ID = "TABLE_ID";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_CN_NAME = "CN_NAME";
   public static String COL_DATA_PERCISE = "DATA_PERCISE";
   public static String COL_SQUENCE_NO = "SQUENCE_NO";
   public static String COL_INDICATOR_ID = "INDICATOR_ID";
   public static String COL_DATA_LENGTH = "DATA_LENGTH";
   public static String COL_IS_PRIMARY = "IS_PRIMARY";
   public static String COL_MIN_VALUE = "MIN_VALUE";
   public static String COL_CODE = "CODE";
   public static String COL_NAME = "NAME";
   public static String COL_DATAMETA_ID = "DATAMETA_ID";
   public static String COL_REMARK = "REMARK";
   public static String COL_DEFAULT_VALUE = "DEFAULT_VALUE";
   public static String COL_ID = "ID";
   public static String COL_CODESET_ID = "CODESET_ID";
   //主键标示枚举
   public static String IS_PRIMARYKEY="1";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.resource.ResourceColumn)) return false;
      else {
         com.liusy.datapp.model.resource.ResourceColumn o = (com.liusy.datapp.model.resource.ResourceColumn) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[ResourceColumn:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" isForeign:").append(dealNull(isForeign));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" securityLevel:").append(dealNull(securityLevel));
		buffer.append(" dataType:").append(dealNull(dataType));
		buffer.append(" isNull:").append(dealNull(isNull));
		buffer.append(" maxValue:").append(dealNull(maxValue));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" tableId:").append(dealNull(tableId));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" cnName:").append(dealNull(cnName));
		buffer.append(" dataPercise:").append(dealNull(dataPercise));
		buffer.append(" squenceNo:").append(dealNull(squenceNo));
		buffer.append(" indicatorId:").append(dealNull(indicatorId));
		buffer.append(" dataLength:").append(dealNull(dataLength));
		buffer.append(" isPrimary:").append(dealNull(isPrimary));
		buffer.append(" minValue:").append(dealNull(minValue));
		buffer.append(" code:").append(dealNull(code));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" datametaId:").append(dealNull(datametaId));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" defaultValue:").append(dealNull(defaultValue));
		buffer.append(" codesetId:").append(dealNull(codesetId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"isForeign\":\"").append(dealNull(isForeign)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"securityLevel\":\"").append(dealNull(securityLevel)).append("\"");
		buffer.append(",\"dataType\":\"").append(dealNull(dataType)).append("\"");
		buffer.append(",\"isNull\":\"").append(dealNull(isNull)).append("\"");
		buffer.append(",\"maxValue\":\"").append(dealNull(maxValue)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"tableId\":\"").append(dealNull(tableId)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"cnName\":\"").append(dealNull(cnName)).append("\"");
		buffer.append(",\"dataPercise\":\"").append(dealNull(dataPercise)).append("\"");
		buffer.append(",\"squenceNo\":\"").append(dealNull(squenceNo)).append("\"");
		buffer.append(",\"indicatorId\":\"").append(dealNull(indicatorId)).append("\"");
		buffer.append(",\"dataLength\":\"").append(dealNull(dataLength)).append("\"");
		buffer.append(",\"isPrimary\":\"").append(dealNull(isPrimary)).append("\"");
		buffer.append(",\"minValue\":\"").append(dealNull(minValue)).append("\"");
		buffer.append(",\"code\":\"").append(dealNull(code)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"datametaId\":\"").append(dealNull(datametaId)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"defaultValue\":\"").append(dealNull(defaultValue)).append("\"");
		buffer.append(",\"codesetId\":\"").append(dealNull(codesetId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
