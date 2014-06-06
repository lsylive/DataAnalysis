package com.liusy.analysismodel.template.ui.dialog;

import java.io.File;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
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
import com.liusy.analysismodel.Application;

public class HelpDialog extends Dialog {

   protected Shell shell;
   private String  helpContext;
   private Browser browser;

   public HelpDialog(Shell parent, int style) {
      super(parent, style);
   }

   public HelpDialog(Shell parent, String helpContext) {
      this(parent, SWT.NONE);
      this.helpContext = helpContext;
   }

   public String open() {
      createContents();

      try {
         String helpPath = FileLocator.toFileURL(Platform.getBundle(Application.PLUGIN_ID).getEntry("")).getPath();
         String fName = "docs/" + helpContext + ".html";
         File file = new File(helpPath + fName);
         String url = file.toURL().toString();
         browser.setUrl(url);
      }
      catch (Exception e) {
         browser.setText("");
      }
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return helpContext;
   }

   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/question-frame.png"));
      shell.setLayout(new FillLayout());
      shell.setSize(800, 500);
      shell.setText("°ïÖú");

      final Composite composite = new Composite(shell, SWT.NONE);
      composite.setLayout(new FormLayout());

      final Group group_1 = new Group(composite, SWT.NONE);
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.bottom = new FormAttachment(100, -36);
      fd_group_1.left = new FormAttachment(0, 0);
      fd_group_1.top = new FormAttachment(0, -6);
      fd_group_1.right = new FormAttachment(100, 0);
      group_1.setLayoutData(fd_group_1);

      browser = new Browser(group_1, SWT.NONE);
      final FormData fd_browser = new FormData();
      fd_browser.bottom = new FormAttachment(100, -1);
      fd_browser.right = new FormAttachment(100, -1);
      fd_browser.left = new FormAttachment(0, 1);
      fd_browser.top = new FormAttachment(0, -5);
      browser.setLayoutData(fd_browser);

      final Button btnCancel = new Button(composite, SWT.NONE);
      final FormData fd_btnCancel = new FormData();
      fd_btnCancel.bottom = new FormAttachment(100, -5);
      fd_btnCancel.left = new FormAttachment(50, -40);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/Exit.png"));
      btnCancel.setText("¹Ø±Õ(&E)");
      btnCancel.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            shell.dispose();
         }
      });
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
