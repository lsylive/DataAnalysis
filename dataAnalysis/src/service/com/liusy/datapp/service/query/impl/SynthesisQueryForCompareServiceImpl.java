package com.liusy.datapp.service.query.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.query.SynthesisQueryDao;
import com.liusy.datapp.dao.query.SynthesisQueryForCompareDao;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.query.SynthesisQueryForCompareService;
import com.liusy.datapp.service.query.SynthesisQueryService;

public class SynthesisQueryForCompareServiceImpl implements SynthesisQueryForCompareService {
	private SynthesisQueryForCompareDao	synthesisQueryForCompareDao;
	private TableConfigPool		tableConfigPool;
	private Map						specialTables;
	private Map						adminLevels;

	public PageQuery queryBySql(String sqlstr, PageQuery pageQuery) throws ServiceException {
		try {
			return synthesisQueryForCompareDao.queryBySql(sqlstr, pageQuery);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public PageQuery queryBySqlAndPage(String sqlstr, PageQuery pageQuery) throws ServiceException {
		try {
			int minId =getMinId(sqlstr);
			return synthesisQueryForCompareDao.queryBySqlAndPage(sqlstr, pageQuery,minId);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}

	}

	public int queryCountBySql(String querySQL) {
		int count = 0;
		try {
			count = synthesisQueryForCompareDao.queryCountBySql(querySQL);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int queryByInt(String querySQL) {
		int count = 0;
		try {
			count = synthesisQueryForCompareDao.queryByInt(querySQL);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}



	public List queryBySql(String sqlstr) throws ServiceException {
		try {
			return synthesisQueryForCompareDao.queryBySql(sqlstr);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public Map getSpecialTables() {
		return specialTables;
	}

	public void setSpecialTables(Map specialTables) {
		this.specialTables = specialTables;
	}

	public void setTableConfigPool(TableConfigPool tableConfigPool) {
		this.tableConfigPool = tableConfigPool;
	}
	
	public Map getAdminLevels() {
		return adminLevels;
	}

	public void setAdminLevels(Map adminLevels) {
		this.adminLevels = adminLevels;
	}

	public boolean checkUserTableSecurityLevel(String tableLevel, Session user) throws ServiceException {

		String userLevel = user.getSecurityLevel();
		if (userLevel == null) {
			userLevel = "";
		}
		if (tableLevel == null) {
			tableLevel = "";
		}

		if (tableLevel.equals("")) {
			return true;
		}
		else if (userLevel.equals("")) {
			return false;
		}
		else {
			userLevel = (String)getAdminLevels().get(userLevel);
			if (userLevel.compareToIgnoreCase(tableLevel) <= 0){
				return true;
			}
		}

		return false;

	}
	
	public int getMinId(String sqlStr) throws ServiceException {
		try {
			return synthesisQueryForCompareDao.getMinId(sqlStr);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void setSynthesisQueryForCompareDao(
			SynthesisQueryForCompareDao synthesisQueryForCompareDao) {
		this.synthesisQueryForCompareDao = synthesisQueryForCompareDao;
	}

}
