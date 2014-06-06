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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UserAudioLogDetailDlg extends Dialog {

	private Text list;
	private Text dataOriginal;
	private Text logoutResult;
	private Text logoutTime;
	private Text optTime;
	private Text optType;
	private Text resName;
	private Text ipAdress;
	private Text logonResult;
	private Text logonTime;
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
	public UserAudioLogDetailDlg(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public UserAudioLogDetailDlg(Shell parent) {
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
		shell.setSize(480, 405);
		shell.setText("SWT Dialog");

		final Group group = new Group(shell, SWT.NONE);
		final FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(78, 0);
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

		final Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("登陆时间：");

		logonTime = new Text(group, SWT.BORDER);
		final GridData gd_logonTime = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logonTime.widthHint = 120;
		logonTime.setLayoutData(gd_logonTime);

		final Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("登陆结果：");

		logonResult = new Text(group, SWT.BORDER);
		final GridData gd_logonResult = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logonResult.widthHint = 120;
		logonResult.setLayoutData(gd_logonResult);

		final Group group_1 = new Group(shell, SWT.NONE);
		final FormData fd_group_1 = new FormData();
		fd_group_1.bottom = new FormAttachment(78, 0);
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
		label_6.setText("资源名称：");

		resName = new Text(group_1, SWT.BORDER);
		final GridData gd_resName = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_resName.widthHint = 90;
		resName.setLayoutData(gd_resName);

		final Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setText("操作类型：");

		optType = new Text(group_1, SWT.BORDER);
		final GridData gd_optType = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_optType.widthHint = 120;
		optType.setLayoutData(gd_optType);

		final Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setText("操作时间：");

		optTime = new Text(group_1, SWT.BORDER);
		final GridData gd_optTime = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_optTime.widthHint = 120;
		optTime.setLayoutData(gd_optTime);

		final Label label_11 = new Label(group_1, SWT.NONE);
		label_11.setText("登出时间：");

		logoutTime = new Text(group_1, SWT.BORDER);
		final GridData gd_logoutTime = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logoutTime.widthHint = 120;
		logoutTime.setLayoutData(gd_logoutTime);

		final Label label_12 = new Label(group_1, SWT.NONE);
		label_12.setText("登出结果：");

		logoutResult = new Text(group_1, SWT.BORDER);
		final GridData gd_logoutResult = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_logoutResult.widthHint = 120;
		logoutResult.setLayoutData(gd_logoutResult);

		final Label label_13 = new Label(group_1, SWT.NONE);
		label_13.setText("数据来源：");

		dataOriginal = new Text(group_1, SWT.BORDER);
		final GridData gd_dataOriginal = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_dataOriginal.widthHint = 120;
		dataOriginal.setLayoutData(gd_dataOriginal);

		final Group group_2 = new Group(shell, SWT.NONE);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 2;
		group_2.setLayout(gridLayout_2);
		final FormData fd_group_2 = new FormData();
		fd_group_2.top = new FormAttachment(77, 0);
		fd_group_2.bottom = new FormAttachment(100, -5);
		fd_group_2.right = new FormAttachment(100, -5);
		fd_group_2.left = new FormAttachment(0, 5);
		group_2.setLayoutData(fd_group_2);

		final Label label = new Label(group_2, SWT.NONE);
		final GridData gd_label = new GridData();
		gd_label.horizontalIndent = 8;
		label.setLayoutData(gd_label);
		label.setText("查询条件：");

		list = new Text(group_2, SWT.V_SCROLL | SWT.MULTI | SWT.CENTER | SWT.BORDER);
		final GridData gd_list = new GridData(345, 40);
		list.setLayoutData(gd_list);
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



	public void setOptTime(Text optTime) {
		this.optTime = optTime;
	}


	public void setOptType(Text optType) {
		this.optType = optType;
	}


	public void setResName(Text resName) {
		this.resName = resName;
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


	public void setList(Text list) {
		this.list = list;
	}


	public void setDataOriginal(Text dataOriginal) {
		this.dataOriginal = dataOriginal;
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

   public Text getList() {
      return list;
   }

   public Text getDataOriginal() {
      return dataOriginal;
   }

   public Text getLogoutResult() {
      return logoutResult;
   }

   public Text getLogoutTime() {
      return logoutTime;
   }

   public Text getOptTime() {
      return optTime;
   }

   public Text getOptType() {
      return optType;
   }

   public Text getResName() {
      return resName;
   }

   public Text getIpAdress() {
      return ipAdress;
   }

   public Text getLogonResult() {
      return logonResult;
   }

   public Text getLogonTime() {
      return logonTime;
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
