package com.liusy.analysismodel;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultMultiTabListener;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultSimpleTabListener;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultTabFolder;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultThemeListener;
import org.eclipse.ui.internal.presentations.util.PresentablePartFolder;
import org.eclipse.ui.internal.presentations.util.TabbedStackPresentation;
import org.eclipse.ui.presentations.IStackPresentationSite;
import org.eclipse.ui.presentations.StackPresentation;
import org.eclipse.ui.presentations.WorkbenchPresentationFactory;

public class UnCloseableEditorPresentationFactory extends WorkbenchPresentationFactory {
   private static int editorTabPosition = PlatformUI.getPreferenceStore().getInt("EDITOR_TAB_POSITION");
   private static int viewTabPosition   = PlatformUI.getPreferenceStore().getInt("VIEW_TAB_POSITION");

   @SuppressWarnings("restriction")
   public StackPresentation createEditorPresentation(Composite parent, IStackPresentationSite site) {

      DefaultTabFolder folder = new UnCloseableEditorFolder(parent, editorTabPosition | SWT.BORDER, false, false);

      /*
       * Set the minimum characters to display, if the preference is something
       * other than the default. This is mainly intended for RCP applications or
       * for expert users (i.e., via the plug-in customization file).
       */
      final IPreferenceStore store = PlatformUI.getPreferenceStore();
      if (store.contains(IWorkbenchPreferenceConstants.EDITOR_MINIMUM_CHARACTERS)) {
         final int minimumCharacters = store.getInt(IWorkbenchPreferenceConstants.EDITOR_MINIMUM_CHARACTERS);
         if (minimumCharacters >= 0) {
            folder.setMinimumCharacters(minimumCharacters);
         }
      }

      PresentablePartFolder partFolder = new PresentablePartFolder(folder);
      //      partFolder.getTabFolder().get
      TabbedStackPresentation result = new TabbedStackPresentation(site, partFolder, new MyEditorSystemMenu(site));

      DefaultThemeListener themeListener = new DefaultThemeListener(folder, result.getTheme());
      result.getTheme().addListener(themeListener);

      new DefaultMultiTabListener(result.getApiPreferences(), "SHOW_MULTIPLE_EDITOR_TABS", folder);

      new DefaultSimpleTabListener(result.getApiPreferences(), IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, folder);

      return result;

   }

   public StackPresentation createViewPresentation(Composite parent, IStackPresentationSite site) {
      DefaultTabFolder folder = new UnCloseableEditorFolder(parent, viewTabPosition | SWT.BORDER, false, false);

      final IPreferenceStore store = PlatformUI.getPreferenceStore();
      final int minimumCharacters = store.getInt(IWorkbenchPreferenceConstants.VIEW_MINIMUM_CHARACTERS);
      if (minimumCharacters >= 0) {
         folder.setMinimumCharacters(minimumCharacters);
      }

      PresentablePartFolder partFolder = new PresentablePartFolder(folder);

      folder.setUnselectedCloseVisible(false);
      folder.setUnselectedImageVisible(true);

      TabbedStackPresentation result = new TabbedStackPresentation(site, partFolder, new MyViewSystemMenu(site));

      DefaultThemeListener themeListener = new DefaultThemeListener(folder, result.getTheme());
      result.getTheme().addListener(themeListener);

      new DefaultSimpleTabListener(result.getApiPreferences(), IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, folder);

      return result;

   }
}
