package com.liusy.analysismodel.log.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.log.listener.ExportLogDlgListener;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

public class ExportLogDlg extends Dialog {

   private Button closeBtn;
   private Button resumeBtn;
   private Button exportBtn;
   private Button viewBtn;
   private Label exportResultLab;
   private Label munLab;
   private Label resultLab;
   private Button fileNameDefChk;
   private Text fileName;
   private Button filePathDefChk;
   private Button fileOpenBtn;
   private Text filePath;
   private Button endDateBtn;
   private Text endDate;
   private Button startDateBtn;
   private Text startDate;
   protected Object result;
   protected Shell  shell;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public ExportLogDlg(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public ExportLogDlg(Shell parent) {
      this(parent, SWT.NONE);
   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public Object open() {
      createContents();
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return result;
   }

   /**
    * Create contents of the dialog
    */
   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(500, 375);
      shell.setText("日志导出");

      final Label label_4 = new Label(shell, SWT.CENTER);
      final FormData fd_label_4 = new FormData();
      fd_label_4.top = new FormAttachment(0, 40);
      fd_label_4.right = new FormAttachment(80, 0);
      fd_label_4.left = new FormAttachment(20, 0);
      label_4.setLayoutData(fd_label_4);
      label_4.setText("把选定时间段的日志数据导出到文件中");

      final Group composite_1 = new Group(shell, SWT.EMBEDDED);
      composite_1.setLayout(new FormLayout());
      final FormData fd_composite = new FormData();
      fd_composite.bottom = new FormAttachment(60, 0);
      fd_composite.top = new FormAttachment(0, 70);
      fd_composite.right = new FormAttachment(100, -20);
      fd_composite.left = new FormAttachment(0, 20);
      composite_1.setLayoutData(fd_composite);

      final Label label = new Label(composite_1, SWT.NONE);
      final FormData fd_label = new FormData();
      fd_label.right = new FormAttachment(15, 0);
      fd_label.bottom = new FormAttachment(15, 20);
      fd_label.top = new FormAttachment(15, 0);
      fd_label.left = new FormAttachment(0, 10);
      label.setLayoutData(fd_label);
      label.setText("开始时间：");

      startDate = new Text(composite_1, SWT.CENTER | SWT.BORDER);
      final FormData fd_startDate = new FormData();
      fd_startDate.right = new FormAttachment(40, 0);
      fd_startDate.left = new FormAttachment(15, 0);
      fd_startDate.bottom = new FormAttachment(15, 20);
      fd_startDate.top = new FormAttachment(15, 0);
      startDate.setLayoutData(fd_startDate);
      
      startDate.setEditable(false);

      startDateBtn = new Button(composite_1, SWT.NONE);
      startDateBtn.setImage(SWTResourceManager.getImage(ExportLogDlg.class, "/com/thunisoft/dataplatform/template/image/data.png"));
      final FormData fd_startDateBtn = new FormData();
      fd_startDateBtn.left = new FormAttachment(40, 0);
      fd_startDateBtn.right = new FormAttachment(45, 0);
      fd_startDateBtn.bottom = new FormAttachment(15, 20);
      fd_startDateBtn.top = new FormAttachment(15, 0);
      startDateBtn.setLayoutData(fd_startDateBtn);

      final Label label_1 = new Label(composite_1, SWT.NONE);
      final FormData fd_label_1 = new FormData();
      fd_label_1.bottom = new FormAttachment(15, 20);
      fd_label_1.top = new FormAttachment(15, 0);
      fd_label_1.right = new FormAttachment(65, 0);
      fd_label_1.left = new FormAttachment(50, 0);
      label_1.setLayoutData(fd_label_1);
      label_1.setText("截止时间：");

      endDate = new Text(composite_1, SWT.CENTER | SWT.BORDER);
      final FormData fd_endDate = new FormData();
      fd_endDate.bottom = new FormAttachment(15, 20);
      fd_endDate.top = new FormAttachment(15, 0);
      fd_endDate.right = new FormAttachment(90, 0);
      fd_endDate.left = new FormAttachment(65, 0);
      endDate.setLayoutData(fd_endDate);
      endDate.setEditable(false);

      endDateBtn = new Button(composite_1, SWT.NONE);
      endDateBtn.setImage(SWTResourceManager.getImage(ExportLogDlg.class, "/com/thunisoft/dataplatform/template/image/data.png"));
      final FormData fd_endDateBtn = new FormData();
      fd_endDateBtn.bottom = new FormAttachment(15, 20);
      fd_endDateBtn.top = new FormAttachment(15, 0);
      fd_endDateBtn.right = new FormAttachment(95,0 );
      fd_endDateBtn.left = new FormAttachment(90, 0);
      endDateBtn.setLayoutData(fd_endDateBtn);

      final Label label_2 = new Label(composite_1, SWT.NONE);
      final FormData fd_label_2 = new FormData();
      fd_label_2.bottom = new FormAttachment(43, 20);
      fd_label_2.top = new FormAttachment(43, 0);
      fd_label_2.right = new FormAttachment(15, 0);
      fd_label_2.left = new FormAttachment(0, 10);
      label_2.setLayoutData(fd_label_2);
      label_2.setText("文件路径：");

      filePath = new Text(composite_1, SWT.BORDER);
      final FormData gd_filePath = new FormData();
      gd_filePath.bottom = new FormAttachment(43, 20);
      gd_filePath.top = new FormAttachment(43, 0);
      gd_filePath.right = new FormAttachment(55, 0);
      gd_filePath.left = new FormAttachment(15, 0);
      filePath.setLayoutData(gd_filePath);

      fileOpenBtn = new Button(composite_1, SWT.NONE);
      fileOpenBtn.setImage(SWTResourceManager.getImage(ExportLogDlg.class, "/com/thunisoft/dataplatform/template/image/directory.gif"));
      final FormData gd_fileOpenBtn = new FormData();
      gd_fileOpenBtn.bottom = new FormAttachment(43, 20);
      gd_fileOpenBtn.top = new FormAttachment(43, 0);
      gd_fileOpenBtn.right = new FormAttachment(55, 20);
      gd_fileOpenBtn.left = new FormAttachment(55, 0);
      fileOpenBtn.setLayoutData(gd_fileOpenBtn);

      filePathDefChk = new Button(composite_1, SWT.CHECK);
      final FormData fd_filePathDefChk = new FormData();
      fd_filePathDefChk.left = new FormAttachment(65, 0);
      fd_filePathDefChk.right = new FormAttachment(100, 0);
      fd_filePathDefChk.bottom = new FormAttachment(43, 20);
      fd_filePathDefChk.top = new FormAttachment(43, 0);
      filePathDefChk.setLayoutData(fd_filePathDefChk);
      filePathDefChk.setText("使用默认的文件路径");

      final Label label_3 = new Label(composite_1, SWT.NONE);
      final FormData fd_label_3 = new FormData();
      fd_label_3.right = new FormAttachment(15, 0);
      fd_label_3.top = new FormAttachment(70, 0);
      fd_label_3.bottom = new FormAttachment(70, 20);
      fd_label_3.left = new FormAttachment(0, 10);
      label_3.setLayoutData(fd_label_3);
      label_3.setText("文件名称：");

      fileName = new Text(composite_1, SWT.BORDER);
      final FormData gd_fileName = new FormData();
      gd_fileName.right = new FormAttachment(55, 0);
      
      gd_fileName.top = new FormAttachment(70, 0);
      gd_fileName.bottom = new FormAttachment(70, 20);
      gd_fileName.left = new FormAttachment(15, 0);
      fileName.setLayoutData(gd_fileName);

      fileNameDefChk = new Button(composite_1, SWT.CHECK);
      final FormData fd_fileNameDefChk = new FormData();
      fd_fileNameDefChk.left = new FormAttachment(65, 0);
      fd_fileNameDefChk.bottom = new FormAttachment(70, 20);
      fd_fileNameDefChk.top = new FormAttachment(70, 0);
      fd_fileNameDefChk.right = new FormAttachment(90, 0);
      fileNameDefChk.setLayoutData(fd_fileNameDefChk);
      fileNameDefChk.setText("使用默认的文件名");

      final Group composite_8 = new Group(shell, SWT.NONE);
      final FormLayout formLayout = new FormLayout();
      formLayout.marginHeight = 10;
      composite_8.setLayout(formLayout);
      final FormData fd_composite_8 = new FormData();
      fd_composite_8.top = new FormAttachment(60, 0);
      fd_composite_8.bottom = new FormAttachment(93, 0);
      fd_composite_8.right = new FormAttachment(100, -20);
      fd_composite_8.left = new FormAttachment(0, 20);
      composite_8.setLayoutData(fd_composite_8);

      resultLab = new Label(composite_8, SWT.NONE);
      final FormData gd_resultLab = new FormData();
      gd_resultLab.right = new FormAttachment(30, 0);
      gd_resultLab.left = new FormAttachment(15, 0);
      resultLab.setLayoutData(gd_resultLab);
      resultLab.setText("记录数量：");

      munLab = new Label(composite_8, SWT.NONE);
      final FormData fd_munLab = new FormData();
      fd_munLab.left = new FormAttachment(30, 0);
      fd_munLab.right = new FormAttachment(50, 0);
      munLab.setLayoutData(fd_munLab);
      munLab.setVisible(false);
      munLab.setText("Label");

      final Label label_6 = new Label(composite_8, SWT.NONE);
      final FormData gd_label_6 = new FormData();
      gd_label_6.right = new FormAttachment(75, 0);
      gd_label_6.left = new FormAttachment(60, 0);
      label_6.setLayoutData(gd_label_6);
      label_6.setText("导出结果：");

      exportResultLab = new Label(composite_8, SWT.NONE);
      final FormData fd_exportResultLab = new FormData();
      fd_exportResultLab.right = new FormAttachment(95, 0);
      fd_exportResultLab.left = new FormAttachment(75, 0);
      exportResultLab.setLayoutData(fd_exportResultLab);
      exportResultLab.setVisible(false);
      exportResultLab.setText("Label");

      viewBtn = new Button(composite_8, SWT.NONE);
      final FormData fd_viewBtn = new FormData();
      fd_viewBtn.right = new FormAttachment(32, 0);
      fd_viewBtn.left = new FormAttachment(22, 0);
      fd_viewBtn.top = new FormAttachment(50, 0);
      viewBtn.setLayoutData(fd_viewBtn);
      viewBtn.setText("查看");

      exportBtn = new Button(composite_8, SWT.NONE);
      final FormData fd_exportBtn = new FormData();
      fd_exportBtn.right = new FormAttachment(47, 0);
      fd_exportBtn.left = new FormAttachment(37, 0);
      fd_exportBtn.top = new FormAttachment(50, 0);
      exportBtn.setLayoutData(fd_exportBtn);
      exportBtn.setText("导出");

      resumeBtn = new Button(composite_8, SWT.NONE);
      final FormData fd_resumeBtn = new FormData();
      fd_resumeBtn.right = new FormAttachment(62, 0);
      fd_resumeBtn.left = new FormAttachment(52, 0);
      fd_resumeBtn.top = new FormAttachment(50, 0);
      resumeBtn.setLayoutData(fd_resumeBtn);
      resumeBtn.setText("重置");

      closeBtn = new Button(composite_8, SWT.NONE);
      final FormData fd_closeBtn = new FormData();
      fd_closeBtn.right = new FormAttachment(77, 0);
      fd_closeBtn.left = new FormAttachment(67, 0);
      fd_closeBtn.top = new FormAttachment(50, 0);
      closeBtn.setLayoutData(fd_closeBtn);
      closeBtn.setText("关闭");
      //
      addListener();
   }

   public void addListener() {//关闭关闭
      ExportLogDlgListener dlgListener = new ExportLogDlgListener(this);
      dlgListener.addListener();
   }

   public Point getParentLocation() {
      Shell shell = getParent();
      int x = shell.getLocation().x;
      int y = shell.getLocation().y;
      Rectangle rectParent = shell.getBounds();
      Point size = this.shell.getSize();
      int xx = x + rectParent.width / 2 - size.x / 2;
      int yy = y + rectParent.height / 2 - size.y / 2;
      return new Point(xx, yy);
   }

   public void setCloseBtn(Button closeBtn) {
      this.closeBtn = closeBtn;
   }

   public void setResumeBtn(Button resumeBtn) {
      this.resumeBtn = resumeBtn;
   }

   public void setExportBtn(Button exportBtn) {
      this.exportBtn = exportBtn;
   }

   public void setViewBtn(Button viewBtn) {
      this.viewBtn = viewBtn;
   }

   public void setResultLab(Label resultLab) {
      this.resultLab = resultLab;
   }


   public void setFileNameDefChk(Button fileNameDefChk) {
      this.fileNameDefChk = fileNameDefChk;
   }


   public void setFileName(Text fileName) {
      this.fileName = fileName;
   }


   public void setFilePathDefChk(Button filePathDefChk) {
      this.filePathDefChk = filePathDefChk;
   }


   public void setFileOpenBtn(Button fileOpenBtn) {
      this.fileOpenBtn = fileOpenBtn;
   }


   public void setFilePath(Text filePath) {
      this.filePath = filePath;
   }


   public void setEndDateBtn(Button endDateBtn) {
      this.endDateBtn = endDateBtn;
   }


   public void setEndDate(Text endDate) {
      this.endDate = endDate;
   }


   public void setStartDateBtn(Button startDateBtn) {
      this.startDateBtn = startDateBtn;
   }


   public void setStartDate(Text startDate) {
      this.startDate = startDate;
   }

   public Shell getShell() {
      return shell;
   }

   public void setShell(Shell shell) {
      this.shell = shell;
   }

   public void setExportResultLab(Label exportResultLab) {
      this.exportResultLab = exportResultLab;
   }

   public void setMunLab(Label munLab) {
      this.munLab = munLab;
   }

   public Object getResult() {
      return result;
   }

   public void setResult(Object result) {
      this.result = result;
   }

   public Button getCloseBtn() {
      return closeBtn;
   }

   public Button getResumeBtn() {
      return resumeBtn;
   }

   public Button getExportBtn() {
      return exportBtn;
   }

   public Button getViewBtn() {
      return viewBtn;
   }

   public Label getExportResultLab() {
      return exportResultLab;
   }

   public Label getMunLab() {
      return munLab;
   }

   public Label getResultLab() {
      return resultLab;
   }

   public Button getFileNameDefChk() {
      return fileNameDefChk;
   }

   public Text getFileName() {
      return fileName;
   }

   public Button getFilePathDefChk() {
      return filePathDefChk;
   }

   public Button getFileOpenBtn() {
      return fileOpenBtn;
   }

   public Text getFilePath() {
      return filePath;
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








}
