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
		throw new RuntimeException("错误的列别名:" + property);
	}

	// 从CellEditor取值得此单元格的值
	// 参数element是表格行对象TableItem，其getData()方法可取得PeopleEntity
	// 参数property是列别名
	// 参数value是修改后的新值。每种CellEditor的value的数据类型各不相同
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
			throw new RuntimeException("错误的列别名:" + property);
		}
		tv.update(o, null);

	}

}
