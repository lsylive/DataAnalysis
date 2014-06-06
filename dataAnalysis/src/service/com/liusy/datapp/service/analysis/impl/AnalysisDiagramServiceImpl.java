package com.liusy.datapp.service.analysis.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.analysis.AnalysisDiagramDao;
import com.liusy.datapp.model.analysis.AnalysisDiagram;
import com.liusy.datapp.service.analysis.AnalysisDiagramService;

public class AnalysisDiagramServiceImpl implements AnalysisDiagramService {
	private static final long	serialVersionUID	= 1L;

	public AnalysisDiagram findAnalysisDiagram(Serializable id) throws ServiceException {
		try {
			return analysisDiagramDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createAnalysisDiagram(AnalysisDiagram analysisDiagram) throws ServiceException {
		try {
			analysisDiagramDao.save(analysisDiagram);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateAnalysisDiagram(AnalysisDiagram analysisDiagram) throws ServiceException {
		try {
			AnalysisDiagram tmp = analysisDiagramDao.get(analysisDiagram.getId());
			ConvertUtil.convertToModelForUpdate(tmp, analysisDiagram);
			analysisDiagramDao.update(tmp);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDiagram(Serializable id) throws ServiceException {
		try {
			analysisDiagramDao.delete(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void removeAnalysisDiagrams(Serializable[] ids) throws ServiceException {
		try {
			analysisDiagramDao.deleteAll(ids);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public PageQuery queryAnalysisDiagram(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery query = analysisDiagramDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public List<AnalysisDiagram> queryDiagramByType(String id) throws ServiceException {
		try {
			List list = analysisDiagramDao.findByField(AnalysisDiagram.PROP_TYPE, id,AnalysisDiagram.PROP_NAME,true);
			return list;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private AnalysisDiagramDao	analysisDiagramDao;

	/**
	 * ×¢ÈëDAO
	 * 
	 * @see com.liusy.core.service.AnalysisDiagramService#setAnalysisDiagramDao(AnalysisDiagram
	 *      analysisDiagramDao)
	 */
	public void setAnalysisDiagramDao(AnalysisDiagramDao analysisDiagramDao) {
		this.analysisDiagramDao = analysisDiagramDao;
	}
}
