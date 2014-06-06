package com.liusy.analysismodel.template.parts;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

public class ObjectPropertyDialog extends Dialog{
	//用来定制的bean
	private IPropertySource bean;
	public ObjectPropertyDialog(Shell parent,IPropertySource bean) {
		super(parent);
		this.bean=bean;
	}
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		GridLayout gl=new GridLayout();
		gl.verticalSpacing = 8;
		composite.setLayout(gl);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label nameLabel=new Label(composite,SWT.NONE);
		GridData listGridData = new GridData(SWT.LEFT,SWT.TOP,true,false,1,1);
		listGridData.minimumWidth=240;
		nameLabel.setLayoutData(listGridData);
		PropertySheetPage psp = new PropertySheetPage();
		psp.createControl(composite);
		psp.setRootEntry(new PropertySheetEntry());
		GridData gridData=new GridData(GridData.FILL_BOTH);
		psp.getControl().setLayoutData(gridData);
		psp.selectionChanged(null,new StructuredSelection(bean));
		nameLabel.setText("类:"+bean.getClass().getName());
		return composite;	
	}
	protected Point getInitialSize() {
		return new Point(400,320);
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("属性查看器");
	}
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,true);
	}
}
