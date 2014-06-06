package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.Filter;

public class FiltersCellModifier implements ICellModifier {
   private TableViewer tv;
   private String[]    aliasNames;

   public FiltersCellModifier(TableViewer tv, String[] aliasNames) {
      this.tv = tv;
      this.aliasNames = aliasNames;
   }

   public boolean canModify(Object element, String property) {
      return true;
   }

   public Object getValue(Object element, String property) {
      Filter o = (Filter) element;
      //      if (property.equals("field")) return o.getField();
      if (property.equals("field")) return getIndex(aliasNames, o.getField());
      else if (property.equals("operator")) return getIndex(Consts.OPERATOR, o.getOperator());
      else if (property.equals("data")) return o.getExpression();
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
      Filter o = (Filter) item.getData();

      if (property.equals("field")) {
//         o.setField((String) value);
         Integer itmp = (Integer) value;
         o.setField(aliasNames[itmp]);
      }
      else if (property.equals("data")) {
         o.setExpression((String) value);
      }
      else if (property.equals("operator")) {
         Integer itmp = (Integer) value;
         o.setOperator(Consts.OPERATOR[itmp]);
      }
      else {
         throw new RuntimeException("������б���:" + property);
      }
      tv.update(o, null);

   }

   public String[] getAliasNames() {
      return aliasNames;
   }

   public void setAliasNames(String[] aliasNames) {
      this.aliasNames = aliasNames;
   }

}
