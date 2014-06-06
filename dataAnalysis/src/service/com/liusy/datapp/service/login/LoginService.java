package com.liusy.datapp.service.login;

import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.ServiceException;

public interface LoginService {
	public Session login(String accountName, String password, String SSLLogin) throws ServiceException;
}
