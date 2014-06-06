package com.liusy.analysismodel.template.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.liusy.analysismodel.Activator;

public class SaveDiagramAction extends Action {

	private final IWorkbenchWindow window;
	public SaveDiagramAction(IWorkbenchWindow window, String label) {
		this.window = window;
		setText(label);
		setId("333");
		ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID,"/src/com/thunisoft/dataplatform/template/image/png16/saveBar.png");
   	this.setImageDescriptor(imageDes);
	}
	public void run() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		page.getActiveEditor().doSave(null);
	}

}
