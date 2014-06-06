package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.MultiTableConnectFilter;

public class MultiTableConnectFiltersCellModifier implements ICellModifier {
	private TableViewer	tv;
	private String[]		tableAliasNames;
	private String[]		tableEnNames;

	public MultiTableConnectFiltersCellModifier(TableViewer tv, String[] tableEnNames,String[] tableAliasNames) {
		this.tv = tv;
		this.tableAliasNames = tableAliasNames;
		this.tableEnNames=tableEnNames;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		MultiTableConnectFilter o = (MultiTableConnectFilter) element;
		if (property.equals("tableAliasName")) return getIndex(tableAliasNames, o.getTableAliasName());
		else if (property.equals("tableEnName")) return getIndex(tableEnNames, o.getTableEnName());
		else if (property.equals("connectedTableEnName")) return getIndex(tableEnNames, o.getConnectedEnTableName());
		else if (property.equals("operator")) return getIndex(Consts.CONNECT, o.getOperator());
		else if (property.equals("connectedTableAliasName")) return getIndex(tableAliasNames, o.getConnectedTableAliasName());
		else if (property.equals("connectedConditions")) return o.getConnectedConditions();
		throw new RuntimeException("������б���:" + property);
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
		MultiTableConnectFilter o = (MultiTableConnectFilter) item.getData();

		if (property.equals("tableAliasName")) {
			// o.setField((String) value);
			Integer itmp = (Integer) value;
			o.setTableAliasName(tableAliasNames[itmp]);
		}
		else if (property.equals("tableEnName")) {
			Integer itmp = (Integer) value;
			o.setTableEnName(tableEnNames[itmp]);
		}
		else if (property.equals("connectedTableEnName")) {
			Integer itmp = (Integer) value;
			o.setConnectedEnTableName(tableEnNames[itmp]);
		}
		else if (property.equals("operator")) {
			Integer itmp = (Integer) value;
			o.setOperator(Consts.CONNECT[itmp]);
		}
		else if (property.equals("connectedTableAliasName")) {
			Integer itmp = (Integer) value;
			o.setConnectedTableAliasName(tableAliasNames[itmp]);
		}
		else if (property.equals("connectedConditions")) {
			o.setConnectedConditions((String) value);
		}
		else {
			throw new RuntimeException("������б���:" + property);
		}
		tv.update(o, null);

	}

	public String[] getTableAliasNames() {
		return tableAliasNames;
	}

	public void setTableAliasNames(String[] aliasNames) {
		this.tableAliasNames = aliasNames;
	}

}
