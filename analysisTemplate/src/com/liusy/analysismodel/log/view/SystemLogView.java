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
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.log.dao.LogSystemSql;
import com.liusy.analysismodel.log.dao.formBean.SystemBean;
import com.liusy.analysismodel.log.model.log.LogOperate;
import com.liusy.analysismodel.log.model.log.LogSystem;
import com.liusy.analysismodel.log.service.provider.LogSystemProvider;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.util.PageBean;
import com.liusy.analysismodel.ui.STCalendar;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;


public class SystemLogView extends ViewPart {
	private Button clearBtn;
	private Combo combo;
	private Text endDate;
	private Text startDate;
	private Button searchBtn;
	private Button goBtn;
	private Text desPage;
	private Text tolPageNum;
	private Text pageNum;
	private  Button startBtn;
	private  Button previewBtn;
	private  Button nextBtn;
	private  Button endBtn;
	private  Button startDateBtn;
	private  Button endDateBtn;
	private boolean reSearchFlg=false;
	private Table table;
	private Action searchAction;
	private Action nextAction;
	private Action previewAction;
	private Action startAction;
	private Action endAction;
	private Action goAction;
	private PageBean pageBean;
	private LogSystemProvider logProvider;
	private String serachSql;
	private SystemBean logonBean;
	private LogSystemSql sqlBuilder;
	public static final String ID = "DataAdminPlatform.log.view.SystemLogView";
	private int x = 0,y=0;
	private boolean flagX=false,flagY=false;
	private TableViewer viewer;
	private Vector logList;
	private Action addAction;
	private int logLevelHd = 0;
	private String startDateHd = "";
	private String endDateHd = "";

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class FocusListioner extends FocusAdapter {

		 public void focusLost(FocusEvent focusevent)
           {
			 	if (combo.getSelectionIndex() != logLevelHd||
			 			!startDate.getText().trim().equals(startDateHd)||
			 			!endDate.getText().trim().equals(endDateHd)) {
			 		reSearchFlg = true;
			 	} else {
			 		reSearchFlg = false;
			 	}
			 //set enable = false;
//			 setForm();
           }
	}
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
			LogSystem lonInfo = (LogSystem) obj;
			switch (columnIndex) {
			case 0:
				return lonInfo.getId()==null?"":String.valueOf(lonInfo.getId());
			case 1:
				return lonInfo.getLogLevel();
			case 2:
				return StringUtil.dateToString(lonInfo.getLogTime());
			case 3:
				return lonInfo.getLogClass();
			case 4:
				return lonInfo.getLogMessage();
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
		fd_group.bottom = new FormAttachment(17, 0);
		fd_group.top = new FormAttachment(2, 0);
		fd_group.left = new FormAttachment(0, 0);
		fd_group.right = new FormAttachment(100, 0);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		final Composite composite_7 = new Composite(group, SWT.NONE);
		final FormData fd_composite_7 = new FormData();
		fd_composite_7.top = new FormAttachment(0, 0);
		fd_composite_7.bottom = new FormAttachment(100, 0);
		fd_composite_7.right = new FormAttachment(90, 0);
		fd_composite_7.left = new FormAttachment(0, 0);
		composite_7.setLayoutData(fd_composite_7);
		final GridLayout gridLayout_3 = new GridLayout();
		gridLayout_3.marginTop = 10;
		gridLayout_3.marginHeight = 0;
		gridLayout_3.numColumns = 6;
		composite_7.setLayout(gridLayout_3);

		final Label label_1 = new Label(composite_7, SWT.RIGHT);
		final GridData gd_label_1 = new GridData();
		gd_label_1.horizontalIndent = 20;
		label_1.setLayoutData(gd_label_1);
		label_1.setAlignment(SWT.CENTER);
		label_1.setText("日志级别：");

		final Composite composite_8 = new Composite(composite_7, SWT.NONE);
		composite_8.setLayoutData(new GridData());
		composite_8.setLayout(new GridLayout());

		combo = new Combo(composite_8, SWT.NONE);
		combo.setItems(new String[] {"","DEBUG", "INFO", "WARN","ERROR","FATAL"});
		final GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false);
		gd_combo.widthHint = 80;
		combo.setLayoutData(gd_combo);

		final Label label_6 = new Label(composite_7, SWT.NONE);
		final GridData gd_label_6 = new GridData();
		gd_label_6.horizontalIndent = 20;
		label_6.setLayoutData(gd_label_6);
		label_6.setText("日志开始时间：");

