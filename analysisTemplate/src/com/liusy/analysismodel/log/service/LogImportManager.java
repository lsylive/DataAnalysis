package com.liusy.analysismodel.log.service;

import java.util.List;

public interface LogImportManager {
	public List queryLogTableInfo() throws Exception;
	public void insertLogTableInfo(Object[] array) throws Exception;
}
