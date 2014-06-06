package com.liusy.analysismodel.log.view;

import java.util.List;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.log.dao.LogonLogSql;
import com.liusy.analysismodel.log.dao.formBean.LogonBean;
import com.liusy.analysismodel.log.model.log.LogonLog;
import com.liusy.analysismodel.log.service.provider.LogMonitorProvider;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.util.PageBean;

import com.swtdesigner.SWTResourceManager;
import com.liusy.analysismodel.Activator;


public class UserStaticLogView extends ViewPart {
	private Button searchBtn;
	private Button endDateBtn;
	private Text endDate;
	private Text userName;
	private Button clearBtn;
	private Button startDateBtn;
	private Text startDate;
	private Text userAcount;
	private Button startBtn;
	private Button previewBtn;
	private Button nextBtn;
	private Button endBtn;
	private Button goBtn;
	private Text desPage;
	private Text tolPageNum;
	private Text pageNum;
	private TableViewer viewer;
	private Table table_2;
	private boolean reSearchFlg=false;
	private Action searchAction;
	private Action clearAction;
	private Action nextAction;
	private Action previewAction;
	private Action startAction;
	private Action endAction;
	private Action goAction;
	private PageBean pageBean;
	private LogMonitorProvider logProvider;
	private String serachSql;
	private LogonBean logonBean;
	private LogonLogSql sqlBuilder;
	public static final String ID = "DataAdminPlatform.view.log.UserStaticLogView";
	private int x = 0,y=0;
	private boolean flagX=false,flagY=false;
	//隐藏变量，保存检索条件的值
	private String userAcountHd;
	private String userNameHd;
	private String depNameHd;
	private String orgNameHd;
	private String startDateHd;
	private String endDateHd;
	private Vector logList;
//	class VerifyListeneor 
	class FocusListioner extends FocusAdapter {

