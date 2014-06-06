package com.liusy.analysismodel.template.ui.contentProvider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class BaseLabelProvider extends LabelProvider {
   public String getColumnText(Object obj, int columnIndex) {
      return null;
   }
   
   protected String getLabel(String[] labels,String[] values,String name) {
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
}
