package com.liusy.datapp.model.query;



/**
 * 综查单表字段配置
 * 
 * @author 戴浩 Aug. 24, 2009
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="T_QUERY_DATATABLE_COLUMN_CFG"
 * @hibernate.comment 综查单表字段配置表
 */
public class DataTableColumnConfig {
private static final long serialVersionUID = 1L;
	
	private Long columnId;						//主、外键
	private String isShown;						//是否显示
	private String isSubject;					//是否概要显示
	private String isSortable;					//是否可排序
	private String isFilter;					//是否查询条件字段
	private String filterOperator;				//过滤条件运算符
	private String filterValue;					//过滤条件值
	private Integer displayWidth;				//显示宽度
	private String defaultSort;					//默认排序方式
	private String batchQuery;					//是否可批量查
	private String homonymQuery;				//是否提供同音查
	private String stQuery;						//是否提供简繁查
	private String fuzzyQuery;					//是否提供模糊查
	
	
	/**	
	 * @hibernate.id unsaved-value="null"
	 * @hibernate.generator class="assigned"
	 * @hibernate.column comment="主、外键ID"
     * @form.no_search
     * @form.no_list
	 * @return columnId
	 */
	public Long getColumnId() {
		return columnId;
	}
	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="true" comment="是否显示"
	 */	
	public String getIsShown() {
		return isShown;
	}
	public void setIsShown(String isShown) {
		this.isShown = isShown;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="true" comment="是否概要显示"
	 */	
	public String getIsSubject() {
		return isSubject;
	}
	public void setIsSubject(String isSubject) {
		this.isSubject = isSubject;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="是否可排序"
	 */	
	public String getIsSortable() {
		return isSortable;
	}
	public void setIsSortable(String isSortable) {
		this.isSortable = isSortable;
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
	 * @hibernate.column not-null="false" not-null="false" comment="显示宽度"
	 */
	public Integer getDisplayWidth() {
		return displayWidth;
	}
	public void setDisplayWidth(Integer displayWidth) {
		this.displayWidth = displayWidth;
	}
	
	/**
	 * @hibernate.property
	 * @hibernate.column length="1" not-null="false" comment="默认排序方式"
	 */	
	public String getDefaultSort() {
		return defaultSort;
	}
	public void setDefaultSort(String defaultSort) {
		this.defaultSort = defaultSort;
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
	 * Override hashCode.
	 *
	 * @return the Objects hashcode.
	 */
	public int hashCode() {
		int hashCode = 1;
		hashCode = 31
			* hashCode
			+ (int) (+serialVersionUID ^ (serialVersionUID >>> 32));
		hashCode = 31 * hashCode + (columnId == null ? 0 : columnId.hashCode());
		hashCode = 31 * hashCode + (isShown == null ? 0 : isShown.hashCode());
		hashCode = 31
			* hashCode
			+ (isSubject == null ? 0 : isSubject.hashCode());
		hashCode = 31
			* hashCode
			+ (isSortable == null ? 0 : isSortable.hashCode());
		hashCode = 31 * hashCode + (isFilter == null ? 0 : isFilter.hashCode());
		hashCode = 31
			* hashCode
			+ (filterOperator == null ? 0 : filterOperator.hashCode());
		hashCode = 31
			* hashCode
			+ (filterValue == null ? 0 : filterValue.hashCode());
		hashCode = 31
			* hashCode
			+ (displayWidth == null ? 0 : displayWidth.hashCode());
		hashCode = 31
			* hashCode
			+ (defaultSort == null ? 0 : defaultSort.hashCode());
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
			buffer.append("[DataTableColumnConfig:");
			buffer.append(" serialVersionUID: ");
			buffer.append(serialVersionUID);
			buffer.append(" columnId: ");
			buffer.append(columnId);
			buffer.append(" isShown: ");
			buffer.append(isShown);
			buffer.append(" isSubject: ");
			buffer.append(isSubject);
			buffer.append(" isSortable: ");
			buffer.append(isSortable);
			buffer.append(" isFilter: ");
			buffer.append(isFilter);
			buffer.append(" filterOperator: ");
			buffer.append(filterOperator);
			buffer.append(" filterValue: ");
			buffer.append(filterValue);
			buffer.append(" displayWidth: ");
			buffer.append(displayWidth);
			buffer.append(" defaultSort: ");
			buffer.append(defaultSort);
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
