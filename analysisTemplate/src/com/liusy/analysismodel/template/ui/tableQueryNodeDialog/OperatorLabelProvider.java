package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.UnionOperationField;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;

public class OperatorLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
	public String getColumnText(Object obj, int columnIndex) {
		UnionOperationField dsi = (UnionOperationField) obj;
		switch (columnIndex) {
		case 0:
			return dsi.getCnName() == null ? "" : dsi.getCnName();
		case 1:
			return dsi.getName() == null ? "" : dsi.getName();
		case 2:
			return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
		case 3:
			return dsi.getExpression() == null ? "" : dsi.getExpression();
		}
		return null;
	}

	public String getText(Object element) {
		return element == null ? "" : element.toString();
	}

	public Image getColumnImage(Object obj, int index) {
		return null;
	}

	public static String[]	DATAFIELDS	= new String[] { "cnName", "name", "dataType", "expression"};
}
