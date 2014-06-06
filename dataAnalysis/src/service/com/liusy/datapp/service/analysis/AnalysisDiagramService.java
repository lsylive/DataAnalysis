package com.liusy.datapp.service.analysis;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.analysis.AnalysisDiagram;

public interface AnalysisDiagramService {

	public AnalysisDiagram findAnalysisDiagram(Serializable id) throws ServiceException;

	public void createAnalysisDiagram(AnalysisDiagram analysisDiagram) throws ServiceException;

	public void updateAnalysisDiagram(AnalysisDiagram analysisDiagram) throws ServiceException;

	public void removeAnalysisDiagram(Serializable id) throws ServiceException;

	public PageQuery queryAnalysisDiagram(PageQuery pageQuery) throws ServiceException;

	public List<AnalysisDiagram> queryDiagramByType(String id) throws ServiceException;

	public void removeAnalysisDiagrams(Serializable[] ids) throws ServiceException;  
}

