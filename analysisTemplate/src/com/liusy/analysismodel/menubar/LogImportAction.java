package com.liusy.analysismodel.menubar;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import com.liusy.analysismodel.log.dialog.ImportLogDlg;

public class LogImportAction extends Action {


	private final IWorkbenchWindow window;
	private final String viewId;
	public LogImportAction(IWorkbenchWindow window, String label,String viewId){
		this.window=window;
		this.viewId=viewId;
		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(viewId);
	}
	
	
	public void run() {
		if(window != null) {
			ImportLogDlg dlg =new ImportLogDlg(window.getShell());
			dlg.open();
//			try {
//				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//				IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.perspective");
//				page.setPerspective(perspective);
//				page.showView(viewId);				
//			} catch (PartInitException e) {
//				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
//			}
		}
	}


}
