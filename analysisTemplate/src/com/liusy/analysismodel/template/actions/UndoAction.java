package com.liusy.analysismodel.template.actions;

import java.text.MessageFormat;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.StackAction;

import com.liusy.analysismodel.template.commands.DataNodeBeanEditCommand;
import com.liusy.analysis.template.model.util.StringUtil;
/**
 * An action to undo the last command.
 */
public class UndoAction
	extends StackAction
{
public SchemaContextMenuProvider menuProvider;
/**
 * Creates an <code>MyUndoAction</code> and associates it with the given editor.
 * @param editor The editor this action is associated with.
 */
public UndoAction(IEditorPart editor) {
	super(editor);
}

/**
 * Creates an <code>MyUndoAction</code> and associates it with the given editor.
 * @param part The workbench part this action is associated with.
 */
public UndoAction(IWorkbenchPart part) {
	super(part);
}

/**
 * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
 */
protected boolean calculateEnabled() {
//	return getCommandStack().canUndo();
	Command undoCmd = getCommandStack().getUndoCommand();
	if (undoCmd instanceof DataNodeBeanEditCommand ) {
		return false;
	} else {
		return getCommandStack().canUndo();
	}
}

/**
 * Initializes this action's text and images.
 */
protected void init() {
	super.init();
	setToolTipText(MessageFormat.format(
			ActionMessages.UndoAction_Tooltip,	
			new Object[] {""}).trim());  //$NON-NLS-1$
	setText(MessageFormat.format(
			ActionMessages.UndoAction_Label, 
			new Object[] {""}).trim()  //$NON-NLS-1$
			);
	setId(StringUtil.undoId);

	ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
	setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
	setDisabledImageDescriptor(sharedImages.getImageDescriptor(
			ISharedImages.IMG_TOOL_UNDO_DISABLED));
}

/**
 * Refreshes this action's text to use the last executed command's label.
 */
protected void refresh() {
	Command undoCmd = getCommandStack().getUndoCommand();
	
	super.refresh();
	if (undoCmd instanceof DataNodeBeanEditCommand ) {
//		menuProvider.setUndoFlag(false);
		setToolTipText(MessageFormat.format(
				ActionMessages.UndoAction_Tooltip,	
				new Object[] {""}).trim());  //$NON-NLS-1$
		setText(MessageFormat.format(
				ActionMessages.UndoAction_Label, 
				new Object[] {""}).trim()  //$NON-NLS-1$
				);
	} else {
//		menuProvider.setUndoFlag(true);
		setToolTipText(MessageFormat.format(
				ActionMessages.UndoAction_Tooltip,
				new Object []{getLabelForCommand(undoCmd)}).trim());
		setText(MessageFormat.format(
				ActionMessages.UndoAction_Label,
				new Object []{getLabelForCommand(undoCmd)}).trim()
				);
	}
	
}

/**
 * Undoes the last command.
 */
public void run() {
	Command undoCmd = getCommandStack().getUndoCommand();
	if (undoCmd instanceof DataNodeBeanEditCommand ) {
//		menuProvider.setUndoFlag(false);
//		getCommandStack().undo();
	} else {
		getCommandStack().undo();
	}
	
}

public SchemaContextMenuProvider getMenuProvider() {
	return menuProvider;
}

public void setMenuProvider(SchemaContextMenuProvider menuProvider) {
	this.menuProvider = menuProvider;
}


//public String getId() {
//	return id;
//}

//public void setId(String id) {
//	this.id = id;
//}

}
