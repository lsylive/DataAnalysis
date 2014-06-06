package com.liusy.datapp.model.system.org;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_SYS_DEPT_INFO table.
 *
 * @hibernate.class
 *  table="T_SYS_DEPT_INFO"
 */

public class SysDept implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.String deptName;   //  

   private java.lang.String deptAbbr;   //  

   private java.lang.Integer treeLevel;   //  

   private java.lang.String deptCode;   //  

   private java.lang.String deptStatus;   //  

   private java.lang.String upDeptId;   //  

   private java.lang.Integer orgId;   //  

   private java.lang.Integer leaderUserId;   //  

   private java.lang.String treeCode;   //  

   private java.lang.String remark;   //  

   private java.lang.Integer orderNo;   //  


   // Constructors
   public SysDept() {
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
    * Return the value associated with the column: DEPT_NAME
    *  
    */
   public java.lang.String getDeptName() { 
      return this.deptName; 
   }
   /**
    * Set the value related to the column: DEPT_NAME
    * @param deptName is the 'DEPT_NAME' value
    */
   public void setDeptName(java.lang.String deptName) { 
      this.deptName = deptName; 
   }

   /**
    * Return the value associated with the column: DEPT_ABBR
    *  
    */
   public java.lang.String getDeptAbbr() { 
      return this.deptAbbr; 
   }
   /**
    * Set the value related to the column: DEPT_ABBR
    * @param deptAbbr is the 'DEPT_ABBR' value
    */
   public void setDeptAbbr(java.lang.String deptAbbr) { 
      this.deptAbbr = deptAbbr; 
   }

   /**
    * Return the value associated with the column: TREE_LEVEL
    *  
    */
   public Integer getTreeLevel() { 
      return this.treeLevel; 
   }
   /**
    * Set the value related to the column: TREE_LEVEL
    * @param treeLevel is the 'TREE_LEVEL' value
    */
   public void setTreeLevel(Integer treeLevel) { 
      this.treeLevel = treeLevel; 
   }
   /**
    * Return the value associated with the column: DEPT_CODE
    *  
    */
   public java.lang.String getDeptCode() { 
      return this.deptCode; 
   }
   /**
    * Set the value related to the column: DEPT_CODE
    * @param deptCode is the 'DEPT_CODE' value
    */
   public void setDeptCode(java.lang.String deptCode) { 
      this.deptCode = deptCode; 
   }

   /**
    * Return the value associated with the column: DEPT_STATUS
    *  
    */
   public java.lang.String getDeptStatus() { 
      return this.deptStatus; 
   }
   /**
    * Set the value related to the column: DEPT_STATUS
    * @param deptStatus is the 'DEPT_STATUS' value
    */
   public void setDeptStatus(java.lang.String deptStatus) { 
      this.deptStatus = deptStatus; 
   }

   /**
    * Return the value associated with the column: UP_DEPT_ID
    *  
    */
   public java.lang.String getUpDeptId() { 
      return this.upDeptId; 
   }
   /**
    * Set the value related to the column: UP_DEPT_ID
    * @param upDeptId is the 'UP_DEPT_ID' value
    */
   public void setUpDeptId(java.lang.String upDeptId) { 
      this.upDeptId = upDeptId; 
   }

   /**
    * Return the value associated with the column: ORG_ID
    *  
    */
   public java.lang.Integer getOrgId() { 
      return this.orgId; 
   }
   /**
    * Set the value related to the column: ORG_ID
    * @param orgId is the 'ORG_ID' value
    */
   public void setOrgId(java.lang.Integer orgId) { 
      this.orgId = orgId; 
   }

   /**
    * Return the value associated with the column: LEADER_USER_ID
    *  
    */
   public java.lang.Integer getLeaderUserId() { 
      return this.leaderUserId; 
   }
   /**
    * Set the value related to the column: LEADER_USER_ID
    * @param leaderUserId is the 'LEADER_USER_ID' value
    */
   public void setLeaderUserId(java.lang.Integer leaderUserId) { 
      this.leaderUserId = leaderUserId; 
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
   public Integer getOrderNo() { 
      return this.orderNo; 
   }
   /**
    * Set the value related to the column: ORDER_NO
    * @param orderNo is the 'ORDER_NO' value
    */
   public void setOrderNo(Integer orderNo) { 
      this.orderNo = orderNo; 
   }


   public static String REF_CLASS = "SysDept";
   public static String PROP_DEPT_NAME = "deptName";
   public static String PROP_DEPT_ABBR = "deptAbbr";
   public static String PROP_TREE_LEVEL = "treeLevel";
   public static String PROP_DEPT_CODE = "deptCode";
   public static String PROP_DEPT_STATUS = "deptStatus";
   public static String PROP_UP_DEPT_ID = "upDeptId";
   public static String PROP_ORG_ID = "orgId";
   public static String PROP_LEADER_USER_ID = "leaderUserId";
   public static String PROP_TREE_CODE = "treeCode";
   public static String PROP_REMARK = "remark";
   public static String PROP_ORDER_NO = "orderNo";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_SYS_DEPT_INFO";
   public static String COL_DEPT_NAME = "DEPT_NAME";
   public static String COL_DEPT_ABBR = "DEPT_ABBR";
   public static String COL_TREE_LEVEL = "TREE_LEVEL";
   public static String COL_DEPT_CODE = "DEPT_CODE";
   public static String COL_DEPT_STATUS = "DEPT_STATUS";
   public static String COL_UP_DEPT_ID = "UP_DEPT_ID";
   public static String COL_ORG_ID = "ORG_ID";
   public static String COL_LEADER_USER_ID = "LEADER_USER_ID";
   public static String COL_TREE_CODE = "TREE_CODE";
   public static String COL_REMARK = "REMARK";
   public static String COL_ORDER_NO = "ORDER_NO";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.system.org.SysDept)) return false;
      else {
         com.liusy.datapp.model.system.org.SysDept o = (com.liusy.datapp.model.system.org.SysDept) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SysDept:");
		buffer.append(" serialVersionUID: ");
		buffer.append(serialVersionUID);
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" deptName: ");
		buffer.append(deptName);
		buffer.append(" deptAbbr: ");
		buffer.append(deptAbbr);
		buffer.append(" treeLevel: ");
		buffer.append(treeLevel);
		buffer.append(" deptCode: ");
		buffer.append(deptCode);
		buffer.append(" deptStatus: ");
		buffer.append(deptStatus);
		buffer.append(" upDeptId: ");
		buffer.append(upDeptId);
		buffer.append(" orgId: ");
		buffer.append(orgId);
		buffer.append(" leaderUserId: ");
		buffer.append(leaderUserId);
		buffer.append(" treeCode: ");
		buffer.append(treeCode);
		buffer.append(" remark: ");
		buffer.append(remark);
		buffer.append(" orderNo: ");
		buffer.append(orderNo);
		buffer.append(" REF_CLASS: ");
		buffer.append(REF_CLASS);
		buffer.append(" PROP_DEPT_NAME: ");
		buffer.append(PROP_DEPT_NAME);
		buffer.append(" PROP_DEPT_ABBR: ");
		buffer.append(PROP_DEPT_ABBR);
		buffer.append(" PROP_TREE_LEVEL: ");
		buffer.append(PROP_TREE_LEVEL);
		buffer.append(" PROP_DEPT_CODE: ");
		buffer.append(PROP_DEPT_CODE);
		buffer.append(" PROP_DEPT_STATUS: ");
		buffer.append(PROP_DEPT_STATUS);
		buffer.append(" PROP_UP_DEPT_ID: ");
		buffer.append(PROP_UP_DEPT_ID);
		buffer.append(" PROP_ORG_ID: ");
		buffer.append(PROP_ORG_ID);
		buffer.append(" PROP_LEADER_USER_ID: ");
		buffer.append(PROP_LEADER_USER_ID);
		buffer.append(" PROP_TREE_CODE: ");
		buffer.append(PROP_TREE_CODE);
		buffer.append(" PROP_REMARK: ");
		buffer.append(PROP_REMARK);
		buffer.append(" PROP_ORDER_NO: ");
		buffer.append(PROP_ORDER_NO);
		buffer.append(" PROP_ID: ");
		buffer.append(PROP_ID);
		buffer.append(" REF_TABLE: ");
		buffer.append(REF_TABLE);
		buffer.append(" COL_DEPT_NAME: ");
		buffer.append(COL_DEPT_NAME);
		buffer.append(" COL_DEPT_ABBR: ");
		buffer.append(COL_DEPT_ABBR);
		buffer.append(" COL_TREE_LEVEL: ");
		buffer.append(COL_TREE_LEVEL);
		buffer.append(" COL_DEPT_CODE: ");
		buffer.append(COL_DEPT_CODE);
		buffer.append(" COL_DEPT_STATUS: ");
		buffer.append(COL_DEPT_STATUS);
		buffer.append(" COL_UP_DEPT_ID: ");
		buffer.append(COL_UP_DEPT_ID);
		buffer.append(" COL_ORG_ID: ");
		buffer.append(COL_ORG_ID);
		buffer.append(" COL_LEADER_USER_ID: ");
		buffer.append(COL_LEADER_USER_ID);
		buffer.append(" COL_TREE_CODE: ");
		buffer.append(COL_TREE_CODE);
		buffer.append(" COL_REMARK: ");
		buffer.append(COL_REMARK);
		buffer.append(" COL_ORDER_NO: ");
		buffer.append(COL_ORDER_NO);
		buffer.append(" COL_ID: ");
		buffer.append(COL_ID);
		buffer.append("]");
		return buffer.toString();
	}

  
}
