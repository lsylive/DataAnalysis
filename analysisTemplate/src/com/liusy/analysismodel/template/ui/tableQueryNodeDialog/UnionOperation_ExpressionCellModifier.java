package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.UnionOperationField;

public class UnionOperation_ExpressionCellModifier implements ICellModifier {
	private TableViewer	tv;

	public UnionOperation_ExpressionCellModifier(TableViewer tv) {
		this.tv = tv;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		UnionOperationField o = (UnionOperationField) element;

		if (property.equals("cnName")) return o.getCnName();
		else if (property.equals("name")) return o.getName();
		else if (property.equals("dataType")) return getIndex(Consts.DATATYPE, o.getDataType());
		else if (property.equals("expression")) return o.getExpression();
		else throw new RuntimeException("������б���:" + property);

	}

	private int getIndex(String[] labels, String name) {
		for (int i = 0; i < labels.length; i++) {
			if (labels[i].equalsIgnoreCase(name)) return i;
		}
		return 0;
	}

	// ��CellEditorȡֵ�ô˵�Ԫ���ֵ
	// ����element�Ǳ���ж���TableItem����getData()������ȡ��PeopleEntity
	// ����property���б���
	// ����value���޸ĺ����ֵ��ÿ��CellEditor��value���������͸�����ͬ
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		UnionOperationField o = (UnionOperationField) item.getData();
		if (property.equals("name")) o.setName((String) value);
		else if (property.equals("cnName")) o.setCnName((String) value);
		else if (property.equals("expression")) o.setExpression((String) value);
		else if (property.equals("dataType")) o.setDataType(Consts.DATATYPE[(Integer) value]);
		else {
			throw new RuntimeException("������б���:" + property);
		}

		tv.update(o, null);

	}
}
