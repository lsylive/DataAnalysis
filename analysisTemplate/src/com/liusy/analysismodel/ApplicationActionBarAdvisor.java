package com.liusy.analysismodel;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.liusy.analysismodel.log.view.ApplayLogView;
import com.liusy.analysismodel.log.view.LogExportView;
import com.liusy.analysismodel.log.view.LogImportView;
import com.liusy.analysismodel.log.view.LogonLogView;
import com.liusy.analysismodel.log.view.SystemLogView;
import com.liusy.analysismodel.log.view.UserAudioLogView;
import com.liusy.analysismodel.menubar.AnalysisModelAction;
import com.liusy.analysismodel.menubar.ApplyLogAction;
import com.liusy.analysismodel.menubar.ExitAction;
import com.liusy.analysismodel.menubar.LogExportAction;
import com.liusy.analysismodel.menubar.LogImportAction;
import com.liusy.analysismodel.menubar.LogonLogAction;
import com.liusy.analysismodel.menubar.SystemLogAction;
import com.liusy.analysismodel.menubar.UserAudioLogAction;
import com.liusy.analysismodel.template.TemplateView;
import com.liusy.analysismodel.template.actions.DeleteDiagramAction;
import com.liusy.analysismodel.template.actions.NewDiagramAction;
import com.liusy.analysismodel.template.actions.SaveDiagramAction;
import com.liusy.analysismodel.template.ui.dialog.DataSourceDialog;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

   private Action              dataSourceAction;
   private ExitAction          exitAction;

   private AnalysisModelAction analysisModelAction;

   private SaveDiagramAction save;
   
   private DeleteDiagramAction del;

   private NewDiagramAction newDiagramAction;
   public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
      super(configurer);
   	ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();
   	IActionSetDescriptor[] actionSets = reg.getActionSets();
   	// removing annoying gotoLastPosition Message.
   	String actionSetId = "org.eclipse.ui.edit.text.actionSet.navigation"; //$NON-NLS-1$
   	String actionSetId2 = "org.eclipse.ui.edit.text.actionSet.annotationNavigation"; //$NON-NLS-1$
   	String actionSetId3 = "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo"; //$NON-NLS-1$
   	for (int i = 0; i <actionSets.length; i++)
   	{
   	    if (!(actionSets[i].getId().equals(actionSetId)||actionSets[i].getId().equals(actionSetId2)||
   	   		 actionSets[i].getId().equals(actionSetId3)))
   	        continue;
   	        IExtension ext = actionSets[i].getConfigurationElement()
   	            .getDeclaringExtension();
   	        reg.removeExtension(ext, new Object[] {actionSets[i]});
   	}
   	
   }

   protected void makeActions(final IWorkbenchWindow window) {
	
      exitAction = new ExitAction(window, "是否真的退出?");
      exitAction.setToolTipText("退出系统");
      exitAction.setText("退出(&E)");
      register(exitAction);
      
      save = new SaveDiagramAction(window, "保存模板");
//      ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID,"/src/com/thunisoft/dataplatform/template/image/save.png");
//      save.setImageDescriptor(imageDes);
      register(save);
      del = new DeleteDiagramAction(window, "删除模板");
      register(del);
      newDiagramAction = new NewDiagramAction(window, "新建模板");
      register(newDiagramAction);
      analysisModelAction = new AnalysisModelAction(window, "数据分析模版", TemplateView.ID);
      analysisModelAction.setText("数据分析模版(&T)");
      register(analysisModelAction);

      dataSourceAction = new Action("数据源管理(&D)") {
         public void run() {
        	 DataSourceDialog dlg = new DataSourceDialog(window.getShell());
 			dlg.open();
         }
      };
   }

   protected void fillMenuBar(IMenuManager menuBar) {
      //文件
      try {
         //			MenuManager newMenu = new MenuManager("&New","new");
         //			newMenu.add(newWizardAction);

         //			menuBar.add(newMenu);

         MenuManager analysitModelManagerMenu = new MenuManager("分析模版管理(&A)");

         analysitModelManagerMenu.add(dataSourceAction);

         analysitModelManagerMenu.add(analysisModelAction);
         //			menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
         Separator sep2 = new Separator();
         analysitModelManagerMenu.add(sep2);
         analysitModelManagerMenu.add(exitAction);
         
         menuBar.add(analysitModelManagerMenu);

      }
      catch (Exception e) {
         e.printStackTrace();
      }

   }
   protected void fillCoolBar(ICoolBarManager coolBar) {
//   	ActionSetRegistry reg = WorkbenchPlugin.getDefault().getActionSetRegistry();
//   	IActionSetDescriptor[] actionSets = reg.getActionSets();
//   	// removing annoying gotoLastPosition Message.
//   	String actionSetId = "org.eclipse.ui.edit.text.actionSet.navigation"; //$NON-NLS-1$
//   	for (int i = 0; i <actionSets.length; i++)
//   	{
//   	    if (!actionSets[i].getId().equals(actionSetId))
//   	        continue;
//   	        IExtension ext = actionSets[i].getConfigurationElement()
//   	            .getDeclaringExtension();
//   	        reg.removeExtension(ext, new Object[] { actionSets[i] });
//   	}
//   	actionSetId = "org.eclipse.ui.edit.text.actionSet.annotationNavigation"; //$NON-NLS-1$
//   	for (int i = 0; i <actionSets.length; i++)
//   	{
//   	    if (!actionSets[i].getId().equals(actionSetId))
//   	        continue;
//   	        IExtension ext = actionSets[i].getConfigurationElement()
//   	            .getDeclaringExtension();
//   	        reg.removeExtension(ext, new Object[] { actionSets[i] });
//   	}
//   	// Removing convert line delimiters menu.
//   	actionSetId = "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo"; //$NON-NLS-1$
//   	for (int i = 0; i <actionSets.length; i++)
//   	{
//   	    if (!actionSets[i].getId().equals(actionSetId))
//   	        continue;
//   	    IExtension ext = actionSets[i].getConfigurationElement()
//   	            .getDeclaringExtension();
//   	   reg.removeExtension(ext, new Object[] { actionSets[i] });
//   	} 
   	
//   	coolBar.removeAll();
   	IToolBarManager toolbar1 = new ToolBarManager(coolBar.getStyle());
		coolBar.add(toolbar1);
		toolbar1.add(newDiagramAction);
		toolbar1.add(save);
		toolbar1.add(del);
   	
   }

}
