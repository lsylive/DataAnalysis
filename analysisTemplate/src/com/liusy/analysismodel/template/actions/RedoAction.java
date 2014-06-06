package com.liusy.analysismodel.template.actions;

import java.text.MessageFormat;

import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.actions.ActionFactory;

import org.eclipse.gef.commands.Command;
//import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.StackAction;

import com.liusy.analysis.template.model.util.StringUtil;
/**
 * An action to redo the last command.
 */
public class RedoAction extends StackAction{

	/**
	 * Creates a <code>MyRedoAction</code> and associates it with the given workbech part.
	 * @param part The workbench part this action is associated with.
	 */
	public RedoAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Creates a <code>MyRedoAction</code> and associates it with the given editor.
	 * @param editor The editor this action is associated with.
	 */
	public RedoAction(IEditorPart editor) {
		super(editor);
	}

	/**
	 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return getCommandStack().canRedo();
	}

	/**
	 * Initializes this actions text and images.
	 */
	protected void init() {
		super.init();
		setToolTipText(MessageFormat.format(
				ActionMessages.RedoAction_Tooltip,	
				new Object[] {""}).trim());  //$NON-NLS-1$
		setText(MessageFormat.format(
				ActionMessages.RedoAction_Label, 
				new Object[] {""}).trim()  //$NON-NLS-1$
				);
		setId(StringUtil.redoId);
		
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_REDO_DISABLED));
	}

	/**
	 * Refreshes this action's text to use the last undone command's label.
	 */
	protected void refresh() {
		Command redoCmd = getCommandStack().getRedoCommand();
		setToolTipText(MessageFormat.format(
				ActionMessages.RedoAction_Tooltip,
				new Object [] {getLabelForCommand(redoCmd)}).trim());
		setText(MessageFormat.format(
				ActionMessages.RedoAction_Label,
				new Object[]{getLabelForCommand(redoCmd)}).trim());
		super.refresh();
	}

	/**
	 * Redoes the last command.
	 */
	public void run() {
		getCommandStack().redo();
	}

}
