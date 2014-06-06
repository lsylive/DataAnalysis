package com.liusy.analysismodel.template.ui.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.GenerateDataField;

public class GenerateColumnDialog extends Dialog {

   private Combo             cboType;
   private StyledText        txtExpression;
   protected Shell           shell;
   private GenerateDataField dataField;

   public GenerateColumnDialog(Shell parent, int style) {
      super(parent, style);
   }

   public GenerateColumnDialog(Shell parent, GenerateDataField dataField) {
      this(parent, SWT.NONE);
      this.dataField = dataField;
   }

   private int getIndex(String value){
      for (int i = 0; i < Consts.GENCOLUMN_LABEL.length; i++) {
         if (Consts.GENCOLUMN_LABEL[i].equalsIgnoreCase(value)) return i;
      }
      return -1;
   }
   
   public GenerateDataField open() {
      createContents();
      cboType.select(getIndex(dataField.getExpressionType()));
      txtExpression.setText(dataField.getExpression());
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return dataField;
   }

   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(450, 230);
      shell.setText("生成表达式");

      final Group group_1 = new Group(shell, SWT.NONE);
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.bottom = new FormAttachment(100, -35);
      fd_group_1.top = new FormAttachment(0, 0);
      fd_group_1.right = new FormAttachment(100, -1);
      fd_group_1.left = new FormAttachment(0, 1);
      group_1.setLayoutData(fd_group_1);

      final Label lblType = new Label(group_1, SWT.RIGHT);
      lblType.setVisible(false);
      final FormData fd_lblType = new FormData();
      fd_lblType.top = new FormAttachment(0, 0);
      fd_lblType.right = new FormAttachment(20, 0);
      fd_lblType.left = new FormAttachment(0, 0);
      lblType.setLayoutData(fd_lblType);
      lblType.setText("表达式类型：");

      cboType = new Combo(group_1, SWT.NONE | SWT.READ_ONLY);
      cboType.setVisible(false);
      final FormData fd_cboType = new FormData();
      fd_cboType.top = new FormAttachment(0, 0);
      fd_cboType.left = new FormAttachment(lblType, 1);
      fd_cboType.right = new FormAttachment(70, 0);
      cboType.setLayoutData(fd_cboType);
      cboType.setItems(Consts.GENCOLUMN_LABEL);
      cboType.setVisibleItemCount(10);

      final Label lblExpression = new Label(group_1, SWT.RIGHT);
      lblExpression.setVisible(false);
      final FormData fd_lblExpression = new FormData();
      fd_lblExpression.top = new FormAttachment(lblType, 10);
      fd_lblExpression.right = new FormAttachment(20, 0);
      fd_lblExpression.left = new FormAttachment(0, 0);
      lblExpression.setLayoutData(fd_lblExpression);
      lblExpression.setText("表达式：");

      txtExpression = new StyledText(group_1, SWT.BORDER);
      txtExpression.setFont(SWTResourceManager.getFont("Fixedsys", 12, SWT.NONE));
      final FormData fd_txtExpression = new FormData();
      fd_txtExpression.top = new FormAttachment(0, -5);
      fd_txtExpression.left = new FormAttachment(0, 1);
//      fd_txtExpression.top = new FormAttachment(lblType, 10);
//      fd_txtExpression.left = new FormAttachment(lblExpression, 1);
      fd_txtExpression.bottom = new FormAttachment(100, -1);
      fd_txtExpression.right = new FormAttachment(100, -1);
      txtExpression.setLayoutData(fd_txtExpression);

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 25;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -10);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");
      btnOk.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            ok();
         }
      });

      final Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
      final FormData fd_btnCancel = new FormData();
      fd_btnCancel.height = 25;
      fd_btnCancel.width = 80;
      fd_btnCancel.bottom = new FormAttachment(100, -5);
      fd_btnCancel.left = new FormAttachment(50, 10);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");
      btnCancel.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            shell.dispose();
         }
      });
   }

   private void ok() {
      dataField.setExpressionType(cboType.getText());
      dataField.setExpression(txtExpression.getText());
      shell.dispose();
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
}
