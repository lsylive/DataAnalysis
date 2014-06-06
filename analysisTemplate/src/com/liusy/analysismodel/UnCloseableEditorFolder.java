package com.liusy.analysismodel;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.internal.presentations.defaultpresentation.DefaultTabFolder;
import org.eclipse.ui.internal.presentations.util.AbstractTabItem;

public class UnCloseableEditorFolder extends DefaultTabFolder {
	private static ToolBar  toolBar = null;
   @SuppressWarnings("restriction")
public UnCloseableEditorFolder(Composite parent, int flags, boolean allowMin, boolean allowMax) {
	   super(parent, flags, allowMin, allowMax);
   }
   public void layout(boolean flushCache) {
	   super.layout(flushCache);
   }
   @SuppressWarnings("restriction")
public AbstractTabItem add(int index, int flags) {
      return super.add(index, flags);
  }
}
