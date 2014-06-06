package com.liusy.analysismodel.template.ui;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.ui.IEditorPart;

import com.liusy.analysismodel.template.actions.SelfCommandStack;


/**
 * The collective state of a GEF "application", loosely defined by a CommandStack,
 * one or more EditPartViewers, and the active Tool. An EditDomain is usually tied with an
 * Eclipse {@link org.eclipse.ui.IEditorPart IEditorPart}).  However, the distinction
 * between EditorPart and EditDomain was made to allow for much flexible use of the
 * Graphical Editing Framework.
 */
public class LiusyEditDomain extends DefaultEditDomain{

	public LiusyEditDomain(IEditorPart editorPart) {
		super(editorPart);
	}
	public void setCommandStack(SelfCommandStack stack) {
		super.setCommandStack(stack);
	}
}