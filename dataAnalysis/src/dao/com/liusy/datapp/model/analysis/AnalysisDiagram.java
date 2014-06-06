package com.liusy.datapp.model.analysis;

import java.sql.Blob;
import java.util.Date;

/**
 * This is an object that contains data related to the T_ANALYSIS_DIAGRAM table.
 *
 * @hibernate.class
 *  table="T_ANALYSIS_DIAGRAM"
 */
public class AnalysisDiagram implements java.io.Serializable {
   private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private String name;
	private String type;
	private String createBy;
	private Date createDate;
	private String description;
   private String visiable;
	private Blob body;
//	private byte[] body;

	// Constructors

	/** default constructor */
	public AnalysisDiagram() {
	}
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getBody() {
		return this.body;
	}

	public void setBody(Blob body) {
		this.body = body;
	}

   public static String REF_CLASS = "AnalysisDiagram";
   public static String PROP_NAME = "name";
   public static String PROP_TYPE = "type";
   public static String PROP_CREATEBY = "createBy";
   public static String PROP_CREATEDATE = "createDate";
   public static String PROP_DESCRIPTION = "description";
   public static String PROP_BODY = "body";
   public static String PROP_ID = "id";

   public static String REF_TABLE = "T_ANALYSIS_DIAGRAM";
   public static String COL_NAME = "NAME";
   public static String COL_TYPE = "TYPE";
   public static String COL_CREATEBY = "CREATE_BY";
   public static String COL_CREATEDATE = "CREATE_DATE";
   public static String COL_DESCRIPTION = "DESCRIPTION";
   public static String COL_ID = "ID";

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final AnalysisDiagram other = (AnalysisDiagram) obj;
		if (createBy == null) {
			if (other.createBy != null) return false;
		}
		else if (!createBy.equals(other.createBy)) return false;
		if (createDate == null) {
			if (other.createDate != null) return false;
		}
		else if (!createDate.equals(other.createDate)) return false;
		if (description == null) {
			if (other.description != null) return false;
		}
		else if (!description.equals(other.description)) return false;
		if (id == null) {
			if (other.id != null) return false;
		}
		else if (!id.equals(other.id)) return false;
		if (name == null) {
			if (other.name != null) return false;
		}
		else if (!name.equals(other.name)) return false;
		if (type == null) {
			if (other.type != null) return false;
		}
		else if (!type.equals(other.type)) return false;
		return true;
	}

   public String getVisiable() {
      return visiable;
   }

   public void setVisiable(String visiable) {
      this.visiable = visiable;
   }

//	public byte[] getBody() {
//		return body;
//	}
//
//	public void setBody(byte[] body) {
//		this.body = body;
//	}	
}