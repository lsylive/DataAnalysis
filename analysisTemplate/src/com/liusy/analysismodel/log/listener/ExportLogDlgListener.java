package com.liusy.analysismodel.log.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.MessageBox;
import com.liusy.analysismodel.log.dao.LogImportSql2;
import com.liusy.analysismodel.log.dao.formBean.LogImportBean2;
import com.liusy.analysismodel.log.dialog.ExportLogDlg;
import com.liusy.analysismodel.log.model.log.ExportLog;
import com.liusy.analysismodel.log.service.provider.LogExportProvider;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.util.DateUtil;
import com.liusy.analysismodel.ui.STCalendar;

public class ExportLogDlgListener {
   private ExportLogDlg      dlg;
   private LogExportProvider logProvider;
   private LogImportBean2    logImportBean2;
   private LogImportSql2     sqlBuilder;
   private Action            insertAction;
   private Action            searchAction;
   private Action            deleteAction;
   private int               x = 0, y = 0;
   private boolean           flagX = false, flagY = false;
   private static Log log=LogFactory.getLog(ExportLogDlgListener.class);
   public ExportLogDlgListener(ExportLogDlg dlg) {
      super();
      this.dlg = dlg;
      makeAction();
   }

   public void addListener() {

      dlg.getViewBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            if (check()) {
               searchAction.run();
            }

         }
      });
      dlg.getFileOpenBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            DirectoryDialog directorySelect = new DirectoryDialog(dlg.getShell(), SWT.SINGLE);
            String rul2 = directorySelect.open();
            dlg.getFilePath().setText(rul2);
         }
      });
      dlg.getFileNameDefChk().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            boolean flag = dlg.getFileNameDefChk().getSelection();
            if (flag) {
               String fileName = "";
               String startDate = dlg.getStartDate().getText().trim();
               String endDate = dlg.getEndDate().getText().trim();
               fileName = "logTable" + getStandDate(startDate) + "-" + getStandDate(endDate)+StringUtil.fileNameExtension;
               dlg.getFileName().setText(fileName);
            }
            else {
               dlg.getFileName().setText("");
            }
         }
      });
      dlg.getFilePathDefChk().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            boolean flag = dlg.getFilePathDefChk().getSelection();
            if (flag) {
               dlg.getFilePath().setText(StringUtil.defFilePath);
            }
            else {
               dlg.getFilePath().setText("");
            }
         }
      });
      dlg.getStartDateBtn().addMouseListener(new MouseAdapter() {
         public void mouseDown(final MouseEvent e) {
            DateUtil.dateLocationInit();
            DateUtil.setX(DateUtil.getXlocation(dlg.getStartDateBtn()));
            ;
            DateUtil.setY(DateUtil.getYlocation(dlg.getStartDateBtn(), false));
            ;
            STCalendar stc = new STCalendar(dlg.getShell(), dlg.getStartDate(), DateUtil.getX(), DateUtil.getY());
            stc.open();
         }
      });
      dlg.getEndDateBtn().addMouseListener(new MouseAdapter() {
         public void mouseDown(final MouseEvent e) {
            DateUtil.dateLocationInit();
            DateUtil.setX(DateUtil.getXlocation(dlg.getEndDateBtn()));
            ;
            DateUtil.setY(DateUtil.getYlocation(dlg.getEndDateBtn(), false));
            ;
            STCalendar stc = new STCalendar(dlg.getShell(), dlg.getEndDate(), DateUtil.getX(), DateUtil.getY());
            stc.open();
         }
      });
      dlg.getCloseBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            dlg.getShell().close();
         }
      });
      dlg.getResumeBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            dlg.getStartDate().setText("");
            dlg.getEndDate().setText("");
            dlg.getFileName().setText("");
            dlg.getFilePath().setText("");
            dlg.getMunLab().setText("");
            dlg.getExportResultLab().setText("");
         }
      });
      dlg.getExportBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            if (check() && checkFile()) {
               insertAction.run();
               deleteAction.run();
            }
         }
      });

   }

   public boolean check() {
      if ("".equals(dlg.getStartDate().getText().trim())) {
         MessageBox messageBox = new MessageBox(dlg.getShell(), SWT.OK);
         messageBox.setMessage("日期不全！");
         messageBox.open();
         dlg.getStartDate().setFocus();
         return false;
      } else if ("".equals(dlg.getEndDate().getText().trim())) {
         MessageBox messageBox = new MessageBox(dlg.getShell(), SWT.OK);
         messageBox.setMessage("日期不全！");
         messageBox.open();
         dlg.getEndDate().setFocus();
         return false;
      }
      else {
         return true;
      }

   }

   public boolean checkFile() {
      String filePath = dlg.getFilePath().getText().trim();
      String fileName = dlg.getFileName().getText().trim();
      if ("".equals(filePath)) {
         MessageBox messageBox = new MessageBox(dlg.getShell(), SWT.OK);
         messageBox.setMessage("文件路径为空！");
         messageBox.open();
         dlg.getFilePath().setFocus();
         return false;
      }
      else {
         filePath.replaceAll("\\\\", "\\\\\\\\");
         File file = new File(filePath);
         if (!file.exists()) {
            MessageBox messageBox = new MessageBox(dlg.getShell(), SWT.OK);
            messageBox.setMessage("文件路径不合法！");
            messageBox.open();
            dlg.getFilePath().setFocus();
            return false;
         }
         //			java.util.regex.Pattern p=java.util.regex.Pattern.compile(StringUtil.defFileDictory);
         //	        java.util.regex.Matcher m=p.matcher(filePath);
         //	        if (!m.matches()) {
         //	        	MessageBox messageBox= new MessageBox(dlg.getShell(),SWT.OK);
         //				messageBox.setMessage("文件路径不合法！");
         //				messageBox.open();
         //				dlg.getFilePath().setFocus();
         //				return false;
         //	        }
      }
      if ("".equals(fileName)) {
         MessageBox messageBox = new MessageBox(dlg.getShell(), SWT.OK);
         messageBox.setMessage("文件名为空！");
         messageBox.open();
         dlg.getFileName().setFocus();
         return false;
      }
      else {
         java.util.regex.Pattern p = java.util.regex.Pattern.compile(StringUtil.fileNameRex);
         java.util.regex.Matcher m = p.matcher(fileName);
         if (!m.matches()) {
            MessageBox messageBox = new MessageBox(dlg.getShell(), SWT.OK);
            messageBox.setMessage("文件名称不合法！");
            int result = messageBox.open();
            if (result == SWT.OK) {
               MessageBox messageBox2 = new MessageBox(dlg.getShell(), SWT.OK);
               messageBox2.setMessage("文件后缀必须为：txt！");
               messageBox2.open();
            }
            dlg.getFileName().setFocus();
            return false;
         }

      }
      return true;
   }

   public void makeAction() {
      logProvider = new LogExportProvider();
      sqlBuilder = new LogImportSql2();
      logImportBean2 = new LogImportBean2(dlg.getStartDate(), dlg.getEndDate(), dlg.getFilePath(), dlg.getFileName());
      insertAction = new Action() {
         public void run() {
            try {
               String serachSql = sqlBuilder.getSelectLogTableSql(logImportBean2);
               List<ExportLog> logonList = logProvider.queryLogTableInfo(serachSql);
               if (logonList != null && !logonList.isEmpty()) {
                  try {
                     String filePathStr = logImportBean2.getFilePath().getText().trim();
                     filePathStr.replaceAll("\\\\", "\\\\\\\\");
                     String fileNameStr = logImportBean2.getFileName().getText().trim();
                     FileWriter ouput = new FileWriter(filePathStr + "\\" + fileNameStr, true);
                     ouput.write("ID" + ",");
                     ouput.write("用户账号" + ",");
                     ouput.write("用户姓名" + ",");
                     ouput.write("机构ID" + ",");
                     ouput.write("部门ID" + ",");
                     ouput.write("机构名称" + ",");
                     ouput.write("部门名称" + ",");
                     ouput.write("登录时间" + ",");
                     ouput.write("ip" + ",");
                     ouput.write("登出时间" + ",");
                     ouput.write("结果" + ",");
                     ouput.write("资源Id" + ",");
                     ouput.write("操作类型" + ",");
                     ouput.write("操作时间" + ",");
                     ouput.write("查询条件" + ",");
                     ouput.write("数据来源" + "\r\n");
                     for (int i = 0; i < logonList.size(); i++) {
                        ouput.write(((ExportLog) logonList.get(i)).getId().toString() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getUserAccount() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getUserName() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getOrgId().toString() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getDeptId().toString() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getOrgName() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getDeptName() + ",");
                        Date logonTime = ((ExportLog) logonList.get(i)).getLogonTime();
                        ouput.write(logonTime + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getIpAddress() + ",");
                        Date logoutTime = ((ExportLog) logonList.get(i)).getLogonOutTime();
                        ouput.write(logoutTime + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getResult() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getResId().toString() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getOptType() + ",");
                        Timestamp optTime = ((ExportLog) logonList.get(i)).getOptTime();
                        ouput.write(optTime + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getQueryCondi() + ",");
                        ouput.write(((ExportLog) logonList.get(i)).getOriginal() + ";\r\n");
                     }
                     ouput.close();
                  }
                  catch (IOException e) {
                     // TODO Auto-generated catch block
                     log.error(e);
                     e.printStackTrace();
                     dlg.getExportResultLab().setText("导出失败！");
                     dlg.getExportResultLab().setVisible(true);
                     return;
                  }
               } else {
                  dlg.getExportResultLab().setText("导出失败！");
                  dlg.getExportResultLab().setVisible(true);
                  return;
               }

            }
            catch (Exception e) {
               log.error(e);
               e.printStackTrace();
            }
         }
      };
      searchAction = new Action() {
         public void run() {
            String countSql = sqlBuilder.getLogTableCountSql(logImportBean2);
            try {
               int recordCount = logProvider.countLogon(countSql);
               dlg.getMunLab().setText(String.valueOf(recordCount) + "条");
               //				dlg.getResultLab().setSize(100, 20);
               dlg.getMunLab().setVisible(true);
            }
            catch (Exception e) {
               // TODO Auto-generated catch block
               log.error(e);
               e.printStackTrace();
            }
         }

      };
      deleteAction = new Action() {
         public void run() {
            String logonDeleteSql = sqlBuilder.getDeleteLogonTableSql(logImportBean2);
            String operatorDeleteSql = sqlBuilder.getDeleteOperatorTableSql(logImportBean2);
            try {
               int logonResult = logProvider.excuteDelete(logonDeleteSql,operatorDeleteSql);
//               int operatorResult = logProvider.excuteMethod(operatorDeleteSql);
               if (logonResult > 0 ) {
                  dlg.getExportResultLab().setText("导出成功！");
                  dlg.getExportResultLab().setVisible(true);
               }
               else {
                  dlg.getExportResultLab().setText("导出失败！");
                  dlg.getExportResultLab().setVisible(true);
               }
            }
            catch (Exception e) {
               // TODO Auto-generated catch block
               log.error(e);
               e.printStackTrace();
            }
         }

      };
   }

   public String getStandDate(String date) {
      StringBuffer dateBuf = new StringBuffer(date);
      int start = dateBuf.indexOf("-");
      int col = 0;
      String[] contents = new String[3];
      while (start != -1) {
         contents[col] = dateBuf.substring(0, start);
         col++;
         dateBuf.delete(0, start + 1);
         start = dateBuf.indexOf("-");

      }
      contents[2] = dateBuf.substring(0);
      if (contents[1].length() == 1) {
         contents[1] = "0" + contents[1];
      }
      if (contents[2].length() == 1) {
         contents[2] = "0" + contents[2];
      }
      return contents[0] + contents[1] + contents[2];
   }
}
