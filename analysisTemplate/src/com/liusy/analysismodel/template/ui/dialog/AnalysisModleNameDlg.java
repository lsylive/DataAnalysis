package com.liusy.analysismodel.template.ui.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;

public class AnalysisModleNameDlg extends Dialog {

   private Text txtFileName;
   private String textHd;
   protected Object result;
   protected Shell  shell;
   private Point loc;
   private int dlgType = 0;

   /**
    * Create the dialog
    * @param parent
    * @param style
    */
   public AnalysisModleNameDlg(Shell parent, int style) {
      super(parent, style);
      
   }

   /**
    * Create the dialog
    * @param parent
    */
   public AnalysisModleNameDlg(Shell parent,String text,Point location,int type) {
      this(parent, SWT.NONE);
      this.textHd = text;
      this.loc = location;
      this.dlgType = type;
   }

   /**
    * Open the dialog
    * @return the result
    */
   public Object open() {
      createContents();
      shell.setLocation(loc);
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return textHd;
   }

   /**
    * Create contents of the dialog
    */
   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setSize(320, 150);
      shell.setText("模板名称设置");

      final Label labAction = new Label(shell, SWT.NONE);
      if (dlgType == 1|| dlgType == 3) {
      	labAction.setText("新建：");
      } else {
      	labAction.setText("修改：");
      }
      
      labAction.setBounds(10, 48, 60, 15);

      txtFileName = new Text(shell, SWT.BORDER);
      txtFileName.addKeyListener(new KeyAdapter() {
      	public void keyPressed(final KeyEvent e) {
      		if (e.character == SWT.CR ) {
      			textHd = txtFileName.getText().trim();
               shell.close();
      		} 
      	}
      });
      txtFileName.setBounds(76, 45, 228, 22);
      txtFileName.setText(this.textHd);

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      btnOk.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            textHd = txtFileName.getText().trim();
            shell.close();
         }
      });
      btnOk.setText("确定(&O)");
      btnOk.setBounds(135, 95, 80, 22);

      final Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
      btnCancel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
         	textHd = "";
            shell.close();
         }
      });
      btnCancel.setText("取消(&C)");
      btnCancel.setBounds(225, 95, 80, 22);

     
      //
      txtFileName.setSelection(textHd.length());

      final CLabel imageLab = new CLabel(shell, SWT.NONE);
      if (dlgType == 1) {
      	imageLab.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/pencil.png"));
      } else {
      	imageLab.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/pencil.png"));
      }
      
      imageLab.setBounds(10, 10, 38, 19);

      final Label labInfo = new Label(shell, SWT.NONE);
      if (dlgType == 1) {
      	labInfo.setText("请输入模板名称。");
      } else {
      	labInfo.setText("请输入新的模板名称。");
      }
      
      labInfo.setBounds(75, 15, 228, 15);
      //
      init();
   }
   public void init() {
   	if (dlgType == 1) {
   		
   	} else if (dlgType == 2){
   		
   	}
   }

}
