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
		throw new RuntimeException("错误的列别名:" + property);
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
			throw new RuntimeException("错误的列别名:" + property);
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
