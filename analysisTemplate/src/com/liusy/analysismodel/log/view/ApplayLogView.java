package com.liusy.analysismodel.log.view;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.log.dao.LogOperateSql;
import com.liusy.analysismodel.log.dao.formBean.LogOperateBean;
import com.liusy.analysismodel.log.listener.ApplayLogListener;
import com.liusy.analysismodel.log.model.log.LogOperate;
import com.liusy.analysismodel.log.service.provider.LogMonitorProvider;
import com.liusy.analysismodel.log.view.labelProvider.ApplayLogViewLabelProvider;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.util.PageBean;

import com.swtdesigner.SWTResourceManager;
import com.liusy.analysismodel.Activator;

public class ApplayLogView extends ViewPart {
	private Button					startBtn;
	private Button					previewBtn;
	private Button					nextBtn;
	private Button					endBtn;
	private Button					goBtn;
	private Text					desPage;
	private Text					tolPageNum;
	private Text					pageNum;
	private TableViewer			viewer;
	private Table					table_1;
	private Button					endDateBtn;
	private Text					endDate;
	private Button					startDateBtn;
	private Text					startDate;
	private Button					clearBtn;
	private Button					searchBtn;
	private Combo					deptNameCombo;
	private Combo					orgNameCombo;
	private Text					userName;
	private Text					userAcount;
	private boolean				reSearchFlg	= false;
	private Action					searchAction;
	private Action					clearAction;
	private Action					nextAction;
	private Action					previewAction;
	private Action					startAction;
	private Action					endAction;
	private Action					goAction;
	private Action					closeAction;
	private Action					getComboContentAction;
	private Action					getDeptComboContentAction;
	private PageBean				pageBean;
	private LogMonitorProvider	logProvider;
	private String					serachSql;
	private LogOperateBean		logonBean;
	private LogOperateSql		sqlBuilder;
	public static final String	ID				= "DataAdminPlatform.log.view.ApplayLogView";
	private int						x				= 0, y = 0;
	private boolean				flagX			= false, flagY = false;
	private boolean				flagW			= true, flagH = true;
	private Vector<LogOperate>	logList;
	//隐藏变量，保存检索条件的值
	private String					userAcountHd;
	private String					userNameHd;
	private String					depNameHd;
	private String					orgNameHd;
	private String					startDateHd;
	private String					endDateHd;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	public class FocusListioner extends FocusAdapter {

		public void focusLost(FocusEvent focusevent) {
			if (!userName.getText().trim().equals(userAcountHd) || !userAcount.getText().trim().equals(userNameHd) || !deptNameCombo.getText().trim().equals(depNameHd)
					|| !orgNameCombo.getText().trim().equals(orgNameHd)) {
				reSearchFlg = true;
			}
			else {
				reSearchFlg = false;
			}
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		final Group group = new Group(parent, SWT.NONE);
		final FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(0, 100);
		fd_group.top = new FormAttachment(0, 0);
		fd_group.left = new FormAttachment(0, 5);
		fd_group.right = new FormAttachment(100, -5);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		final Label label = new Label(group, SWT.RIGHT);
		final FormData gd_label = new FormData();
		gd_label.top = new FormAttachment(24, 0);
		gd_label.right = new FormAttachment(10, 0);
		gd_label.left = new FormAttachment(0, 5);
		label.setLayoutData(gd_label);
		label.setText("用户帐号：");

		userAcount = new Text(group, SWT.BORDER);
		final FormData fd_userAcount = new FormData();
		fd_userAcount.right = new FormAttachment(20, 0);
		fd_userAcount.top = new FormAttachment(20, 0);
		fd_userAcount.left = new FormAttachment(10, 0);
		userAcount.setLayoutData(fd_userAcount);

		final Label label_8 = new Label(group, SWT.RIGHT);
		final FormData gd_label_8 = new FormData();
		gd_label_8.right = new FormAttachment(30, 0);
		gd_label_8.left = new FormAttachment(20, 0);
		gd_label_8.top = new FormAttachment(24, 0);
		label_8.setLayoutData(gd_label_8);
		label_8.setText("用户姓名：");

		userName = new Text(group, SWT.BORDER);
		final FormData fd_userName = new FormData();
		fd_userName.right = new FormAttachment(40, 0);
		fd_userName.left = new FormAttachment(30, 0);
		fd_userName.top = new FormAttachment(20, 0);
		userName.setLayoutData(fd_userName);

		final Label label_1 = new Label(group, SWT.RIGHT);
		final FormData gd_label_1 = new FormData();
		gd_label_1.right = new FormAttachment(53, 0);
		gd_label_1.left = new FormAttachment(43, 0);
		gd_label_1.top = new FormAttachment(24, 0);
		label_1.setLayoutData(gd_label_1);
		label_1.setText("机构名称：");

		orgNameCombo = new Combo(group, SWT.READ_ONLY);
		final FormData fd_orgNameCombo = new FormData();
		fd_orgNameCombo.right = new FormAttachment(71, 0);
		fd_orgNameCombo.left = new FormAttachment(53, 0);
		fd_orgNameCombo.top = new FormAttachment(20, 0);
		orgNameCombo.setLayoutData(fd_orgNameCombo);

		final Label label_2 = new Label(group, SWT.RIGHT);
		final FormData gd_label_2 = new FormData();
		gd_label_2.right = new FormAttachment(80, 0);
		gd_label_2.left = new FormAttachment(71, 0);
		gd_label_2.top = new FormAttachment(24, 0);
		label_2.setLayoutData(gd_label_2);
		label_2.setText("部门名称：");

		deptNameCombo = new Combo(group, SWT.READ_ONLY);
		final FormData fd_deptNameCombo = new FormData();
		fd_deptNameCombo.left = new FormAttachment(80, 0);
		fd_deptNameCombo.top = new FormAttachment(20, 0);
		fd_deptNameCombo.right = new FormAttachment(98, 0);
		deptNameCombo.setLayoutData(fd_deptNameCombo);

		final Label label_7 = new Label(group, SWT.RIGHT);
		final FormData gd_label_7 = new FormData();
		gd_label_7.right = new FormAttachment(10, 0);
		gd_label_7.left = new FormAttachment(0, 5);
		gd_label_7.top = new FormAttachment(64, 0);
		label_7.setLayoutData(gd_label_7);
		label_7.setText("操作时间：");

		startDate = new Text(group, SWT.CENTER | SWT.BORDER);
		//		gd_label_7.top = new FormAttachment(startDate, -12, SWT.BOTTOM);
		//		gd_label_7.bottom = new FormAttachment(startDate, 0, SWT.BOTTOM);
		final FormData gd_startDate = new FormData();
		gd_startDate.right = new FormAttachment(20, 0);
		gd_startDate.left = new FormAttachment(10, 0);
		gd_startDate.top = new FormAttachment(60, 0);
		startDate.setLayoutData(gd_startDate);
		startDate.setEditable(false);

		startDateBtn = new Button(group, SWT.NONE);
		startDateBtn.setImage(SWTResourceManager.getImage(ApplayLogView.class, "/com/thunisoft/dataplatform/template/image/data.png"));
		final FormData fd_startDateBtn = new FormData();
		fd_startDateBtn.right = new FormAttachment(20, 20);
		fd_startDateBtn.left = new FormAttachment(20, 0);
		fd_startDateBtn.height = 20;
		//		fd_startDateBtn.width = 20;
		fd_startDateBtn.top = new FormAttachment(60, 0);
		startDateBtn.setLayoutData(fd_startDateBtn);

		final Label label_10 = new Label(group, SWT.CENTER);
		final FormData gd_label_10 = new FormData();
		gd_label_10.right = new FormAttachment(25, 0);
		gd_label_10.left = new FormAttachment(20, 20);
		gd_label_10.top = new FormAttachment(60, 2);
		label_10.setLayoutData(gd_label_10);
		label_10.setText("-");

		endDate = new Text(group, SWT.CENTER | SWT.BORDER);
		final FormData gd_endDate = new FormData();
		gd_endDate.right = new FormAttachment(35, 0);
		gd_endDate.left = new FormAttachment(25, 0);
		gd_endDate.top = new FormAttachment(60, 0);
		endDate.setLayoutData(gd_endDate);
		endDate.setEditable(false);

		endDateBtn = new Button(group, SWT.NONE);
		endDateBtn.setImage(SWTResourceManager.getImage(ApplayLogView.class, "/com/thunisoft/dataplatform/template/image/data.png"));
		final FormData fd_endDateBtn = new FormData();
		fd_endDateBtn.right = new FormAttachment(35, 20);
		fd_endDateBtn.left = new FormAttachment(35, 0);
		fd_endDateBtn.height = 20;
		//		fd_endDateBtn.width = 20;
		fd_endDateBtn.top = new FormAttachment(60, 0);
		endDateBtn.setLayoutData(fd_endDateBtn);

		searchBtn = new Button(group, SWT.NONE);
		final FormData gd_searchBtn = new FormData();
		gd_searchBtn.right = new FormAttachment(74, 0);
		gd_searchBtn.left = new FormAttachment(66, 0);
		gd_searchBtn.top = new FormAttachment(60, 0);
		searchBtn.setLayoutData(gd_searchBtn);
		searchBtn.setText("查询");

		clearBtn = new Button(group, SWT.NONE);
		final FormData gd_clearBtn = new FormData();
		gd_clearBtn.left = new FormAttachment(90, 0);
		gd_clearBtn.right = new FormAttachment(98, 0);
		gd_clearBtn.top = new FormAttachment(60, 0);
		clearBtn.setLayoutData(gd_clearBtn);
		clearBtn.setText("重置");

		final Group group_1 = new Group(parent, SWT.NONE);
		final FormData fd_group_1 = new FormData();
		fd_group_1.top = new FormAttachment(group, 0, SWT.DEFAULT);
		fd_group_1.left = new FormAttachment(0, 5);
		fd_group_1.right = new FormAttachment(100, -5);
		group_1.setLayoutData(fd_group_1);
		group_1.setLayout(new FormLayout());

		Group group_2;
		group_2 = new Group(parent, SWT.NONE);
		fd_group_1.bottom = new FormAttachment(group_2, 0, SWT.DEFAULT);

		viewer = new TableViewer(group_1, SWT.FULL_SELECTION);
		viewer.setLabelProvider(new ApplayLogViewLabelProvider());
		viewer.setContentProvider(new ViewContentProvider());
		table_1 = viewer.getTable();
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -5);
		fd_table.top = new FormAttachment(0, 0);
		fd_table.right = new FormAttachment(100, -5);
		fd_table.left = new FormAttachment(0, 5);
		table_1.setLayoutData(fd_table);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);

