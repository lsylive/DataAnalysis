package com.liusy.datapp.service.system.org;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.org.SysUser;

public interface SysUserService {
	
	public static String	SYSUSER	= "1";

	public SysUser findSysUser(Serializable id) throws ServiceException;

	public void createSysUser(SysUser sysUser,String roleIds) throws ServiceException;

	public void updateSysUser(SysUser sysUser,String roleIds) throws ServiceException;

	public void removeSysUser(Serializable id) throws ServiceException;

	public PageQuery querySysUser(PageQuery pageQuery) throws ServiceException;

	public void removeSysUsers(Serializable[] ids) throws ServiceException;

	public SysUser findSysUserByUK(String ukField, Object ukValue) throws ServiceException;

	public List<SysUser> findAllSysUser() throws ServiceException;

	public Collection findSysUserByField(String fieldName, Object fieldValue) throws ServiceException;

	public int countSysUserByField(String fieldName, Object fieldValue) throws ServiceException;

	/** ҵ�񷽷� * */
	public SysUser validateLogin(String userName, String password) throws ServiceException;

	public void findSysUserByPage(PageQuery pageQuery) throws ServiceException;

	// ����һ���ʺ�
	public void startSysUsers(Serializable[] ids) throws ServiceException;

	// ��ͣһ���ʺ�
	public void stopSysUsers(Serializable[] ids) throws ServiceException;

	// ��������
	public void savePwd(Serializable id, String pwd) throws ServiceException;

	// ����Ȩ��
	public void saveRights(Integer id, String rights) throws ServiceException;

	// ������˽��
	public void saveVerify(Integer id) throws ServiceException;

	// ��ѯ�û���ɫ
	public PageQuery findUserRoles(PageQuery pageQuery) throws ServiceException;
	
	// ��ѯ����δ������˿ռ���û�
	public List<SysUser> findAllUnAllocPersonalUsers() throws ServiceException;
	
	//�õ���Դ��
	public String getResourceTreeStr(String treeId,String cityCode)throws ServiceException;
	
	//���ݱ�id�õ�����Ϣ
	public ResourceTable getTableById(Serializable tableId)throws ServiceException;
	
	//���ݱ�id�õ�����ֶ��б�
	public List<ResourceColumn> getColumnsByTableId(Serializable tableId)throws ServiceException;
	
	public PageQuery findAviableAdminUser() throws ServiceException;
	
	public int maxOrder()throws ServiceException;
	
	public List<SysUser> findByAdminLevel(String adminLevel,String dept)throws ServiceException;
	
}
