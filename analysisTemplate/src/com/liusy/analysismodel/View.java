package com.liusy.analysismodel;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.liusy.analysismodel.log.view.ApplayLogView;

import com.swtdesigner.SWTResourceManager;

public class View extends ViewPart {
	public static final String ID = "DataAdminPlatform.view";
	private Action closeAction;
	



	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		Composite container = new Composite(parent, SWT.NONE);
		final RowLayout rowLayout = new RowLayout();
		rowLayout.justify = true;
		container.setLayout(rowLayout);
		final FormData fd_container = new FormData();
		fd_container.bottom = new FormAttachment(60, 0);
		fd_container.top = new FormAttachment(40, 0);
		fd_container.right = new FormAttachment(85, 0);
		fd_container.left = new FormAttachment(15, 0);
		container.setLayoutData(fd_container);

		final Label introductionLab = new Label(container, SWT.NONE);
		introductionLab.setFont(SWTResourceManager.getFont("", 36, SWT.NONE));
//		introductionLab.setFont(new Font()); //$NON-NLS-1$
		introductionLab.setText("欢迎使用本系统"); //$NON-NLS-1$
		//
		
		closeAction = new Action("close",AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID, "/src/com/thunisoft/dataplatform/template/image/delete.png")) {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.perspective");
				page.setPerspective(perspective);
				page.hideView(page.findView(ID));
		}
		};
		initializeToolBar();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		toolBarManager.add(closeAction);
	}
}