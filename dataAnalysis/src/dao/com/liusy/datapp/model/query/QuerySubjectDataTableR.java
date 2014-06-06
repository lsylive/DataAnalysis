package com.liusy.datapp.model.query;

/**
 * 综查主题资源表关联
 * 
 * @author 戴浩 Aug. 24, 2009
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="T_QUERY_SUBJECT_DATATABLE_R"
 * @hibernate.comment 综查主题资源表关联
 */
public class QuerySubjectDataTableR {
	private static final long serialVersionUID = 1L;
	
	private	Long		id;				//Id
	private Long		subjectId;		//主题Id
	private Long		dtId;			//资源表Id
	
	/**	
	 * @hibernate.id unsaved-value="null"
	 * @hibernate.generator class="native"
	 * @hibernate.column comment="主键ID"
     * @form.no_search
     * @form.no_list
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="主题id"
	 * @form.no_search
     * @form.no_list
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="资源表id"
	 * @form.no_search
     * @form.no_list
	 */
	public Long getDtId() {
		return dtId;
	}

	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}

	/**
	 * Override hashCode.
	 *
	 * @return the Objects hashcode.
	 */
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31
			* hashCode
			+ (int) (+serialVersionUID ^ (serialVersionUID >>> 32));
		hashCode = 31 * hashCode + (id == null ? 0 : id.hashCode());
		hashCode = 31
			* hashCode
			+ (subjectId == null ? 0 : subjectId.hashCode());
		hashCode = 31 * hashCode + (dtId == null ? 0 : dtId.hashCode());
		return hashCode;
	}

	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[QuerySubjectDataTableR:");
			buffer.append(" serialVersionUID: ");
			buffer.append(serialVersionUID);
			buffer.append(" id: ");
			buffer.append(id);
			buffer.append(" subjectId: ");
			buffer.append(subjectId);
			buffer.append(" dtId: ");
			buffer.append(dtId);
			buffer.append("]");
			return buffer.toString();
		}
	
	

}
