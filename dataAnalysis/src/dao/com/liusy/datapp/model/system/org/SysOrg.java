package com.liusy.datapp.model.system.org;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_ORG_INFO table.
 *
 * @hibernate.class
 *  table="T_SYS_ORG_INFO"
 */

public class SysOrg implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer treeLevel;   //  

   private java.lang.String orgStatus;   //  

   private java.lang.Integer upOrgId;   //  

   private java.lang.String orgCode;   //  

   private java.lang.String name;   //  

   private java.lang.String treeCode;   //  

   private java.lang.String orgAbbr;   //  

   private java.lang.String remark;   //  

   private java.lang.Integer orderNo;   //  


   private java.lang.String orgType;   //  

   // Constructors
   public SysOrg() {
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
    * Return the value associated with the column: ORG_STATUS
    *  
    */
   public java.lang.String getOrgStatus() { 
      return this.orgStatus; 
   }
   /**
    * Set the value related to the column: ORG_STATUS
    * @param orgStatus is the 'ORG_STATUS' value
    */
   public void setOrgStatus(java.lang.String orgStatus) { 
      this.orgStatus = orgStatus; 
   }

   /**
    * Return the value associated with the column: UP_ORG_ID
    *  
    */
   public java.lang.Integer getUpOrgId() { 
      return this.upOrgId; 
   }
   /**
    * Set the value related to the column: UP_ORG_ID
    * @param upOrgId is the 'UP_ORG_ID' value
    */
   public void setUpOrgId(java.lang.Integer upOrgId) { 
      this.upOrgId = upOrgId; 
   }

   /**
    * Return the value associated with the column: ORG_CODE
    *  
    */
   public java.lang.String getOrgCode() { 
      return this.orgCode; 
   }
   /**
    * Set the value related to the column: ORG_CODE
    * @param orgCode is the 'ORG_CODE' value
    */
   public void setOrgCode(java.lang.String orgCode) { 
      this.orgCode = orgCode; 
   }

   /**
    * Return the value associated with the column: ORG_NAME
    *  
    */
   public java.lang.String getName() { 
      return this.name; 
   }
   /**
    * Set the value related to the column: ORG_NAME
    * @param name is the 'ORG_NAME' value
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
    * Return the value associated with the column: ORG_ABBR
    *  
    */
   public java.lang.String getOrgAbbr() { 
      return this.orgAbbr; 
   }
   /**
    * Set the value related to the column: ORG_ABBR
    * @param orgAbbr is the 'ORG_ABBR' value
    */
   public void setOrgAbbr(java.lang.String orgAbbr) { 
      this.orgAbbr = orgAbbr; 
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
    * Return the value associated with the column: ORG_TYPE
    *  
    */
   public java.lang.String getOrgType() { 
      return this.orgType; 
   }
   /**
    * Set the value related to the column: ORG_TYPE
    * @param orgType is the 'ORG_TYPE' value
    */
   public void setOrgType(java.lang.String orgType) { 
      this.orgType = orgType; 
   }


   public static String REF_CLASS = "SysOrg";
   public static String PROP_TREE_LEVEL = "treeLevel";
   public static String PROP_ORG_STATUS = "orgStatus";
   public static String PROP_UP_ORG_ID = "upOrgId";
   public static String PROP_ORG_CODE = "orgCode";
   public static String PROP_ORG_NAME = "name";
   public static String PROP_TREE_CODE = "treeCode";
   public static String PROP_ORG_ABBR = "orgAbbr";
   public static String PROP_REMARK = "remark";
   public static String PROP_ORDER_NO = "orderNo";
   public static String PROP_ID = "id";
   public static String PROP_ORG_TYPE = "orgType";

   public static String REF_TABLE = "T_SYS_ORG_INFO";
   public static String COL_TREE_LEVEL = "TREE_LEVEL";
   public static String COL_ORG_STATUS = "ORG_STATUS";
   public static String COL_UP_ORG_ID = "UP_ORG_ID";
   public static String COL_ORG_CODE = "ORG_CODE";
   public static String COL_ORG_NAME = "ORG_NAME";
   public static String COL_TREE_CODE = "TREE_CODE";
   public static String COL_ORG_ABBR = "ORG_ABBR";
   public static String COL_REMARK = "REMARK";
   public static String COL_ORDER_NO = "ORDER_NO";
   public static String COL_ID = "ID";
   public static String COL_ORG_TYPE = "ORG_TYPE";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.org.SysOrg)) return false;
      else {
         com.liusy.datapp.model.system.org.SysOrg o = (com.liusy.datapp.model.system.org.SysOrg) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysOrg:");
		buffer.append(" id:").append(id);
		buffer.append(" treeLevel:").append(dealNull(treeLevel));
		buffer.append(" orgStatus:").append(dealNull(orgStatus));
		buffer.append(" upOrgId:").append(dealNull(upOrgId));
		buffer.append(" orgCode:").append(dealNull(orgCode));
		buffer.append(" name:").append(dealNull(name));
		buffer.append(" treeCode:").append(dealNull(treeCode));
		buffer.append(" orgAbbr:").append(dealNull(orgAbbr));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" orderNo:").append(dealNull(orderNo));
		buffer.append(" orgType:").append(dealNull(orgType));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"treeLevel\":\"").append(dealNull(treeLevel)).append("\"");
		buffer.append(",\"orgStatus\":\"").append(dealNull(orgStatus)).append("\"");
		buffer.append(",\"upOrgId\":\"").append(dealNull(upOrgId)).append("\"");
		buffer.append(",\"orgCode\":\"").append(dealNull(orgCode)).append("\"");
		buffer.append(",\"name\":\"").append(dealNull(name)).append("\"");
		buffer.append(",\"treeCode\":\"").append(dealNull(treeCode)).append("\"");
		buffer.append(",\"orgAbbr\":\"").append(dealNull(orgAbbr)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"orderNo\":\"").append(dealNull(orderNo)).append("\"");
		buffer.append(",\"orgType\":\"").append(dealNull(orgType)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
