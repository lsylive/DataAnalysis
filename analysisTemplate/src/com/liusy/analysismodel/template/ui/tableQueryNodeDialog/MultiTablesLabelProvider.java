package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ITableLabelProvider;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.MultiTablesDataField;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;

public class MultiTablesLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
		MultiTablesDataField dsi = (MultiTablesDataField) obj;
		switch (columnIndex) {
		case 0:
			return dsi.getTableCnName() == null ? "" : dsi.getTableCnName();
		case 1:
			return dsi.getTableName() == null ? "" : dsi.getTableName();
		case 2:
			return dsi.getTableAliasName() == null ? "" : dsi.getTableAliasName();
		}
		return null;
	}

	public static String[]	DATAFIELDS	= new String[] { "tableCnName","tableName","tableAliasName"};
}
