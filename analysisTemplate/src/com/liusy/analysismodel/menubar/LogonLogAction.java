package com.liusy.analysismodel.menubar;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import com.liusy.analysismodel.log.IdCommands;


/**
 * µÇÂ¼ÈÕÖ¾
 * @author andy
 *
 */
public class LogonLogAction extends Action{

	private final IWorkbenchWindow window;
	private int instanceNum = 0;
	private final String viewId;
	private String title;
	public LogonLogAction(IWorkbenchWindow window, String label,String viewId){
		this.window=window;
		this.viewId=viewId;
		this.title = label;
		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(IdCommands.CMD_SYSTEMLOG);
		
	}
	
	
	public void run() {
		if(window != null) {	
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.perspective");
				page.setPerspective(perspective);
//				page.hideView(page.getViews()[0]);
				
//				page.hideView(page.getViewReferences()[0].getView(true));
				
				page.showView(viewId, null, IWorkbenchPage.VIEW_ACTIVATE);
				window.getShell().setText(title);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
			}
		}
	}

}
