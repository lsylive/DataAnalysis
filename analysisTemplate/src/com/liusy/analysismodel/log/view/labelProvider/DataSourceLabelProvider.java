package com.liusy.analysismodel.log.view.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysismodel.template.ui.dialog.DataSourceBean;

public class DataSourceLabelProvider  extends LabelProvider implements
ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
	   DataSourceBean dataSourceInfo = (DataSourceBean) obj;
		switch (columnIndex) {
		case 0:
			return dataSourceInfo.getDataSourceName();
		case 1:
			return dataSourceInfo.getDriver();
		case 2:
			return dataSourceInfo.getUrl();
		case 3:
			return dataSourceInfo.getUserName();
		case 4:
			return dataSourceInfo.getPassword();
		}
		return null;
	}

	public Image getColumnImage(Object obj, int index) {
		return null;
	}

}
