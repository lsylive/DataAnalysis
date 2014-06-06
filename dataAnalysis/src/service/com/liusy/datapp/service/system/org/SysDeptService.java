package com.liusy.datapp.service.system.org;

import java.io.Serializable;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.system.org.SysDept;

public interface SysDeptService {

	public SysDept findSysDept(Serializable id) throws ServiceException;

	public PageQuery querySysDept(PageQuery pageQuery) throws ServiceException;
	
	public List<SysDept> findSysDepts(Serializable orgId) throws ServiceException;
	
	public List<SysDept> findAllDept()throws ServiceException;
	
}
