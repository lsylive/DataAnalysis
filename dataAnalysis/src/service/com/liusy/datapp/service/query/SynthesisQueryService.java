package com.liusy.datapp.service.query;

import java.util.List;
import java.util.Map;

import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;

public interface SynthesisQueryService {
	public PageQuery queryBySql(String sqlstr, PageQuery pageQuery) throws ServiceException;

	public List queryBySql(String sqlstr) throws ServiceException;

	public int queryCountBySql(String querySQL);

	public int queryByInt(String querySQL);
	
	public Map getSpecialTables();
	
	public PageQuery queryBySqlAndPage(String sqlstr, PageQuery pageQuery) throws ServiceException;
	
	public int getMinId(String sqlStr) throws ServiceException;
	
	public boolean checkUserTableSecurityLevel(String tableLevel,Session user) throws ServiceException;

}
