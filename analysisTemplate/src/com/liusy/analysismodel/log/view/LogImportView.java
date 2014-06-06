package com.liusy.analysismodel.log.view;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import com.liusy.analysismodel.log.dao.LogExportSql2;
import com.liusy.analysismodel.log.dao.formBean.LogExportBean2;
import com.liusy.analysismodel.log.model.log.ImportLog2;
import com.liusy.analysismodel.log.service.provider.LogExportProvider2;
import com.liusy.analysismodel.util.Conversion;

import com.swtdesigner.SWTResourceManager;

//import com.niis.myprice.util.message.Messages;


public class LogImportView extends ViewPart {
	private Button fileOpenBtn;
	private Text filePath;
	private Button viewBtn;
	private Vector logList;
	private LogExportProvider2 logProvider;
	private LogExportBean2 logExportBean2;
	public static final String ID = "DataAdminPlatform.log.view.LogExportView";
	private Button importBtn;
	private Button resumeBtn;
	private Button closeBtn;
	private Label resultLab;
	private int x = 0,y=0;
	private boolean flagX=false,flagY=false;
	private Action insertAction;
	private Action searchAction;
	private LogExportSql2 sqlBuilder;
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
		gridLayout.marginTop = 20;
		gridLayout.horizontalSpacing = 2;
		gridLayout.verticalSpacing = 10;
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);

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
		gridLayout_3.numColumns = 2;
		composite_4.setLayout(gridLayout_3);

		filePath = new Text(composite_4, SWT.BORDER);
		final GridData gd_filePath = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_filePath.widthHint = 290;
		filePath.setLayoutData(gd_filePath);

		fileOpenBtn = new Button(composite_4, SWT.NONE);
		fileOpenBtn.setImage(SWTResourceManager.getImage(LogImportView.class, "/com/thunisoft/dataplatform/image/directory.gif"));
		final GridData gd_fileOpenBtn = new GridData(20, 20);
		fileOpenBtn.setLayoutData(gd_fileOpenBtn);

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
		gd_label_7.horizontalIndent = 146;
		label_7.setLayoutData(gd_label_7);
		label_7.setText("查看结果：");

		resultLab = new Label(composite_5, SWT.NONE);
		resultLab.setVisible(false);
		resultLab.setText("Label");

		final Composite composite_3 = new Composite(group_1, SWT.NO_RADIO_GROUP);
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
		logExportBean2 = new LogExportBean2(filePath);
		logProvider = new LogExportProvider2();
		sqlBuilder = new LogExportSql2();
		insertAction = new Action() {
			public void run() {
				try {
					String filePathStr = logExportBean2.getTxtFilePath().getText().trim();
					filePathStr.replaceAll("\\\\", "\\\\\\\\");
					FileReader input = new FileReader(filePathStr);
					BufferedReader bufInput = new BufferedReader(input);
					String line;
					List<ImportLog2> logonList = new ArrayList<ImportLog2>();
					ImportLog2 logBean = new ImportLog2();
					while((line=bufInput.readLine())!= null) {
						System.out.print(line);
						System.out.print("\r\n");
						StringBuffer buf = new StringBuffer(line);
						int start = buf.indexOf(",");
						int col = 0;
						String[] contents = new String[15];
						while(start != -1) {
							contents[col] = buf.substring(0, start);
							col++;
							buf.delete(0, start+1);
							start = buf.indexOf(",");
							
						}
						logBean.setId(Integer.valueOf(contents[0]));
						logBean.setUserAccount(contents[1]);
						logBean.setUserName(contents[2]);
						logBean.setOrgId(Integer.valueOf(contents[3]));
						logBean.setDeptId(Integer.valueOf(contents[4]));
						logBean.setOrgName(contents[5]);
						logBean.setDeptName(contents[6]);
						logBean.setLogonTime(Conversion.getUtilDateToSqlDate(Conversion.getObjectToUtilDate(contents[7])));
						logBean.setIpAddress(contents[8]);
						logBean.setLogonOutTime(Conversion.getUtilDateToSqlDate(Conversion.getObjectToUtilDate(contents[9])));
						logBean.setResult(contents[10]);
						logBean.setResId(Integer.valueOf(contents[11]));
						logBean.setOptType(contents[12]);
						logBean.setQueryCondi(contents[13]);
						logBean.setOriginal(contents[14]);
						logonList.add(logBean);
					}
					
					input.close();
					bufInput.close();
					
					
					if (logonList != null && !logonList.isEmpty()) {
						for (int i = 0;i< logonList.size();i++) {
							ImportLog2 logBean2 = logonList.get(i);
//							String serachSql = sqlBuilder.getInsertDataSqlLogon(logBean2);
//							logProvider.insertLogTableInfo("");
						}
						
					}

				}catch(Exception e){
					e.printStackTrace();
			}
		}
	};
	searchAction = new Action() {
		public void run() {
//			String countSql = sqlBuilder.getLogTableCountSql(logImportBean2);
			try {
				int recordCount = 0;
				String filePathStr = logExportBean2.getTxtFilePath().getText().trim();
				filePathStr.replaceAll("\\\\", "\\\\\\\\");
				FileReader input = new FileReader(filePathStr);
				BufferedReader bufInput = new BufferedReader(input);
				String line;
				while((line=bufInput.readLine())!= null) {
					recordCount++;
					System.out.print(line);
					System.out.print("\r\n");
					
				}
				input.close();
				bufInput.close();
				
				
				resultLab.setText("共有"+String.valueOf(recordCount)+"条记录");
				resultLab.setSize(100, 20);
				resultLab.setVisible(true);
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
				FileDialog fileselect=new FileDialog(getSite().getShell(),SWT.SINGLE); 
                fileselect.setFilterNames(new String[]{"*.txt","所有文件"}); 
                fileselect.setFilterExtensions(new String[]{"*.txt","*.*"}); 
                String url=""; 
                url=fileselect.open();
                filePath.setText(url);
			}
		});
		this.closeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
//				getSite().getPage().close();
			}
		});
		this.resumeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				filePath.setText("");
			}
		});
		this.importBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				check();
				insertAction.run();
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
		if ("".equals(filePath.getText().trim())) {
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
	public void setFilePath(Text filePath) {
		this.filePath = filePath;
	}

}