		final TableColumn firstColumn = new TableColumn(table_1, SWT.NONE);
		firstColumn.setWidth(0);
		firstColumn.setText("New column");

		final TableColumn newColumnTableColumn_2 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_2.setAlignment(SWT.CENTER);
		newColumnTableColumn_2.setWidth(80);
		newColumnTableColumn_2.setText("用户账号");

		final TableColumn newColumnTableColumn = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn.setAlignment(SWT.CENTER);
		newColumnTableColumn.setWidth(80);
		newColumnTableColumn.setText("用户姓名");

		final TableColumn newColumnTableColumn_6 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_6.setAlignment(SWT.CENTER);
		newColumnTableColumn_6.setWidth(150);
		newColumnTableColumn_6.setText("机构名称");

		final TableColumn newColumnTableColumn_7 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_7.setAlignment(SWT.CENTER);
		newColumnTableColumn_7.setWidth(115);
		newColumnTableColumn_7.setText("部门名称");

		final TableColumn newColumnTableColumn_8 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_8.setAlignment(SWT.CENTER);
		newColumnTableColumn_8.setWidth(120);
		newColumnTableColumn_8.setText("资源");

		final TableColumn newColumnTableColumn_9 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_9.setAlignment(SWT.CENTER);
		newColumnTableColumn_9.setWidth(80);
		newColumnTableColumn_9.setText("操作类型");

