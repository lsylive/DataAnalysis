package com.liusy.analysismodel.menubar;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import com.liusy.analysismodel.log.IdCommands;

public class AnalysisModelAction extends Action {
	private final IWorkbenchWindow window;
	private int instanceNum = 0;
	private final String viewId;
	public AnalysisModelAction(IWorkbenchWindow window, String label,String viewId){
		this.window=window;
		this.viewId=viewId;
		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(IdCommands.CMD_SYSTEMLOG);
		
	}
	
	
	public void run() {
		if(window != null) {	
			//********************************
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.analysisPerspective");
				page.setPerspective(perspective);
				page.showView(viewId, null, IWorkbenchPage.VIEW_ACTIVATE);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "错误", "打开模板错误：" + e.getMessage());
			}
			
			IPath path= Platform.getLocation();//得到workspace的路径
//			File file=path.append("xing.txt").toFile();//这步将eclipse的IPath转换化java中的File.
			
//			IPath folederPath = path.append("analisis");
//			File folder2 = folederPath.toFile();
//			if (!folder2.exists()) {
//				folder2.mkdir();
//			}
			
//			Bundle bundle = Platform.getBundle("DataAdminPlatform");
			
//			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//			IResource resource = root.findMember(folederPath);
//			IProject project = resource.getProject();
//			IFolder folder = project.getFolder(folederPath);
			
			
//			IFolder rootFolder  = new IFolder(bundle.getResource("analisis").toString());
			
			
			
//			IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("DataAdminPlatform");
//			IFolder folder = project.getFolder(new Path("analisis"));
//			boolean isSysnc = folder.isSynchronized(IResource.DEPTH_ONE);
//			if (!folder.exists()) {
//				try{
////					folder.create(true, true, null);
//					folder.createLink(path.append("xing.txt"), IResource.ALLOW_MISSING_LOCAL, null);
//					
//				} catch(Exception e) {
//					e.printStackTrace();
//				}
//			}
//			SampleNewWizard wizard = new SampleNewWizard(path.append("xing.txt").toOSString());
//			wizard.init(PlatformUI.getWorkbench(), null);
//			WizardDialog dialog = new WizardDialog(IDEWorkbenchPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell(),wizard);
//			dialog.open();

		}
	}
}
