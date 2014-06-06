package com.liusy.datapp.web.system.org.form;

import java.util.ArrayList;
import java.util.List;

import com.liusy.dataapplatform.base.form.QueryForm;

public class SysRoleForm extends QueryForm {

	private static final long serialVersionUID = 1L;
	
	private List					rights;

	private List					users;

	public List getRights() {
		if (rights == null) rights = new ArrayList();
		return rights;
	}

	public void setRights(List rights) {
		this.rights = rights;
	}

	public List getUsers() {
		return users;
	}

	public void setUsers(List users) {
		this.users = users;
	}
}
