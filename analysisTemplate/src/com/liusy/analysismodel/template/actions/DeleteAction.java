package com.liusy.analysismodel.template.actions;

import org.eclipse.gef.ui.actions.SelectionAction;

import java.util.List;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;

import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.template.parts.NodePart;
public class DeleteAction extends SelectionAction {

	/** @deprecated Use ActionFactory.DELETE.getId() instead. */
	public static final String ID = "MyDelete";

	/**
	 * @deprecated use MyDeleteAction(IWorkbenchPart part)
	 * @param editor The editor this action will be associated with.
	 */
	public DeleteAction(IEditorPart editor) {
		this((IWorkbenchPart)editor);
	}

	/**
	 * Creates a <code>MyDeleteAction</code> with the given label.
	 * @deprecated use MyDeleteAction(IWorkbenchPart)
	 * @param editor The editor this action will be associated with.
	 * @param label  The label to be displayed for this action.
	 */
	public DeleteAction(IEditorPart editor, String label) {
		this((IWorkbenchPart)editor);
		setText(label);
	}

	/**
	 * Constructs a <code>MyDeleteAction</code> using the specified part.
	 * @param part The part for this action
	 */
	public DeleteAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * Returns <code>true</code> if the selected objects can
	 * be deleted.  Returns <code>false</code> if there are
	 * no objects selected or the selected objects are not
	 * {@link EditPart}s.
	 * @return <code>true</code> if the command should be enabled
	 */
	protected boolean calculateEnabled() {
		Command cmd = createDeleteCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	/**
	 * Create a command to remove the selected objects.
	 * @param objects The objects to be deleted.
	 * @return The command to remove the selected objects.
	 */
	public Command createDeleteCommand(List objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		GroupRequest deleteReq =
			new GroupRequest(RequestConstants.REQ_DELETE);
		deleteReq.setEditParts(objects);

		CompoundCommand compoundCmd = new CompoundCommand(
			ActionMessages.DeleteAction_ActionDeleteCommandName);
		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			Command cmd = object.getCommand(deleteReq);
			if (cmd != null) compoundCmd.add(cmd);
		}

		return compoundCmd;
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText(ActionMessages.DeleteAction_Label);
		setToolTipText(ActionMessages.DeleteAction_Tooltip);
		setId(StringUtil.deleteId);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_DELETE_DISABLED));
		setEnabled(true);
	}

	/**
	 * Performs the delete action on the selected objects.
	 */
	public void run() {
		execute(createDeleteCommand(getSelectedObjects()));
	}
}
