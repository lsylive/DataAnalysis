package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ITableLabelProvider;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.MultiTableConnectFilter;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;

public class MultiTableFiltersLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
		MultiTableConnectFilter dsi = (MultiTableConnectFilter) obj;
		switch (columnIndex) {
		case 0:
			return dsi.getTableEnName() == null ? "" : dsi.getTableEnName();
		case 1:
			return dsi.getTableAliasName() == null ? "" : dsi.getTableAliasName();
		case 2:
			return dsi.getOperator() == null ? "" : getLabel(Consts.CONNECT_LABEL, Consts.CONNECT, dsi.getOperator());
		case 3:
			return dsi.getConnectedEnTableName() == null ? "" : dsi.getConnectedEnTableName();
		case 4:
			return dsi.getConnectedTableAliasName() == null ? "" : dsi.getConnectedTableAliasName();
		case 5:
			return dsi.getConnectedConditions() == null ? "" : dsi.getConnectedConditions();
		}
		return null;
	}

	public static String[]	DATAFIELDS	= new String[] { "tableEnName","tableAliasName", "operator", "connectedTableEnName","connectedTableAliasName", "connectedConditions" };
}
