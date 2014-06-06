package com.liusy.datapp.service.system.org.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.datapp.dao.system.org.SysDeptDao;
import com.liusy.datapp.dao.system.org.SysOrgDao;
import com.liusy.datapp.model.system.org.SysDept;
import com.liusy.datapp.model.system.org.SysOrg;
import com.liusy.datapp.service.system.org.SysOrgService;

public class SysOrgServiceImpl implements SysOrgService {
	private static final long	serialVersionUID	= 1L;

	public SysOrg findSysOrg(Serializable id) throws ServiceException {

		try {
			SysOrg tmp=sysOrgDao.get(id);
			
			return tmp;
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
	}

	public void createSysOrg(SysOrg sysOrg) throws ServiceException {
		try {
			List list = sysOrgDao.findByField(SysOrg.PROP_ORG_NAME, sysOrg.getName());
			if (list != null && list.size() > 0) {
				throw new ServiceException("单位名:[" + sysOrg.getName() + "]已经存在，不能使用。");
			}

			list = sysOrgDao.findByField(SysOrg.PROP_ORG_CODE, sysOrg.getOrgCode());
			if (list != null && list.size() > 0) {
				throw new ServiceException("单位编号:[" + sysOrg.getOrgCode() + "]已经存在，不能使用。");
			}

			sysOrgDao.save(sysOrg);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
	}

	public void updateSysOrg(SysOrg sysOrg) throws ServiceException {
		try {
			List list = sysOrgDao.findByField(SysOrg.PROP_ORG_NAME, sysOrg.getName());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysOrg org = (SysOrg) list.get(i);
					if (sysOrg.getId().intValue() != org.getId().intValue()) {
						throw new ServiceException("单位名:[" + sysOrg.getName() + "]存在重复，不能使用。");
					}
				}
			}
			list = sysOrgDao.findByField(SysOrg.PROP_ORG_CODE, sysOrg.getOrgCode());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysOrg org = (SysOrg) list.get(i);
					if (sysOrg.getId().intValue() != org.getId().intValue()) {
						throw new ServiceException("单位编号:[" + sysOrg.getOrgCode() + "]存在重复，不能使用。");
					}
				}
			}

			SysOrg tmp = sysOrgDao.get(sysOrg.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysOrg);
			sysOrgDao.update(tmp);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void removeSysOrg(Serializable id) throws ServiceException {
		try {
			SysOrg tmp = sysOrgDao.get(id);
			if (tmp == null) return;
			if (SYSORG.equals(tmp.getOrgType())) {
				throw new ServiceException("该单位不能删除。");// 系统顶层单位不能删除。
			}

			sysDeptDao.deleteByField(SysDept.PROP_ORG_ID, tmp.getId());
			sysOrgDao.deleteByField(SysOrg.PROP_UP_ORG_ID, tmp.getId());// 级联删除尚未完成。
			sysOrgDao.delete(tmp.getId());
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void removeSysOrgs(Serializable[] ids) throws ServiceException {
		return;
	}

	public List findSysOrgAll() throws ServiceException {
		try {
			List list = sysOrgDao.findAll();
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public SysDept findSysDept(Serializable id) throws ServiceException {
		try {
			return sysDeptDao.get(id);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
	}

	public void createSysDept(SysDept sysDept) throws ServiceException {
		try {
			Integer orgId = sysDept.getOrgId();
			if (orgId == null) throw new ServiceException("找不到该部门所在单位。");

			List list = sysDeptDao.findByField(SysDept.PROP_DEPT_NAME, sysDept.getDeptName());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysDept dept = (SysDept) list.get(i);
					if (sysDept.getOrgId().intValue() == dept.getOrgId().intValue()) {
						throw new ServiceException("部门名:[" + sysDept.getDeptName() + "]存在重复，不能使用。");
					}
				}
			}
			list = sysDeptDao.findByField(SysDept.PROP_DEPT_CODE, sysDept.getDeptCode());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysDept dept = (SysDept) list.get(i);
					if (sysDept.getOrgId().intValue() == dept.getOrgId().intValue()) {
						throw new ServiceException("部门编号:[" + sysDept.getDeptName() + "]存在重复，不能使用。");
					}
				}
			}

			sysDeptDao.save(sysDept);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void updateSysDept(SysDept sysDept) throws ServiceException {
		try {
			Integer orgId = sysDept.getOrgId();
			if (orgId == null) throw new ServiceException("找不到该部门所在单位。");

			List list = sysDeptDao.findByField(SysDept.PROP_DEPT_NAME, sysDept.getDeptName());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysDept dept = (SysDept) list.get(i);
					if (sysDept.getId().intValue() != dept.getId().intValue() && sysDept.getOrgId().intValue() == dept.getOrgId().intValue()) {
						throw new ServiceException("部门名:[" + sysDept.getDeptName() + "]存在重复，不能使用。");
					}
				}
			}
			list = sysDeptDao.findByField(SysDept.PROP_DEPT_CODE, sysDept.getDeptCode());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysDept dept = (SysDept) list.get(i);
					if (sysDept.getId().intValue() != dept.getId().intValue() && sysDept.getOrgId().intValue() == dept.getOrgId().intValue()) {
						throw new ServiceException("部门编号:[" + sysDept.getDeptName() + "]存在重复，不能使用。");
					}
				}
			}

			SysDept tmp = sysDeptDao.get(sysDept.getId());
			ConvertUtil.convertToModelForUpdate(tmp, sysDept);
			sysDeptDao.update(tmp);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public void removeSysDept(Serializable id) throws ServiceException {
		try {
			SysDept tmp = sysDeptDao.get(id);
			if (tmp == null) return;

			sysDeptDao.deleteByField(SysDept.PROP_UP_DEPT_ID, tmp.getId().toString());// 级联操作尚未实现。
			sysDeptDao.delete(id);
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException(de);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List<SysDept> findSysDepts(Serializable orgId) throws ServiceException {
		List<SysDept> list;
		try {
			list = sysDeptDao.findByField(SysDept.PROP_ORG_ID, new Integer(orgId.toString()));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return list;
	}

	public List findSysDeptAll() throws ServiceException {
		try {
			List list = sysDeptDao.findAll();
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	public List findOrgs() throws ServiceException {
		return null;
	}

	public List findChildOrgs(Serializable orgId) throws ServiceException {
		if (orgId != null) return sysOrgDao.findByField(SysOrg.PROP_UP_ORG_ID, new Integer(orgId.toString()));
		else return sysOrgDao.findByField(SysOrg.PROP_UP_ORG_ID, null);
	}

	public List findChildDeptsByOrgId(Serializable orgId) throws ServiceException {
		List<SysDept> list = findSysDepts(orgId);
		List<SysDept> res = new ArrayList<SysDept>();
		for (int i = 0; i < list.size(); i++) {
			SysDept tmp = list.get(i);
			if (tmp.getUpDeptId() == null) res.add(tmp);
		}
		return res;
	}

	public List findChildDeptsByDeptId(Serializable deptId) throws ServiceException {
		return sysDeptDao.findByField(SysDept.PROP_UP_DEPT_ID, deptId);
	}

	private SysOrgDao		sysOrgDao;

	private SysDeptDao	sysDeptDao;

	public void setSysOrgDao(SysOrgDao sysOrgDao) {
		this.sysOrgDao = sysOrgDao;
	}

	public SysDeptDao getSysDeptDao() {
		return sysDeptDao;
	}

	public void setSysDeptDao(SysDeptDao sysDeptDao) {
		this.sysDeptDao = sysDeptDao;
	}
}
