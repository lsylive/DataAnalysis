package com.liusy.analysismodel;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.presentations.util.ISystemMenu;
import org.eclipse.ui.presentations.IPresentablePart;
import org.eclipse.ui.presentations.IStackPresentationSite;

public class MyViewSystemMenu implements ISystemMenu {
	MenuManager menuManager = new MenuManager();

	/**
	 * Create the standard view menu
	 * 
	 * @param site
	 *            the site to associate the view with
	 */
	public MyViewSystemMenu(IStackPresentationSite site) {
		site.addSystemActions(menuManager);
	}

	public String getMoveMenuText() {
		return WorkbenchMessages.ViewPane_moveView;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		menuManager.dispose();
		menuManager.removeAll();

	}

	public void show(Control control, Point displayCoordinates,
			IPresentablePart currentSelection) {
		// TODO Auto-generated method stub
		Menu aMenu = menuManager.createContextMenu(control);
		menuManager.removeAll();
		aMenu.setLocation(displayCoordinates.x, displayCoordinates.y);
		aMenu.setVisible(false);
	}

}
