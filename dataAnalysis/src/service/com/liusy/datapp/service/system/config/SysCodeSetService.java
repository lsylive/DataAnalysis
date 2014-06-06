package com.liusy.datapp.service.system.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.code.Code;
import com.liusy.datapp.model.system.config.SysCodeSet;

public interface SysCodeSetService {
	/**
	 * ��PK���Ҷ���
	 */
	public SysCodeSet findSysCodeSet(Serializable id) throws ServiceException;

	/**
	 * ��Unique Key���Ҷ���
	 */
	public SysCodeSet findSysCodeSetByUK(String ukField, Object ukValue) throws ServiceException;

	/**
	 * ��������
	 * 
	 * @param
	 */
	public void createSysCodeSet(SysCodeSet sysCodeSet) throws ServiceException;

	/**
	 * ���¶���
	 * 
	 * @param
	 */
	public void updateSysCodeSet(SysCodeSet sysCodeSet) throws ServiceException;

	/**
	 * ɾ������
	 * 
	 * @param clazz
	 *           model class to lookup
	 * @param id
	 *           the identifier (primary key) of the class
	 */
	public void removeSysCodeSet(Serializable id) throws ServiceException;

	/**
	 * ��ɾ������
	 * 
	 * @param id
	 *           the identifier (primary key) of the class
	 */
	public void removeSysCodeSets(Serializable[] ids) throws ServiceException;

	/**
	 * @param fieldName
	 *           �ֶ���
	 * @param fieldValue
	 *           �ֶ�ֵ,������봫���ֶ���Ӧ������,��Long������String
	 */
	public void removeSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException;

	/**
	 * @return ����ȫ������� Collection<SysCodeSet>
	 * @throws Exception
	 */
	public Collection findAllSysCodeSet() throws ServiceException;

	/**
	 * @param fieldName
	 *           �ֶ���
	 * @param fieldValue
	 *           �ֶ�ֵ,������봫���ֶ���Ӧ������,��Long������String
	 */
	public Collection findSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException;

	/**
	 * �����¼��
	 * 
	 * @param fieldName
	 *           �ֶ���
	 * @param fieldValue
	 *           �ֶ�ֵ,������봫���ֶ���Ӧ������,��Long������String
	 * @return
	 */
	public int countSysCodeSetByField(String fieldName, Object fieldValue) throws ServiceException;
	/**
	 * ��ҳ��̬��ѯ
	 * 
	 * @param queryObject
	 *           (queryObject.getResult()<Map(fieldName, fieldValue)>)
	 * @return
	 */
	// public PagerObject querySysCodeSet(QueryString queryString,PagerObject
	// queryObject) throws ServiceException ;

	/**
	 * @return ����ȫ�������
	 * @throws Exception
	 */
	public Map<String, List<Code>> findAllCode() throws ServiceException;
}
