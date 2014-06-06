package com.liusy.analysismodel.log.dialog;

import org.eclipse.jface.action.Action;
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
import com.liusy.analysismodel.log.listener.ImportLogDlgListener;

import com.swtdesigner.SWTResourceManager;

public class ImportLogDlg extends Dialog {

	private Button closeBtn;
	private Button resumeBtn;
	private Button importBtn;
	private Button viewBtn;
	private Label importResultLab;
	private Label numLab;
	private Label resultLab;
	private Button filePathDefChk;
	private Button fileOpenBtn;
	private Text filePath;
	protected Object result;
	protected Shell shell;
	private Action fileOpenAction;

	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public ImportLogDlg(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public ImportLogDlg(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public Object open() {
		createContents();
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
		shell.setSize(500, 375);
		shell.setText("日志导入");

		final Label label = new Label(shell, SWT.CENTER);
		final FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 60);
		fd_label.right = new FormAttachment(70, 0);
		fd_label.left = new FormAttachment(30, 0);
		label.setLayoutData(fd_label);
		label.setText("把选定的文件数据导入到系统中");

		final Group composite = new Group(shell, SWT.NONE);
		composite.setLayout(new FormLayout());
		final FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0, 20);
		fd_composite.bottom = new FormAttachment(0, 185);
		fd_composite.right = new FormAttachment(100, -20);
		fd_composite.top = new FormAttachment(0, 100);
		composite.setLayoutData(fd_composite);

		final Label label_2 = new Label(composite, SWT.NONE);
		final FormData fd_label_2 = new FormData();
		fd_label_2.right = new FormAttachment(15, 0);
		fd_label_2.bottom = new FormAttachment(0, 41);
		fd_label_2.top = new FormAttachment(0, 28);
		fd_label_2.left = new FormAttachment(0, 10);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("文件路径：");

		filePath = new Text(composite, SWT.BORDER);
		final FormData fd_filePath = new FormData();
		fd_filePath.right = new FormAttachment(55, 0);
		fd_filePath.left = new FormAttachment(15, 0);
		fd_filePath.bottom = new FormAttachment(0, 44);
		fd_filePath.top = new FormAttachment(0, 25);
		filePath.setLayoutData(fd_filePath);

		fileOpenBtn = new Button(composite, SWT.NONE);
		fileOpenBtn.setImage(SWTResourceManager.getImage(ImportLogDlg.class, "/com/thunisoft/dataplatform/template/image/file.gif"));
		final FormData fd_fileOpenBtn = new FormData();
		fd_fileOpenBtn.right = new FormAttachment(55, 20);
		fd_fileOpenBtn.left = new FormAttachment(55, 0);
		fd_fileOpenBtn.bottom = new FormAttachment(0, 45);
		fd_fileOpenBtn.top = new FormAttachment(0, 25);
		fileOpenBtn.setLayoutData(fd_fileOpenBtn);

		filePathDefChk = new Button(composite, SWT.CHECK);
		final FormData fd_filePathDefChk = new FormData();
		fd_filePathDefChk.right = new FormAttachment(100, 0);
		fd_filePathDefChk.left = new FormAttachment(65, 0);
		fd_filePathDefChk.bottom = new FormAttachment(0, 44);
		fd_filePathDefChk.top = new FormAttachment(0, 24);
		filePathDefChk.setLayoutData(fd_filePathDefChk);
		filePathDefChk.setText("使用默认的文件路径");

		final Group composite_1 = new Group(shell, SWT.NONE);
		final FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(55, 0);
		fd_composite_1.bottom = new FormAttachment(90, 0);
		fd_composite_1.right = new FormAttachment(100, -20);
		fd_composite_1.left = new FormAttachment(0, 20);
		composite_1.setLayoutData(fd_composite_1);
		final FormLayout formLayout = new FormLayout();
		formLayout.marginHeight = 10;
		composite_1.setLayout(formLayout);

		resultLab = new Label(composite_1, SWT.NONE);
		final FormData gd_resultLab = new FormData();
		gd_resultLab.left = new FormAttachment(15, 0);
		gd_resultLab.right = new FormAttachment(35, 0);
		resultLab.setLayoutData(gd_resultLab);
		resultLab.setText("记录数量：");

		numLab = new Label(composite_1, SWT.NONE);
		final FormData gd_numLab = new FormData();
		gd_numLab.right = new FormAttachment(50, 0);
		gd_numLab.left = new FormAttachment(35, 0);
		numLab.setLayoutData(gd_numLab);
		numLab.setVisible(false);
		numLab.setText("Label");

		final Label label_3 = new Label(composite_1, SWT.NONE);
		final FormData gd_label_3 = new FormData();
		gd_label_3.right = new FormAttachment(75, 0);
		gd_label_3.left = new FormAttachment(60, 0);
		label_3.setLayoutData(gd_label_3);
		label_3.setText("导入结果：");

		importResultLab = new Label(composite_1, SWT.NONE);
		final FormData gd_importResultLab = new FormData();
		gd_importResultLab.right = new FormAttachment(100, 0);
		gd_importResultLab.left = new FormAttachment(75, 0);
		importResultLab.setLayoutData(gd_importResultLab);
		importResultLab.setVisible(false);
		importResultLab.setText("Label");

		viewBtn = new Button(composite_1, SWT.NONE);
		final FormData fd_viewBtn = new FormData();
		fd_viewBtn.top = new FormAttachment(50, 0);
		fd_viewBtn.right = new FormAttachment(32, 0);
		fd_viewBtn.left = new FormAttachment(22, 0);
		viewBtn.setLayoutData(fd_viewBtn);
		viewBtn.setText("查看");

		importBtn = new Button(composite_1, SWT.NONE);
		final FormData fd_importBtn = new FormData();
		fd_importBtn.right = new FormAttachment(47, 0);
		fd_importBtn.left = new FormAttachment(37, 0);
		fd_importBtn.top = new FormAttachment(50, 0);
		importBtn.setLayoutData(fd_importBtn);
		importBtn.setText("导入");

		resumeBtn = new Button(composite_1, SWT.NONE);
		final FormData fd_resumeBtn = new FormData();
		fd_resumeBtn.right = new FormAttachment(62, 0);
		fd_resumeBtn.left = new FormAttachment(52, 0);
		fd_resumeBtn.top = new FormAttachment(50, 0);
		resumeBtn.setLayoutData(fd_resumeBtn);
		resumeBtn.setText("重置");

		closeBtn = new Button(composite_1, SWT.NONE);
		final FormData fd_closeBtn = new FormData();
		fd_closeBtn.right = new FormAttachment(77, 0);
		fd_closeBtn.left = new FormAttachment(67, 0);
		fd_closeBtn.top = new FormAttachment(50, 0);
		closeBtn.setLayoutData(fd_closeBtn);
		closeBtn.setText("关闭");
		//
		addListener();
	}
	public void addListener() {
		ImportLogDlgListener dlgListener = new ImportLogDlgListener(this);
		dlgListener.addListener();
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


	public void setFilePathDefChk(Button filePathDefChk) {
		this.filePathDefChk = filePathDefChk;
	}


	public void setCloseBtn(Button closeBtn) {
		this.closeBtn = closeBtn;
	}


	public void setResumeBtn(Button resumeBtn) {
		this.resumeBtn = resumeBtn;
	}


	public void setImportBtn(Button importBtn) {
		this.importBtn = importBtn;
	}


	public void setViewBtn(Button viewBtn) {
		this.viewBtn = viewBtn;
	}


	public void setResultLab(Label resultLab) {
		this.resultLab = resultLab;
	}



	public void setNumLab(Label numLab) {
		this.numLab = numLab;
	}


	public void setFileOpenBtn(Button fileOpenBtn) {
		this.fileOpenBtn = fileOpenBtn;
	}


	public void setFilePath(Text filePath) {
		this.filePath = filePath;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public void setImportResultLab(Label importResultLab) {
		this.importResultLab = importResultLab;
	}

	public Action getFileOpenAction() {
		return fileOpenAction;
	}

	public void setFileOpenAction(Action fileOpenAction) {
		this.fileOpenAction = fileOpenAction;
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

   public Button getCloseBtn() {
      return closeBtn;
   }

   public Button getResumeBtn() {
      return resumeBtn;
   }

   public Button getImportBtn() {
      return importBtn;
   }

   public Button getViewBtn() {
      return viewBtn;
   }

   public Label getImportResultLab() {
      return importResultLab;
   }

   public Label getNumLab() {
      return numLab;
   }

   public Label getResultLab() {
      return resultLab;
   }
}
