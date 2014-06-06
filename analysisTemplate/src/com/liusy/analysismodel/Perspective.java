package com.liusy.analysismodel;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.liusy.analysismodel.log.view.ApplayLogView;
import com.liusy.analysismodel.log.view.LogExportView;
import com.liusy.analysismodel.log.view.LogonLogView;
import com.liusy.analysismodel.log.view.SystemLogView;
import com.liusy.analysismodel.log.view.UserAudioLogView;
import com.liusy.analysismodel.log.view.UserStaticLogView;
import com.liusy.analysismodel.template.TemplateView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		
		layout.setFixed(true);
		layout.getViewLayout(View.ID).setCloseable(false);
		layout.getViewLayout(SystemLogView.ID).setCloseable(false);
		layout.getViewLayout(LogonLogView.ID).setCloseable(false);
		layout.getViewLayout(ApplayLogView.ID).setCloseable(false);
		layout.getViewLayout(LogExportView.ID).setCloseable(false);
		layout.getViewLayout(UserAudioLogView.ID).setCloseable(false);
		layout.getViewLayout(UserStaticLogView.ID).setCloseable(false);
		
		layout.getViewLayout(SystemLogView.ID).setMoveable(false);
		layout.getViewLayout(LogonLogView.ID).setMoveable(false);
		layout.getViewLayout(ApplayLogView.ID).setMoveable(false);
		layout.getViewLayout(LogExportView.ID).setMoveable(false);
		layout.getViewLayout(UserAudioLogView.ID).setMoveable(false);
		layout.getViewLayout(UserStaticLogView.ID).setMoveable(false);
		layout.getViewLayout(TemplateView.ID).setMoveable(false);
		
		//*****************11-28
//		layout.addStandaloneView(View.ID, false, IPageLayout.LEFT, 1.0f, editorArea);
//		layout.addStandaloneViewPlaceholder(ApplayLogView.ID, IPageLayout.LEFT, 1.0f, editorArea, true);
//		layout.addStandaloneViewPlaceholder(LogonLogView.ID, IPageLayout.LEFT, 1.0f, editorArea, true);
//		layout.addStandaloneViewPlaceholder(UserAudioLogView.ID, IPageLayout.LEFT, 1.0f, editorArea, true);
		//*****************11-28
		
		//layout.addView(View.ID, IPageLayout.LEFT, 1.0f, editorArea);
		
		
		//*************11-30
		IFolderLayout folder = layout.createFolder("folderID", IPageLayout.TOP,   
			    0.5f, IPageLayout.ID_EDITOR_AREA);   
			  folder.addPlaceholder(View.ID + ":*");   
			  folder.addView(View.ID);  
			  folder.addPlaceholder(ApplayLogView.ID + ":*");   
			  folder.addView(ApplayLogView.ID);  
			  folder.addPlaceholder(LogonLogView.ID + ":*");   
			  folder.addView(LogonLogView.ID); 
			  folder.addPlaceholder(UserAudioLogView.ID + ":*");   
			  folder.addView(UserAudioLogView.ID); 

		
		//*************11-30
		
		
		//************************
		// Top left: Resource Navigator view and Bookmarks view placeholder
//		 IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f,
//		    editorArea);
//		 topLeft.addView(IPageLayout.ID_RES_NAV);
//		 topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);
//
//		 // Bottom left: Outline view and Property Sheet view
//		 IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.50f,
//		 	   "topLeft");
//		 bottomLeft.addView(IPageLayout.ID_OUTLINE);
//		 bottomLeft.addView(IPageLayout.ID_PROP_SHEET);
//
//		 // Bottom right: Task List view
//		 layout.addView(IPageLayout.ID_TASK_LIST, IPageLayout.BOTTOM, 0.66f, editorArea);
		 //*************************
		
//		layout.getViewLayout(View.ID).setCloseable(true);
	}

}
