package com.liusy.analysismodel.template.ui.tableQueryNodeDialog;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.Filter;

public class FiltersLabelProvider extends LabelProvider implements ITableLabelProvider {
   public String getColumnText(Object obj, int columnIndex) {
      Filter dsi = (Filter) obj;
      switch (columnIndex) {
      case 0:
         return dsi.getField() == null ? "" : dsi.getField();
      case 1:
         return dsi.getOperator() == null ? "" : getLabel(Consts.OPERATOR_LABEL,Consts.OPERATOR,dsi.getOperator());
      case 2:
         return dsi.getExpression() == null ? "" : dsi.getExpression();
      }
      return null;
   }

   private String getLabel(String[] labels, String[] values, String name) {
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

   public static String[] DATAFIELDS = new String[] { "field", "operator", "data" };
}
