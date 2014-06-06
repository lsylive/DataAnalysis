package com.liusy.analysismodel.template.actions;

import java.util.EventObject;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;

public class CommandStackEvent extends EventObject {
	private static final long	serialVersionUID	= 1L;
	private final Command		command;
	private final int				detail;

	public CommandStackEvent(Object source, Command c, int detail) {
		super(source);
		this.command = c;
		this.detail = detail;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns <code>null</code> or a Command if a command is relevant to the
	 * current event.
	 * 
	 * @since 3.1
	 * @return <code>null</code> or a command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * Returns <code>true</code> if this event is fired prior to the stack
	 * changing.
	 * 
	 * @return <code>true</code> if pre-change event
	 * @since 3.2
	 */
	public final boolean isPreChangeEvent() {
		return 0 != (getDetail() & CommandStack.PRE_MASK);
	}

	/**
	 * Returns <code>true</code> if this event is fired after the stack having
	 * changed.
	 * 
	 * @return <code>true</code> if post-change event
	 * @since 3.2
	 */
	public final boolean isPostChangeEvent() {
		return 0 != (getDetail() & CommandStack.POST_MASK);
	}

	/**
	 * Returns an integer identifying the type of event which has occurred.
	 * 
	 * @since 3.1
	 * @return the detail of the event
	 */
	public int getDetail() {
		return detail;
	}
}