		final TableColumn newColumnTableColumn_10 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_10.setAlignment(SWT.CENTER);
		newColumnTableColumn_10.setWidth(90);
		newColumnTableColumn_10.setText("IP地址");

		final TableColumn newColumnTableColumn_11 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_11.setAlignment(SWT.CENTER);
		newColumnTableColumn_11.setWidth(150);
		newColumnTableColumn_11.setText("操作时间");

		final TableColumn newColumnTableColumn_4 = new TableColumn(table_1, SWT.NONE);
		newColumnTableColumn_4.setAlignment(SWT.CENTER);
		newColumnTableColumn_4.setWidth(300);
		newColumnTableColumn_4.setText("查询条件");

		final FormData fd_group_2 = new FormData();
		fd_group_2.top = new FormAttachment(100, -48);
		fd_group_2.bottom = new FormAttachment(100, -5);
		fd_group_2.right = new FormAttachment(100, -5);
		fd_group_2.left = new FormAttachment(0, 5);
		group_2.setLayoutData(fd_group_2);
		group_2.setLayout(new FormLayout());

		final Label label_3 = new Label(group_2, SWT.RIGHT);
		final FormData gd_label_3 = new FormData();
		gd_label_3.top = new FormAttachment(0, 5);
		gd_label_3.right = new FormAttachment(10, 0);
		gd_label_3.left = new FormAttachment(0, 5);
		label_3.setLayoutData(gd_label_3);
		label_3.setText("当前页数：");

