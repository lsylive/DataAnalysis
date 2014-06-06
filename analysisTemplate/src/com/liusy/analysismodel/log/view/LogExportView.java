package com.liusy.analysismodel.log.view;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.log.dao.LogImportSql2;
import com.liusy.analysismodel.log.dao.formBean.LogImportBean2;
import com.liusy.analysismodel.log.model.log.ImportLog2;
import com.liusy.analysismodel.log.service.provider.LogImportProvider2;
import com.liusy.analysismodel.ui.STCalendar;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

//import com.niis.myprice.util.message.Messages;


public class LogExportView extends ViewPart {
	private Button fileOpenBtn;
	private Text filePath;
	private Button endDateBtn;
	private Text endDate;
	private Button startDateBtn;
	private Button fileNameDefChk;
	private Button filePathDefChk;
	private Button viewBtn;
	private Text startDate;
	private Text fileName;
	private Vector logList;
	private LogImportProvider2 logProvider;
	private LogImportBean2 logImportBean2;
	public static final String ID = "DataAdminPlatform.log.view.LogImportView";
	private Button importBtn;
	private Button resumeBtn;
	private Button closeBtn;
	private Label resultLab;
	private int x = 0,y=0;
	private boolean flagX=false,flagY=false;
	private Action insertAction;
	private Action searchAction;
	private Action deleteAction;
	private LogImportSql2 sqlBuilder;
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());

		final Group group = new Group(parent, SWT.NONE);
		final FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(30, 0);
		fd_group.top = new FormAttachment(0, 0);
		fd_group.right = new FormAttachment(100, 0);
		fd_group.left = new FormAttachment(0, 0);
		group.setLayoutData(fd_group);
		group.setLayout(new FormLayout());

		final Composite container = new Composite(group, SWT.NONE);
		final FormData fd_container = new FormData();
		fd_container.bottom = new FormAttachment(90, 0);
		fd_container.top = new FormAttachment(5, 0);
		fd_container.right = new FormAttachment(80, 0);
		fd_container.left = new FormAttachment(20, 0);
		container.setLayoutData(fd_container);
		final RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 10;
		rowLayout.marginHeight = 30;
		rowLayout.justify = true;
		container.setLayout(rowLayout);

		final Label introductionLab = new Label(container, SWT.CENTER);
		introductionLab.setText("日志导入：把选定时间段的日志数据增加到后台。");

		final Group group_1 = new Group(parent, SWT.NONE);
		final FormData fd_group_1 = new FormData();
		fd_group_1.left = new FormAttachment(0, 0);
		fd_group_1.right = new FormAttachment(100, 0);
		fd_group_1.bottom = new FormAttachment(100, 0);
		fd_group_1.top = new FormAttachment(30, 0);
		group_1.setLayoutData(fd_group_1);
		group_1.setLayout(new FormLayout());

		final Composite composite = new Composite(group_1, SWT.NONE);
		final FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(82, 0);
		fd_composite.left = new FormAttachment(18, 0);
		fd_composite.bottom = new FormAttachment(40, 0);
		fd_composite.top = new FormAttachment(0, -2);
		composite.setLayoutData(fd_composite);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 2;
		gridLayout.verticalSpacing = 10;
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);

		final Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("请选择时间段：");

		final Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 4;
		composite_1.setLayout(gridLayout_1);

		final Label label = new Label(composite_1, SWT.NONE);
		final GridData gd_label = new GridData();
		gd_label.horizontalIndent = 45;
		label.setLayoutData(gd_label);
		label.setText("开始时间：");

		final Composite composite_7 = new Composite(composite_1, SWT.NONE);
		final GridLayout gridLayout_7 = new GridLayout();
		gridLayout_7.numColumns = 2;
		composite_7.setLayout(gridLayout_7);

		startDate = new Text(composite_7, SWT.CENTER | SWT.BORDER);
		startDate.setEditable(false);
		final GridData gd_startDate = new GridData(80, SWT.DEFAULT);
		startDate.setLayoutData(gd_startDate);

		startDateBtn = new Button(composite_7, SWT.NONE);
		startDateBtn.setImage(SWTResourceManager.getImage(LogExportView.class, "/com/thunisoft/dataplatform/template/image/data.png"));
		final GridData gd_startDateBtn = new GridData(20, 20);
		startDateBtn.setLayoutData(gd_startDateBtn);

		final Label label_1 = new Label(composite_1, SWT.NONE);
		final GridData gd_label_1 = new GridData();
		label_1.setLayoutData(gd_label_1);
		label_1.setText("截止时间：");

		final Composite composite_2 = new Composite(composite_1, SWT.NONE);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 2;
		composite_2.setLayout(gridLayout_2);

		endDate = new Text(composite_2, SWT.CENTER | SWT.BORDER);
		endDate.setEditable(false);
		final GridData gd_endDate = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_endDate.horizontalIndent = 8;
		gd_endDate.widthHint = 80;
		endDate.setLayoutData(gd_endDate);

		endDateBtn = new Button(composite_2, SWT.NONE);
		endDateBtn.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "src/com/thunisoft/dataplatform/image/oldDate.png"));
		final GridData gd_endDateBtn = new GridData(20, 20);
		endDateBtn.setLayoutData(gd_endDateBtn);

		final Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("请选择文件路径：");

		final Label label_2 = new Label(composite, SWT.NONE);
		final GridData gd_label_2 = new GridData();
		gd_label_2.horizontalIndent = 50;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("文件路径：");

		final Composite composite_4 = new Composite(composite, SWT.NONE);
		final GridData gd_composite_4 = new GridData(365, SWT.DEFAULT);
		composite_4.setLayoutData(gd_composite_4);
		final GridLayout gridLayout_3 = new GridLayout();
		gridLayout_3.numColumns = 3;
		composite_4.setLayout(gridLayout_3);

		filePath = new Text(composite_4, SWT.BORDER);
		final GridData gd_filePath = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_filePath.horizontalIndent = 2;
		gd_filePath.widthHint = 290;
		filePath.setLayoutData(gd_filePath);

		fileOpenBtn = new Button(composite_4, SWT.NONE);
		fileOpenBtn.setImage(SWTResourceManager.getImage(LogImportView.class, "/com/thunisoft/dataplatform/image/directory.gif"));
		final GridData gd_fileOpenBtn = new GridData(20, 20);
		fileOpenBtn.setLayoutData(gd_fileOpenBtn);

		filePathDefChk = new Button(composite_4, SWT.CHECK);
		filePathDefChk.setText("使用默认的文件路径");

		final Label label_6 = new Label(composite, SWT.NONE);
		label_6.setLayoutData(new GridData());
		label_6.setText("请输入文件名称：");

		final Label label_3 = new Label(composite, SWT.NONE);
		final GridData gd_label_3 = new GridData();
		gd_label_3.horizontalIndent = 50;
		label_3.setLayoutData(gd_label_3);
		label_3.setText("文件名称：");

		final Composite composite_6 = new Composite(composite, SWT.NONE);
		final GridLayout gridLayout_6 = new GridLayout();
		gridLayout_6.numColumns = 2;
		composite_6.setLayout(gridLayout_6);

		fileName = new Text(composite_6, SWT.BORDER);
		final GridData gd_fileName = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_fileName.horizontalIndent = 2;
		gd_fileName.widthHint = 80;
		fileName.setLayoutData(gd_fileName);

		fileNameDefChk = new Button(composite_6, SWT.CHECK);
		fileNameDefChk.setText("使用默认的文件名");

		final Composite composite_5 = new Composite(group_1, SWT.NONE);
		final FormData fd_composite_5 = new FormData();
		fd_composite_5.right = new FormAttachment(0, 500);
		fd_composite_5.left = new FormAttachment(18, 0);
		fd_composite_5.bottom = new FormAttachment(60, 0);
		fd_composite_5.top = new FormAttachment(45, 0);
		composite_5.setLayoutData(fd_composite_5);
		final GridLayout gridLayout_5 = new GridLayout();
		gridLayout_5.numColumns = 2;
		composite_5.setLayout(gridLayout_5);

		final Label label_7 = new Label(composite_5, SWT.NONE);
		final GridData gd_label_7 = new GridData();
		gd_label_7.horizontalIndent = 143;
		label_7.setLayoutData(gd_label_7);
		label_7.setText("查看结果：");

		resultLab = new Label(composite_5, SWT.NONE);
		resultLab.setVisible(false);
		resultLab.setText("Label");

		final Composite composite_3 = new Composite(group_1, SWT.NONE);
		final RowLayout rowLayout_1 = new RowLayout();
		rowLayout_1.justify = true;
		composite_3.setLayout(rowLayout_1);
		final FormData fd_composite_3 = new FormData();
		fd_composite_3.bottom = new FormAttachment(70, 0);
		fd_composite_3.top = new FormAttachment(60, 0);
		fd_composite_3.right = new FormAttachment(70, 0);
		fd_composite_3.left = new FormAttachment(30, 0);
		composite_3.setLayoutData(fd_composite_3);

		viewBtn = new Button(composite_3, SWT.NONE);
		viewBtn.setText("查看");

		importBtn = new Button(composite_3, SWT.NONE);
		importBtn.setText("导入");

		resumeBtn = new Button(composite_3, SWT.NONE);
		resumeBtn.setText("重置");

		closeBtn = new Button(composite_3, SWT.NONE);
		closeBtn.setText("关闭");
		makeAddAction();
		addListener();
		initializeToolBar();
		initForm();
	}
	public void makeAddAction() {
		logProvider = new LogImportProvider2();
		sqlBuilder = new LogImportSql2();
		logImportBean2 = new LogImportBean2(this.startDate,this.endDate,this.filePath,this.fileName);
		insertAction = new Action() {
			public void run() {
				try {
					
//					pageBean.setTotlRecord(recordCount);
//					tolPageNum.setText(String.valueOf(pageBean.getTotlPage()));
//					pageNum.setText("1");
					String serachSql = sqlBuilder.getSelectLogTableSql(logImportBean2);
					List<ImportLog2> logonList = logProvider
							.selectLogTableInfo(serachSql);
					if (logonList != null && !logonList.isEmpty()) {
						try {
							String filePathStr = logImportBean2.getFilePath().getText().trim();
							filePathStr.replaceAll("\\\\", "\\\\\\\\");
							String fileNameStr = logImportBean2.getFileName().getText().trim();
							FileWriter ouput = new FileWriter(filePathStr+"\\"+fileNameStr,true);
							ouput.write("ID"+",");
							ouput.write("用户账号"+",");
							ouput.write("用户姓名"+",");
							ouput.write("机构ID"+",");
							ouput.write("部门ID"+",");
							ouput.write("机构名称"+",");
							ouput.write("部门名称"+",");
							ouput.write("登录时间"+",");
							ouput.write("ip"+",");
							ouput.write("登出时间"+",");
							ouput.write("结果"+",");
							ouput.write("资源Id"+",");
							ouput.write("操作类型"+",");
							ouput.write("操作时间"+",");
							ouput.write("查询条件"+",");
							ouput.write("数据来源"+"\r\n");
							for (int i = 0;i<logonList.size();i++) {
								ouput.write(((ImportLog2)logonList.get(i)).getId().toString()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getUserAccount()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getUserName()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getOrgId().toString()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getDeptId().toString()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getOrgName()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getDeptName()+",");
								Date logonTime = ((ImportLog2)logonList.get(i)).getLogonTime();
								ouput.write(logonTime+",");
								ouput.write(((ImportLog2)logonList.get(i)).getIpAddress()+",");
								Date logoutTime = ((ImportLog2)logonList.get(i)).getLogonOutTime();
								ouput.write(logoutTime+",");
								ouput.write(((ImportLog2)logonList.get(i)).getResult()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getResId().toString()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getOptType()+",");
								Timestamp optTime = ((ImportLog2)logonList.get(i)).getOptTime();
								ouput.write(optTime+",");
								ouput.write(((ImportLog2)logonList.get(i)).getQueryCondi()+",");
								ouput.write(((ImportLog2)logonList.get(i)).getOriginal()+"\r\n");
							}
							ouput.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
//						userNameHd.setText(userName.getText().trim());
//						depNameHd.setText(depName.getText().trim());
//						orgNameHd.setText(orgName.getText().trim());
//						setNavigatorAble();
					}

				}catch(Exception e){
					e.printStackTrace();
			}
		}
	};
	searchAction = new Action() {
		public void run() {
			String countSql = sqlBuilder.getLogTableCountSql(logImportBean2);
			try {
				int recordCount = logProvider.selectCount(countSql);
				resultLab.setText("共有"+String.valueOf(recordCount)+"条记录");
				resultLab.setSize(100, 20);
				resultLab.setVisible(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	deleteAction = new Action() {
		public void run() {
			String logonDeleteSql = sqlBuilder.getDeleteLogonTableSql(logImportBean2);
			String operatorDeleteSql = sqlBuilder.getDeleteOperatorTableSql(logImportBean2);
			try {
				int logonResult = logProvider.excuteMethod(logonDeleteSql);
				int operatorResult = logProvider.excuteMethod(operatorDeleteSql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	}
	public void addListener() {
		this.viewBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				check();
				searchAction.run();
//				DirectoryDialog directorySelect = new DirectoryDialog(getSite().getShell(),SWT.SINGLE);
//	            String rul2 = directorySelect.open();
//	            filePath.setText(rul2);
			}
		});
		this.fileOpenBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				DirectoryDialog directorySelect = new DirectoryDialog(getSite().getShell(),SWT.SINGLE);
	            String rul2 = directorySelect.open();
	            filePath.setText(rul2);
			}
		});
		this.fileNameDefChk.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean flag = fileNameDefChk.getSelection();
				if (flag) {
					fileName.setText("sdfsf");
				} else {
					fileName.setText("");
				}
			}
		});
		this.filePathDefChk.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean flag = filePathDefChk.getSelection();
				if (flag) {
					filePath.setText("sdfsf");
				} else {
					filePath.setText("");
				}
			}
		});
		this.startDateBtn.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				x = 0;
				y = 0;
				flagX = false;
				flagY = false;
				Integer x=getXlocation(startDateBtn); 
				Integer y= getYlocation(startDateBtn); 
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
				Integer x=getXlocation(endDateBtn); 
				Integer y= getYlocation(endDateBtn); 
				STCalendar stc = new STCalendar(getShell(),endDate,x,y);
				stc.open();
			}
		});
		this.closeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
//				getSite().getPage().close();
			}
		});
		this.resumeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				startDate.setText("");
				endDate.setText("");
				filePath.setText("");
				fileName.setText("");
			}
		});
		this.importBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				check();
				insertAction.run();
				deleteAction.run();
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//				page.findView(ID).dispose();
			}
		});
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
	public void check() {
		if ("".equals(startDate.getText().trim())|| "".equals(endDate.getText().trim())) {
			return ;
		}
		if ("".equals(filePath.getText().trim())|| "".equals(fileName.getText().trim())) {
			return ;
		}
	}
	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
	private void initializeToolBar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
	}
	private void initForm() {
		
		
		
	}
	public Shell getShell() {
		return getSite().getShell();
	}
	public void showMessage(String message) {
		MessageDialog.openInformation(getSite().getShell(),"warm!", message); //$NON-NLS-1$
	}
	public void setFileName(Text fileName) {
		this.fileName = fileName;
	}
	public void setFilePath(Text filePath) {
		this.filePath = filePath;
	}
	public void setEndDate(Text endDate) {
		this.endDate = endDate;
	}
	public void setStartDate(Text startDate) {
		this.startDate = startDate;
	}
}