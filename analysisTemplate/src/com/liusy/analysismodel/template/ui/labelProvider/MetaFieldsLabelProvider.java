package com.liusy.analysismodel.template.ui.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;

public class MetaFieldsLabelProvider extends BaseLabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		Metadata o = (Metadata) element;
		switch (columnIndex) {
		case 0:
			return o.getCnName();
		case 1:
			return o.getName();
		case 2:
			return o.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, o.getDataType());
		case 3:
			return o.getLength() == null ? "" : o.getLength().toString();
		case 4:
			return o.getPrecision() == null ? "" : o.getPrecision().toString();
		}
		return null;
	}
}
