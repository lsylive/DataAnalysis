package com.liusy.datapp.service.query.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.QuerySubjectDao;
import com.liusy.datapp.dao.resource.ResourceTableDao;
import com.liusy.datapp.model.query.QuerySubject;
import com.liusy.datapp.model.query.SubjectTableRelation;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.query.QuerySubjectService;
import com.liusy.datapp.service.query.SubjectColumnCfgService;
import com.liusy.datapp.service.query.SubjectTableRelationService;
import com.liusy.datapp.service.resource.ResourceTableService;

public class QuerySubjectServiceImpl implements QuerySubjectService {
	private static final long serialVersionUID = 1L;
	private SubjectTableRelationService subjectTableRelationService;
	private SubjectColumnCfgService subjectColumnCfgService;

	public QuerySubject findQuerySubject(Serializable id) throws ServiceException {
		try {
			return querySubjectDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createQuerySubject(QuerySubject querySubject) throws ServiceException {
		try {
			querySubjectDao.save(querySubject);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateQuerySubject(QuerySubject querySubject) throws ServiceException {
		try {
			
			querySubjectDao.update(querySubject);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void removeQuerySubject(Serializable id) throws ServiceException {
		try {
			querySubjectDao.delete(id);
			subjectColumnCfgService.removeSubjectColumnCfgBySubjectId(id.toString());
			subjectTableRelationService.removeSubjectTableRelationBySubjectId(id.toString());
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeQuerySubjects(Serializable[] ids) throws ServiceException {
		try {
			querySubjectDao.deleteAll(ids);
			for(int i=0;i<ids.length;i++){
				subjectColumnCfgService.removeSubjectColumnCfgBySubjectId(ids[i].toString());
				subjectTableRelationService.removeSubjectTableRelationBySubjectId(ids[i].toString());
			}
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryQuerySubject(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery  query=querySubjectDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public QuerySubject findSubjectByCatagoryId(Integer cataId) throws ServiceException{
		try{
			List list = querySubjectDao.findByField(QuerySubject.PROP_SC_ID, cataId);
			if(list!=null && !list.isEmpty())
				return (QuerySubject) list.get(0);
			else
				return null;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	private QuerySubjectDao	querySubjectDao;
	private ResourceTableDao resourceTableDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.QuerySubjectService#setQuerySubjectDao(QuerySubject
	 *      querySubjectDao)
	 */
	
	
	public void setQuerySubjectDao(QuerySubjectDao querySubjectDao) {
		this.querySubjectDao = querySubjectDao;
	}
	public void setResourceTableDao(ResourceTableDao resourceTableDao) {
		this.resourceTableDao = resourceTableDao;
	}

	public List<QuerySubject> findCommSubject() throws ServiceException{
		List<QuerySubject> list;
		try{
			//list=querySubjectDao.findByField(QuerySubject.PROP_SC_ID, null);
			list=querySubjectDao.findByField(QuerySubject.PROP_SC_ID, null,QuerySubject.PROP_ORDE,false);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		return list;
	}
	public QuerySubject findSubjectByCataId(String cataId) throws ServiceException{
		try{
			List list=querySubjectDao.findByField(QuerySubject.PROP_SC_ID, Integer.valueOf(cataId));
			if(list!=null && !list.isEmpty())	
				return (QuerySubject) list.get(0);
			else
				return null;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	public void createSubjectWithSelTable(QuerySubject subject,String[] tabIds) throws ServiceException{
		try{
			//检查编码和名称是否重复
			if(isSubjectCodeExist(subject.getSubCode()))
				throw new ServiceException("新增主题的编码重复!");
			if(isSubjectNameDoubled(subject.getSubName()))
				throw new ServiceException("新增主题名称重复");
			createQuerySubject(subject);
			Integer subjectid=subject.getId();
			//添加主题资源表关联表信息
			for(int i=0;i<tabIds.length;i++){
				if(tabIds[i].startsWith("tab"))
					tabIds[i]=tabIds[i].substring(3,tabIds[i].length());
				
				Integer tableId = Integer.valueOf(tabIds[i]);
				ResourceTable table = resourceTableDao.get(tableId);
				SubjectTableRelation relation=new SubjectTableRelation();
				relation.setDtId(tableId);
				relation.setSubjectId(Integer.valueOf(subjectid));
				relation.setDtOrde(table.getOrde()==null?0:table.getOrde());
				subjectTableRelationService.createSubjectTableRelation(relation);
			}
		}catch (DAOException e) {
			throw new ServiceException(e);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public void updateSubjectWithSelTable(QuerySubject subject,String[] tabIds) throws ServiceException{
		try{
			//判断主题名和编码不能重复
			List<QuerySubject> list =querySubjectDao.findByField(QuerySubject.PROP_SUB_CODE, subject.getSubCode());
			if(list!=null && !list.isEmpty()){
				for(QuerySubject tmpsubject:list){
					if(tmpsubject.getId().intValue()!=subject.getId().intValue())
						throw new ServiceException("主题编码重复");
				}
			}
			updateQuerySubject(subject);
			Integer subjectid=subject.getId();
			//添加主题资源表关联表信息,先删除原有的记录
			subjectTableRelationService.removeSubjectTableRelationBySubjectId(String.valueOf(subjectid));
			for(int i=0;i<tabIds.length;i++){
//				if(tabIds[i].startsWith("tab"))
//					tabIds[i]=tabIds[i].substring(3,tabIds[i].length());
//				SubjectTableRelation relation=new SubjectTableRelation();
//				relation.setDtId(Integer.valueOf(tabIds[i]));
//				relation.setSubjectId(Integer.valueOf(subjectid));
//				subjectTableRelationService.createSubjectTableRelation(relation);

				if(tabIds[i].startsWith("tab"))
					tabIds[i]=tabIds[i].substring(3,tabIds[i].length());
				Integer tableId = Integer.valueOf(tabIds[i]);
				ResourceTable table = resourceTableDao.get(tableId);
				SubjectTableRelation relation=new SubjectTableRelation();
				relation.setDtId(tableId);
				relation.setSubjectId(Integer.valueOf(subjectid));
				relation.setDtOrde(table.getOrde()==null?0:table.getOrde());
				subjectTableRelationService.createSubjectTableRelation(relation);
			}
		}catch (DAOException e) {
			throw new ServiceException(e);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void setSubjectTableRelationService(
			SubjectTableRelationService subjectTableRelationService) {
		this.subjectTableRelationService = subjectTableRelationService;
	}

	public void setSubjectColumnCfgService(
			SubjectColumnCfgService subjectColumnCfgService) {
		this.subjectColumnCfgService = subjectColumnCfgService;
	}
	public boolean isSubjectCodeExist(String code) throws ServiceException{
		try{
		List list=querySubjectDao.findByField(QuerySubject.PROP_SUB_CODE, code);
		if(list!=null && !list.isEmpty())
			return true;
		else
			return false;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	public boolean isSubjectNameDoubled(String subjectName) throws ServiceException{
		try{
			List list=querySubjectDao.findByField(QuerySubject.PROP_SUB_NAME, subjectName.trim());
			if(list!=null && !list.isEmpty())
				return true;
			else
				return false;
		}catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
}

