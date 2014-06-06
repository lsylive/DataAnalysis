package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.vo.MultiTablesDataField;

public class MultiTablesCellModifier implements ICellModifier {
	private TableViewer	tv;

	public MultiTablesCellModifier(TableViewer tv) {
		this.tv = tv;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		MultiTablesDataField o = (MultiTablesDataField) element;
		if (property.equals("tableCnName")) return o.getTableCnName();
		else if (property.equals("tableAliasName")) return o.getTableAliasName();
		else if (property.equals("tableName")) return o.getTableName();
		throw new RuntimeException("������б���:" + property);
	}

	// ��CellEditorȡֵ�ô˵�Ԫ���ֵ
	// ����element�Ǳ���ж���TableItem����getData()������ȡ��PeopleEntity
	// ����property���б���
	// ����value���޸ĺ����ֵ��ÿ��CellEditor��value���������͸�����ͬ
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		MultiTablesDataField o = (MultiTablesDataField) item.getData();

		if (property.equals("tableCnName")) {
			o.setTableCnName((String) value);
		}
		else if (property.equals("tableAliasName")) {
			o.setTableAliasName((String) value);
		}
		else if (property.equals("tableName")) {
			o.setTableName((String) value);
		}
		else {
			throw new RuntimeException("������б���:" + property);
		}
		tv.update(o, null);

	}

}
