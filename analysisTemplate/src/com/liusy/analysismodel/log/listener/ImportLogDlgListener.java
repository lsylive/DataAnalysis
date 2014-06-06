package com.liusy.analysismodel.log.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import com.liusy.analysismodel.log.dao.LogExportSql2;
import com.liusy.analysismodel.log.dialog.ImportLogDlg;
import com.liusy.analysismodel.log.model.log.ImportLog2;
import com.liusy.analysismodel.log.service.provider.LogExportProvider2;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.util.Conversion;

public class ImportLogDlgListener {
	private ImportLogDlg dlg;
	private Action searchAction;
	private Action insertAction;
	private LogExportProvider2 logProvider;
	private LogExportSql2 sqlBuilder;
	public ImportLogDlgListener(ImportLogDlg dlg) {
		super();
		this.dlg = dlg;
		makeAction();
	}
	public void addListener() {
		dlg.getFileOpenBtn().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
	            FileDialog fileselect=new FileDialog(dlg.getShell(),SWT.SINGLE); 
                fileselect.setFilterNames(new String[]{"*.txt","所有文件"}); 
                fileselect.setFilterExtensions(new String[]{"*.txt","*.*"}); 
                String url=""; 
                url=fileselect.open();
	            dlg.getFilePath().setText(url);
			}
		});
		dlg.getFilePathDefChk().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
//				DirectoryDialog directorySelect = new DirectoryDialog(dlg.getShell(),SWT.SINGLE);
//	            String rul2 = directorySelect.open();
	            dlg.getFilePath().setText("D:\\");
			}
		});
		dlg.getViewBtn().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (check()) {
					searchAction.run();
				}
			}
		});
		dlg.getImportBtn().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (check()) {
					 insertAction.run();
				}
	           
			}
		});
		dlg.getResumeBtn().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
	            dlg.getFilePath().setText("");
			}
		});
		dlg.getCloseBtn().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
	            dlg.getShell().close();
			}
		});
		
	}
	public void makeAction() {
		logProvider = new LogExportProvider2();
		sqlBuilder = new LogExportSql2();
		insertAction = new Action() {
			public void run() {
				try {
					int checkCount = 5;
					String filePathStr = dlg.getFilePath().getText().trim();
					filePathStr.replaceAll("\\\\", "\\\\\\\\");
					FileReader input = new FileReader(filePathStr);
					BufferedReader bufInput = new BufferedReader(input);
					String line;
					List<ImportLog2> logonList = new ArrayList<ImportLog2>();
					
					bufInput.readLine();
					while ((line = bufInput.readLine()) != null) {
						if(checkCount>0) {
							//文件内容检测
							java.util.regex.Pattern p=java.util.regex.Pattern.compile("([^\\,]+\\,)+\\w+;");
					        java.util.regex.Matcher m=p.matcher(line);
					      //不符合要求直接返回
					        if(!m.matches()){
					        	MessageBox messageBox= new MessageBox(dlg.getShell(),SWT.OK);
								messageBox.setMessage("文件内容不正确！");
								messageBox.open();
//								break;
								return;
					        } 
							checkCount--;
						}
						ImportLog2 logBean = new ImportLog2();
						StringBuffer buf = new StringBuffer(line);
						int start = buf.indexOf(",");
						int col = 0;
						String[] contents = new String[16];
						while (start != -1) {
							contents[col] = buf.substring(0, start);
							col++;
							buf.delete(0, start + 1);
							start = buf.indexOf(",");

						}
						int end = buf.indexOf(";");
						contents[15]= buf.substring(0, end);
						logBean.setId(Integer.valueOf(contents[0]));
						logBean.setUserAccount(contents[1]);
						logBean.setUserName(contents[2]);
						logBean.setOrgId(Integer.valueOf(contents[3]));
						logBean.setDeptId(Integer.valueOf(contents[4]));
						logBean.setOrgName(contents[5]);
						logBean.setDeptName(contents[6]);
						logBean.setLogonTime(Conversion
								.getUtilDateToSqlDate(Conversion
										.getObjectToUtilDate(contents[7])));
						logBean.setIpAddress(contents[8]);
						logBean.setLogonOutTime(Conversion
								.getUtilDateToSqlDate(Conversion
										.getObjectToUtilDate(contents[9])));
						logBean.setResult(contents[10]);
						logBean.setResId(Integer.valueOf(contents[11]));
						logBean.setOptType(contents[12]);
						logBean.setOptTime(Conversion.getObjectToSqlTime(contents[13]));
						logBean.setQueryCondi(contents[14]);
						logBean.setOriginal(contents[15]);
						logonList.add(logBean);
					}

					input.close();
					bufInput.close();

					if (logonList != null && !logonList.isEmpty()) {
//						String serachSql = sqlBuilder.getInsertDataSqlLogon(logonList);
						boolean result = logProvider.insertLogTableInfo(logonList,sqlBuilder);
						if (result) {
							dlg.getImportResultLab().setText("导入成功！");
							dlg.getImportResultLab().setVisible(true);
						} else {
							dlg.getImportResultLab().setText("导入失败！");
							dlg.getImportResultLab().setVisible(true);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		searchAction = new Action() {
			public void run() {
				// String countSql =
				// sqlBuilder.getLogTableCountSql(logImportBean2);
				try {
					int recordCount = 0;
					int checkCount = 5;
					String filePathStr = dlg.getFilePath().getText().trim();
					filePathStr.replaceAll("\\\\", "\\\\\\\\");
					FileReader input = new FileReader(filePathStr);
					BufferedReader bufInput = new BufferedReader(input);
					String line;
					while ((line = bufInput.readLine()) != null) {
						if(checkCount>0) {
							//文件内容检测
							java.util.regex.Pattern p=java.util.regex.Pattern.compile("([^\\,]+\\,)+\\w+;");
					        java.util.regex.Matcher m=p.matcher(line);
					      //不符合要求直接返回
					        if(!m.matches()){
					        	MessageBox messageBox= new MessageBox(dlg.getShell(),SWT.OK);
								messageBox.setMessage("文件内容不正确！");
								messageBox.open();
//								break;
								return;
					        } 
							checkCount--;
						}
						recordCount++;

					}
					input.close();
					bufInput.close();

					dlg.getNumLab().setText(
							String.valueOf(recordCount) + "条");
//					dlg.getResultLab().setSize(100, 20);
					dlg.getNumLab().setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}
	public boolean check() {
		String pathstr = dlg.getFilePath().getText().trim();


		if ("".equals(pathstr)){
//			MessageDialog.openWarning(dlg.getShell(), "日志导入警告", "文件路径为空！");
			MessageBox messageBox= new MessageBox(dlg.getShell(),SWT.OK);
			messageBox.setMessage("文件路径为空！");
			messageBox.open();
			return false;
		} else {
			pathstr=pathstr.replaceAll("\\\\", "/").trim();
	        java.util.regex.Pattern p=java.util.regex.Pattern.compile(StringUtil.filePathRex);
	        java.util.regex.Matcher m=p.matcher(pathstr);
	        //不符合要求直接返回
	        if(!m.matches()){
	        	MessageBox messageBox= new MessageBox(dlg.getShell(),SWT.OK);
				messageBox.setMessage("文件路径不合法！");
				messageBox.open();
	            return false;
	        } else {
	        	File path=new File(pathstr);
	        	if (!path.exists()) {
	        		MessageBox messageBox= new MessageBox(dlg.getShell(),SWT.OK);
					messageBox.setMessage("文件不存在！");
					messageBox.open();
		            return false;
	        	} else {
	        		return true;
	        	}
	        }
		}
	}
	
}
