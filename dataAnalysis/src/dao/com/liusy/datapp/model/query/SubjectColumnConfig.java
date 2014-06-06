package com.liusy.datapp.model.query;



/**
 * 综查主题查询条件字段配置
 * 
 * @author 戴浩 Aug. 24, 2009
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="T_QUERY_SUBJECT_COLUMN_CFG"
 * @hibernate.comment 综查主题查询条件字段配置表
 */
public class SubjectColumnConfig {
private static final long serialVersionUID = 1L;
	
	private Long id;							//主键id
	private Long subjectId;						//所属主题id
	private Long indId;							//指标id
	private String isFilter;					//是否查询条件字段
	private String filterOperator;				//过滤条件运算符
	private String filterValue;					//过滤条件值
	private Integer orderNo;					//顺序号
	private String batchQuery;					//是否可批量查
	private String homonymQuery;				//是否提供同音查
	private String stQuery;						//是否提供简繁查
	private String fuzzyQuery;					//是否提供模糊查
	
	
	/**	
	 * @hibernate.id unsaved-value="null"
	 * @hibernate.generator class="native"
	 * @hibernate.column comment="主键ID"
     * @form.no_search
     * @form.no_list
	 * @return columnId
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="true" comment="是否属于过滤条件"
	 */	
	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="32" not-null="false" comment="过滤条件运算符"
	 */	
	public String getFilterOperator() {
		return filterOperator;
	}
	public void setFilterOperator(String filterOperator) {
		this.filterOperator = filterOperator;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1024" not-null="false" comment="过滤条件值"
	 */	
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="是否提供批量查"
	 */	
	public String getBatchQuery() {
		return batchQuery;
	}
	public void setBatchQuery(String batchQuery) {
		this.batchQuery = batchQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="是否提供同音查"
	 */	
	public String getHomonymQuery() {
		return homonymQuery;
	}
	public void setHomonymQuery(String homonymQuery) {
		this.homonymQuery = homonymQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="是否提供简繁查"
	 */	
	public String getStQuery() {
		return stQuery;
	}
	public void setStQuery(String stQuery) {
		this.stQuery = stQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="是否提供模糊查"
	 */	
	public String getFuzzyQuery() {
		return fuzzyQuery;
	}
	public void setFuzzyQuery(String fuzzyQuery) {
		this.fuzzyQuery = fuzzyQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="所属主题id"
	 */
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="对应指标id"
	 */
	public Long getIndId() {
		return indId;
	}
	public void setIndId(Long indId) {
		this.indId = indId;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="顺序号"
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
		hashCode = 31 * hashCode + (indId == null ? 0 : indId.hashCode());
		hashCode = 31 * hashCode + (isFilter == null ? 0 : isFilter.hashCode());
		hashCode = 31
			* hashCode
			+ (filterOperator == null ? 0 : filterOperator.hashCode());
		hashCode = 31
			* hashCode
			+ (filterValue == null ? 0 : filterValue.hashCode());
		hashCode = 31 * hashCode + (orderNo == null ? 0 : orderNo.hashCode());
		hashCode = 31
			* hashCode
			+ (batchQuery == null ? 0 : batchQuery.hashCode());
		hashCode = 31
			* hashCode
			+ (homonymQuery == null ? 0 : homonymQuery.hashCode());
		hashCode = 31 * hashCode + (stQuery == null ? 0 : stQuery.hashCode());
		hashCode = 31
			* hashCode
			+ (fuzzyQuery == null ? 0 : fuzzyQuery.hashCode());
		return hashCode;
	}
	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[SubjectColumnConfig:");
			buffer.append(" serialVersionUID: ");
			buffer.append(serialVersionUID);
			buffer.append(" id: ");
			buffer.append(id);
			buffer.append(" subjectId: ");
			buffer.append(subjectId);
			buffer.append(" indId: ");
			buffer.append(indId);
			buffer.append(" isFilter: ");
			buffer.append(isFilter);
			buffer.append(" filterOperator: ");
			buffer.append(filterOperator);
			buffer.append(" filterValue: ");
			buffer.append(filterValue);
			buffer.append(" orderNo: ");
			buffer.append(orderNo);
			buffer.append(" batchQuery: ");
			buffer.append(batchQuery);
			buffer.append(" homonymQuery: ");
			buffer.append(homonymQuery);
			buffer.append(" stQuery: ");
			buffer.append(stQuery);
			buffer.append(" fuzzyQuery: ");
			buffer.append(fuzzyQuery);
			buffer.append("]");
			return buffer.toString();
		}
	
	

	

	
	
	
}
