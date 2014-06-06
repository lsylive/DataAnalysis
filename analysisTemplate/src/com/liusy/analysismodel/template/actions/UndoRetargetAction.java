package com.liusy.analysismodel.template.actions;

import java.text.MessageFormat;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.LabelRetargetAction;

import com.liusy.analysis.template.model.util.StringUtil;

public class UndoRetargetAction extends LabelRetargetAction {
	public UndoRetargetAction() {
		super(StringUtil.undoId,MessageFormat.format(ActionMessages.UndoAction_Label,new Object[] {""}).trim()); //$NON-NLS-1$
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_UNDO_DISABLED));
	}
}
