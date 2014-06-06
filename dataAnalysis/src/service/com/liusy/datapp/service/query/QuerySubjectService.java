package com.liusy.datapp.service.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.QuerySubject;

public interface QuerySubjectService {

	public QuerySubject findQuerySubject(Serializable id) throws ServiceException;

	public void createQuerySubject(QuerySubject querySubject) throws ServiceException;

	public void updateQuerySubject(QuerySubject querySubject) throws ServiceException;

	public void removeQuerySubject(Serializable id) throws ServiceException;

	public PageQuery queryQuerySubject(PageQuery pageQuery) throws ServiceException;

	public void removeQuerySubjects(Serializable[] ids) throws ServiceException;
	/**业务方法
	 * 
	 */
	public List<QuerySubject> findCommSubject() throws ServiceException;
	//public QuerySubject findSubjectByCataId(String cataId) throws ServiceException;
	public void createSubjectWithSelTable(QuerySubject subject,String[] tabIds) throws ServiceException;
	public void updateSubjectWithSelTable(QuerySubject subject,String[] tabIds) throws ServiceException;
	/**
	 * 按照行业id查找对应的行业分类
	 * @param cataId
	 * @return
	 * @throws ServiceException
	 */
	public QuerySubject findSubjectByCatagoryId(Integer cataId) throws ServiceException;
	/**
	 * 判断主题代码是否重复
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public boolean isSubjectCodeExist(String code) throws ServiceException;
	/**
	 * 判断主题名称是否重复
	 * @param subjectName
	 * @return
	 * @throws ServiceException
	 */
	public boolean isSubjectNameDoubled(String subjectName) throws ServiceException;
}

