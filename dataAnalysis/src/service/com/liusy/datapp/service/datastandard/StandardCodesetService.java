package com.liusy.datapp.service.datastandard;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;

import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;

public interface StandardCodesetService {

	public StandardCodeset findStandardCodeset(Serializable id) throws ServiceException;

	public void createStandardCodeset(StandardCodeset standardCodeset) throws ServiceException;

	public void updateStandardCodeset(StandardCodeset standardCodeset) throws ServiceException;

	public void removeStandardCodeset(Serializable id) throws ServiceException;

	public PageQuery queryStandardCodeset(PageQuery pageQuery) throws ServiceException;

	public void removeStandardCodesets(Serializable[] ids) throws ServiceException; 

	public PageQuery getCategorysForTree(PageQuery pagequery) throws ServiceException;
	public void removeStandardCodesetsAndCodes(Serializable[] ids) throws ServiceException;
	public List<StandardCodeset> findAllCodeSet() throws ServiceException;
	public Map<String, List<StandardCode>> findAllBussCode() throws ServiceException;
	public StandardCodeset findCodeSetByCodeNo(String codeNo) throws ServiceException;
}

