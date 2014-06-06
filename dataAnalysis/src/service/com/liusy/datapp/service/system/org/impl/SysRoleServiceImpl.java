package com.liusy.datapp.service.system.org.impl;

import java.io.Serializable;
import java.util.List;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.system.org.SysResourceRoleDao;
import com.liusy.datapp.dao.system.org.SysRoleDao;
import com.liusy.datapp.model.system.org.SysResourceRole;
import com.liusy.datapp.model.system.org.SysRole;
import com.liusy.datapp.model.system.org.SysUser;
import com.liusy.datapp.service.system.org.SysRoleService;

public class SysRoleServiceImpl implements SysRoleService {
	private static final long	serialVersionUID	= 1L;

	public SysRole findSysRole(Serializable id) throws ServiceException {
		try {
			return sysRoleDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysRole(SysRole sysRole) throws ServiceException {
		try {
			List list = sysRoleDao.findByField(SysRole.PROP_ROLE_NAME, sysRole.getRoleName());
			if (list != null && list.size() > 0) {
				throw new ServiceException("角色名:[" + sysRole.getRoleName() + "]已经存在，不能使用。");
			}

			sysRoleDao.save(sysRole);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	public void updateSysRole(SysRole sysRole) throws ServiceException {
		try {
			List list = sysRoleDao.findByField(SysRole.PROP_ROLE_NAME, sysRole.getRoleName());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysRole role = (SysRole) list.get(i);
					if (sysRole.getId().intValue() != role.getId().intValue()) {
						throw new ServiceException("角色名:[" + sysRole.getRoleName() + "]存在重复，不能使用。");
					}
				}
			}

			Integer roleId = sysRole.getId();
			SysRole tmp = sysRoleDao.get(roleId);
			String state = tmp.getRoleStatus();
			String uName = tmp.getRoleName();

			ConvertUtil.convertToModelForUpdate(tmp, sysRole);

			if (SYSROLE.equals(tmp.getRoleType())) {
				tmp.setRoleStatus(state);
				tmp.setRoleName(uName);
			}
			sysRoleDao.update(tmp);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException("数据库操作错误。");

		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	public void removeSysRole(Serializable id) throws ServiceException {
		try {
			SysRole tmp = sysRoleDao.get(id);

			if (SYSROLE.equals(tmp.getRoleType())) {
				throw new ServiceException("该角色不能删除。");
			}
			sysRoleDao.delete(tmp);
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库操作错误。");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	public void removeSysRoles(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				SysRole tmp = sysRoleDao.get(ids[i]);
				if (SYSROLE.equals(tmp.getRoleType())) {
					throw new ServiceException("系统角色不能删除。");
				}
			}
			sysRoleDao.deleteAll(ids);
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库操作错误。");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	public PageQuery querySysRole(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery query = sysRoleDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query.getRecordSet());
			pageQuery.setRecordCount(query.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public void startSysRoles(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				SysRole tmp = sysRoleDao.get(ids[i]);
				if (tmp != null) {
					tmp.setRoleStatus("1");
					sysRoleDao.update(tmp);
				}
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库操作错误。");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}
	
	public void stopSysRoles(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				SysRole tmp = sysRoleDao.get(ids[i]);
				if (tmp != null&& !SYSROLE.equals(tmp.getRoleType())) { // 系统固定的用户不能暂停。
					tmp.setRoleStatus("0");
					sysRoleDao.update(tmp);
				}
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库操作错误。");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	public void saveVerify(Integer id) throws ServiceException {
		try {
			SysRole sysRole = sysRoleDao.get(id);
			if (sysRole == null) throw new ServiceException("该角色不存在。");
			List roleRights = sysResourceRoleDao.findByField(SysResourceRole.PROP_ROLE_ID, id);
			for (int i = 0; i < roleRights.size(); i++) {
				SysResourceRole sru = (SysResourceRole) roleRights.get(i);
				if (Const.SYS_NOTVERIFY.equals(sru.getStatus())) {
					if (Const.SYS_OPER_DELETE.equals(sru.getOper())) {
						sysResourceRoleDao.delete(sru.getId());
					}
					else {
						sru.setStatus(Const.SYS_VERIFIED);
						sru.setOper(Const.SYS_OPER_NONE);
						sysResourceRoleDao.update(sru);
					}
				}
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库操作错误。");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	public void saveRights(Integer id, String rights) throws ServiceException {
		String tmp = "," + rights + ",";
		try {
			SysRole sysRole = sysRoleDao.get(id);
			if (sysRole == null) throw new ServiceException("该角色不存在。");
			List userRights = sysResourceRoleDao.findByField(SysResourceRole.PROP_ROLE_ID, id);
			for (int i = 0; i < userRights.size(); i++) {
				SysResourceRole sru = (SysResourceRole) userRights.get(i);
				if (tmp.indexOf("," + sru.getResId().toString() + ",") < 0) {
					// 已审核的权限，将进入待审核、被删除状态
					if (sru.getStatus().equals(Const.SYS_VERIFIED)) {
						sru.setStatus(Const.SYS_NOTVERIFY);
						sru.setOper(Const.SYS_OPER_DELETE);
						sysResourceRoleDao.update(sru);
					}
					else {
						// 待审核的权限、新增加状态，将被取消
						if (sru.getOper() != null && sru.getOper().equals(Const.SYS_OPER_ADD)) {
							sysResourceRoleDao.delete(sru.getId());
						}
					}
				}
				else {
					// 待审核状态
					if (sru.getStatus().equals(Const.SYS_NOTVERIFY)) {
						// 删除操作，将被取消
						if (sru.getOper() != null && sru.getOper().equals(Const.SYS_OPER_DELETE)) {
							sru.setStatus(Const.SYS_VERIFIED);
							sru.setOper(Const.SYS_OPER_NONE);
							sysResourceRoleDao.update(sru);
						}

					}
					// 已审核状态
					tmp = tmp.replace("," + sru.getResId().toString() + ",", ",");// 放弃没有变化的权限
				}
			}
			String[] newRights = tmp.split(",");
			for (int i = 0; i < newRights.length; i++) {
				String r = newRights[i];
				if (r == null || r.equals("")) continue;
				SysResourceRole sru = new SysResourceRole();// 增加权限
				sru.setRoleId(id);
				sru.setStatus(Const.SYS_VERIFIED);// 待审核//2011/4/6日已经改为“不需要审核”
				sru.setOper(Const.SYS_OPER_ADD);// 设置增加操作
				sru.setResId(new Integer(r));
				sysResourceRoleDao.save(sru);
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("数据库操作错误。");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统错误。");
		}
	}

	private SysRoleDao			sysRoleDao;

	private SysResourceRoleDao	sysResourceRoleDao;

	/**
	 * 注入DAO
	 * 
	 * @see com.liusy.core.service.SysRoleService#setSysRoleDao(SysRole
	 *      sysRoleDao)
	 */
	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	public void setSysResourceRoleDao(SysResourceRoleDao sysResourceRoleDao) {
		this.sysResourceRoleDao = sysResourceRoleDao;
	}
}
