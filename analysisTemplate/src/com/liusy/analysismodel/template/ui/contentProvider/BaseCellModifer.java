package com.liusy.analysismodel.template.ui.contentProvider;

import org.eclipse.jface.viewers.TableViewer;

public class BaseCellModifer {
   protected TableViewer tv;

   public boolean canModify(Object element, String property) {
      return true;
   }

   protected int getIndex(String[] labels, String name) {
      for (int i = 0; i < labels.length; i++) {
         if (labels[i].equalsIgnoreCase(name)) return i;
      }
      return 0;
   }

}
