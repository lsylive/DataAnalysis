package com.liusy.analysismodel.template.ui.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;

public class FilterExpressionDialog extends Dialog {

   private StyledText txtExpression;
   protected Shell    shell;
   private String     expression;

   public FilterExpressionDialog(Shell parent, int style) {
      super(parent, style);
   }

   public FilterExpressionDialog(Shell parent, String expression) {
      this(parent, SWT.NONE);
      this.expression = expression;
   }

   public String open() {
      createContents();
      txtExpression.setText(expression);
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return expression;
   }

   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
      shell.setLayout(new FormLayout());
      shell.setSize(450, 230);
      shell.setText("生成表达式");

      final Composite composite = new Composite(shell, SWT.NONE);
      final FormData fd_composite = new FormData();
      fd_composite.bottom = new FormAttachment(100, 0);
      fd_composite.right = new FormAttachment(100, 0);
      fd_composite.top = new FormAttachment(0, 0);
      fd_composite.left = new FormAttachment(0, 0);
      composite.setLayoutData(fd_composite);
      composite.setLayout(new FormLayout());

      final Group group_1 = new Group(composite, SWT.NONE);
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.bottom = new FormAttachment(100, -37);
      fd_group_1.top = new FormAttachment(0, -7);
      fd_group_1.right = new FormAttachment(100, 0);
      fd_group_1.left = new FormAttachment(0, 0);
      group_1.setLayoutData(fd_group_1);

      txtExpression = new StyledText(group_1, SWT.BORDER);
      final FormData fd_txtExpression = new FormData();
      fd_txtExpression.top = new FormAttachment(0, -6);
      fd_txtExpression.left = new FormAttachment(0, 0);
      fd_txtExpression.bottom = new FormAttachment(100, 0);
      fd_txtExpression.right = new FormAttachment(100, 0);
      txtExpression.setLayoutData(fd_txtExpression);

      final Button btnOk = new Button(composite, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 28;
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

      final Button btnCancel = new Button(composite, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
      final FormData fd_btnCancel = new FormData();
      fd_btnCancel.height = 28;
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
      expression = txtExpression.getText();
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
