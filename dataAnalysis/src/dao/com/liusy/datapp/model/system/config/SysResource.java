package com.liusy.datapp.model.system.config;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_RESOURCE_INFO table.
 *
 * @hibernate.class
 *  table="T_SYS_RESOURCE_INFO"
 */

public class SysResource implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String resName;   //  

   private java.lang.String resType;   //  

   private java.lang.String actionUrl;   //  

   private java.lang.Integer powerId;   //  

   private java.lang.String resCode;   //  

   private java.lang.String remark;   //  

   private java.lang.Integer orderNo;   //  


   private java.lang.Integer parentId;   //  

   // Constructors
   public SysResource() {
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
    * Return the value associated with the column: RES_NAME
    *  
    */
   public java.lang.String getResName() { 
      return this.resName; 
   }
   /**
    * Set the value related to the column: RES_NAME
    * @param resName is the 'RES_NAME' value
    */
   public void setResName(java.lang.String resName) { 
      this.resName = resName; 
   }

   /**
    * Return the value associated with the column: RES_TYPE
    *  
    */
   public java.lang.String getResType() { 
      return this.resType; 
   }
   /**
    * Set the value related to the column: RES_TYPE
    * @param resType is the 'RES_TYPE' value
    */
   public void setResType(java.lang.String resType) { 
      this.resType = resType; 
   }

   /**
    * Return the value associated with the column: ACTION_URL
    *  
    */
   public java.lang.String getActionUrl() { 
      return this.actionUrl; 
   }
   /**
    * Set the value related to the column: ACTION_URL
    * @param actionUrl is the 'ACTION_URL' value
    */
   public void setActionUrl(java.lang.String actionUrl) { 
      this.actionUrl = actionUrl; 
   }

   /**
    * Return the value associated with the column: POWER_ID
    *  
    */
   public java.lang.Integer getPowerId() { 
      return this.powerId; 
   }
   /**
    * Set the value related to the column: POWER_ID
    * @param powerId is the 'POWER_ID' value
    */
   public void setPowerId(java.lang.Integer powerId) { 
      this.powerId = powerId; 
   }

   /**
    * Return the value associated with the column: RES_CODE
    *  
    */
   public java.lang.String getResCode() { 
      return this.resCode; 
   }
   /**
    * Set the value related to the column: RES_CODE
    * @param resCode is the 'RES_CODE' value
    */
   public void setResCode(java.lang.String resCode) { 
      this.resCode = resCode; 
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


   public static String REF_CLASS = "SysResource";
   public static String PROP_RES_NAME = "resName";
   public static String PROP_RES_TYPE = "resType";
   public static String PROP_ACTION_URL = "actionUrl";
   public static String PROP_POWER_ID = "powerId";
   public static String PROP_RES_CODE = "resCode";
   public static String PROP_REMARK = "remark";
   public static String PROP_ORDER_NO = "orderNo";
   public static String PROP_ID = "id";
   public static String PROP_PARENT_ID = "parentId";

   public static String REF_TABLE = "T_SYS_RESOURCE_INFO";
   public static String COL_RES_NAME = "RES_NAME";
   public static String COL_RES_TYPE = "RES_TYPE";
   public static String COL_ACTION_URL = "ACTION_URL";
   public static String COL_POWER_ID = "POWER_ID";
   public static String COL_RES_CODE = "RES_CODE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ORDER_NO = "ORDER_NO";
   public static String COL_ID = "ID";
   public static String COL_PARENT_ID = "PARENT_ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.config.SysResource)) return false;
      else {
         com.liusy.datapp.model.system.config.SysResource o = (com.liusy.datapp.model.system.config.SysResource) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysResource:");
		buffer.append(" id:").append(id);
		buffer.append(" resName:").append(dealNull(resName));
		buffer.append(" resType:").append(dealNull(resType));
		buffer.append(" actionUrl:").append(dealNull(actionUrl));
		buffer.append(" powerId:").append(dealNull(powerId));
		buffer.append(" resCode:").append(dealNull(resCode));
		buffer.append(" remark:").append(dealNull(remark));
		buffer.append(" orderNo:").append(dealNull(orderNo));
		buffer.append(" parentId:").append(dealNull(parentId));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"resName\":\"").append(dealNull(resName)).append("\"");
		buffer.append(",\"resType\":\"").append(dealNull(resType)).append("\"");
		buffer.append(",\"actionUrl\":\"").append(dealNull(actionUrl)).append("\"");
		buffer.append(",\"powerId\":\"").append(dealNull(powerId)).append("\"");
		buffer.append(",\"resCode\":\"").append(dealNull(resCode)).append("\"");
		buffer.append(",\"remark\":\"").append(dealNull(remark)).append("\"");
		buffer.append(",\"orderNo\":\"").append(dealNull(orderNo)).append("\"");
		buffer.append(",\"parentId\":\"").append(dealNull(parentId)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
