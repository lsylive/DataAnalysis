package com.liusy.analysismodel;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.util.DbConnectionManager;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {
   public static final String         PLUGIN_ID     = "DataAdminPlatform";
   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
    */
   public Object start(IApplicationContext context) {

      int initresult = DataAdminPlatform.init();

      if (initresult == DataAdminPlatform.INIT_SUCCESS) {
         DbConnectionManager.getConnection();
         Display display = PlatformUI.createDisplay();

         //进入登陆界面
         int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
         if (returnCode == PlatformUI.RETURN_RESTART) {
            return IApplication.EXIT_RESTART;
         }
         else {
            DbConnectionManager.closeConnection();
            return IApplication.EXIT_OK;
         }

      }
      else {
         return IApplication.EXIT_OK;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.eclipse.equinox.app.IApplication#stop()
    */
   public void stop() {
      final IWorkbench workbench = PlatformUI.getWorkbench();

      if (workbench == null) return;
      final Display display = workbench.getDisplay();
      display.syncExec(new Runnable() {
         public void run() {
            if (!display.isDisposed()) workbench.close();
         }
      });
   }
}