		 public void focusLost(FocusEvent focusevent)
          {
			 if (!userAcount.getText().trim().equals(userAcountHd)
			 			||!userName.getText().trim().equals(userNameHd)) {
			 		reSearchFlg = true;
			 	} else {
			 		reSearchFlg = false;
			 	}
			 //set enable = false;
//			 setForm();
          }
	}
	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			
			Vector v = (Vector) parent;
			return v.toArray();
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int columnIndex) {
//			return getText(obj);
			LogonLog lonInfo = (LogonLog) obj;
			switch (columnIndex) {
			case 0:
				return lonInfo.getUserAccount();
			case 1:
				return lonInfo.getUserName();
			case 2:
				return lonInfo.getOrgName();
			case 3:
				return lonInfo.getDeptName();
			case 4:
				return StringUtil.dateToString(lonInfo.getLogonTime());
			case 5:
				return lonInfo.getIpAddress();
			case 6:
				return StringUtil.dateToString(lonInfo.getLogonOutTime());
			case 7:
				return lonInfo.getResult();
			}
			return null;
		}

		public Image getColumnImage(Object obj, int index) {
			return null;
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
		fd_group.bottom = new FormAttachment(0, 63);
		fd_group.top = new FormAttachment(0, 0);
		fd_group.right = new FormAttachment(100, -5);
		fd_group.left = new FormAttachment(0, 5);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		final Composite composite_3_1 = new Composite(group, SWT.NONE);
		final FormData fd_composite_3_1 = new FormData();
		fd_composite_3_1.bottom = new FormAttachment(50, 0);
		fd_composite_3_1.top = new FormAttachment(0, 0);
		fd_composite_3_1.right = new FormAttachment(100, 0);
		fd_composite_3_1.left = new FormAttachment(0, 0);
		composite_3_1.setLayoutData(fd_composite_3_1);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 5;
		gridLayout_2.marginTop = -2;
		gridLayout_2.marginHeight = 0;
		composite_3_1.setLayout(gridLayout_2);

		final Label label_8 = new Label(composite_3_1, SWT.NONE);
		final GridData gd_label_8 = new GridData();
		gd_label_8.horizontalIndent = 10;
		label_8.setLayoutData(gd_label_8);
		label_8.setText("用户帐号：");

		final Composite composite_9 = new Composite(composite_3_1, SWT.NONE);
		composite_9.setLayout(new GridLayout());

		userAcount = new Text(composite_9, SWT.BORDER);
		final GridData gd_userAcount = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_userAcount.widthHint = 80;
		userAcount.setLayoutData(gd_userAcount);

		final Label label_6 = new Label(composite_3_1, SWT.NONE);
		final GridData gd_label_6 = new GridData();
		gd_label_6.horizontalIndent = 18;
		label_6.setLayoutData(gd_label_6);
		label_6.setText("操作开始时间：");

		final Composite composite_4 = new Composite(composite_3_1, SWT.NONE);
		final GridData gd_composite_4 = new GridData(120, SWT.DEFAULT);
		composite_4.setLayoutData(gd_composite_4);
		final GridLayout gridLayout_3 = new GridLayout();
		gridLayout_3.numColumns = 2;
		composite_4.setLayout(gridLayout_3);

		startDate = new Text(composite_4, SWT.CENTER | SWT.BORDER);
		startDate.setEditable(false);
		final GridData gd_startDate = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_startDate.widthHint = 80;
		startDate.setLayoutData(gd_startDate);

		startDateBtn = new Button(composite_4, SWT.NONE);
		startDateBtn.setImage(SWTResourceManager.getImage(UserStaticLogView.class, "/com/thunisoft/dataplatform/template/image/data.png"));
		startDateBtn.setLayoutData(new GridData(20, 20));

		clearBtn = new Button(composite_3_1, SWT.NONE);
		final GridData gd_clearBtn = new GridData(80, SWT.DEFAULT);
		gd_clearBtn.horizontalIndent = 10;
		clearBtn.setLayoutData(gd_clearBtn);
		clearBtn.setText("重置");

		final Composite composite_7 = new Composite(group, SWT.NONE);
		final FormData fd_composite_7 = new FormData();
		fd_composite_7.bottom = new FormAttachment(100, 0);
		fd_composite_7.top = new FormAttachment(50, 0);
		fd_composite_7.right = new FormAttachment(100, 0);
		fd_composite_7.left = new FormAttachment(0, 0);
		composite_7.setLayoutData(fd_composite_7);
		final GridLayout gridLayout_4 = new GridLayout();
		gridLayout_4.numColumns = 5;
		gridLayout_4.marginTop = -5;
		gridLayout_4.marginHeight = 0;
		composite_7.setLayout(gridLayout_4);

		final Label label = new Label(composite_7, SWT.RIGHT);
		final GridData gd_label = new GridData();
		gd_label.horizontalIndent = 21;
		label.setLayoutData(gd_label);
		label.setAlignment(SWT.CENTER);
		label.setText("用户名：");

		final Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayoutData(new GridData());
		composite_8.setLayout(new GridLayout());

		userName = new Text(composite_8, SWT.BORDER);
		final GridData gd_userName = new GridData(80, SWT.DEFAULT);
		gd_userName.horizontalIndent = 1;
		userName.setLayoutData(gd_userName);

		final Label label_7 = new Label(composite_7, SWT.NONE);
		final GridData gd_label_7 = new GridData();
		gd_label_7.horizontalIndent = 18;
		label_7.setLayoutData(gd_label_7);
		label_7.setText("操作截止时间：");

		final Composite composite_6 = new Composite(composite_7, SWT.NONE);
		composite_6.setLayoutData(new GridData(120, SWT.DEFAULT));
		final GridLayout gridLayout_5 = new GridLayout();
		gridLayout_5.numColumns = 2;
		composite_6.setLayout(gridLayout_5);

		endDate = new Text(composite_6, SWT.CENTER | SWT.BORDER);
		endDate.setEditable(false);
		final GridData gd_endDate = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_endDate.widthHint = 80;
		endDate.setLayoutData(gd_endDate);

		endDateBtn = new Button(composite_6, SWT.NONE);
		endDateBtn.setImage(SWTResourceManager.getImage(UserStaticLogView.class, "/com/thunisoft/dataplatform/template/image/data.png"));
		endDateBtn.setLayoutData(new GridData(20, 20));

		searchBtn = new Button(composite_7, SWT.NONE);
		final GridData gd_searchBtn = new GridData(80, SWT.DEFAULT);
		gd_searchBtn.horizontalIndent = 10;
		searchBtn.setLayoutData(gd_searchBtn);
		searchBtn.setText("查询");

		final Group group_1 = new Group(parent, SWT.NONE);
		final FormData fd_group_1 = new FormData();
		fd_group_1.top = new FormAttachment(group, 0, SWT.DEFAULT);
		fd_group_1.right = new FormAttachment(100, -5);
		fd_group_1.left = new FormAttachment(0, 5);
		group_1.setLayoutData(fd_group_1);
		group_1.setLayout(new FormLayout());

		final Composite composite = new Composite(group_1, SWT.NONE);
		final FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, 0);
		fd_composite.top = new FormAttachment(0, 0);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.left = new FormAttachment(0, 0);
		composite.setLayoutData(fd_composite);
		final FormLayout formLayout_1 = new FormLayout();
		composite.setLayout(formLayout_1);

		viewer = new TableViewer(composite, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setContentProvider(new ViewContentProvider());
		table_2 = viewer.getTable();
		final FormData fd_table_1 = new FormData();
		fd_table_1.right = new FormAttachment(100, -5);
		fd_table_1.bottom = new FormAttachment(100, -4);
		fd_table_1.top = new FormAttachment(0, 0);
		fd_table_1.left = new FormAttachment(0, 5);
		table_2.setLayoutData(fd_table_1);
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);

		final TableColumn newColumnTableColumn_9_1 = new TableColumn(table_2, SWT.NONE);
		newColumnTableColumn_9_1.setWidth(100);
		newColumnTableColumn_9_1.setText("用户账号");

		final TableColumn style1_1 = new TableColumn(table_2, SWT.NONE);
		style1_1.setWidth(100);
		style1_1.setText("用户姓名");

		final TableColumn newColumnTableColumn_10 = new TableColumn(table_2, SWT.NONE);
		newColumnTableColumn_10.setWidth(100);
		newColumnTableColumn_10.setText("资源");

		final TableColumn newColumnTableColumn_3_1 = new TableColumn(table_2, SWT.NONE);
		newColumnTableColumn_3_1.setWidth(70);
		newColumnTableColumn_3_1.setText("访问次数");

		final TableColumn newColumnTableColumn_5_1 = new TableColumn(table_2, SWT.NONE);
		newColumnTableColumn_5_1.setWidth(200);
		newColumnTableColumn_5_1.setText("机构名称");

		final TableColumn newColumnTableColumn_6_1 = new TableColumn(table_2, SWT.NONE);
		newColumnTableColumn_6_1.setWidth(150);
		newColumnTableColumn_6_1.setText("部门名称");

		Group group_2;
		group_2 = new Group(parent, SWT.NONE);
		fd_group_1.bottom = new FormAttachment(group_2, 0, SWT.DEFAULT);
		final FormData fd_group_2 = new FormData();
		fd_group_2.bottom = new FormAttachment(100, -5);
		fd_group_2.top = new FormAttachment(100, -50);
		fd_group_2.right = new FormAttachment(100, -5);
		fd_group_2.left = new FormAttachment(0, 5);
		group_2.setLayoutData(fd_group_2);
		group_2.setLayout(new FormLayout());

		final Composite composite_1 = new Composite(group_2, SWT.NONE);
		final FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(100, 0);
		fd_composite_1.top = new FormAttachment(0, 0);
		fd_composite_1.right = new FormAttachment(100, 0);
		fd_composite_1.left = new FormAttachment(0, 0);
		composite_1.setLayoutData(fd_composite_1);
		composite_1.setLayout(new FormLayout());

		final Composite composite_5 = new Composite(composite_1, SWT.NONE);
		final FormData fd_composite_5 = new FormData();
		fd_composite_5.bottom = new FormAttachment(100, 0);
		fd_composite_5.top = new FormAttachment(0, 0);
		fd_composite_5.right = new FormAttachment(70, 0);
		fd_composite_5.left = new FormAttachment(0, 0);
		composite_5.setLayoutData(fd_composite_5);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 7;
		composite_5.setLayout(gridLayout);

		final Label label_3 = new Label(composite_5, SWT.RIGHT);
		final GridData gd_label_3 = new GridData();
		gd_label_3.horizontalIndent = 10;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("当前页数：");

		pageNum = new Text(composite_5, SWT.BORDER);
		pageNum.setEditable(false);
		final GridData gd_pageNum = new GridData();
		pageNum.setLayoutData(gd_pageNum);

		final Label label_4 = new Label(composite_5, SWT.RIGHT);
		final GridData gd_label_4 = new GridData();
		gd_label_4.horizontalIndent = 20;
		label_4.setLayoutData(gd_label_4);
		label_4.setText("总页数：");

		tolPageNum = new Text(composite_5, SWT.BORDER);
		tolPageNum.setEditable(false);
		final GridData gd_tolPageNum = new GridData();
		tolPageNum.setLayoutData(gd_tolPageNum);

		final Label label_5 = new Label(composite_5, SWT.NONE);
		final GridData gd_label_5 = new GridData();
		gd_label_5.horizontalIndent = 20;
		label_5.setLayoutData(gd_label_5);
		label_5.setText("跳到指定页：");

		desPage = new Text(composite_5, SWT.BORDER);
		desPage.setEditable(false);
		final GridData gd_desPage = new GridData();
		desPage.setLayoutData(gd_desPage);

		goBtn = new Button(composite_5, SWT.NONE);
		goBtn.setEnabled(true);
		goBtn.setText("跳转");

		final Composite composite_2 = new Composite(composite_1, SWT.RIGHT_TO_LEFT);
		final FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(100, 0);
		fd_composite_2.top = new FormAttachment(0, 0);
		fd_composite_2.right = new FormAttachment(100, 0);
		fd_composite_2.left = new FormAttachment(70, 0);
		composite_2.setLayoutData(fd_composite_2);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 4;
		composite_2.setLayout(gridLayout_1);

		endBtn = new Button(composite_2, SWT.NONE);
		endBtn.setText("最后页");

		nextBtn = new Button(composite_2, SWT.NONE);
		nextBtn.setText("下一页");

		previewBtn = new Button(composite_2, SWT.NONE);
		previewBtn.setText("上一页");

		startBtn = new Button(composite_2, SWT.NONE);
		startBtn.setText("第一页");
		makeAddAction();
		addListener();
		initializeToolBar();
	}
	private void makeAddAction() {
		logList = new Vector<LogonLog>();
		logProvider = new LogMonitorProvider();
		viewer.setInput(logList);
		sqlBuilder = new LogonLogSql();
		pageBean = new PageBean();
		pageBean.setPageRecordCount(200);
		pageBean.setEnd(20);
		searchAction = new Action() {
			public void run() {
				try {
					String countSql = sqlBuilder.getRecordCount(logonBean,
							pageBean);
					int recordCount = logProvider.countLogon(countSql);
					pageBean.setTotlRecord(recordCount);
					if (recordCount > 0) {
						tolPageNum.setText(String.valueOf(pageBean.getTotlPage()));
						pageNum.setText("1");
						pageBean.setCurrentPage(1);
					} else {
						tolPageNum.setText("");
						pageNum.setText("");
					}
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogonLog> logonList = logProvider
							.queryAllLogon(serachSql);
					logList.clear();
					if (logonList != null && !logonList.isEmpty()) {
						logList.addAll(logonList);
						//把值存到隐藏变量中
						userAcountHd = userAcount.getText().trim();
						userNameHd=(userName.getText().trim());
						startDateHd = startDate.getText().trim();
						endDateHd = endDate.getText().trim();
						setNavigatorAble();
					}
					viewer.refresh(false);

				}catch(Exception e){
					e.printStackTrace();
			}
		}
	};
	clearAction =  new Action() {
		public void run() {
			userAcount.setText("");
			userName.setText("");
			startDate.setText("");
			endDate.setText("");
			
		}
	};
	nextAction = new Action() {
		public void run() {
			try {
					if (reSearchFlg) {
						userAcount.setText(userAcountHd);
						userName.setText(userNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					pageBean.next();
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogonLog> logonList = logProvider
							.queryAllLogon(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText(String.valueOf(pageBean
								.getCurrentPage()));
					} else {
						pageBean.preview();
					}
					viewer.refresh(false);

				}catch(Exception e){
				e.printStackTrace();
		}
	}
	};
	previewAction = new Action() {
		public void run() {
			try {
					if (reSearchFlg) {
						userAcount.setText(userAcountHd);
						userName.setText(userNameHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					pageBean.preview();
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogonLog> logonList = logProvider
							.queryAllLogon(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText(String.valueOf(pageBean
								.getCurrentPage()));
					} else {
						pageBean.next();
					}
					viewer.refresh(false);

				}catch(Exception e){
				e.printStackTrace();
		}
	}
	};
	startAction = new Action() {
		public void run() {
			try {
				if (reSearchFlg) {
					userAcount.setText(userAcountHd);
					userName.setText(userNameHd);
					startDate.setText(startDateHd);
					endDate.setText(endDateHd);
				}
					String oldPageNum = pageNum.getText().trim();
					int currenPage = 1;
					pageBean.setCurrentPage(currenPage);
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogonLog> logonList = logProvider
							.queryAllLogon(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText("1");
					}else {
						pageNum.setText(oldPageNum);
					}
					viewer.refresh(false);

				}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	};
	endAction = new Action() {
		public void run() {
			try {
				if (reSearchFlg) {
					userAcount.setText(userAcountHd);
					userName.setText(userNameHd);
					startDate.setText(startDateHd);
					endDate.setText(endDateHd);
				}
					String oldPageNum = pageNum.getText().trim();
					int currenPage = pageBean.getTotlPage();
					pageBean.setCurrentPage(currenPage);
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogonLog> logonList = logProvider
							.queryAllLogon(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						viewer.refresh(false);
						logList.addAll(logonList);
						pageNum.setText(String.valueOf(currenPage));
					}else {
						pageNum.setText(oldPageNum);
					}
					viewer.refresh(false);

				}catch(Exception e){
				e.printStackTrace();
		}
	}
	};
	goAction = new Action() {
		public void run() {
			try {
				if (reSearchFlg) {
					userAcount.setText(userAcountHd);
					userName.setText(userNameHd);
					startDate.setText(startDateHd);
					endDate.setText(endDateHd);
				}
					String desPageNum = desPage.getText().trim();
					int desPageInt = Integer.valueOf(desPageNum);
					if (desPageInt > pageBean.getTotlPage()) {
						return ;
					}
					pageBean.setCurrentPage(desPageInt);
					serachSql = sqlBuilder.getSearchSql(logonBean, pageBean);
					List<LogonLog> logonList = logProvider
							.queryAllLogon(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText(desPageNum);
					}
					viewer.refresh(false);

				}catch(Exception e){
				e.printStackTrace();
		}
	}
	};
	}
	public void addListener(){
		//查询按钮
		//查询条件改变监听
		//日期控件监听
		
		//导航条按钮
		
	
	}
	//日期改变检测
	public boolean check() {
		if (!startDate.getText().trim().equals(startDateHd)||
	 			!endDate.getText().trim().equals(endDateHd)) {
	 		return true;
	 	} else {
	 		return false;
	 	}
	}
	int getXlocation(Object btn) {
		if (btn instanceof Button) {
			Object parent  = ((Button)btn).getParent();
			if (parent instanceof Shell) {
				return ((Button)btn).getLocation().x;
			} else if (parent instanceof Composite && !flagX) {
				x = x+ ((Composite)parent).getLocation().x;
				return getXlocation(((Composite)parent).getParent());
			}
		} 
		if (btn instanceof Shell) {
			flagX = true;
			return x+((Shell)btn).getLocation().x;
			
		} 
		if (btn instanceof Composite && !flagX) {
			x = x+ ((Composite)btn).getLocation().x;
			return getXlocation(((Composite)btn).getParent());
		} 
		return 0;
	}
	int getYlocation(Object btn) {
		if (btn instanceof Button) {
			Object parent  = ((Button)btn).getParent();
			if (parent instanceof Shell) {
				return ((Button)btn).getLocation().y;
			} else if (parent instanceof Composite) {
				y = y+ ((Composite)parent).getLocation().y;
				return getYlocation(((Composite)parent).getParent());
			}
		} else if (btn instanceof Shell) {
			flagY = true;
			return y+((Shell)btn).getLocation().y;
		} else if (btn instanceof Composite && !flagY) {
			y = y+ ((Composite)btn).getLocation().y;
			return getYlocation(((Composite)btn).getParent());
		} 
		return 0;
	
	}
	/* general getter and setter */
	public Shell getShell() {
		return viewer.getControl().getShell();
	}
	public void setForm() {
		if(this.reSearchFlg) {
		} else {
			this.startBtn.setEnabled(true);
			this.nextBtn.setEnabled(true);
			this.previewBtn.setEnabled(true);
			this.endBtn.setEnabled(true);
			this.goBtn.setEnabled(true);
			this.desPage.setEditable(true);
		}
	}
	public void setNavigatorAble () {
	}
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}
}