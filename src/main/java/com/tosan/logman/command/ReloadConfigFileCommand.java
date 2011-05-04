/**
 *
 */
package com.tosan.logman.command;

import ch.qos.logback.core.joran.spi.JoranException;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class ReloadConfigFileCommand extends AbstractCommand {

	/**
	 * @param facade
	 */
	public ReloadConfigFileCommand(LoggerConfigurerFacade facade, Shell shell) {
		super(facade, shell);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		try {
			facade.reload();
		} catch (JoranException e) {
			throw new ShellCommandException(e);
		}

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
		return "reloads essential configuration file	(reload)";
	}

}
