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

	/** 业务方法 * */
	public SysUser validateLogin(String userName, String password) throws ServiceException;

	public void findSysUserByPage(PageQuery pageQuery) throws ServiceException;

	// 启用一批帐号
	public void startSysUsers(Serializable[] ids) throws ServiceException;

	// 暂停一批帐号
	public void stopSysUsers(Serializable[] ids) throws ServiceException;

	// 保存密码
	public void savePwd(Serializable id, String pwd) throws ServiceException;

	// 保存权限
	public void saveRights(Integer id, String rights) throws ServiceException;

	// 保存审核结果
	public void saveVerify(Integer id) throws ServiceException;

	// 查询用户角色
	public PageQuery findUserRoles(PageQuery pageQuery) throws ServiceException;
	
	// 查询所有未分配个人空间的用户
	public List<SysUser> findAllUnAllocPersonalUsers() throws ServiceException;
	
	//得到资源树
	public String getResourceTreeStr(String treeId,String cityCode)throws ServiceException;
	
	//根据表id得到表信息
	public ResourceTable getTableById(Serializable tableId)throws ServiceException;
	
	//根据表id得到表的字段列表
	public List<ResourceColumn> getColumnsByTableId(Serializable tableId)throws ServiceException;
	
	public PageQuery findAviableAdminUser() throws ServiceException;
	
	public int maxOrder()throws ServiceException;
	
	public List<SysUser> findByAdminLevel(String adminLevel,String dept)throws ServiceException;
	
}
