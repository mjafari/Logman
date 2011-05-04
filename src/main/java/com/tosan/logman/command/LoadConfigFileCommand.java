/**
 *
 */
package com.tosan.logman.command;

import java.io.FileNotFoundException;

import ch.qos.logback.core.joran.spi.JoranException;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.exception.InvalidCommandArgumentException;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class LoadConfigFileCommand extends AbstractCommand {

	String configFilePath = null;
	/**
	 * @param facade
	 */
	public LoadConfigFileCommand(LoggerConfigurerFacade facade, Shell shell) {
		super(facade, shell);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		try {
			facade.load(configFilePath);
		} catch (FileNotFoundException e) {
			shell.setErrorMessage("file not found: " + configFilePath);
			throw new ShellCommandException(e);
		} catch (JoranException e) {
			shell.setErrorMessage("Internal error: " + e.getMessage());
			throw new ShellCommandException(e);
		}

	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#setParams(java.lang.String[])
	 */
	@Override
	public void setParams(String[] params) throws InvalidCommandArgumentException {
		configFilePath = params[0];
	}

	@Override
	public String comment() {
		return "loads determinened file as log configuration	(load file-path)";
	}

}
