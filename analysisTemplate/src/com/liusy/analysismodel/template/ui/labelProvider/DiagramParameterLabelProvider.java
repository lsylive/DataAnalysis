package com.liusy.analysismodel.template.ui.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;

public class DiagramParameterLabelProvider extends BaseLabelProvider implements ITableLabelProvider {

	private String[]	codeSets;
	private String[]	codeSetLabels;

	public DiagramParameterLabelProvider(String[] codeSets, String[] codeSetLabels) {
		this.codeSets = codeSets;
		this.codeSetLabels = codeSetLabels;
	}

	public DiagramParameterLabelProvider() {

	}

	public String getColumnText(Object obj, int columnIndex) {
		DiagramParameter dataSourceInfo = (DiagramParameter) obj;
		switch (columnIndex) {
		case 0:
			return dataSourceInfo.getName() == null ? "" : dataSourceInfo.getName();
		case 1:
			return dataSourceInfo.getCnName() == null ? "" : dataSourceInfo.getCnName();
		case 2:
			return dataSourceInfo.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dataSourceInfo.getDataType());
		case 3:
			return dataSourceInfo.getLength() == null ? "" : dataSourceInfo.getLength().toString();
		case 4:
			return dataSourceInfo.getPrecision() == null ? "" : dataSourceInfo.getPrecision().toString();
		case 5:
			return dataSourceInfo.getPolyLineFlag() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dataSourceInfo.getPolyLineFlag());
		case 6:
			return dataSourceInfo.getCodeSet() == null ? "" : getLabel(codeSetLabels, codeSets, dataSourceInfo.getCodeSet());
		}
		return null;
	}

	public String getText(Object element) {
		return element == null ? "" : element.toString();
	}

	public Image getColumnImage(Object obj, int index) {
		return null;
	}

	// public static String[] DATAFIELDS = new String[] { "name", "cnName",
	// "dataType", "length", "precision", "polyLineFlag", "codeSet" };

	public void setCodeSets(String[] codeSets) {
		this.codeSets = codeSets;
	}

	public void setCodeSetLabels(String[] codeSetLabels) {
		this.codeSetLabels = codeSetLabels;
	}
}
