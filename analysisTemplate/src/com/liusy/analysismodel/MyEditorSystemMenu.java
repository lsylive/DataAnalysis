package com.liusy.analysismodel;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.presentations.SystemMenuClose;
import org.eclipse.ui.internal.presentations.util.ISystemMenu;
import org.eclipse.ui.presentations.IPresentablePart;
import org.eclipse.ui.presentations.IStackPresentationSite;

public class MyEditorSystemMenu implements ISystemMenu {
   /* package */MenuManager menuManager = new MenuManager();
   private SystemMenuClose   close;

   public MyEditorSystemMenu(IStackPresentationSite site) {
      //      super(site);
      close = new SystemMenuClose(site);
      //      menuManager.add(close);
      site.addSystemActions(menuManager);
      // TODO Auto-generated constructor stub
   }

   String getMoveMenuText() {
      return WorkbenchMessages.EditorPane_moveEditor;
      //	   return "";
   }

   public void show(Control parent, Point displayCoordinates, IPresentablePart currentSelection) {
      //     super.show(parent, displayCoordinates, currentSelection);

      //	   SystemMenuClose close = new SystemMenuClose(siteMy);
      close.setTarget(currentSelection);
      //	   Menu aMenu = menuManager.createContextMenu(parent);
      //       menuManager.update(true);
      //       aMenu.setLocation(displayCoordinates.x, displayCoordinates.y);
      //       aMenu.setVisible(true);
   }

   public void dispose() {
      // TODO Auto-generated method stub

   }
}
