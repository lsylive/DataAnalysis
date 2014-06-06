package com.liusy.datapp.service.system.org.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.criterion.Order;

import com.liusy.core.util.Const;
import com.liusy.dataapplatform.base.exception.DAOException;
import com.liusy.dataapplatform.base.exception.ServiceException;
import com.liusy.dataapplatform.base.util.Condition;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.ICondition;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.datapp.dao.datastandard.StandardCategoryDao;
import com.liusy.datapp.dao.resource.ResourceColumnDao;
import com.liusy.datapp.dao.resource.ResourceTableDao;
import com.liusy.datapp.dao.system.org.SysResourceUserDao;
import com.liusy.datapp.dao.system.org.SysUserDao;
import com.liusy.datapp.dao.system.org.SysUserRoleDao;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.model.system.org.SysResourceUser;
import com.liusy.datapp.model.system.org.SysUser;
import com.liusy.datapp.model.system.org.SysUserRole;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.system.org.SysUserService;
import com.liusy.datapp.web.system.resource.action.SysResourceTableAction;

public class SysUserServiceImpl implements SysUserService {

	public SysUser findSysUser(Serializable id) throws ServiceException {
		try {
			return sysUserDao.get(id);
		}
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void createSysUser(SysUser sysUser, String roleIds) throws ServiceException {
		try {
			List list = sysUserDao.findByField(SysUser.PROP_USER_ACCOUNT, sysUser.getUserAccount());
			if (list != null && list.size() > 0) {
				throw new ServiceException("�ʺ���:[" + sysUser.getUserAccount() + "]�Ѿ����ڣ�����ʹ�á�");
			}

			Integer userId = (Integer) sysUserDao.save(sysUser);

			String[] ids = roleIds.split(";");
			for (int i = 0; i < ids.length; i++) {
				if (ids[i].trim().equals("")) continue;
				SysUserRole sru = new SysUserRole();
				sru.setUserId(userId);
				sru.setRoleId(new Integer(ids[i]));
				sru.setStatus(Const.SYS_NOTVERIFY);
				sru.setOper(Const.SYS_OPER_ADD);
				sysUserRoleDao.save(sru);
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void updateSysUser(SysUser sysUser, String roleIds) throws ServiceException {
		try {
			List list = sysUserDao.findByField(SysUser.PROP_USER_ACCOUNT, sysUser.getUserAccount());
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					SysUser user = (SysUser) list.get(i);
					if (sysUser.getId().intValue() != user.getId().intValue()) {
						throw new ServiceException("�ʺ���:[" + sysUser.getUserAccount() + "]�����ظ�������ʹ�á�");
					}
				}
			}

			Integer userId = sysUser.getId();
			String tmp = ";" + roleIds;
			SysUser tmpUser = sysUserDao.get(userId);
			String state = tmpUser.getUserStatus();
			String uName = tmpUser.getUserName();
			String uAcc = tmpUser.getUserAccount();

			ConvertUtil.convertToModelForUpdate(tmpUser, sysUser);
			if (SYSUSER.equals(tmpUser.getAccountType())) { // ϵͳ�̶����û�����Ϣ�����޸ġ�
				tmpUser.setUserStatus(state);
				tmpUser.setUserName(uName);
				tmpUser.setUserAccount(uAcc);
			}
			sysUserDao.update(tmpUser);
			if (SYSUSER.equals(tmpUser.getAccountType())) return; // ϵͳ�̶����û������޸Ľ�ɫ��Ȩ�ޡ�

			List userRoles = sysUserRoleDao.findByField(SysUserRole.PROP_USER_ID, userId);
			for (int i = 0; i < userRoles.size(); i++) {
				SysUserRole sru = (SysUserRole) userRoles.get(i);
				if (tmp.indexOf(";" + sru.getRoleId().toString() + ";") < 0) {
					// ����˵�Ȩ�ޣ����������ˡ���ɾ��״̬
					if (sru.getStatus().equals(Const.SYS_VERIFIED)) {
						sru.setStatus(Const.SYS_NOTVERIFY);
						sru.setOper(Const.SYS_OPER_DELETE);
						sysUserRoleDao.update(sru);
					}
					else {
						// ����˵�Ȩ�ޡ�������״̬������ȡ��
						if (sru.getOper() != null && sru.getOper().equals(Const.SYS_OPER_ADD)) {
							sysUserRoleDao.delete(sru.getId());
						}
					}
				}
				else {
					// �����״̬
					if (sru.getStatus().equals(Const.SYS_NOTVERIFY)) {
						// ɾ������������ȡ��
						if (sru.getOper() != null && sru.getOper().equals(Const.SYS_OPER_DELETE)) {
							sru.setStatus(Const.SYS_VERIFIED);
							sru.setOper(Const.SYS_OPER_NONE);
							sysUserRoleDao.update(sru);
						}

					}
					// �����״̬
					tmp = tmp.replace(";" + sru.getRoleId().toString() + ";", ";");// ����û�б仯��Ȩ��
				}
			}
			String[] newRoles = tmp.split(";");
			for (int i = 0; i < newRoles.length; i++) {
				String r = newRoles[i];
				if (r == null || r.equals("")) continue;
				SysUserRole sru = new SysUserRole();// ����Ȩ��
				sru.setUserId(userId);
				sru.setStatus(Const.SYS_NOTVERIFY);// �����
				sru.setOper(Const.SYS_OPER_ADD);// �������Ӳ���
				sru.setRoleId(new Integer(r));
				sysUserRoleDao.save(sru);
			}
		}
		catch (DAOException de) {
			de.printStackTrace();
			throw new ServiceException("���ݿ��������");

		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void startSysUsers(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				SysUser tmp = sysUserDao.get(ids[i]);
				if (tmp != null) {
					tmp.setUserStatus("1");
					sysUserDao.update(tmp);
				}
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");

		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void stopSysUsers(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				SysUser tmp = sysUserDao.get(ids[i]);
				if (tmp != null && !SYSUSER.equals(tmp.getAccountType())) { // ϵͳ�̶����û�������ͣ��
					tmp.setUserStatus("0");
					sysUserDao.update(tmp);
				}
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void removeSysUser(Serializable id) throws ServiceException {
		try {
			SysUser tmp = sysUserDao.get(id);
			if (SYSUSER.equals(tmp.getAccountType())) {
				throw new ServiceException("���û�����ɾ����");
			}
			sysUserDao.delete(tmp);
			sysUserRoleDao.deleteByField(SysUserRole.PROP_USER_ID, id);
			sysResourceUserDao.deleteByField(SysResourceUser.PROP_USER_ID, id);
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void removeSysUsers(Serializable[] ids) throws ServiceException {
		try {
			for (int i = 0; i < ids.length; i++) {
				SysUser tmp = sysUserDao.get(ids[i]);
				if (SYSUSER.equals(tmp.getAccountType())) {
					throw new ServiceException("ϵͳ�û�����ɾ����");
				}
			}
			sysUserDao.deleteAll(ids);
			for (int i = 0; i < ids.length; i++) {
				sysUserRoleDao.deleteByField(SysUserRole.PROP_USER_ID, ids[i]);
				sysResourceUserDao.deleteByField(SysResourceUser.PROP_USER_ID, ids[i]);
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public PageQuery querySysUser(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery query1 = sysUserDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query1.getRecordSet());
			pageQuery.setRecordCount(query1.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	/**
	 * ��Unique Key���Ҷ���
	 */
	public SysUser findSysUserByUK(String ukField, Object ukValue) throws ServiceException {
		try {
			Collection c = findSysUserByField(ukField, ukValue);
			if (c != null && !c.isEmpty()) {
				return (SysUser) c.iterator().next();
			}
			return null;
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	/**
	 * ʹ���⽡��ȡ�ӱ��¼
	 * 
	 * @param fieldName
	 *           �ֶ���
	 * @param fieldValue
	 *           �ֶ�ֵ,������봫���ֶ���Ӧ������,��Long������String
	 * @see com.liusy.core.service.SysUserService#findSysUserByField(String
	 *      fieldName, Object fieldValue)
	 */
	public Collection findSysUserByField(String fieldName, Object fieldValue) throws ServiceException {
		try {
			return sysUserDao.findByField(fieldName, fieldValue);
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	/**
	 * ʹ���⽡����ӱ��¼��
	 * 
	 * @param fieldName
	 *           �ֶ���
	 * @param fieldValue
	 *           �ֶ�ֵ,������봫���ֶ���Ӧ������,��Long������String
	 * @return
	 * @see com.liusy.core.service.SysUserService#countSysUserByField(String
	 *      fieldName, Object fieldValue)
	 */
	public int countSysUserByField(String fieldName, Object fieldValue) throws ServiceException {
		try {
			return sysUserDao.countByField(fieldName, fieldValue);
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	/**
	 * @return ȫ��
	 * @throws Exception
	 */
	public List<SysUser> findAllSysUser() throws ServiceException {
		try {
			return sysUserDao.findAll();
		}
		catch (Exception e) {

			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void findSysUserByPage(PageQuery pageQuery) throws ServiceException {
		try {
			Map fields = pageQuery.getParameters();
			List<ICondition> conditions = new ArrayList<ICondition>();

			Object str = fields.get("userAccount");
			if (str != null && str.toString().trim().length() > 0) {
				conditions.add(new Condition(SysUser.PROP_USER_ACCOUNT, Condition.LIKE, "%" + str.toString() + "%"));
			}
			str = fields.get("userName");
			if (str != null && str.toString().trim().length() > 0) {
				conditions.add(new Condition(SysUser.PROP_USER_NAME, Condition.LIKE, "%" + str.toString() + "%"));
			}
			Order order;
			if (pageQuery.getOrder() == null) order = null;
			else if (pageQuery.getOrderDirection().equalsIgnoreCase(PageQuery.ASC)) order = Order.asc(pageQuery.getOrder());
			else if (pageQuery.getOrderDirection().equalsIgnoreCase(PageQuery.DESC)) order = Order.desc(pageQuery.getOrder());
			else order = null;
			Collection<Order> orders = null;
			if (order != null) {
				orders = new ArrayList<Order>();
				orders.add(order);
			}
			long rc = sysUserDao.countByFilter(conditions);
			if (rc > 0) {
				List list = sysUserDao.commonQuery(conditions, orders, Integer.parseInt(pageQuery.getPageNumber()), Integer.parseInt(pageQuery.getPageSize()));
				pageQuery.setRecordSet(list);
				pageQuery.setRecordCount(String.valueOf(rc));
			}
		}
		catch (DAOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
	}

	public SysUser validateLogin(String userName, String password) throws ServiceException {
		// TODO Auto-generated method stub
		SysUser user = null;
		try {
			List result = sysUserDao.findByField(SysUser.PROP_USER_ACCOUNT, userName);
			if (result != null && !result.isEmpty()) user = (SysUser) result.get(0);
			if (user.getUserPassword().equals(password)) return user;
			else return null;

		}
		catch (Exception e) {

			e.printStackTrace();
		}
		return user;
	}

	public void savePwd(Serializable id, String pwd) throws ServiceException {
		try {
			SysUser sysUser = sysUserDao.get(id);
			if (sysUser != null) {
				sysUser.setUserPassword(pwd);
				sysUserDao.update(sysUser);
			}
			else throw new ServiceException("���û������ڡ�");
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
	}

	public void saveVerify(Integer id) throws ServiceException {
		try {
			SysUser sysUser = sysUserDao.get(id);
			if (sysUser == null) throw new ServiceException("���û������ڡ�");
			if (SYSUSER.equals(sysUser.getAccountType())) throw new ServiceException("������˸��û���Ϣ��");
			List userRights = sysResourceUserDao.findByField(SysResourceUser.PROP_USER_ID, id);
			List userRoles = sysUserRoleDao.findByField(SysUserRole.PROP_USER_ID, id);
			for (int i = 0; i < userRights.size(); i++) {
				SysResourceUser sru = (SysResourceUser) userRights.get(i);
				if (sru.getStatus().equals(Const.SYS_NOTVERIFY)) {
					if (sru.getOper().equals(Const.SYS_OPER_DELETE)) {
						sysResourceUserDao.delete(sru.getId());
					}
					else {
						sru.setStatus(Const.SYS_VERIFIED);
						sru.setOper(Const.SYS_OPER_NONE);
						sysResourceUserDao.update(sru);
					}
				}
			}
			for (int i = 0; i < userRoles.size(); i++) {
				SysUserRole sru = (SysUserRole) userRoles.get(i);
				if (sru.getStatus().equals(Const.SYS_NOTVERIFY)) {
					if (sru.getOper().equals(Const.SYS_OPER_DELETE)) {
						sysUserRoleDao.delete(sru.getId());
					}
					else {
						sru.setStatus(Const.SYS_VERIFIED);
						sru.setOper(Const.SYS_OPER_NONE);
						sysUserRoleDao.update(sru);
					}
				}
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public void saveRights(Integer id, String rights) throws ServiceException {
		String tmp = "," + rights + ",";
		try {
			SysUser sysUser = sysUserDao.get(id);
			if (sysUser == null) throw new ServiceException("���û������ڡ�");
			List userRights = sysResourceUserDao.findByField(SysResourceUser.PROP_USER_ID, id);
			for (int i = 0; i < userRights.size(); i++) {
				SysResourceUser sru = (SysResourceUser) userRights.get(i);
				if (tmp.indexOf("," + sru.getResId().toString() + ",") < 0) {
					// ����˵�Ȩ�ޣ����������ˡ���ɾ��״̬
					if (sru.getStatus().equals(Const.SYS_VERIFIED)) {
						sru.setStatus(Const.SYS_NOTVERIFY);
						sru.setOper(Const.SYS_OPER_DELETE);
						sysResourceUserDao.update(sru);
					}
					else {
						// ����˵�Ȩ�ޡ�������״̬������ȡ��
						if (sru.getOper() != null && sru.getOper().equals(Const.SYS_OPER_ADD)) {
							sysResourceUserDao.delete(sru.getId());
						}
					}
				}
				else {
					// �����״̬
					if (sru.getStatus().equals(Const.SYS_NOTVERIFY)) {
						// ɾ������������ȡ��
						if (sru.getOper() != null && sru.getOper().equals(Const.SYS_OPER_DELETE)) {
							sru.setStatus(Const.SYS_VERIFIED);
							sru.setOper(Const.SYS_OPER_NONE);
							sysResourceUserDao.update(sru);
						}

					}
					// �����״̬
					tmp = tmp.replace("," + sru.getResId().toString() + ",", ",");// ����û�б仯��Ȩ��
				}
			}
			String[] newRights = tmp.split(",");
			for (int i = 0; i < newRights.length; i++) {
				String r = newRights[i];
				if (r == null || r.equals("")) continue;
				SysResourceUser sru = new SysResourceUser();// ����Ȩ��
				sru.setUserId(id);
				sru.setStatus(Const.SYS_VERIFIED);// �����//2011/4/6���Ѿ���Ϊ������Ҫ��ˡ�
				sru.setOper(Const.SYS_OPER_ADD);// �������Ӳ���
				sru.setResId(new Integer(r));
				sysResourceUserDao.save(sru);
			}
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
		catch (ServiceException se) {
			se.printStackTrace();
			throw se;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("ϵͳ����");
		}
	}

	public PageQuery findUserRoles(PageQuery pageQuery) throws ServiceException {
		try {
			PageQuery query1 = sysUserRoleDao.queryBySelectId(pageQuery);
			pageQuery.setRecordSet(query1.getRecordSet());
			pageQuery.setRecordCount(query1.getRecordCount());
			return pageQuery;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("���ݿ��������");
		}
	}



	private SysUserDao			sysUserDao;

	private SysUserRoleDao		sysUserRoleDao;

	private SysResourceUserDao	sysResourceUserDao;



	private ResourceTableDao resourceTableDao;
	
	private ResourceColumnDao resourceColumnDao;
	
	private StandardCategoryDao standardCategoryDao;
	
	
	/**
	 * ע��DAO
	 * 
	 * @see com.liusy.core.service.SysUserService#setSysUserDao(SysUserDao
	 *      sysUserDao)
	 */
	
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}

	public void setResourceColumnDao(ResourceColumnDao resourceColumnDao)
	{
		this.resourceColumnDao = resourceColumnDao;
	}

	public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
		this.sysUserRoleDao = sysUserRoleDao;
	}

	public void setSysResourceUserDao(SysResourceUserDao sysResourceUserDao) {
		this.sysResourceUserDao = sysResourceUserDao;
	}


	
	public void setResourceTableDao(ResourceTableDao resourceTableDao)
	{
		this.resourceTableDao = resourceTableDao;
	}

	public void setStandardCategoryDao(StandardCategoryDao standardCategoryDao)
	{
		this.standardCategoryDao = standardCategoryDao;
	}

	public String getResourceTreeStr(String treeId, String cityCode) throws ServiceException
	{
		Element root = DocumentHelper.createElement("tree").addAttribute("id", treeId);
		if ("0".equals(treeId))
		{
			//�����з�����ȡ������չ��
			List<StandardCategory> list;
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.EQUALS, Integer.valueOf(0)));
			conditions.add(new Condition(StandardCategory.PROP_CITY_CODE, Condition.EQUALS, cityCode));
			list = standardCategoryDao.commonQuery(conditions);
			
			if (list!=null)
			{
				Iterator<StandardCategory> it = list.iterator();
				while (it.hasNext())
				{
					StandardCategory sc = it.next();
					Element temp = root.addElement("item").addAttribute("text", sc.getName())
					.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");
					temp.addAttribute("id", "cator_" + sc.getId()).addAttribute("child", "1").addElement("userdata").addAttribute("name", "name").addText(sc.getName());					
				}
			}
		}else if (treeId.indexOf("cator_")>-1) {
			//�Ѹ÷����µ������ӷ���ͱ����ȡ����
			treeId = treeId.replaceFirst("cator_", "");
			
			//�����ӷ���
			List<StandardCategory> list;
			List<ICondition> conditions = new ArrayList<ICondition>();
			conditions.add(new Condition(StandardCategory.PROP_PARENT_ID, Condition.EQUALS, Integer.valueOf(treeId)));
			conditions.add(new Condition(StandardCategory.PROP_CITY_CODE, Condition.EQUALS, cityCode));
			list = standardCategoryDao.commonQuery(conditions);			
			if (list!=null)
			{
				Iterator<StandardCategory> it = list.iterator();
				while (it.hasNext())
				{
					StandardCategory sc = it.next();
					Element temp = root.addElement("item").addAttribute("text", sc.getName())
					.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");
					temp.addAttribute("id", "cator_" + sc.getId()).addAttribute("child", "1").addElement("userdata").addAttribute("name", "name").addText(sc.getName());					
				}
			}
			
			//�������
			List<ResourceTable> tables;
			conditions.clear();
			conditions.add(new Condition(ResourceTable.PROP_CATEGORY_ID, Condition.EQUALS, Integer.valueOf(treeId)));
			conditions.add(new Condition(ResourceTable.PROP_CITY_CODE, Condition.EQUALS, cityCode));
			tables = resourceTableDao.commonQuery(conditions);
			if (tables!=null)
			{
				Iterator<ResourceTable> it = tables.iterator();
				while (it.hasNext())
				{
					ResourceTable rt = it.next();
					Element temp = root.addElement("item").addAttribute("text", rt.getCnName())
					.addAttribute("im0", "book_titel.gif").addAttribute("im1", "books_open.gif").addAttribute("im2", "book.gif");
					temp.addAttribute("id", rt.getId().toString()).addAttribute("child", "0").addElement("userdata").addAttribute("name", "name").addText(rt.getCnName());					
				}
			}
		}
		
		return root.asXML();
	}
	
	public List<ResourceColumn> getColumnsByTableId(Serializable tableId) throws ServiceException
	{
		List<ResourceColumn> list;
		List<ICondition> conditions = new ArrayList<ICondition>();
		conditions.add(new Condition(ResourceColumn.PROP_TABLE_ID,Condition.EQUALS,tableId));
		list = resourceColumnDao.commonQuery(conditions);
		return list;
	}
	public PageQuery findAviableAdminUser() throws ServiceException{
		try{
			PageQuery pageQuery=new PageQuery();
			pageQuery.setPageSize("0");
			pageQuery.setSelectParamId("GET_PROCESS_ARRANGE_PAGE");
			return querySysUser(pageQuery);
		}catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public ResourceTable getTableById(Serializable tableId) throws ServiceException
	{
		// TODO Auto-generated method stub
		return resourceTableDao.get(tableId);
	}
	
	public int maxOrder() throws ServiceException {
		Integer max_order;	
		List list = sysUserDao.findByHql("select max(user.orderNo) from SysUser as user");
		if (list!=null&&!list.isEmpty())
		{			
			max_order = ((Integer)list.get(0));
			if (max_order==null)
			{
				max_order = 0;
			}
		}
		else {
			max_order = 0;
		}
		return max_order;
	}
	
	public List<SysUser> findByAdminLevel(String level,String dept) throws ServiceException {
		try {
			List<SysUser> list = null;
			String[] params = {SysUser.PROP_ADMIN_LEVEL,SysUser.PROP_DEPT_ID};
			list = sysUserDao.findByFields(params, new Object[]{level,Integer.valueOf(dept)});
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}	
	}

	public List<SysUser> findAllUnAllocPersonalUsers() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
