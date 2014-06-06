package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.DataField;

public class DataFieldsCellModifier implements ICellModifier {
   private TableViewer tv;

   public DataFieldsCellModifier(TableViewer tv) {
      this.tv = tv;
   }

   public boolean canModify(Object element, String property) {
      return true;
   }

   public Object getValue(Object element, String property) {
      DataField o = (DataField) element;
      if (property.equals("name")) return o.getName();
      else if (property.equals("aliasName")) return o.getAliasName();
      else if (property.equals("cnName")) return o.getCnName();
      else if (property.equals("dataType")) return getIndex(Consts.DATATYPE, o.getDataType());
      else if (property.equals("sortNo")) return o.getSortNo();
      else if (property.equals("sortDirect")) return getIndex(Consts.SORTDIRECT, o.getSortDirect());
      else if (property.equals("aggregate")) return getIndex(Consts.AGGREGATE, o.getAggregate());
      else if (property.equals("aggregateNo")) return o.getAggregateNo();
      else if (property.equals("output")) return getIndex(Consts.YESNO, o.getOutput());
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
      DataField o = (DataField) item.getData();

      if (property.equals("name")) {
         o.setName((String) value);
      }
      else if (property.equals("cnName")) {
         o.setCnName((String) value);
      }
      else if (property.equals("aliasName")) {
         o.setAliasName((String) value);
      }
      else if (property.equals("dataType")) {
         Integer itmp = (Integer) value;
         o.setDataType(Consts.DATATYPE[itmp]);
      }
      else if (property.equals("sortNo")) {
         o.setSortNo((String) value);
      }
      else if (property.equals("aggregateNo")) {
         o.setAggregateNo((String) value);
      }
      else if (property.equals("aggregate")) {
         Integer itmp = (Integer) value;
         o.setAggregate(Consts.AGGREGATE[itmp]);
      }
      else if (property.equals("output")) {
         Integer itmp = (Integer) value;
         o.setOutput(Consts.YESNO[itmp]);
      }
      else if (property.equals("sortDirect")) {
         Integer itmp = (Integer) value;
         o.setSortDirect(Consts.SORTDIRECT[itmp]);
      }
      else {
         throw new RuntimeException("错误的列别名:" + property);
      }
      tv.update(o, null);

   }

}
