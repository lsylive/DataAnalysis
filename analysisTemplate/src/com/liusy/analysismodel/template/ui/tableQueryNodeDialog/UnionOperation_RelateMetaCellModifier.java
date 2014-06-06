package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import java.util.Map;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.vo.UnionOperationConfig;

public class UnionOperation_RelateMetaCellModifier implements ICellModifier {
	private TableViewer				tv;
	private String[]					nodeId;
	private Map<String, String[]>	fieldNames;

	public UnionOperation_RelateMetaCellModifier(TableViewer tv, String[] nodeId, Map<String, String[]> fieldNames) {
		this.tv = tv;
		this.nodeId = nodeId;
		this.fieldNames = fieldNames;
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		UnionOperationConfig o = (UnionOperationConfig) element;
		for (int i = 0; i < nodeId.length; i++) {
			if (property.equals(nodeId[i])) {
				String[] labels = fieldNames.get(nodeId[i]);
				String labelName = o.getFieldNameByNodeId(nodeId[i]);
				return getIndex(labels, labelName);
			}
		}
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
		UnionOperationConfig o = (UnionOperationConfig) item.getData();
		for (int i = 0; i < nodeId.length; i++) {
			if (property.equals(nodeId[i])) {
				Integer itmp = (Integer) value;
				Map<String, String> relateMetaList=o.getRelateMetaList();
				relateMetaList.put(nodeId[i], fieldNames.get(nodeId[i])[itmp.intValue()]);
				o.setRelateMetaList(relateMetaList);
				break;
			}
			if (i == nodeId.length - 1) throw new RuntimeException("错误的列别名:" + property);
		}
		tv.update(o, null);

	}
}
