/**
 *
 */
package com.tosan.logman.command;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.exception.InvalidCommandArgumentException;
import com.tosan.logman.exception.ShellCommandException;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class SetLevelCommand extends AbstractCommand {

	private String loggerName;
	private String level;

	/**
	 * @param facade
	 */
	public SetLevelCommand(LoggerConfigurerFacade facade, Shell shell) {
		super(facade, shell);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#execute()
	 */
	@Override
	public void execute() throws ShellCommandException {
		facade.setLevel(loggerName, level);
	}

	/* (non-Javadoc)
	 * @see com.tosan.logman.command.AbstractCommand#setParams(java.lang.String[])
	 */
	@Override
	public void setParams(String[] params) throws InvalidCommandArgumentException{
		if(params == null || params.length == 0){
			shell.setErrorMessage("Command needs at least one parameter.");
			throw new InvalidCommandArgumentException("no command parameters.");
		}
		try {
			if (params.length == 1) {//That is setting log level for root
				loggerName = Logger.ROOT_LOGGER_NAME;
				level = normalize(params[0]);
			} else {
				loggerName = params[0];
				level = normalize(params[1]);
			}
		} catch (IllegalArgumentException e) {
			shell.setErrorMessage("Invalid log level entered.");
			throw new InvalidCommandArgumentException(e);
		}

	}

	private String normalize(String strLevel) {
		String localStrLevel = strLevel.trim().toUpperCase();
		Level level = Level.toLevel(localStrLevel, Level.DEBUG);
		if (level.levelInt == Level.DEBUG_INT && !"DEBUG".equals(strLevel)) {
			throw new IllegalArgumentException("Invalid log level: "
					+ localStrLevel);
		}
		return localStrLevel;
	}


	@Override
	public String comment() {
		return "set a log level for a logger (level [logger-name]log-level)";
	}


}
