package com.liusy.datapp.service.query;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.query.SubjectColumnCfg;

public interface SubjectColumnCfgService {

	public SubjectColumnCfg findSubjectColumnCfg(Serializable id) throws ServiceException;

	public void createSubjectColumnCfg(SubjectColumnCfg subjectColumnCfg) throws ServiceException;

	public void updateSubjectColumnCfg(SubjectColumnCfg subjectColumnCfg) throws ServiceException;

	public void removeSubjectColumnCfg(Serializable id) throws ServiceException;

	public PageQuery querySubjectColumnCfg(PageQuery pageQuery) throws ServiceException;

	public void removeSubjectColumnCfgs(Serializable[] ids) throws ServiceException;
	public void removeSubjectColumnCfgBySubjectId(String subjectId) throws ServiceException;
	public List<SubjectColumnCfg> findAllConfigByOrder(String subjectId) throws ServiceException;
	public List<SubjectColumnCfg> findAllConfigByOrderForBatch(String subjectId) throws ServiceException;
}

