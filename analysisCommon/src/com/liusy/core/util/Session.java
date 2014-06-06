


//   Session.java

package com.liusy.core.util;

import java.util.*;

public class Session
{

	private String userId;
	private String userCode;
	private String userName;
	private String accountName;
	private String address;
	private String zipCode;
	private String telephone;
	private String moblie;
	private String email;
	private String description;
	private String orgId;
	private String orgCode;
	private String orgName;
	private String orgShortName;
	private String orgNumber;
	private String deptId;
	private String deptCode;
	private String deptName;
	private String deptShortName;
	private String deptNumber;
	private Date loginTime;
	private String cityCode;
	private String securityLevel;
	private String adminLevel;
	private String spaceId;
	private Map roles;
	private Map modules;
	private Map privileges;

	public Session()
	{
		roles = new HashMap();
		modules = new HashMap();
		privileges = new HashMap();
	}

	public boolean hasRole(String key)
	{
		return roles.containsKey(key);
	}

	public boolean hasModule(String key)
	{
		return modules.containsKey(key);
	}

	public boolean hasPrivilege(String key)
	{
		return privileges.containsKey(key);
	}

	public String getAccountName()
	{
		return accountName;
	}

	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getDeptCode()
	{
		return deptCode;
	}

	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}

	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getDeptNumber()
	{
		return deptNumber;
	}

	public void setDeptNumber(String deptNumber)
	{
		this.deptNumber = deptNumber;
	}

	public String getDeptShortName()
	{
		return deptShortName;
	}

	public void setDeptShortName(String deptShortName)
	{
		this.deptShortName = deptShortName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Date getLoginTime()
	{
		return loginTime;
	}

	public void setLoginTime(Date loginTime)
	{
		this.loginTime = loginTime;
	}

	public String getMoblie()
	{
		return moblie;
	}

	public void setMoblie(String moblie)
	{
		this.moblie = moblie;
	}

	public Map getModules()
	{
		return modules;
	}

	public void setModules(Map modules)
	{
		this.modules = modules;
	}

	public String getOrgCode()
	{
		return orgCode;
	}

	public void setOrgCode(String orgCode)
	{
		this.orgCode = orgCode;
	}

	public String getOrgId()
	{
		return orgId;
	}

	public void setOrgId(String orgId)
	{
		this.orgId = orgId;
	}

	public String getOrgName()
	{
		return orgName;
	}

	public void setOrgName(String orgName)
	{
		this.orgName = orgName;
	}

	public String getOrgNumber()
	{
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber)
	{
		this.orgNumber = orgNumber;
	}

	public String getOrgShortName()
	{
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName)
	{
		this.orgShortName = orgShortName;
	}

	public Map getPrivileges()
	{
		return privileges;
	}

	public void setPrivileges(Map privileges)
	{
		this.privileges = privileges;
	}

	public Map getRoles()
	{
		return roles;
	}

	public void setRoles(Map roles)
	{
		this.roles = roles;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getUserCode()
	{
		return userCode;
	}

	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public String getCityCode()
	{
		return cityCode;
	}

	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}

	public String getAdminLevel()
	{
		return adminLevel;
	}

	public void setAdminLevel(String adminLevel)
	{
		this.adminLevel = adminLevel;
	}

	public String getSecurityLevel()
	{
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel)
	{
		this.securityLevel = securityLevel;
	}

	public String getSpaceId()
	{
		return spaceId;
	}

	public void setSpaceId(String spaceId)
	{
		this.spaceId = spaceId;
	}
}
