package com.liusy.datapp.model.system.config;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_CODE table.
 *
 * @hibernate.class
 *  table="T_SYS_CODE"
 */

public class SysCode implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer treeLevel;   //  

   private java.lang.String modifiedby;   //  

   private java.lang.String value;   //  

   private java.lang.String parentValue;   //  

   private java.lang.String status;   //  

   private java.util.Date modifyDate;   //  

   private java.lang.Integer orderNo;   //  

   private java.util.Date createDate;   //  

   private java.lang.String createdBy;   //  

   private java.lang.String parentId;   //  

   private java.lang.String name;   //  

   private java.lang.String treeCode;   //  

   private java.lang.Integer codeSetId;   //  

   private java.lang.String remark;   //  


   // Constructors
   public SysCode() {
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
    * Return the value associated with the column: TREE_LEVEL
    *  
    */
   public java.lang.Integer getTreeLevel() { 
      return this.treeLevel; 
   }
   /**
    * Set the value related to the column: TREE_LEVEL
    * @param treeLevel is the 'TREE_LEVEL' value
    */
   public void setTreeLevel(java.lang.Integer treeLevel) { 
      this.treeLevel = treeLevel; 
   }

   /**
    * Return the value associated with the column: MODIFYBY
    *  
    */
   public java.lang.String getModifiedby() { 
      return this.modifiedby; 
   }
   /**
    * Set the value related to the column: MODIFYBY
    * @param modifiedby is the 'MODIFYBY' value
    */
   public void setModifiedby(java.lang.String modifiedby) { 
      this.modifiedby = modifiedby; 
   }

   /**
    * Return the value associated with the column: ITEM_VALUE
    *  
    */
   public java.lang.String getValue() { 
      return this.value; 
   }
   /**
    * Set the value related to the column: ITEM_VALUE
    * @param value is the 'ITEM_VALUE' value
    */
   public void setValue(java.lang.String value) { 
      this.value = value; 
   }

   /**
    * Return the value associated with the column: PARENT_VALUE
    *  
    */
   public java.lang.String getParentValue() { 
      return this.parentValue; 
   }
   /**
    * Set the value related to the column: PARENT_VALUE
    * @param parentValue is the 'PARENT_VALUE' value
    */
   public void setParentValue(java.lang.String parentValue) { 
      this.parentValue = parentValue; 
   }

   /**
    * Return the value associated with the column: CODE_STATUS
    *  
    */
   public java.lang.String getStatus() { 
      return this.status; 
   }
   /**
    * Set the value related to the column: CODE_STATUS
    * @param status is the 'CODE_STATUS' value
    */
   public void setStatus(java.lang.String status) { 
      this.status = status; 
   }

   /**
    * Return the value associated with the column: MODIFYDATE
    *  
    */
   public java.util.Date getModifyDate() { 
      return this.modifyDate; 
   }
   /**
    * Set the value related to the column: MODIFYDATE
    * @param modifyDate is the 'MODIFYDATE' value
    */
   public void setModifyDate(java.util.Date modifyDate) { 
      this.modifyDate = modifyDate; 
   }

   /**
    * Return the value associated with the column: ORDER_NO
    *  
    */
   public java.lang.Integer getOrderNo() { 
      return this.orderNo; 
   }
   /**
    * Set the value related to the column: ORDER_NO
    * @param orderNo is the 'ORDER_NO' value
    */
   public void setOrderNo(java.lang.Integer orderNo) { 
      this.orderNo = orderNo; 
   }

   /**
    * Return the value associated with the column: CREATEDATE
    *  
    */
   public java.util.Date getCreateDate() { 
      return this.createDate; 
   }
   /**
    * Set the value related to the column: CREATEDATE
    * @param createDate is the 'CREATEDATE' value
    */
   public void setCreateDate(java.util.Date createDate) { 
      this.createDate = createDate; 
   }

   /**
    * Return the value associated with the column: CREATEBY
    *  
    */
   public java.lang.String getCreatedBy() { 
      return this.createdBy; 
   }
   /**
    * Set the value related to the column: CREATEBY
    * @param createdBy is the 'CREATEBY' value
    */
   public void setCreatedBy(java.lang.String createdBy) { 
      this.createdBy = createdBy; 
   }

   /**
    * Return the value associated with the column: PARENT_ITEM_ID
    *  
    */
   public java.lang.String getParentId() { 
      return this.parentId; 
   }
   /**
    * Set the value related to the column: PARENT_ITEM_ID
    * @param parentId is the 'PARENT_ITEM_ID' value
    */
   public void setParentId(java.lang.String parentId) { 
      this.parentId = parentId; 
   }

   /**
    * Return the value associated with the column: ITEM_NAME
    *  
    */
   public java.lang.String getName() { 
      return this.name; 
   }
   /**
    * Set the value related to the column: ITEM_NAME
    * @param name is the 'ITEM_NAME' value
    */
   public void setName(java.lang.String name) { 
      this.name = name; 
   }

   /**
    * Return the value associated with the column: TREE_CODE
    *  
    */
   public java.lang.String getTreeCode() { 
      return this.treeCode; 
   }
   /**
    * Set the value related to the column: TREE_CODE
    * @param treeCode is the 'TREE_CODE' value
    */
   public void setTreeCode(java.lang.String treeCode) { 
      this.treeCode = treeCode; 
   }

   /**
    * Return the value associated with the column: CS_ID
    *  
    */
   public java.lang.Integer getCodeSetId() { 
      return this.codeSetId; 
   }
   /**
    * Set the value related to the column: CS_ID
    * @param codeSetId is the 'CS_ID' value
    */
   public void setCodeSetId(java.lang.Integer codeSetId) { 
      this.codeSetId = codeSetId; 
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


   public static String REF_CLASS = "SysCode";
   public static String PROP_TREE_LEVEL = "treeLevel";
   public static String PROP_MODIFYBY = "modifiedby";
   public static String PROP_ITEM_VALUE = "value";
   public static String PROP_PARENT_VALUE = "parentValue";
   public static String PROP_CODE_STATUS = "status";
   public static String PROP_MODIFYDATE = "modifyDate";
   public static String PROP_ORDER_NO = "orderNo";
   public static String PROP_CREATEDATE = "createDate";
   public static String PROP_CREATEBY = "createdBy";
   public static String PROP_PARENT_ITEM_ID = "parentId";
   public static String PROP_ITEM_NAME = "name";
   public static String PROP_TREE_CODE = "treeCode";
   public static String PROP_CS_ID = "codeSetId";
   public static String PROP_REMARK = "remark";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_SYS_CODE";
   public static String COL_TREE_LEVEL = "TREE_LEVEL";
   public static String COL_MODIFYBY = "MODIFYBY";
   public static String COL_ITEM_VALUE = "ITEM_VALUE";
   public static String COL_PARENT_VALUE = "PARENT_VALUE";
   public static String COL_CODE_STATUS = "CODE_STATUS";
   public static String COL_MODIFYDATE = "MODIFYDATE";
   public static String COL_ORDER_NO = "ORDER_NO";
   public static String COL_CREATEDATE = "CREATEDATE";
   public static String COL_CREATEBY = "CREATEBY";
   public static String COL_PARENT_ITEM_ID = "PARENT_ITEM_ID";
   public static String COL_ITEM_NAME = "ITEM_NAME";
   public static String COL_TREE_CODE = "TREE_CODE";
   public static String COL_CS_ID = "CS_ID";
   public static String COL_REMARK = "REMARK";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.config.SysCode)) return false;
      else {
         com.liusy.datapp.model.system.config.SysCode o = (com.liusy.datapp.model.system.config.SysCode) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysCode:");
		buffer.append(" id:").append(id);
		buffer.append(" treeLevel:").append(dealNull(treeLevel));
		buffer.append(" modifiedby:").append(dealNull(modifiedby));
		buffer.append(" value:").append(dealNull(value));
		buffer.append(" parentValue:").append(dealNull(parentValue));
		buffer.append(" status:").append(dealNull(status));
		buffer.append(" modifyDate:").append(dealNull(modifyDate));
		buffer.append(" orderNo:").append(dealNull(orderNo));
		buffer.append(" createDate:").append(dealNull(createDate));
		buffer.append(" createdBy:").append(dealNull(createdBy));
		buffer.append(" parentId:").append(dealNull(parentId));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" treeCode:").append(dealNull(treeCode));
		buffer.append(" codeSetId:").append(dealNull(codeSetId));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"treeLevel\":\"").append(dealNull(treeLevel)).append("\"");
		buffer.append(",\"modifiedby\":\"").append(dealNull(modifiedby)).append("\"");
		buffer.append(",\"value\":\"").append(dealNull(value)).append("\"");
		buffer.append(",\"parentValue\":\"").append(dealNull(parentValue)).append("\"");
		buffer.append(",\"status\":\"").append(dealNull(status)).append("\"");
		buffer.append(",\"modifyDate\":\"").append(dealNull(modifyDate)).append("\"");
		buffer.append(",\"orderNo\":\"").append(dealNull(orderNo)).append("\"");
		buffer.append(",\"createDate\":\"").append(dealNull(createDate)).append("\"");
		buffer.append(",\"createdBy\":\"").append(dealNull(createdBy)).append("\"");
		buffer.append(",\"parentId\":\"").append(dealNull(parentId)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"treeCode\":\"").append(dealNull(treeCode)).append("\"");
		buffer.append(",\"codeSetId\":\"").append(dealNull(codeSetId)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
