package com.liusy.analysismodel.log.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class LogonLogDetailDlg extends Dialog {

	private Text logoutResult;
	private Text logoutTime;
	private Text logonResult;
	private Text logonTime;
	private Text ipAdress;
	private Text deptName;
	private Text orgName;
	private Text userName;
	private Text userAcount;
	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public LogonLogDetailDlg(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public LogonLogDetailDlg(Shell parent) {
		this(parent, SWT.NONE);
		createContents();
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public Object open() {
		shell.setLocation(getParentLocation());
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setLayout(new FormLayout());
		shell.setSize(480, 250);
		shell.setText("SWT Dialog");

		final Group group = new Group(shell, SWT.NONE);
		final FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(100, -5);
		fd_group.top = new FormAttachment(0, 0);
		fd_group.right = new FormAttachment(50, 0);
		fd_group.left = new FormAttachment(0, 5);
		group.setLayoutData(fd_group);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 22;
		gridLayout.numColumns = 2;
		group.setLayout(gridLayout);

		final Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("用户帐号：");

		userAcount = new Text(group, SWT.BORDER);
		final GridData gd_userAcount = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		userAcount.setLayoutData(gd_userAcount);

		final Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("用户名称：");

		userName = new Text(group, SWT.BORDER);
		final GridData gd_userName = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		userName.setLayoutData(gd_userName);

		final Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("机构名称：");

		orgName = new Text(group, SWT.BORDER);
		final GridData gd_orgName = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_orgName.widthHint = 120;
		orgName.setLayoutData(gd_orgName);

		final Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("部门名称：");

		deptName = new Text(group, SWT.BORDER);
		final GridData gd_deptName = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_deptName.widthHint = 120;
		deptName.setLayoutData(gd_deptName);

		final Group group_1 = new Group(shell, SWT.NONE);
		final FormData fd_group_1 = new FormData();
		fd_group_1.bottom = new FormAttachment(100, -5);
		fd_group_1.top = new FormAttachment(0, 0);
		fd_group_1.right = new FormAttachment(100, -5);
		fd_group_1.left = new FormAttachment(50, 0);
		group_1.setLayoutData(fd_group_1);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.verticalSpacing = 22;
		gridLayout_1.numColumns = 2;
		group_1.setLayout(gridLayout_1);

		final Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setText("IP地址：");

		ipAdress = new Text(group_1, SWT.BORDER);
		final GridData gd_ipAdress = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_ipAdress.widthHint = 90;
		ipAdress.setLayoutData(gd_ipAdress);

		final Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setText("登陆时间：");

		logonTime = new Text(group_1, SWT.BORDER);
		final GridData gd_logonTime = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logonTime.widthHint = 90;
		logonTime.setLayoutData(gd_logonTime);

		final Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setText("登陆结果：");

		logonResult = new Text(group_1, SWT.BORDER);
		final GridData gd_logonResult = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logonResult.widthHint = 120;
		logonResult.setLayoutData(gd_logonResult);

		final Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setText("登出时间：");

		logoutTime = new Text(group_1, SWT.BORDER);
		final GridData gd_logoutTime = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logoutTime.widthHint = 120;
		logoutTime.setLayoutData(gd_logoutTime);

		final Label label = new Label(group_1, SWT.NONE);
		label.setText("登出结果：");

		logoutResult = new Text(group_1, SWT.BORDER);
		final GridData gd_logoutResult = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logoutResult.widthHint = 120;
		logoutResult.setLayoutData(gd_logoutResult);
		//
	}
	public Point getParentLocation() {
		Shell shell = getParent();
		int x = shell.getLocation().x;
		int y = shell.getLocation().y;
		Rectangle rectParent = shell.getBounds();
		Point size = this.shell.getSize();
		int xx = x+rectParent.width/2 - size.x/2;
		int yy = y+rectParent.height/2- size.y/2;
		return new Point(xx,yy);
	}


	public void setLogoutResult(Text logoutResult) {
		this.logoutResult = logoutResult;
	}


	public void setLogoutTime(Text logoutTime) {
		this.logoutTime = logoutTime;
	}


	public void setLogonResult(Text logonResult) {
		this.logonResult = logonResult;
	}


	public void setLogonTime(Text logonTime) {
		this.logonTime = logonTime;
	}


	public void setIpAdress(Text ipAdress) {
		this.ipAdress = ipAdress;
	}


	public void setDeptName(Text deptName) {
		this.deptName = deptName;
	}


	public void setOrgName(Text orgName) {
		this.orgName = orgName;
	}


	public void setUserName(Text userName) {
		this.userName = userName;
	}


	public void setUserAcount(Text userAcount) {
		this.userAcount = userAcount;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

   public Text getLogoutResult() {
      return logoutResult;
   }

   public Text getLogoutTime() {
      return logoutTime;
   }

   public Text getLogonResult() {
      return logonResult;
   }

   public Text getLogonTime() {
      return logonTime;
   }

   public Text getIpAdress() {
      return ipAdress;
   }

   public Text getDeptName() {
      return deptName;
   }

   public Text getOrgName() {
      return orgName;
   }

   public Text getUserName() {
      return userName;
   }

   public Text getUserAcount() {
      return userAcount;
   }

	

}
