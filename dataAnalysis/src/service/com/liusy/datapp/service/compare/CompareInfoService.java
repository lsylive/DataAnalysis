package com.liusy.datapp.service.compare;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.BaseSqlGen;
import com.liusy.dataapplatform.base.util.PagerObject;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.dao.compare.CompareInfoDao;
import com.liusy.datapp.model.compare.CompareFilter;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;

public interface CompareInfoService {

	public CompareInfo findCompareInfo(Serializable id) throws ServiceException;

	public void createCompareInfo(CompareInfo compareInfo) throws ServiceException;

	public void updateCompareInfo(CompareInfo compareInfo) throws ServiceException;

	public void removeCompareInfo(Serializable id) throws ServiceException;

	public PageQuery queryCompareInfo(PageQuery pageQuery) throws ServiceException;

	public void removeCompareInfos(Serializable[] ids) throws ServiceException;
	
	public CompareInfo getCompareInfoByRunId(Integer runId) throws ServiceException;
	
	public void startCompareInfo(final Serializable[] ids) throws ServiceException;
	public void stopCompareInfo(final Serializable[] ids) throws ServiceException;
	public List<CompareInfo> getMustStartCompare() throws ServiceException;
	public void startRunCompareInfo(final Object ids) throws ServiceException;
	public void stopRunCompareInfo(final Object ids) throws ServiceException;
	//public List<Map<String,String>> getQueryResult(int tableId,List<String> pkidList,String[] fieldList) throws ServiceException;
	public String getCompareQuerySql(Map<String,String> tablepoolMap,List<ColumnPoolObj> columnpoolList,String[] mainFieldArray,ResourceColumn pkobj,Map<String,List<String>> columnIndMap,Collection<CompareFilter> filters) throws Exception;
	public List<Map<String,String>> queryDataBaseForCompare(boolean isPersonalTable,String[] fieldArray,List<String> queryIdList, Map<String, List<String>> columnIndMap,String sqlstr,PageQuery pageQuery);
	
	public boolean hasRunFinished(Serializable id) throws ServiceException;
	
	public List<CompareInfo> findAll() throws ServiceException;
}

