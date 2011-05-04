/**
 *
 */
package com.tosan.logman.command;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class ExitCommand extends AbstractCommand {

	/**
	 * @param facade
	 */
	public ExitCommand(LoggerConfigurerFacade facade, Shell shell) {
		super(facade, shell);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		shell.setRunCompleted(true);
		shell.setSuccessMessage("End of logman.");
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#setParams(java.lang.String[])
	 */
	@Override
	public void setParams(String[] params) {
		//This command has no params.

	}


	@Override
	public String comment() {
		return "ends logman execution (exit)";
	}


}
