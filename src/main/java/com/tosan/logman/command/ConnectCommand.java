/**
 *
 */
package com.tosan.logman.command;

import java.io.IOException;

import javax.management.MalformedObjectNameException;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.exception.InvalidCommandArgumentException;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class ConnectCommand extends AbstractCommand {

	private Integer pid = null;

	/**
	 * @param facade
	 */
	public ConnectCommand(LoggerConfigurerFacade facade, Shell shell) {
		super(facade, shell);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		try {
			facade.connect(pid);
			shell.setPrompt("logman(" + pid + ")>>");
		} catch (MalformedObjectNameException e) {
			shell.setErrorMessage("Internal error.");
			throw new ShellCommandException(e);
		} catch (IOException e) {
			shell.setErrorMessage("Internal error.");
			throw new ShellCommandException(e);
		} catch (InvalidCommandArgumentException e) {
			shell.setErrorMessage("Invalid java PID: " + pid);
			throw e;
		}

	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#setParams(java.lang.String[])
	 */
	@Override
	public void setParams(String[] params) throws InvalidCommandArgumentException {
		try {
			pid = new Integer(params[0]);
		} catch (RuntimeException e) {
			shell.setErrorMessage("Invalid pid entered: " + params[0]);
			throw new InvalidCommandArgumentException(e);
		}
	}


	@Override
	public String comment() {
		return "connects to a java process (conn pid)";
	}


}
