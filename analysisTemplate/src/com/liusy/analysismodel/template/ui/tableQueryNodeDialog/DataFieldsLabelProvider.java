package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.DataField;

public class DataFieldsLabelProvider extends LabelProvider implements ITableLabelProvider {
   public String getColumnText(Object obj, int columnIndex) {
      DataField dsi = (DataField) obj;
      switch (columnIndex) {
      case 0:
         return dsi.getCnName() == null ? "" : dsi.getCnName();
      case 1:
         return dsi.getName() == null ? "" : dsi.getName();
      case 2:
         return dsi.getAliasName() == null ? "" : dsi.getAliasName();
      case 3:
         return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL,Consts.DATATYPE,dsi.getDataType());
      case 4:
         return dsi.getSortNo() == null ? "" : dsi.getSortNo();
      case 5:
         return dsi.getSortDirect() == null ? "" : getLabel(Consts.SORTDIRECT_LABEL,Consts.SORTDIRECT,dsi.getSortDirect());
      case 6:
         return dsi.getAggregate() == null ? "" : getLabel(Consts.AGGREGATE_LABEL,Consts.AGGREGATE,dsi.getAggregate());
      case 7:
         return dsi.getAggregateNo() == null ? "" : dsi.getAggregateNo();
       case 8:
       return dsi.getOutput() == null ? "" : getLabel(Consts.YESNO_LABEL,Consts.YESNO,dsi.getOutput());
      }
      return null;
   }
   
   private String getLabel(String[] labels,String[] values,String name) {
      for (int i = 0; i < values.length; i++) {
         if (values[i].equalsIgnoreCase(name)) return labels[i];
      }
      return "";
   }
   
   public String getText(Object element) {
      return element == null ? "" : element.toString();
   }

   public Image getColumnImage(Object obj, int index) {
      return null;
   }

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "aliasName", "dataType", "sortNo", "sortDirect", "aggregate", "aggregateNo","output" };
}
