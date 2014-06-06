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
		UnionOperationConfig o = (UnionOperationConfig) item.getData();
		for (int i = 0; i < nodeId.length; i++) {
			if (property.equals(nodeId[i])) {
				Integer itmp = (Integer) value;
				Map<String, String> relateMetaList=o.getRelateMetaList();
				relateMetaList.put(nodeId[i], fieldNames.get(nodeId[i])[itmp.intValue()]);
				o.setRelateMetaList(relateMetaList);
				break;
			}
			if (i == nodeId.length - 1) throw new RuntimeException("������б���:" + property);
		}
		tv.update(o, null);

	}
}
