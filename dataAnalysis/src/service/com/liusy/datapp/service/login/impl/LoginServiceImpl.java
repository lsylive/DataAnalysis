package com.liusy.datapp.service.login.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.datapp.dao.system.config.SysResourceDao;
import com.liusy.datapp.dao.system.org.SysDeptDao;
import com.liusy.datapp.dao.system.org.SysOrgDao;
import com.liusy.datapp.dao.system.org.SysResourceRoleDao;
import com.liusy.datapp.dao.system.org.SysResourceUserDao;
import com.liusy.datapp.dao.system.org.SysRoleDao;
import com.liusy.datapp.dao.system.org.SysUserDao;
import com.liusy.datapp.dao.system.org.SysUserRoleDao;
import com.liusy.datapp.model.system.config.SysResource;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.model.system.org.SysOrg;
import com.liusy.datapp.model.system.org.SysResourceRole;
import com.liusy.datapp.model.system.org.SysResourceUser;
import com.liusy.datapp.model.system.org.SysRole;
import com.liusy.datapp.model.system.org.SysUser;
import com.liusy.datapp.model.system.org.SysUserRole;
import com.liusy.datapp.service.login.LoginService;

public class LoginServiceImpl implements LoginService {

	public static String			VERIFIED		= "0";
	public static String			NEEDVERIFY	= "1";

	private SysUserDao			sysUserDao;
	private SysDeptDao			sysDeptDao;
	private SysOrgDao				sysOrgDao;
	private SysUserRoleDao		sysUserRoleDao;
	private SysRoleDao			sysRoleDao;
	private SysResourceDao		sysResourceDao;
	private SysResourceRoleDao	sysResourceRoleDao;
	private SysResourceUserDao	sysResourceUserDao;

	public Session login(String accountName, String password, String SSLLogin) throws ServiceException {
		Session session = null;
		//if (accountName == null || password == null) throw new ServiceException("系统错误。");
		if (accountName == null) throw new ServiceException("系统错误。");

		try {
			session = checkAccount(accountName, password, SSLLogin);
			getInfo(session);
			getPrivileges(session);
			return session;
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

	private void getPrivileges(Session session) throws ServiceException {
		Map<String, String> privileges = session.getPrivileges();

		Iterator it = session.getRoles().keySet().iterator();
		if (it == null) return;
		while (it.hasNext()) {
			Integer key = (Integer) it.next();
			List su = sysResourceRoleDao.findByField(SysResourceRole.PROP_ROLE_ID, key);
			if (su != null) {
				for (int i = 0; i < su.size(); i++) {
					SysResourceRole srr = (SysResourceRole) su.get(i);
					if(!VERIFIED.equals(srr.getStatus())) continue;
					if (!privileges.containsKey(srr.getResId())) {
						SysResource r = sysResourceDao.get(srr.getResId());
						if (r!=null) {
							privileges.put(r.getResCode(),  r.getActionUrl() == null ? "" : r.getActionUrl());
						}
						
					}
				}
			}
		}
	}

	private void getInfo(Session session) throws ServiceException {
		SysOrg sysOrg = sysOrgDao.get(Integer.parseInt(session.getOrgId()));
		SysDept sysDept = sysDeptDao.get(Integer.parseInt(session.getDeptId()));
		if (sysOrg == null) throw new ServiceException("找不到用户所在单位。");
		if (sysDept == null) throw new ServiceException("找不到用户所在部门。");

		session.setOrgName(sysOrg.getName());
		session.setOrgShortName(sysOrg.getOrgAbbr());
		session.setOrgCode(sysOrg.getOrgCode());
		session.setDeptName(sysDept.getDeptName());
		session.setDeptShortName(sysDept.getDeptAbbr());
		session.setDeptNumber(sysDept.getDeptCode());

		List roles = sysUserRoleDao.findByField(SysUserRole.PROP_USER_ID, Integer.valueOf(session.getUserId()));
		if (roles != null) {
			for (int i = 0; i < roles.size(); i++) {
				SysUserRole sur = (SysUserRole) roles.get(i);
				if (sur != null && VERIFIED.equals(sur.getStatus())) {
					SysRole r = sysRoleDao.get(sur.getRoleId());
					if (r != null && Const.LOGIN_ACTIVE.equals(r.getRoleStatus())) session.getRoles().put(sur.getRoleId(), r.getRoleName());
				}
			}
		}

		List rights = sysResourceUserDao.findByField(SysResourceUser.PROP_USER_ID, Integer.valueOf(session.getUserId()));
		if (rights != null) {
			for (int i = 0; i < rights.size(); i++) {
				SysResourceUser sur = (SysResourceUser) rights.get(i);
				if (sur != null && VERIFIED.equals(sur.getStatus())) {
					SysResource r = sysResourceDao.get(sur.getResId());
					if (r != null) session.getPrivileges().put(r.getResCode(), r.getActionUrl() == null ? "" : r.getActionUrl());
				}
			}
		}
	}

	private Session checkAccount(String accountName, String password, String SSLLogin) throws ServiceException {
		Session session = null;
		List users = sysUserDao.findByField(SysUser.PROP_USER_ACCOUNT, accountName);
		if (users == null || users.size() == 0) throw new ServiceException("用户帐号有误。");
		if (users.size() > 1) throw new ServiceException("系统错误。");

		SysUser user = (SysUser) users.get(0);
		if (!Const.LOGIN_ACTIVE.equals(user.getUserStatus())) throw new ServiceException("用户已被停用，请与系统管理员联系。");
		if ((SSLLogin==null || SSLLogin.length()==0) && !password.equals(user.getUserPassword())) throw new ServiceException("用户密码有误。");
		if (user.getOrgId() == null) throw new ServiceException("找不到用户所在单位，请与系统管理员联系。");
		if (user.getDeptId() == null) throw new ServiceException("找不到用户所在部门，请与系统管理员联系。");

		session = new Session();
		session.setUserId(user.getId().toString());
		session.setAccountName(user.getUserAccount());
		session.setUserName(user.getUserName());
		session.setSecurityLevel(user.getAdminLevel());
		session.setAdminLevel(user.getAdminLevel());
		session.setLoginTime(new Date());
		session.setOrgId(user.getOrgId().toString());
		session.setDeptId(user.getDeptId().toString());

		return session;
	}

	public SysDeptDao getSysDeptDao() {
		return sysDeptDao;
	}

	public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}

	public SysOrgDao getSysOrgDao() {
		return sysOrgDao;
	}

	public void setSysOrgDao(SysOrgDao sysOrgDao) {
		this.sysOrgDao = sysOrgDao;
	}

	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public SysUserRoleDao getSysUserRoleDao() {
		return sysUserRoleDao;
	}

	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	public SysResourceDao getSysResourceDao() {
		return sysResourceDao;
	}

	public void setSysResourceDao(SysResourceDao sysResourceDao) {
		this.sysResourceDao = sysResourceDao;
	}

	public SysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

	public SysResourceUserDao getSysResourceUserDao() {
		return sysResourceUserDao;
	}

	public void setSysResourceUserDao(SysResourceUserDao sysResourceUserDao) {
		this.sysResourceUserDao = sysResourceUserDao;
	}

	public SysResourceRoleDao getSysResourceRoleDao() {
		return sysResourceRoleDao;
	}

	public void setSysResourceRoleDao(SysResourceRoleDao sysResourceRoleDao) {
		this.sysResourceRoleDao = sysResourceRoleDao;
	}


}
