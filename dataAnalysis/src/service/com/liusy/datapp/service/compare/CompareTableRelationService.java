package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareTableRelationDao;
import com.liusy.datapp.model.compare.CompareTableRelation;

public interface CompareTableRelationService {

	public CompareTableRelation findCompareTableRelation(Serializable id) throws ServiceException;

	public void createCompareTableRelation(CompareTableRelation compareTableRelation) throws ServiceException;

	public void updateCompareTableRelation(CompareTableRelation compareTableRelation) throws ServiceException;

	public void removeCompareTableRelation(Serializable id) throws ServiceException;
	
	public void removeCompareTableRelationByCompareId(Serializable id) throws ServiceException;

	public PageQuery queryCompareTableRelation(PageQuery pageQuery) throws ServiceException;
	
	public CompareTableRelation getCompareTableRelationByCompIdAndDtId(Integer compId,Integer dtId) throws ServiceException;

	public void removeCompareTableRelations(Serializable[] ids) throws ServiceException;
	public Collection findByFields(String fieldName,Object fieldValue) throws ServiceException;
	public void modifyCompareTableIndicator(Integer compId,String mainTabId,String slaveTabIds,String indIds) throws ServiceException;
}

