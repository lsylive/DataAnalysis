package com.liusy.datapp.model.query;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_QUERY_TABLECOLUMN_CFG table.
 *
 * @hibernate.class
 *  table="T_QUERY_TABLECOLUMN_CFG"
 */

public class QueryColumnCfg implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer columnId;   //  

   private java.lang.Integer displayWidth;   //  

   private java.lang.String isSortable;   //  

   private java.lang.String isSubject;   //  

   private java.lang.String isShown;   //  

   private java.lang.Integer tableId;   //  

   private java.lang.Integer seqNo;   //  


   // Constructors
   public QueryColumnCfg() {
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
    * Return the value associated with the column: COLUMN_ID
    *  
    */
   public java.lang.Integer getColumnId() { 
      return this.columnId; 
   }
   /**
    * Set the value related to the column: COLUMN_ID
    * @param columnId is the 'COLUMN_ID' value
    */
   public void setColumnId(java.lang.Integer columnId) { 
      this.columnId = columnId; 
   }

   /**
    * Return the value associated with the column: DISPLAY_WIDTH
    *  
    */
   public java.lang.Integer getDisplayWidth() { 
      return this.displayWidth; 
   }
   /**
    * Set the value related to the column: DISPLAY_WIDTH
    * @param displayWidth is the 'DISPLAY_WIDTH' value
    */
   public void setDisplayWidth(java.lang.Integer displayWidth) { 
      this.displayWidth = displayWidth; 
   }

   /**
    * Return the value associated with the column: IS_SORTABLE
    *  
    */
   public java.lang.String getIsSortable() { 
      return this.isSortable; 
   }
   /**
    * Set the value related to the column: IS_SORTABLE
    * @param isSortable is the 'IS_SORTABLE' value
    */
   public void setIsSortable(java.lang.String isSortable) { 
      this.isSortable = isSortable; 
   }

   /**
    * Return the value associated with the column: IS_SUBJECT
    *  
    */
   public java.lang.String getIsSubject() { 
      return this.isSubject; 
   }
   /**
    * Set the value related to the column: IS_SUBJECT
    * @param isSubject is the 'IS_SUBJECT' value
    */
   public void setIsSubject(java.lang.String isSubject) { 
      this.isSubject = isSubject; 
   }

   /**
    * Return the value associated with the column: IS_SHOWN
    *  
    */
   public java.lang.String getIsShown() { 
      return this.isShown; 
   }
   /**
    * Set the value related to the column: IS_SHOWN
    * @param isShown is the 'IS_SHOWN' value
    */
   public void setIsShown(java.lang.String isShown) { 
      this.isShown = isShown; 
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
    * Return the value associated with the column: SEQ_NO
    *  
    */
   public java.lang.Integer getSeqNo() { 
      return this.seqNo; 
   }
   /**
    * Set the value related to the column: SEQ_NO
    * @param seqNo is the 'SEQ_NO' value
    */
   public void setSeqNo(java.lang.Integer seqNo) { 
      this.seqNo = seqNo; 
   }


   public static String REF_CLASS = "QueryColumnCfg";
   public static String PROP_COLUMN_ID = "columnId";
   public static String PROP_DISPLAY_WIDTH = "displayWidth";
   public static String PROP_IS_SORTABLE = "isSortable";
   public static String PROP_IS_SUBJECT = "isSubject";
   public static String PROP_IS_SHOWN = "isShown";
   public static String PROP_TABLE_ID = "tableId";
   public static String PROP_SEQ_NO = "seqNo";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_QUERY_TABLECOLUMN_CFG";
   public static String COL_COLUMN_ID = "COLUMN_ID";
   public static String COL_DISPLAY_WIDTH = "DISPLAY_WIDTH";
   public static String COL_IS_SORTABLE = "IS_SORTABLE";
   public static String COL_IS_SUBJECT = "IS_SUBJECT";
   public static String COL_IS_SHOWN = "IS_SHOWN";
   public static String COL_TABLE_ID = "TABLE_ID";
   public static String COL_SEQ_NO = "SEQ_NO";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.query.QueryColumnCfg)) return false;
      else {
         com.liusy.datapp.model.query.QueryColumnCfg o = (com.liusy.datapp.model.query.QueryColumnCfg) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[QueryColumnCfg:");
		buffer.append(" id:").append(id);
		buffer.append(" columnId:").append(dealNull(columnId));
		buffer.append(" displayWidth:").append(dealNull(displayWidth));
		buffer.append(" isSortable:").append(dealNull(isSortable));
		buffer.append(" isSubject:").append(dealNull(isSubject));
		buffer.append(" isShown:").append(dealNull(isShown));
		buffer.append(" tableId:").append(dealNull(tableId));
		buffer.append(" seqNo:").append(dealNull(seqNo));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"columnId\":\"").append(dealNull(columnId)).append("\"");
		buffer.append(",\"displayWidth\":\"").append(dealNull(displayWidth)).append("\"");
		buffer.append(",\"isSortable\":\"").append(dealNull(isSortable)).append("\"");
		buffer.append(",\"isSubject\":\"").append(dealNull(isSubject)).append("\"");
		buffer.append(",\"isShown\":\"").append(dealNull(isShown)).append("\"");
		buffer.append(",\"tableId\":\"").append(dealNull(tableId)).append("\"");
		buffer.append(",\"seqNo\":\"").append(dealNull(seqNo)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
