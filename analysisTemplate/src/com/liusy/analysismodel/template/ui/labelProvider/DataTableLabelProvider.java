package com.liusy.analysismodel.template.ui.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.liusy.analysis.template.model.vo.DataTable;

public class DataTableLabelProvider extends LabelProvider implements ITableLabelProvider {

   public Image getColumnImage(Object element, int columnIndex) {
      // TODO Auto-generated method stub
      return null;
   }

   public String getColumnText(Object element, int columnIndex) {
      DataTable dataSourceInfo = (DataTable) element;
      switch (columnIndex) {
      case 0:
         return dataSourceInfo.getCnName();
      case 1:
         return dataSourceInfo.getName();
      case 2:
         return dataSourceInfo.getCatalogName();
      }
      return null;
   }

}
