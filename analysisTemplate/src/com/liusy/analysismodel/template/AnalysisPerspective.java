package com.liusy.analysismodel.template;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.liusy.analysismodel.template.TemplateView;

public class AnalysisPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.setFixed(true);
//		layout.addStandaloneView(View2.ID, true, IPageLayout.LEFT, 0.4f, editorArea);
//		layout.addView(View2.ID, IPageLayout.LEFT, 0.4f, editorArea);
//		layout.getViewLayout(View2.ID)
//		layout.addStandaloneViewPlaceholder(TemplateView.ID, IPageLayout.LEFT, 0.2f, editorArea, true);
		layout.addView(TemplateView.ID, IPageLayout.LEFT, 0.2f, editorArea);
		//		addFastViews(layout);
	}

}
