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
 * 系统日志action
 * @author andy
 *
 */
public class SystemLogAction extends Action {
	private final IWorkbenchWindow window;
	private int instanceNum = 0;
	private final String viewId;
	public SystemLogAction(IWorkbenchWindow window, String label,String viewId){
		this.window=window;
		this.viewId=viewId;
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
				page.showView(viewId);
				
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
			}
		}
	}
}
