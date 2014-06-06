package com.liusy.analysismodel.template.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.node.IntersectionNode;

public class IntersectionNodeDialog extends Dialog {

   protected int                   result;
   protected Shell                 shell;
   private IntersectionNode        node;
   private Text                    txtName;
   private StyledText              txtDes;
   private NodePropertyEditCommand cmd;

   /**
    * Create the dialog.
    * 
    * @param parent
    * @param style
    */
   public IntersectionNodeDialog(Shell parent, int style) {
      super(parent, style);
      setText("交集转换");
   }

   public IntersectionNodeDialog(Shell parent, IntersectionNode node, NodePropertyEditCommand cmd) {
      this(parent, SWT.NONE);
      this.node = node;
      this.cmd = cmd;
   }

   /**
    * Open the dialog.
    * 
    * @return the result
    */
   public int open() {
      createContents();
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) {
            display.sleep();
         }
      }
      return result;
   }

   /**
    * Create contents of the dialog.
    */
   private void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM);
      shell.setSize(564, 191);
      shell.setText(getText());
      shell.setLayout(new FormLayout());

      Group group = new Group(shell, SWT.NONE);
      FormData fd_group = new FormData();
      fd_group.top = new FormAttachment(0, 10);
      fd_group.left = new FormAttachment(0, 10);
      fd_group.right = new FormAttachment(0, 545);
      group.setLayoutData(fd_group);
      group.setLayout(new FormLayout());

      Label label = new Label(group, SWT.RIGHT);
      label.setText("节点名称：");
      FormData fd_label = new FormData();
      fd_label.top = new FormAttachment(0);
      fd_label.right = new FormAttachment(15);
      fd_label.left = new FormAttachment(0);
      label.setLayoutData(fd_label);

      txtName = new Text(group, SWT.BORDER);
      txtName.setText("");
      txtName.setEditable(true);
      FormData fd_txtName = new FormData();
      fd_txtName.top = new FormAttachment(0);
      fd_txtName.right = new FormAttachment(40);
      fd_txtName.left = new FormAttachment(label);
      txtName.setLayoutData(fd_txtName);

      Label label_1 = new Label(group, SWT.RIGHT);
      label_1.setText("说明：");
      FormData fd_label_1 = new FormData();
      fd_label_1.bottom = new FormAttachment(100, -2);
      fd_label_1.top = new FormAttachment(label, 10);
      fd_label_1.right = new FormAttachment(15);
      fd_label_1.left = new FormAttachment(0);
      label_1.setLayoutData(fd_label_1);

      txtDes = new StyledText(group, SWT.BORDER);
      txtDes.setText("");
      FormData fd_txtDes = new FormData();
      fd_txtDes.bottom = new FormAttachment(100, -2);
      fd_txtDes.top = new FormAttachment(txtName, 5);
      fd_txtDes.right = new FormAttachment(100, -13);
      fd_txtDes.left = new FormAttachment(15);
      txtDes.setLayoutData(fd_txtDes);

      Button btnCancel = new Button(shell, SWT.NONE);
      fd_group.bottom = new FormAttachment(btnCancel, -6);
      btnCancel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            close();
         }
      });
      btnCancel.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/cross.png"));
      FormData fd_btnCancel = new FormData();
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");

      Button btnOk = new Button(shell, SWT.NONE);
      fd_btnCancel.bottom = new FormAttachment(btnOk, 0, SWT.BOTTOM);
      fd_btnCancel.left = new FormAttachment(btnOk, 44);
      btnOk.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            save();
            close();
         }
      });
      btnOk.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/tick.png"));
      FormData fd_btnOk = new FormData();
      fd_btnOk.right = new FormAttachment(100, -303);
      fd_btnOk.bottom = new FormAttachment(100, -10);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");

      init();

   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());
   }

   private void close() {
      shell.dispose();
   }

   private void save() {
      node.setDescription(txtDes.getText().trim());
      node.setName(txtName.getText().trim());
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
