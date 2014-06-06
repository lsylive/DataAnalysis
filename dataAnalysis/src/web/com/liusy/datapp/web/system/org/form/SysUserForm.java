package com.liusy.datapp.web.system.org.form;

import java.util.ArrayList;
import java.util.List;

import com.liusy.dataapplatform.base.form.QueryForm;

public class SysUserForm extends QueryForm {
	private static final long	serialVersionUID	= 1L;

	private List					rights;
	
	private List					roles;

	private String[] 				columnId;
	
	private String[]				columnLevel;
	
	public String[] getColumnId()
	{
		return columnId;
	}

	public void setColumnId(String[] columnId)
	{
		this.columnId = columnId;
	}

	public String[] getColumnLevel()
	{
		return columnLevel;
	}

	public void setColumnLevel(String[] coulumLevel)
	{
		this.columnLevel = coulumLevel;
	}

	public List getRights() {
		if (rights == null) rights = new ArrayList();
		return rights;
	}

	public void setRights(List rights) {
		this.rights = rights;
	}

	public List getRoles() {
		if (roles == null) roles = new ArrayList();
		return roles;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}
}
