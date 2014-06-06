package com.liusy.analysismodel.template.ui.delegate;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.ComboValue;

public class DialogDelegate {

   private Shell   shell;
   private Diagram diagram;

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "dataFormat", "align", "width", "visible", "codeset", "link" };

   public DialogDelegate(Shell shell, Diagram diagram) {
      this.shell = shell;
      this.diagram = diagram;
      setLocation();
   }

   public void setLocation() {
      Composite s = shell.getParent();
      int x = s.getLocation().x;
      int y = s.getLocation().y;
      Rectangle rectParent = s.getBounds();
      Point size = shell.getSize();
      int xx = x + rectParent.width / 2 - size.x / 2;
      int yy = y + rectParent.height / 2 - size.y / 2;
      shell.setLocation(new Point(xx, yy));
   }

   public void setComboValue(ComboViewer combo, ComboValue[] list, String value) {
      if (value == null || list == null || list.length == 0) combo.getCombo().select(-1);
      ComboValue cv = null;
      for (int i = 0; i < list.length; i++) {
         if (value.equals(list[i].getValue())) {
            cv = list[i];
            break;
         }
      }
      if (cv == null) combo.getCombo().select(-1);
      else combo.getCombo().select(combo.getCombo().indexOf(cv.getName()));

   }

   public String getComboValue(ComboViewer combo) {
      ISelection selection = combo.getSelection();
      if (selection == null) return "";
      IStructuredSelection selected = (IStructuredSelection) selection;

      ComboValue cv = (ComboValue) selected.getFirstElement();
      return cv == null ? "" : cv.getValue();
   }
}
