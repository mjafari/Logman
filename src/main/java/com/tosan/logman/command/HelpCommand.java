/**
 *
 */
package com.tosan.logman.command;

import com.tosan.logman.exception.InvalidCommandArgumentException;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class HelpCommand extends AbstractCommand {

	private String helpText = "";

	/**
	 * @param facade
	 * @param shell
	 */
	public HelpCommand(Shell shell) {
		super(null, shell);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.Command#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		shell.setSuccessMessage(helpText);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.Command#setParams(java.lang.String[])
	 */
	@Override
	public void setParams(String[] params)
			throws InvalidCommandArgumentException {
		// This command has no parameter.

	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.Command#comment()
	 */
	@Override
	public String comment() {
		return "print help of commands (help)";
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}



}
