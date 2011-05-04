/**
 *
 */
package com.tosan.logman.command;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public abstract class AbstractCommand implements Command{

	protected LoggerConfigurerFacade facade;
	protected Shell shell;

	protected AbstractCommand(LoggerConfigurerFacade facade, Shell shell) {
		this.facade = facade;
		this.shell = shell;
	}

//	@Override
//	public  abstract void execute() throws ShellCommandException;
//
//	@Override
//	public abstract void setParams(String[] params) throws InvalidCommandArgumentException;

}
