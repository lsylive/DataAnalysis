package com.liusy.analysismodel.template.actions;

import java.text.MessageFormat;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.LabelRetargetAction;

import com.liusy.analysis.template.model.util.StringUtil;

public class RedoRetargetAction extends LabelRetargetAction {
	public RedoRetargetAction() {
		super(StringUtil.redoId,MessageFormat.format(ActionMessages.RedoAction_Label,new Object[] {""}).trim()); //$NON-NLS-1$
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_REDO_DISABLED));
	}
}
