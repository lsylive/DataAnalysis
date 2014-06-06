package com.liusy.datapp.model.datastandard;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_STANDARD_DATAMETA table.
 *
 * @hibernate.class
 *  table="T_STANDARD_DATAMETA"
 */

public class StandardDataMeta implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.util.Date modifyDate;   //  

   private java.lang.Integer categoryId;   //  

   private java.lang.String expression;   //  

   private java.lang.String abbreviations;   //  

   private java.lang.String createBy;   //  

   private java.lang.String dataType;   //  

   private java.lang.String dataRange;   //  

   private java.lang.String modifyBy;   //  

   private java.util.Date createDate;   //  

   private java.lang.String cnName;   //  

   private java.lang.String measurementUnit;   //  

   private java.lang.Integer indicatorId;   //  

   private java.lang.String dataFormat;   //  

   private java.lang.String code;   //  

   private java.lang.String codesetNo;   //  

   private java.lang.String name;   //  

   private java.lang.String synonyms;   //  

   private java.lang.String remark;   //  


   // Constructors
   public StandardDataMeta() {
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
    * Return the value associated with the column: EXPRESSION
    *  
    */
   public java.lang.String getExpression() { 
      return this.expression; 
   }
   /**
    * Set the value related to the column: EXPRESSION
    * @param expression is the 'EXPRESSION' value
    */
   public void setExpression(java.lang.String expression) { 
      this.expression = expression; 
   }

   /**
    * Return the value associated with the column: ABBREVIATIONS
    *  
    */
   public java.lang.String getAbbreviations() { 
      return this.abbreviations; 
   }
   /**
    * Set the value related to the column: ABBREVIATIONS
    * @param abbreviations is the 'ABBREVIATIONS' value
    */
   public void setAbbreviations(java.lang.String abbreviations) { 
      this.abbreviations = abbreviations; 
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
    * Return the value associated with the column: DATA_RANGE
    *  
    */
   public java.lang.String getDataRange() { 
      return this.dataRange; 
   }
   /**
    * Set the value related to the column: DATA_RANGE
    * @param dataRange is the 'DATA_RANGE' value
    */
   public void setDataRange(java.lang.String dataRange) { 
      this.dataRange = dataRange; 
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
    * Return the value associated with the column: MEASUREMENT_UNIT
    *  
    */
   public java.lang.String getMeasurementUnit() { 
      return this.measurementUnit; 
   }
   /**
    * Set the value related to the column: MEASUREMENT_UNIT
    * @param measurementUnit is the 'MEASUREMENT_UNIT' value
    */
   public void setMeasurementUnit(java.lang.String measurementUnit) { 
      this.measurementUnit = measurementUnit; 
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
    * Return the value associated with the column: DATA_FORMAT
    *  
    */
   public java.lang.String getDataFormat() { 
      return this.dataFormat; 
   }
   /**
    * Set the value related to the column: DATA_FORMAT
    * @param dataFormat is the 'DATA_FORMAT' value
    */
   public void setDataFormat(java.lang.String dataFormat) { 
      this.dataFormat = dataFormat; 
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
    * Return the value associated with the column: CODESET_NO
    *  
    */
   public java.lang.String getCodesetNo() { 
      return this.codesetNo; 
   }
   /**
    * Set the value related to the column: CODESET_NO
    * @param codesetNo is the 'CODESET_NO' value
    */
   public void setCodesetNo(java.lang.String codesetNo) { 
      this.codesetNo = codesetNo; 
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
    * Return the value associated with the column: SYNONYMS
    *  
    */
   public java.lang.String getSynonyms() { 
      return this.synonyms; 
   }
   /**
    * Set the value related to the column: SYNONYMS
    * @param synonyms is the 'SYNONYMS' value
    */
   public void setSynonyms(java.lang.String synonyms) { 
      this.synonyms = synonyms; 
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


   public static String REF_CLASS = "StandardDataMeta";
   public static String PROP_MODIFY_DATE = "modifyDate";
   public static String PROP_CATEGORY_ID = "categoryId";
   public static String PROP_EXPRESSION = "expression";
   public static String PROP_ABBREVIATIONS = "abbreviations";
   public static String PROP_CREATE_BY = "createBy";
   public static String PROP_DATA_TYPE = "dataType";
   public static String PROP_DATA_RANGE = "dataRange";
   public static String PROP_MODIFY_BY = "modifyBy";
   public static String PROP_CREATE_DATE = "createDate";
   public static String PROP_CN_NAME = "cnName";
   public static String PROP_MEASUREMENT_UNIT = "measurementUnit";
   public static String PROP_INDICATOR_ID = "indicatorId";
   public static String PROP_DATA_FORMAT = "dataFormat";
   public static String PROP_CODE = "code";
   public static String PROP_CODESET_NO = "codesetNo";
   public static String PROP_NAME = "name";
   public static String PROP_SYNONYMS = "synonyms";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_STANDARD_DATAMETA";
   public static String COL_MODIFY_DATE = "MODIFY_DATE";
   public static String COL_CATEGORY_ID = "CATEGORY_ID";
   public static String COL_EXPRESSION = "EXPRESSION";
   public static String COL_ABBREVIATIONS = "ABBREVIATIONS";
   public static String COL_CREATE_BY = "CREATE_BY";
   public static String COL_DATA_TYPE = "DATA_TYPE";
   public static String COL_DATA_RANGE = "DATA_RANGE";
   public static String COL_MODIFY_BY = "MODIFY_BY";
   public static String COL_CREATE_DATE = "CREATE_DATE";
   public static String COL_CN_NAME = "CN_NAME";
   public static String COL_MEASUREMENT_UNIT = "MEASUREMENT_UNIT";
   public static String COL_INDICATOR_ID = "INDICATOR_ID";
   public static String COL_DATA_FORMAT = "DATA_FORMAT";
   public static String COL_CODE = "CODE";
   public static String COL_CODESET_NO = "CODESET_NO";
   public static String COL_NAME = "NAME";
   public static String COL_SYNONYMS = "SYNONYMS";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.datastandard.StandardDataMeta)) return false;
      else {
         com.liusy.datapp.model.datastandard.StandardDataMeta o = (com.liusy.datapp.model.datastandard.StandardDataMeta) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[StandardDataMeta:");
		buffer.append(" id:").append(id);
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" categoryId:").append(dealNull(categoryId));
		buffer.append(" expression:").append(dealNull(expression));
		buffer.append(" abbreviations:").append(dealNull(abbreviations));
		buffer.append(" createBy:").append(dealNull(createBy));
		buffer.append(" dataType:").append(dealNull(dataType));
		buffer.append(" dataRange:").append(dealNull(dataRange));
		buffer.append(" modifyBy:").append(dealNull(modifyBy));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" cnName:").append(dealNull(cnName));
		buffer.append(" measurementUnit:").append(dealNull(measurementUnit));
		buffer.append(" indicatorId:").append(dealNull(indicatorId));
		buffer.append(" dataFormat:").append(dealNull(dataFormat));
		buffer.append(" code:").append(dealNull(code));
		buffer.append(" codesetNo:").append(dealNull(codesetNo));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" synonyms:").append(dealNull(synonyms));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"categoryId\":\"").append(dealNull(categoryId)).append("\"");
		buffer.append(",\"expression\":\"").append(dealNull(expression)).append("\"");
		buffer.append(",\"abbreviations\":\"").append(dealNull(abbreviations)).append("\"");
		buffer.append(",\"createBy\":\"").append(dealNull(createBy)).append("\"");
		buffer.append(",\"dataType\":\"").append(dealNull(dataType)).append("\"");
		buffer.append(",\"dataRange\":\"").append(dealNull(dataRange)).append("\"");
		buffer.append(",\"modifyBy\":\"").append(dealNull(modifyBy)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"cnName\":\"").append(dealNull(cnName)).append("\"");
		buffer.append(",\"measurementUnit\":\"").append(dealNull(measurementUnit)).append("\"");
		buffer.append(",\"indicatorId\":\"").append(dealNull(indicatorId)).append("\"");
		buffer.append(",\"dataFormat\":\"").append(dealNull(dataFormat)).append("\"");
		buffer.append(",\"code\":\"").append(dealNull(code)).append("\"");
		buffer.append(",\"codesetNo\":\"").append(dealNull(codesetNo)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"synonyms\":\"").append(dealNull(synonyms)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
