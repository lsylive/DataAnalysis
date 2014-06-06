package com.liusy.analysismodel.template.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.liusy.analysismodel.template.commands.KeyMoveNodeCommand;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.template.parts.NodePart;

public class KeyMoveNodeAction extends SelectionAction {


	/** @deprecated Use ActionFactory.DELETE.getId() instead. */
	public static String ID = "KeyMove";

	public String arrayKey = "";
	/**
	 * @deprecated use MyDeleteAction(IWorkbenchPart part)
	 * @param editor The editor this action will be associated with.
	 */
	public KeyMoveNodeAction(IEditorPart editor) {
		this((IWorkbenchPart)editor);
	}

	public KeyMoveNodeAction(IEditorPart editor,String key) {
		this((IWorkbenchPart)editor);
		this.arrayKey = key;
		this.ID = key;
		setId(key);
	}

	/**
	 * Constructs a <code>MyDeleteAction</code> using the specified part.
	 * @param part The part for this action
	 */
	public KeyMoveNodeAction(IWorkbenchPart part) {
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
//		Command cmd = createMoveCommand(getSelectedObjects());
//		if (cmd == null)
//			return false;
//		return cmd.canExecute();
		return true;
	}

	/**
	 * Performs the delete action on the selected objects.
	 */
	public void run() {
		List objects = getSelectedObjects();
		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			if (object instanceof NodePart) {
				KeyMoveNodeCommand cmd = new KeyMoveNodeCommand();
				NodePart part = (NodePart)object;
				cmd.setNode((INode) part.getModel());
				cmd.setArrayKey(arrayKey);
				part.getViewer().getEditDomain().getCommandStack().execute(cmd);
//				break;
			}
		}
	}

	public String getArrayKey() {
		return arrayKey;
	}

	public void setArrayKey(String arrayKey) {
		this.arrayKey = arrayKey;
	}

}
