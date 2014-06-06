package com.liusy.analysismodel.menubar;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import com.liusy.analysismodel.log.IdCommands;

public class ApplyLogAction extends Action {

   private final IWorkbenchWindow window;
   private int                    instanceNum = 0;
   private final String           viewId;
   private String                 title;
   private boolean                closeFlg    = false;
   private long                   clickNum    = 0;

   public ApplyLogAction(IWorkbenchWindow window, String label, String viewId) {
      this.window = window;
      this.viewId = viewId;
      setText(label);
      this.title = label;
      this.closeFlg = false;
      // The id is used to refer to the action in a menu or toolbar
      setId(IdCommands.CMD_SYSTEMLOG);

   }

   public void run() {
      if (window != null) {
         //***********11-28

         //         if (clickNum % 2 == 1) {
         //            setText(title);
         //         }
         //         else {
         //            String label = "¹Ø±Õ" + title;
         //            setText(label);
         //         }
         //         clickNum++;

         //***********11-28
         try {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId("DataAdminPlatform.perspective");
            page.setPerspective(perspective);

            //				try {
            //					PlatformUI.getWorkbench().getViewRegistry().find(viewId).createView();
            //				} catch (CoreException e) {
            //					// TODO Auto-generated catch block
            //					e.printStackTrace();
            //				}

            //				page.hideView(page.getViews()[0]);

            //				page.hideView(page.getViewReferences()[0].getView(true));

            //				page.hideView(page.getViewReferences()[0]);
            //				page.findView(viewId).dispose();
            page.showView(viewId, null, IWorkbenchPage.VIEW_ACTIVATE);
            window.getShell().setText(title);
         }
         catch (PartInitException e) {
            MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
         }
      }
   }

}