		pageNum = new Text(group_2, SWT.BORDER);
		final FormData fd_pageNum = new FormData();
		fd_pageNum.top = new FormAttachment(0, 2);
		fd_pageNum.right = new FormAttachment(18, 0);
		fd_pageNum.left = new FormAttachment(10, 0);
		pageNum.setLayoutData(fd_pageNum);
		pageNum.setEditable(false);

		final Label label_4 = new Label(group_2, SWT.RIGHT);
		final FormData gd_label_4 = new FormData();
		gd_label_4.top = new FormAttachment(0, 5);
		gd_label_4.right = new FormAttachment(26, 0);
		gd_label_4.left = new FormAttachment(18, 0);
		label_4.setLayoutData(gd_label_4);
		label_4.setText("总页数：");

		tolPageNum = new Text(group_2, SWT.BORDER);
		final FormData fd_tolPageNum = new FormData();
		fd_tolPageNum.top = new FormAttachment(0, 2);
		fd_tolPageNum.right = new FormAttachment(34, 0);
		fd_tolPageNum.left = new FormAttachment(26, 0);
		tolPageNum.setLayoutData(fd_tolPageNum);
		tolPageNum.setEditable(false);

		final Label label_5 = new Label(group_2, SWT.RIGHT);
		final FormData gd_label_5 = new FormData();
		gd_label_5.top = new FormAttachment(0, 5);
		gd_label_5.right = new FormAttachment(45, 0);
		gd_label_5.left = new FormAttachment(34, 0);
		label_5.setLayoutData(gd_label_5);
		label_5.setText("跳到指定页：");

		desPage = new Text(group_2, SWT.BORDER);
		final FormData fd_desPage = new FormData();
		fd_desPage.top = new FormAttachment(0, 2);
		fd_desPage.left = new FormAttachment(45, 0);
		fd_desPage.right = new FormAttachment(53, 0);
		desPage.setLayoutData(fd_desPage);

		goBtn = new Button(group_2, SWT.NONE);
		final FormData fd_goBtn = new FormData();
		fd_goBtn.left = new FormAttachment(53, 0);
		fd_goBtn.right = new FormAttachment(58, 0);
		goBtn.setLayoutData(fd_goBtn);
		goBtn.setText("跳转");

		startBtn = new Button(group_2, SWT.NONE);
		final FormData fd_startBtn = new FormData();
		fd_startBtn.right = new FormAttachment(74, 0);
		fd_startBtn.left = new FormAttachment(66, 0);
		startBtn.setLayoutData(fd_startBtn);
		startBtn.setText("第一页");

		previewBtn = new Button(group_2, SWT.NONE);
		final FormData fd_previewBtn = new FormData();
		fd_previewBtn.left = new FormAttachment(74, 0);
		fd_previewBtn.right = new FormAttachment(82, 0);
		previewBtn.setLayoutData(fd_previewBtn);
		previewBtn.setText("上一页");

		nextBtn = new Button(group_2, SWT.NONE);
		final FormData fd_nextBtn = new FormData();
		fd_nextBtn.right = new FormAttachment(90, 0);
		fd_nextBtn.left = new FormAttachment(82, 0);
		nextBtn.setLayoutData(fd_nextBtn);
		nextBtn.setText("下一页");

		endBtn = new Button(group_2, SWT.NONE);
		final FormData fd_endBtn = new FormData();
		fd_endBtn.left = new FormAttachment(90, 0);
		fd_endBtn.right = new FormAttachment(98, 0);
		endBtn.setLayoutData(fd_endBtn);
		endBtn.setText("最后页");

