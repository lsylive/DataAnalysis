package com.liusy.datapp.service.resource;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.dom4j.Element;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.datastandard.StandardDataMeta;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.resource.ResourceColumn;

public interface ResourceColumnService {

	public ResourceColumn findResourceColumn(Serializable id) throws ServiceException;

	public void createResourceColumn(ResourceColumn resourceColumn) throws ServiceException;

	public void updateResourceColumn(ResourceColumn resourceColumn) throws ServiceException;

	public void removeResourceColumn(Serializable id) throws ServiceException;

	public PageQuery queryResourceColumn(PageQuery pageQuery) throws ServiceException;

	public void removeResourceColumns(Serializable[] ids) throws ServiceException;  
	
	public List<StandardDataMeta> getDataMetaCode(Collection<ICondition> conditions) throws  ServiceException;
	
	public List<StandardCodeset> getCodesetCode(Collection<ICondition> conditions) throws ServiceException;
	
	public boolean haveRight(String cityCode, Integer tableId) throws ServiceException;
	
	public Integer getIndicatorIdByDataMete(Integer dataMeteId) throws ServiceException;
	public List<ResourceColumn> findColumnByTableIdSort(String tableId) throws ServiceException;
	public ResourceColumn findResoucrePKColumn(Integer tableId) throws ServiceException;
	
	public List<StandardIndicator> getIndicators(Collection<ICondition> conditions)throws ServiceException;
	
	public StandardDataMeta getDataMetaById(Integer dataMetaId)throws ServiceException;
	
	public String getAllDataMateForTree()throws ServiceException;
	
	public int getMaxOrderFromColumn(Serializable tableId)throws ServiceException;
	
	public void moveUp(Serializable id)throws ServiceException;
	
	public void moveDown(Serializable id)throws ServiceException;
	
	public Integer getMaxOrderFromCFG(Serializable tableId)throws ServiceException;
	
}

