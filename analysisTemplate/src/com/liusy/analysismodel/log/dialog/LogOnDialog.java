package com.liusy.analysismodel.log.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
public class LogOnDialog extends Dialog {
	private Text text = null;

    private Text text_1 = null;

	   public boolean isCorrect = false;

	   private Label label_2 = null;
	   public static String userName=null;
	   public static String passWord=null;

	   protected Control createDialogArea(Composite parent) {
	       getShell().setText("用户登录");
	       Composite container = (Composite) super.createDialogArea(parent);
	       container.setLayout(new FillLayout());
	       final Composite composite = new Composite(container, SWT.NONE);
	       Label label_0 = new Label(composite, SWT.NONE);
	       label_0.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false,
	               2, 1));
	       final GridLayout gridLayout = new GridLayout();
	       gridLayout.numColumns = 2;
	       composite.setLayout(gridLayout);
	       Label label = new Label(composite, SWT.NONE);
	       label.setText("用户名：");
	       text = new Text(composite, SWT.BORDER);
	       GridData gridData = new GridData();
	       gridData.widthHint = 150;
	       text.setLayoutData(gridData);
	       Label label_1 = new Label(composite, SWT.NONE);
	       label_1.setText("密 码：");
	       text_1 = new Text(composite, SWT.PASSWORD | SWT.BORDER);
	       text_1.setLayoutData(gridData);
	       Label label_x=new Label(composite, SWT.NONE); 
	       label_x.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false,
	               2, 1));
	       label_2 = new Label(composite, SWT.NONE);
	       label_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false,
	               2, 1));
	       label_2.setText(" 用户名或密码不正确，请重新输入！");
	       label_2.setVisible(false);
	      // label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	       return container;
	   }

	   public LogOnDialog(Shell parentShell) {
	       super(parentShell);
	   }

	   public LogOnDialog(IShellProvider parentShell) {
	       super(parentShell);
	   }

	   protected Point getInitialSize() {
	       Point p = super.getInitialSize();
	       p.x = 235;//235
	       p.y = 175;
	       return p;
	   }

	   protected void createButtonsForButtonBar(Composite parent) {
	       createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
	               true);
	       createButton(parent, 205, "重设", false);
	   }

	   protected void buttonPressed(int buttonId) {
	       if (buttonId == IDialogConstants.OK_ID) {//按确定时
	           if (text.getText().equals("admin")&&text_1.getText(
	                  ).equals("123")) {//此用户名密码直接给的，若验证数据库需改
	               label_2.setVisible(false);
	               isCorrect = true;
	               userName=text.getText();
	               passWord=text_1.getText();
	           } else {
	               label_2.setVisible(true);
	               return;
	           }

	       } else if (buttonId == 205) {
	           label_2.setVisible(false);
	           text.setText("");
	           text_1.setText("");
	       }
	       super.buttonPressed(buttonId);
	   }

}
