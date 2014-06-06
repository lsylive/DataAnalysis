package com.liusy.analysismodel.template.ui.contentProvider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ViewContentProvider implements IStructuredContentProvider {
   public void inputChanged(Viewer v, Object oldInput, Object newInput) {
   }

   public void dispose() {
   }

   public Object[] getElements(Object parent) {
      List list = (List) parent;
      return list.toArray();
   }

}
