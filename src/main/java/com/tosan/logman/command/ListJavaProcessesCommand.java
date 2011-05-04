/**
 *
 */
package com.tosan.logman.command;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.exception.InvalidCommandArgumentException;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class ListJavaProcessesCommand extends AbstractCommand {

	/**
	 * @param facade
	 * @param shell
	 */
	public ListJavaProcessesCommand(LoggerConfigurerFacade facade, Shell shell) {
		super(facade, shell);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		StringBuffer sb = new StringBuffer();
		for(String text : facade.getJavaProcessDescriptions()){
			sb.append(text);
			sb.append("\n");
		}
		shell.setSuccessMessage(sb.toString());
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#setParams(java.lang.String[])
	 */
	@Override
	public void setParams(String[] params)
			throws InvalidCommandArgumentException {
		// This command has no parameters.

	}


	@Override
	public String comment() {
		return "Displays list of current executing java processses (list)";
	}

}
