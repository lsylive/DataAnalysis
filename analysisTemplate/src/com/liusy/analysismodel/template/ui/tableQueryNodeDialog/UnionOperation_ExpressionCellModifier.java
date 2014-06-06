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
		else throw new RuntimeException("错误的列别名:" + property);

	}

	private int getIndex(String[] labels, String name) {
		for (int i = 0; i < labels.length; i++) {
			if (labels[i].equalsIgnoreCase(name)) return i;
		}
		return 0;
	}

	// 从CellEditor取值得此单元格的值
	// 参数element是表格行对象TableItem，其getData()方法可取得PeopleEntity
	// 参数property是列别名
	// 参数value是修改后的新值。每种CellEditor的value的数据类型各不相同
	public void modify(Object element, String property, Object value) {
		TableItem item = (TableItem) element;
		UnionOperationField o = (UnionOperationField) item.getData();
		if (property.equals("name")) o.setName((String) value);
		else if (property.equals("cnName")) o.setCnName((String) value);
		else if (property.equals("expression")) o.setExpression((String) value);
		else if (property.equals("dataType")) o.setDataType(Consts.DATATYPE[(Integer) value]);
		else {
			throw new RuntimeException("错误的列别名:" + property);
		}

		tv.update(o, null);

	}
}