		final Composite composite_4 = new Composite(composite_7, SWT.NONE);
		final GridLayout gridLayout_4 = new GridLayout();
		gridLayout_4.numColumns = 2;
		composite_4.setLayout(gridLayout_4);

		startDate = new Text(composite_4, SWT.CENTER | SWT.BORDER);
		startDate.setEditable(false);
		final GridData gd_startDate = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_startDate.widthHint = 80;
		startDate.setLayoutData(gd_startDate);

		startDateBtn = new Button(composite_4, SWT.NONE);
		startDateBtn.setLayoutData(new GridData(20, 20));
		startDateBtn.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "src/com/thunisoft/dataplatform/image/oldDate.png"));

		final Label label_7 = new Label(composite_7, SWT.NONE);
		final GridData gd_label_7 = new GridData();
		gd_label_7.horizontalIndent = 20;
		label_7.setLayoutData(gd_label_7);
		label_7.setText("日志截止时间：");

		final Composite composite_6 = new Composite(composite_7, SWT.NONE);
		final GridLayout gridLayout_5 = new GridLayout();
		gridLayout_5.numColumns = 2;
		composite_6.setLayout(gridLayout_5);

		endDate = new Text(composite_6, SWT.CENTER | SWT.BORDER);
		endDate.setEditable(false);
		final GridData gd_endDate = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_endDate.widthHint = 80;
		endDate.setLayoutData(gd_endDate);

		endDateBtn = new Button(composite_6, SWT.NONE);
		endDateBtn.setLayoutData(new GridData(20, 20));
		endDateBtn.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "src/com/thunisoft/dataplatform/image/oldDate.png"));

		final Composite composite_3 = new Composite(group, SWT.RIGHT_TO_LEFT);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 15;
		gridLayout.numColumns = 2;
		gridLayout.marginTop = 10;
		composite_3.setLayout(gridLayout);
		final FormData fd_composite_3 = new FormData();
		fd_composite_3.bottom = new FormAttachment(100, 0);
		fd_composite_3.top = new FormAttachment(0, 0);
		fd_composite_3.left = new FormAttachment(90, 0);
		fd_composite_3.right = new FormAttachment(100, 0);
		composite_3.setLayoutData(fd_composite_3);

		searchBtn = new Button(composite_3, SWT.NONE);
		final GridData gd_searchBtn = new GridData();
		searchBtn.setLayoutData(gd_searchBtn);
		searchBtn.setText("查询");

		clearBtn = new Button(composite_3, SWT.NONE);
		clearBtn.setText("重置");

		final Group group_1 = new Group(parent, SWT.NONE);
		final FormData fd_group_1 = new FormData();
		fd_group_1.top = new FormAttachment(17, 0);
		fd_group_1.bottom = new FormAttachment(85, 0);
		fd_group_1.left = new FormAttachment(0, 0);
		fd_group_1.right = new FormAttachment(100, 0);
		group_1.setLayoutData(fd_group_1);
		group_1.setLayout(new FormLayout());

		final Composite composite = new Composite(group_1, SWT.NONE);
		final FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, 0);
		fd_composite.top = new FormAttachment(0, 0);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.left = new FormAttachment(0, 0);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FormLayout());
		viewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER);
		table = viewer.getTable();
		final FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(5, 0);
		fd_table.bottom = new FormAttachment(95, 0);
		fd_table.right = new FormAttachment(100, 0);
		fd_table.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table);
		table.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			public void getName(AccessibleEvent e) {
				e.result = "table1";
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_2.setWidth(100);
		newColumnTableColumn_2.setText("日志级别");

		final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
		newColumnTableColumn.setWidth(100);
		newColumnTableColumn.setText("日志发生时间");

		final TableColumn newColumnTableColumn_6 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_6.setWidth(100);
		newColumnTableColumn_6.setText("日志发生类");

		final TableColumn newColumnTableColumn_7 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_7.setWidth(100);
		newColumnTableColumn_7.setText("日志消息");
		
		Composite composite_1;

		final Group group_2 = new Group(parent, SWT.NONE);
		final FormData fd_group_2 = new FormData();
		fd_group_2.top = new FormAttachment(85, 0);
		fd_group_2.bottom = new FormAttachment(100, 0);
		fd_group_2.right = new FormAttachment(100, 0);
		fd_group_2.left = new FormAttachment(0, 0);
		group_2.setLayoutData(fd_group_2);
		group_2.setLayout(new FormLayout());
		composite_1 = new Composite(group_2, SWT.NONE);
		final FormData fd_composite_1 = new FormData();
		fd_composite_1.right = new FormAttachment(100, 0);
		fd_composite_1.left = new FormAttachment(0, 0);
		composite_1.setLayoutData(fd_composite_1);
		final FormLayout formLayout = new FormLayout();
		formLayout.marginHeight = 10;
		composite_1.setLayout(formLayout);

		final Composite composite_5 = new Composite(composite_1, SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 7;
		composite_5.setLayout(gridLayout_1);
		final FormData fd_composite_5 = new FormData();
		fd_composite_5.right = new FormAttachment(70, 0);
		fd_composite_5.bottom = new FormAttachment(0, 70);
		fd_composite_5.top = new FormAttachment(0, 0);
		fd_composite_5.left = new FormAttachment(0, 0);
		composite_5.setLayoutData(fd_composite_5);

		final Label label_3 = new Label(composite_5, SWT.RIGHT);
		final GridData gd_label_3 = new GridData();
		gd_label_3.horizontalIndent = 20;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("当前页数：");

		pageNum = new Text(composite_5, SWT.BORDER);
		pageNum.setEditable(false);

		final Label label_4 = new Label(composite_5, SWT.RIGHT);
		final GridData gd_label_4 = new GridData();
		gd_label_4.horizontalIndent = 20;
		label_4.setLayoutData(gd_label_4);
		label_4.setText("总页数：");

		tolPageNum = new Text(composite_5, SWT.BORDER);
		tolPageNum.setEditable(false);

		final Label label_5 = new Label(composite_5, SWT.NONE);
		final GridData gd_label_5 = new GridData();
		gd_label_5.horizontalIndent = 20;
		label_5.setLayoutData(gd_label_5);
		label_5.setText("跳到指定页：");

		desPage = new Text(composite_5, SWT.BORDER);

		goBtn = new Button(composite_5, SWT.NONE);
		goBtn.setText("跳转");

		final Composite composite_2 = new Composite(composite_1, SWT.RIGHT_TO_LEFT);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 4;
		composite_2.setLayout(gridLayout_2);
		final FormData fd_composite_2 = new FormData();
		fd_composite_2.left = new FormAttachment(70, 0);
		fd_composite_2.right = new FormAttachment(100, 0);
		fd_composite_2.bottom = new FormAttachment(0, 70);
		fd_composite_2.top = new FormAttachment(0, 0);
		composite_2.setLayoutData(fd_composite_2);

		endBtn = new Button(composite_2, SWT.NONE);
		endBtn.setText("《-");

		nextBtn = new Button(composite_2, SWT.NONE);
		nextBtn.setText("下一页");

		previewBtn = new Button(composite_2, SWT.NONE);
		previewBtn.setText("上一页");

		startBtn = new Button(composite_2, SWT.NONE);
		startBtn.setText("-》");
		

		makeAddAction();
		addListener();
		initializeToolBar();
	}
	private void makeAddAction() {
		logonBean = new SystemBean(this.combo,this.startDate,this.endDate);
		logProvider = new LogSystemProvider();
		logList = new Vector<LogSystem>();
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setInput(logList);
		sqlBuilder = new LogSystemSql();
		pageBean = new PageBean();
		pageBean.setPageRecordCount(200);
		pageBean.setEnd(200);
		searchAction = new Action() {
			public void run() {
				try {
					String countSql = sqlBuilder.getRecordCount(logonBean,
							pageBean);
					int recordCount = logProvider.countLogon(countSql);
					pageBean.setTotlRecord(recordCount);
					if (recordCount >0) {
						tolPageNum.setText(String.valueOf(pageBean.getTotlPage()));
						pageNum.setText("1");
					}
					
					serachSql = sqlBuilder.getAllSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider
							.queryAllOperate(serachSql);
					logList.clear();
					if (logonList != null && !logonList.isEmpty()) {
						logList.addAll(logonList);
						logLevelHd = combo.getSelectionIndex();
						startDateHd = startDate.getText().trim();
						endDateHd = endDate.getText().trim();
//						userNameHd.setText(userName.getText().trim());
//						depNameHd.setText(depName.getText().trim());
//						orgNameHd.setText(orgName.getText().trim());
						setNavigatorAble();
					}
					viewer.refresh(false);

				}catch(Exception e){
					e.printStackTrace();
			}
		}
	};
	nextAction = new Action() {
		public void run() {
			try {
					if (reSearchFlg) {
						combo.select(logLevelHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					pageBean.next();
					serachSql = sqlBuilder.getAllSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider
							.queryAllOperate(serachSql);
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
						combo.select(logLevelHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					int currenPage = pageBean.getCurrentPage();
					pageBean.setCurrentPage(currenPage - 1);
					serachSql = sqlBuilder.getAllSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider
							.queryAllOperate(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText(String.valueOf(pageBean
								.getCurrentPage()));
					} else {
						pageBean.setCurrentPage(currenPage);
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
						combo.select(logLevelHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					String oldPageNum = pageNum.getText().trim();
					int currenPage = 1;
					pageBean.setCurrentPage(currenPage);
					serachSql = sqlBuilder.getAllSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider
							.queryAllOperate(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText("1");
					} else {
						pageBean.setCurrentPage(Integer.valueOf("".equals(oldPageNum)?"0":oldPageNum));
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
						combo.select(logLevelHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					String oldPageNum = pageNum.getText().trim();
					int currenPage = pageBean.getTotlPage();
					pageBean.setCurrentPage(currenPage);
					serachSql = sqlBuilder.getAllSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider
							.queryAllOperate(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						logList.clear();
						logList.addAll(logonList);
						pageNum.setText(String.valueOf(currenPage));
					} else {
						pageBean.setCurrentPage(Integer.valueOf("".equals(oldPageNum)?"0":oldPageNum));
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
						combo.select(logLevelHd);
						startDate.setText(startDateHd);
						endDate.setText(endDateHd);
					}
					String desPageNum = desPage.getText().trim();
					int desPageInt = Integer.valueOf(desPageNum);
					if (desPageInt > pageBean.getTotlPage()) {
						return ;
					}
					pageBean.setCurrentPage(desPageInt);
					serachSql = sqlBuilder.getAllSearchSql(logonBean, pageBean);
					List<LogOperate> logonList = logProvider
							.queryAllOperate(serachSql);
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
		this.combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent modifyevent){
				reSearchFlg = check();
			}
		});
		this.startDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent modifyevent){
				reSearchFlg = check();
			}
		});
		this.endDate.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent modifyevent){
				reSearchFlg = check();
			}
		});
		this.searchBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				searchAction.run();
			}
		});
		this.desPage.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent verifyevent) {
				// TODO Auto-generated method stub
				verifyevent.doit = false;
				char myChar = verifyevent.character;
				String text = ((Text) verifyevent.widget).getText();
				if (Character.isDigit(myChar)||myChar == '\b') verifyevent.doit = true;

			}
		});
		this.goBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				goAction.run();
			}
		});
		this.startBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				startAction.run();
			}
		});
		this.nextBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				nextAction.run();
			}
		});
		this.previewBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				previewAction.run();
			}
		});
		this.endBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				endAction.run();
			}
		});
		this.startDateBtn.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				x = 0;
				y = 0;
				flagX = false;
				flagY = false;
				x=getXlocation(startDateBtn); 
				y= getYlocation(startDateBtn); 
				STCalendar stc = new STCalendar(getShell(),startDate,x,y);
				stc.open();
			}
		});
		this.endDateBtn.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				x = 0;
				y = 0;
				flagX = false;
				flagY = false;
				x=getXlocation(endDateBtn); 
				y= getYlocation(endDateBtn); 
				STCalendar stc = new STCalendar(getShell(),endDate,x,y);
				stc.open();
			}
		});
	}
	public boolean check() {
		if (combo.getSelectionIndex() != logLevelHd||
	 			!startDate.getText().trim().equals(startDateHd)||
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
	public void setForm() {
		if(this.reSearchFlg) {
			this.startBtn.setEnabled(false);
			this.nextBtn.setEnabled(false);
			this.previewBtn.setEnabled(false);
			this.endBtn.setEnabled(false);
			this.goBtn.setEnabled(false);
			this.desPage.setEditable(false);
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
		this.startBtn.setEnabled(true);
		this.nextBtn.setEnabled(true);
		this.previewBtn.setEnabled(true);
		this.endBtn.setEnabled(true);
		this.goBtn.setEnabled(true);
		this.desPage.setEditable(true);
	}
	/* general getter and setter */
	public Shell getShell() {
		return viewer.getControl().getShell();
	}
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}
}