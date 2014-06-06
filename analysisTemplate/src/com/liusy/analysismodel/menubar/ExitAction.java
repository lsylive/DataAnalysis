package com.liusy.analysismodel.menubar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.liusy.analysismodel.log.IdCommands;
import com.liusy.analysismodel.util.MessageUtil;

/**
 * 菜单退出action
 * 
 * @author andy
 */
public class ExitAction extends Action {
   private final IWorkbenchWindow window;
   private int                    instanceNum = 0;

   /**
    * 构造方法
    * 
    * @param window
    * @param label
    */
   public ExitAction(IWorkbenchWindow window, String label) {

      this.window = window;
      setText(label);
      setId(IdCommands.CMD_EXIT);
      setActionDefinitionId(IdCommands.CMD_EXIT);
      ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin("DataAdminPlatform", "icons/Exit.png");
      this.setImageDescriptor(descriptor);

   }

   /**
    * 运行的方法
    */
   public void run() {
      Shell shell = window.getShell();
      MessageDialog dialog = new MessageDialog(shell, "退出", null, "是否真的退出?", MessageDialog.QUESTION, new String[] { "是", "否" }, 0);
      int result = dialog.open();
      if (result == 0) ActionFactory.QUIT.create(window).run();
   }

}
