package com.liusy.analysismodel.template.actions;

import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class SelfSaveAction extends SaveAction {

	public SelfSaveAction(IEditorPart editor) {
		super(editor);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		page.getActiveEditor().doSave(null);
	}
}
