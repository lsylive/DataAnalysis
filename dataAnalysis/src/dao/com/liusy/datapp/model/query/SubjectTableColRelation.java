package com.liusy.datapp.model.query;

import java.io.Serializable;

/**
 * This is an object that contains data related to the T_QUERY_SUBTABLECOL_R table.
 *
 * @hibernate.class
 *  table="T_QUERY_SUBTABLECOL_R"
 */

public class SubjectTableColRelation implements Serializable {
    private static final long serialVersionUID = 1L;

   // primary key  
   private java.lang.Integer id;   //  

   // fields

   private java.lang.Integer subjectTabId;   //  

   private java.lang.Integer columnid;   //  

   private java.lang.Integer displayWidth;   //  

   private java.lang.String isSortable;   //  

   private java.lang.String isSubject;   //  

   private java.lang.String isShown;   //  

   private java.lang.String isFilter;   //  

   private java.lang.String defaultSort;   //  


   // Constructors
   public SubjectTableColRelation() {
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
    * Return the value associated with the column: SUBJECTTABID
    *  
    */
   public java.lang.Integer getSubjectTabId() { 
      return this.subjectTabId; 
   }
   /**
    * Set the value related to the column: SUBJECTTABID
    * @param subjectTabId is the 'SUBJECTTABID' value
    */
   public void setSubjectTabId(java.lang.Integer subjectTabId) { 
      this.subjectTabId = subjectTabId; 
   }

   /**
    * Return the value associated with the column: COLUMNID
    *  
    */
   public java.lang.Integer getColumnid() { 
      return this.columnid; 
   }
   /**
    * Set the value related to the column: COLUMNID
    * @param columnid is the 'COLUMNID' value
    */
   public void setColumnid(java.lang.Integer columnid) { 
      this.columnid = columnid; 
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
    * Return the value associated with the column: IS_FILTER
    *  
    */
   public java.lang.String getIsFilter() { 
      return this.isFilter; 
   }
   /**
    * Set the value related to the column: IS_FILTER
    * @param isFilter is the 'IS_FILTER' value
    */
   public void setIsFilter(java.lang.String isFilter) { 
      this.isFilter = isFilter; 
   }

   /**
    * Return the value associated with the column: DEFAULT_SORT
    *  
    */
   public java.lang.String getDefaultSort() { 
      return this.defaultSort; 
   }
   /**
    * Set the value related to the column: DEFAULT_SORT
    * @param defaultSort is the 'DEFAULT_SORT' value
    */
   public void setDefaultSort(java.lang.String defaultSort) { 
      this.defaultSort = defaultSort; 
   }


   public static String REF_CLASS = "SubjectTableColRelation";
   public static String PROP_SUBJECTTABID = "subjectTabId";
   public static String PROP_COLUMNID = "columnid";
   public static String PROP_DISPLAY_WIDTH = "displayWidth";
   public static String PROP_IS_SORTABLE = "isSortable";
   public static String PROP_IS_SUBJECT = "isSubject";
   public static String PROP_IS_SHOWN = "isShown";
   public static String PROP_IS_FILTER = "isFilter";
   public static String PROP_DEFAULT_SORT = "defaultSort";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_QUERY_SUBTABLECOL_R";
   public static String COL_SUBJECTTABID = "SUBJECTTABID";
   public static String COL_COLUMNID = "COLUMNID";
   public static String COL_DISPLAY_WIDTH = "DISPLAY_WIDTH";
   public static String COL_IS_SORTABLE = "IS_SORTABLE";
   public static String COL_IS_SUBJECT = "IS_SUBJECT";
   public static String COL_IS_SHOWN = "IS_SHOWN";
   public static String COL_IS_FILTER = "IS_FILTER";
   public static String COL_DEFAULT_SORT = "DEFAULT_SORT";
   public static String COL_ID = "ID";
   
   public boolean equals(Object obj) {
      if (null == obj) return false;
      if (!(obj instanceof com.liusy.datapp.model.query.SubjectTableColRelation)) return false;
      else {
         com.liusy.datapp.model.query.SubjectTableColRelation o = (com.liusy.datapp.model.query.SubjectTableColRelation) obj;
         if (null == this.getId() || null == o.getId()) return false;
         else return (this.getId().equals(o.getId()));
      }
   }

   public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[SubjectTableColRelation:");
		buffer.append(" id:").append(id);
		buffer.append(" subjectTabId:").append(dealNull(subjectTabId));
		buffer.append(" columnid:").append(dealNull(columnid));
		buffer.append(" displayWidth:").append(dealNull(displayWidth));
		buffer.append(" isSortable:").append(dealNull(isSortable));
		buffer.append(" isSubject:").append(dealNull(isSubject));
		buffer.append(" isShown:").append(dealNull(isShown));
		buffer.append(" isFilter:").append(dealNull(isFilter));
		buffer.append(" defaultSort:").append(dealNull(defaultSort));
		buffer.append("]");
		return buffer.toString();
   }

   public String toJson() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"id\":\"").append(id).append("\"");
		buffer.append(",\"subjectTabId\":\"").append(dealNull(subjectTabId)).append("\"");
		buffer.append(",\"columnid\":\"").append(dealNull(columnid)).append("\"");
		buffer.append(",\"displayWidth\":\"").append(dealNull(displayWidth)).append("\"");
		buffer.append(",\"isSortable\":\"").append(dealNull(isSortable)).append("\"");
		buffer.append(",\"isSubject\":\"").append(dealNull(isSubject)).append("\"");
		buffer.append(",\"isShown\":\"").append(dealNull(isShown)).append("\"");
		buffer.append(",\"isFilter\":\"").append(dealNull(isFilter)).append("\"");
		buffer.append(",\"defaultSort\":\"").append(dealNull(defaultSort)).append("\"");
		buffer.append("}");
		return buffer.toString();
   }

	private String dealNull(Object str) {
		if(str==null) return ""; else return str.toString();
	}
}
