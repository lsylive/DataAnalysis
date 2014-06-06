package com.liusy.analysismodel.menubar;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import com.liusy.analysismodel.log.IdCommands;
import com.liusy.analysismodel.log.dialog.ExportLogDlg;

public class LogExportAction extends Action {


	private final IWorkbenchWindow window;
	private final String viewId;
	public LogExportAction(IWorkbenchWindow window, String label,String viewId){
		this.window=window;
		this.viewId=viewId;
		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(IdCommands.CMD_SYSTEMLOG);
	}
	
	
	public void run() {
		if(window != null) {	
//			AddPatientInforWizard wizard = new AddPatientInforWizard();
//			WizardDialog dialog = new WizardDialog(window.getShell(), wizard);
//			dialog.setPageSize(-1, 130);
//			dialog.open();
			ExportLogDlg dlg =new ExportLogDlg(window.getShell());
			dlg.open();
//			try {
//				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//				IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.perspective");
//				page.setPerspective(perspective);
//				page.showView(viewId);
//				
//			} catch (PartInitException e) {
//				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
//			}
		}
	}


}
