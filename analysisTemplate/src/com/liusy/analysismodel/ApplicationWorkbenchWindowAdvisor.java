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
		// ����͸��ͼͼ��
   	IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setShowPerspectiveBar(true);
		// ���ò˵���
		configurer.setShowMenuBar(true);
		// ���ù�����
		configurer.setShowCoolBar(true);
		// ����״̬��
		configurer.setShowStatusLine(false);
		// ���ÿ�����ͼ��
		configurer.setTitle("���ݹ���ƽ̨");
      Rectangle screenSize = Display.getDefault().getClientArea();
      configurer.setInitialSize(new Point(screenSize.width, screenSize.height));
   	
   }

   public void postWindowOpen() {
      //�ô��ھ���
      Shell shell = getWindowConfigurer().getWindow().getShell();
      shell.setLocation(0, 0);
   }
}
