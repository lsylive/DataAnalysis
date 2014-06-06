package com.liusy.datapp.model.query;



/**
 * �۲������ѯ�����ֶ�����
 * 
 * @author ���� Aug. 24, 2009
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="T_QUERY_SUBJECT_COLUMN_CFG"
 * @hibernate.comment �۲������ѯ�����ֶ����ñ�
 */
public class SubjectColumnConfig {
private static final long serialVersionUID = 1L;
	
	private Long id;							//����id
	private Long subjectId;						//��������id
	private Long indId;							//ָ��id
	private String isFilter;					//�Ƿ��ѯ�����ֶ�
	private String filterOperator;				//�������������
	private String filterValue;					//��������ֵ
	private Integer orderNo;					//˳���
	private String batchQuery;					//�Ƿ��������
	private String homonymQuery;				//�Ƿ��ṩͬ����
	private String stQuery;						//�Ƿ��ṩ�򷱲�
	private String fuzzyQuery;					//�Ƿ��ṩģ����
	
	
	/**	
	 * @hibernate.id unsaved-value="null"
	 * @hibernate.generator class="native"
	 * @hibernate.column comment="����ID"
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
	 * @hibernate.column length="1" not-null="true" comment="�Ƿ����ڹ�������"
	 */	
	public String getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(String isFilter) {
		this.isFilter = isFilter;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="32" not-null="false" comment="�������������"
	 */	
	public String getFilterOperator() {
		return filterOperator;
	}
	public void setFilterOperator(String filterOperator) {
		this.filterOperator = filterOperator;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1024" not-null="false" comment="��������ֵ"
	 */	
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="�Ƿ��ṩ������"
	 */	
	public String getBatchQuery() {
		return batchQuery;
	}
	public void setBatchQuery(String batchQuery) {
		this.batchQuery = batchQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="�Ƿ��ṩͬ����"
	 */	
	public String getHomonymQuery() {
		return homonymQuery;
	}
	public void setHomonymQuery(String homonymQuery) {
		this.homonymQuery = homonymQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="�Ƿ��ṩ�򷱲�"
	 */	
	public String getStQuery() {
		return stQuery;
	}
	public void setStQuery(String stQuery) {
		this.stQuery = stQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="�Ƿ��ṩģ����"
	 */	
	public String getFuzzyQuery() {
		return fuzzyQuery;
	}
	public void setFuzzyQuery(String fuzzyQuery) {
		this.fuzzyQuery = fuzzyQuery;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="��������id"
	 */
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="��Ӧָ��id"
	 */
	public Long getIndId() {
		return indId;
	}
	public void setIndId(Long indId) {
		this.indId = indId;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column not-null="true" comment="˳���"
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
