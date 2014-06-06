package com.liusy.analysismodel;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.ide.EditorAreaDropAdapter;
import org.eclipse.ui.internal.util.PrefUtil;
import org.eclipse.ui.views.navigator.LocalSelectionTransfer;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

   public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
      super(configurer);
   }

   public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
      return new ApplicationActionBarAdvisor(configurer);
   }

   public void preWindowOpen() {
   	IPreferenceStore preStore = PrefUtil.getAPIPreferenceStore();
		preStore.setValue(
				IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS,
				true);
		// 设置透视图图栏
   	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setShowPerspectiveBar(true);
		// 设置菜单栏
		configurer.setShowMenuBar(true);
		// 设置工具栏
		configurer.setShowCoolBar(true);
		// 设置状态线
		configurer.setShowStatusLine(false);
		// 设置快速视图栏
		configurer.setTitle("数据管理平台");
      Rectangle screenSize = Display.getDefault().getClientArea();
      configurer.setInitialSize(new Point(screenSize.width, screenSize.height));
   	
   }

   public void postWindowOpen() {
      //让窗口居中
      Shell shell = getWindowConfigurer().getWindow().getShell();
      shell.setLocation(0, 0);
   }
}