		makeAddAction();
		addListener();
		initializeToolBar();
	}

	private void makeAddAction() {
		//		logonBean = new LogOperateBean(this.userAcount,this.userName,this.depName,this.orgName,this.startDate,this.endDate);
		logProvider = new LogMonitorProvider();
		logList = new Vector<LogOperate>();
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ApplayLogViewLabelProvider());
		viewer.setInput(logList);
		sqlBuilder = new LogOperateSql();
		pageBean = new PageBean();
		pageBean.setPageRecordCount(200);
		pageBean.setEnd(20);
		searchAction = new Action() {
			public void run() {
				try {
					String countSql = sqlBuilder.getRecordCount(logonBean, pageBean);
					int recordCount = logProvider.countLogon(countSql);
					pageBean.setTotlRecord(recordCount);
					if (recordCount > 0) {
						tolPageNum.setText(String.valueOf(pageBean.getTotlPage()));
						pageNum.setText("1");
						pageBean.setCurrentPage(1);
					}
					else {
						tolPageNum.setText("");
						pageNum.setText("");
					}
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider.queryAllOperate(serachSql);
					logList.clear();
					if (logonList != null && !logonList.isEmpty()) {
						logList.addAll(logonList);
						userAcountHd = userName.getText().trim();
						userNameHd = (userAcount.getText().trim());
						depNameHd = (deptNameCombo.getText().trim());
						orgNameHd = (orgNameCombo.getText().trim());
						startDateHd = startDate.getText().trim();
						endDateHd = endDate.getText().trim();
					}
					else {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage("结果为空！");
						messageBox.open();
						userAcount.setFocus();
					}
					viewer.refresh(false);

				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		clearAction = new Action() {
			public void run() {
				userName.setText("");
				userAcount.setText("");
				deptNameCombo.setText("");
				orgNameCombo.setText("");
				startDate.setText("");
				endDate.setText("");
			}
		};
		nextAction = new Action() {
			public void run() {
				try {
					if (reSearchFlg) {
						userName.setText(userAcountHd);
						userAcount.setText(userNameHd);
						deptNameCombo.setText(depNameHd);
						orgNameCombo.setText(orgNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					pageBean.next();
					String bounderResult = pageBean.bounderCheck();
					if (!"".equals(bounderResult)) {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage(bounderResult);
						messageBox.open();
						pageNum.setFocus();
						return;
					}
					else {
						Calendar calendar = Calendar.getInstance();
						System.out.println(calendar.getTimeInMillis());
						//	               Date date  =  new Calendor.
						serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
						List<LogOperate> logonList = logProvider.queryAllOperate(serachSql);
						if (logonList != null && !logonList.isEmpty()) {
							Calendar calendar2 = Calendar.getInstance();
							System.out.println(calendar2.getTimeInMillis());
							logList.clear();
							logList.addAll(logonList);
							pageNum.setText(String.valueOf(pageBean.getCurrentPage()));
						}
						else {
							pageBean.preview();
						}
						viewer.refresh(false);
					}
				}
				catch (Exception e) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
					messageBox.setMessage(StringUtil.searchExceptionMessage);
					messageBox.open();
					e.printStackTrace();
				}
			}
		};
		previewAction = new Action() {
			public void run() {
				try {
					if (reSearchFlg) {
						userName.setText(userAcountHd);
						userAcount.setText(userNameHd);
						deptNameCombo.setText(depNameHd);
						orgNameCombo.setText(orgNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					pageBean.preview();
					String bounderResult = pageBean.bounderCheck();
					if (!"".equals(bounderResult)) {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage(bounderResult);
						messageBox.open();
						pageNum.setFocus();
						return;
					}
					else {
						serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
						List<LogOperate> logonList = logProvider.queryAllOperate(serachSql);
						if (logonList != null && !logonList.isEmpty()) {
							logList.clear();
							logList.addAll(logonList);
							pageNum.setText(String.valueOf(pageBean.getCurrentPage()));
						}
						else {
							pageBean.next();
						}
						viewer.refresh(false);
					}
				}
				catch (Exception e) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
					messageBox.setMessage(StringUtil.searchExceptionMessage);
					messageBox.open();
					e.printStackTrace();
				}
			}
		};
		startAction = new Action() {
			public void run() {
				try {
					if (reSearchFlg) {
						userName.setText(userAcountHd);
						userAcount.setText(userNameHd);
						deptNameCombo.setText(depNameHd);
						orgNameCombo.setText(orgNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					String oldPageNum = pageNum.getText().trim();
					int currenPage = 1;
					if (Integer.valueOf(oldPageNum) == 1) {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage(StringUtil.firstPageWarnMessage);
						messageBox.open();
						pageNum.setFocus();
						return;
					}
					pageBean.setCurrentPage(currenPage);
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider.queryAllOperate(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText("1");
					}
					else {
						pageBean.setCurrentPage(Integer.valueOf(oldPageNum == "" ? "0" : oldPageNum));
						pageNum.setText(oldPageNum);
					}
					viewer.refresh(false);

				}
				catch (Exception e) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
					messageBox.setMessage(StringUtil.searchExceptionMessage);
					messageBox.open();
					e.printStackTrace();
				}
			}

		};
		endAction = new Action() {
			public void run() {
				try {
					if (reSearchFlg) {
						userName.setText(userAcountHd);
						userAcount.setText(userNameHd);
						deptNameCombo.setText(depNameHd);
						orgNameCombo.setText(orgNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					String oldPageNum = pageNum.getText().trim();
					int currenPage = pageBean.getTotlPage();
					if (Integer.valueOf(oldPageNum) == currenPage) {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage(StringUtil.lastPageWarnMessage);
						messageBox.open();
						pageNum.setFocus();
						return;
					}
					pageBean.setCurrentPage(currenPage);
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider.queryAllOperate(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						viewer.refresh(false);
						logList.addAll(logonList);
						pageNum.setText(String.valueOf(currenPage));
					}
					else {
						pageBean.setCurrentPage(Integer.valueOf(oldPageNum == "" ? "0" : oldPageNum));
						pageNum.setText(oldPageNum);
					}
					viewer.refresh(false);

				}
				catch (Exception e) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
					messageBox.setMessage(StringUtil.searchExceptionMessage);
					messageBox.open();
					e.printStackTrace();
				}
			}
		};
		goAction = new Action() {
			public void run() {
				try {
					if (reSearchFlg) {
						userName.setText(userAcountHd);
						userAcount.setText(userNameHd);
						deptNameCombo.setText(depNameHd);
						orgNameCombo.setText(orgNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					String currentNum = pageNum.getText().trim();
					String desPageNum = desPage.getText().trim();
					int desPageInt = Integer.valueOf(desPageNum);
					if (desPageInt > pageBean.getTotlPage()) {
						MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
						messageBox.setMessage(StringUtil.pageNumOverMessage);
						messageBox.open();
						desPage.setFocus();
						return;
					}
					pageBean.setCurrentPage(desPageInt);
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider.queryAllOperate(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText(desPageNum);
					}
					else {
						pageBean.setCurrentPage(Integer.valueOf(currentNum == "" ? "0" : currentNum));
					}
					viewer.refresh(false);

				}
				catch (Exception e) {
					MessageBox messageBox = new MessageBox(getShell(), SWT.OK);
					messageBox.setMessage(StringUtil.searchExceptionMessage);
					messageBox.open();
					desPage.setFocus();
					e.printStackTrace();
				}
			}
		};
		closeAction = new Action("close", AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID, "/src/com/thunisoft/dataplatform/template/image/delete.png")) {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.perspective");
				page.setPerspective(perspective);
				page.hideView(page.findView(ID));
			}
		};
		getComboContentAction = new Action() {
			public void run() {
				serachSql = sqlBuilder.getOrgInfoSql();
				String[] strs = logProvider.getOraNameArray(serachSql);
				orgNameCombo.setItems(strs);
			}
		};
		getDeptComboContentAction = new Action() {
			public void run() {
				serachSql = sqlBuilder.getDeptInfoSql(orgNameCombo.getText().trim());
				String[] strs = logProvider.getOraNameArray(serachSql);
				deptNameCombo.setItems(strs);
			}
		};
	}

	public void addListener() {
		ApplayLogListener listener = new ApplayLogListener(this);
		listener.addListener();
	}

	public int getXlocation(Object btn) {
		if (btn instanceof Button) {
			Object parent = ((Button) btn).getParent();
			if (parent instanceof Shell) {
				return ((Button) btn).getLocation().x + ((Shell) parent).getLocation().x;
			}
			else if (parent instanceof Composite && !flagX) {
				x = x + ((Composite) parent).getLocation().x;
				System.out.println("x:" + ((Composite) parent).getLocation().x);
				if (flagW) {
					x = x + ((Composite) parent).getBounds().width;
					System.out.println("width:" + ((Composite) parent).getBounds().width);
				}
				flagW = false;
				return getXlocation(((Composite) parent).getParent());
			}
		}
		if (btn instanceof Shell) {
			flagX = true;
			System.out.println("x:" + ((Shell) btn).getLocation().x);
			return x + ((Shell) btn).getLocation().x + 6;

		}
		if (btn instanceof Composite && !flagX) {
			x = x + ((Composite) btn).getLocation().x;
			System.out.println("x:" + ((Composite) btn).getLocation().x);
			return getXlocation(((Composite) btn).getParent());
		}
		return 0;
	}

	public int getYlocation(Object btn) {
		if (btn instanceof Button) {
			Object parent = ((Button) btn).getParent();
			if (parent instanceof Shell) {
				return ((Button) btn).getLocation().y + ((Shell) parent).getLocation().y;
			}
			else if (parent instanceof Group) {
				y = y + ((Group) parent).getLocation().y + 5;
				System.out.println(((Group) parent).getLocation().y + 5);
				return getYlocation(((Composite) parent).getParent());
			}
			else if (parent instanceof Composite) {
				y = y + ((Composite) parent).getLocation().y;
				System.out.println(((Composite) parent).getLocation().y);
				return getYlocation(((Composite) parent).getParent());
			}
		}
		else if (btn instanceof Shell) {
			flagY = true;
			System.out.println(((Shell) btn).getLocation().y);
			return y + ((Shell) btn).getLocation().y + 29 + 20;
		}
		else if (btn instanceof Group) {
			y = y + ((Group) btn).getLocation().y + 5;
			System.out.println(((Group) btn).getLocation().y + 5);
			return getYlocation(((Composite) btn).getParent());
		}
		else if (btn instanceof Composite && !flagY) {
			y = y + ((Composite) btn).getLocation().y;
			System.out.println(((Composite) btn).getLocation().y);
			return getYlocation(((Composite) btn).getParent());
		}
		return y;

	}

	/* general getter and setter */
	public Shell getShell() {
		return viewer.getControl().getShell();
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

	public void setStartDateBtn(Button startDateBtn) {
		this.startDateBtn = startDateBtn;
	}

	public void setSearchBtn(Button searchBtn) {
		this.searchBtn = searchBtn;
	}

	public void setClearBtn(Button clearBtn) {
		this.clearBtn = clearBtn;
	}

	public void setGoBtn(Button goBtn) {
		this.goBtn = goBtn;
	}

	public void setStartBtn(Button startBtn) {
		this.startBtn = startBtn;
	}

	public void setPreviewBtn(Button previewBtn) {
		this.previewBtn = previewBtn;
	}

	public void setNextBtn(Button nextBtn) {
		this.nextBtn = nextBtn;
	}

	public void setEndBtn(Button endBtn) {
		this.endBtn = endBtn;
	}

	public void setEndDateBtn(Button endDateBtn) {
		this.endDateBtn = endDateBtn;
	}

	public Action getSearchAction() {
		return searchAction;
	}

	public void setSearchAction(Action searchAction) {
		this.searchAction = searchAction;
	}

	public Action getClearAction() {
		return clearAction;
	}

	public void setClearAction(Action clearAction) {
		this.clearAction = clearAction;
	}

	public Action getNextAction() {
		return nextAction;
	}

	public void setNextAction(Action nextAction) {
		this.nextAction = nextAction;
	}

	public Action getPreviewAction() {
		return previewAction;
	}

	public void setPreviewAction(Action previewAction) {
		this.previewAction = previewAction;
	}

	public Action getStartAction() {
		return startAction;
	}

	public void setStartAction(Action startAction) {
		this.startAction = startAction;
	}

	public Action getEndAction() {
		return endAction;
	}

	public void setEndAction(Action endAction) {
		this.endAction = endAction;
	}

	public Action getGoAction() {
		return goAction;
	}

	public void setGoAction(Action goAction) {
		this.goAction = goAction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isFlagX() {
		return flagX;
	}

	public void setFlagX(boolean flagX) {
		this.flagX = flagX;
	}

	public boolean isFlagY() {
		return flagY;
	}

	public void setFlagY(boolean flagY) {
		this.flagY = flagY;
	}

	public void setStartDate(Text startDate) {
		this.startDate = startDate;
	}

	public void setUserName(Text userName) {
		this.userName = userName;
	}

	public void setUserAcount(Text userAcount) {
		this.userAcount = userAcount;
	}

	public void setEndDate(Text endDate) {
		this.endDate = endDate;
	}

	public void setDesPage(Text desPage) {
		this.desPage = desPage;
	}

	public void setTolPageNum(Text tolPageNum) {
		this.tolPageNum = tolPageNum;
	}

	public void setPageNum(Text pageNum) {
		this.pageNum = pageNum;
	}

	public boolean isReSearchFlg() {
		return reSearchFlg;
	}

	public void setReSearchFlg(boolean reSearchFlg) {
		this.reSearchFlg = reSearchFlg;
	}

	public LogOperateBean getLogonBean() {
		return logonBean;
	}

	public void setLogonBean(LogOperateBean logonBean) {
		this.logonBean = logonBean;
	}

	public String getUserAcountHd() {
		return userAcountHd;
	}

	public void setUserAcountHd(String userAcountHd) {
		this.userAcountHd = userAcountHd;
	}

	public String getUserNameHd() {
		return userNameHd;
	}

	public void setUserNameHd(String userNameHd) {
		this.userNameHd = userNameHd;
	}

	public String getDepNameHd() {
		return depNameHd;
	}

	public void setDepNameHd(String depNameHd) {
		this.depNameHd = depNameHd;
	}

	public String getOrgNameHd() {
		return orgNameHd;
	}

	public void setOrgNameHd(String orgNameHd) {
		this.orgNameHd = orgNameHd;
	}

	public String getStartDateHd() {
		return startDateHd;
	}

	public void setStartDateHd(String startDateHd) {
		this.startDateHd = startDateHd;
	}

	public String getEndDateHd() {
		return endDateHd;
	}

	public void setEndDateHd(String endDateHd) {
		this.endDateHd = endDateHd;
	}

	public void setViewer(TableViewer viewer) {
		this.viewer = viewer;
	}

	public Action getCloseAction() {
		return closeAction;
	}

	public void setCloseAction(Action closeAction) {
		this.closeAction = closeAction;
	}

	public void setDeptNameCombo(Combo deptNameCombo) {
		this.deptNameCombo = deptNameCombo;
	}

	public void setOrgNameCombo(Combo orgNameCombo) {
		this.orgNameCombo = orgNameCombo;
	}

	public Action getGetComboContentAction() {
		return getComboContentAction;
	}

	public void setGetComboContentAction(Action getComboContentAction) {
		this.getComboContentAction = getComboContentAction;
	}

	public Action getGetDeptComboContentAction() {
		return getDeptComboContentAction;
	}

	public void setGetDeptComboContentAction(Action getDeptComboContentAction) {
		this.getDeptComboContentAction = getDeptComboContentAction;
	}

	boolean isFlagW() {
		return flagW;
	}

	public void setFlagW(boolean flagW) {
		this.flagW = flagW;
	}

	boolean isFlagH() {
		return flagH;
	}

	public void setFlagH(boolean flagH) {
		this.flagH = flagH;
	}

	public Button getEndDateBtn() {
		return endDateBtn;
	}

	public Text getEndDate() {
		return endDate;
	}

	public Button getStartDateBtn() {
		return startDateBtn;
	}

	public Text getStartDate() {
		return startDate;
	}

	public Button getClearBtn() {
		return clearBtn;
	}

	public Button getSearchBtn() {
		return searchBtn;
	}

	public Combo getDeptNameCombo() {
		return deptNameCombo;
	}

	public Combo getOrgNameCombo() {
		return orgNameCombo;
	}

	public Text getUserName() {
		return userName;
	}

	public Text getUserAcount() {
		return userAcount;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public Button getStartBtn() {
		return startBtn;
	}

	public Button getPreviewBtn() {
		return previewBtn;
	}

	public Button getNextBtn() {
		return nextBtn;
	}

	public Button getEndBtn() {
		return endBtn;
	}

	public Button getGoBtn() {
		return goBtn;
	}

	public Text getDesPage() {
		return desPage;
	}

	public Text getTolPageNum() {
		return tolPageNum;
	}

	public Text getPageNum() {
		return pageNum;
	}
}