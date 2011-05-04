/**
 *
 */
package com.tosan.logman.command;

import java.util.HashMap;
import java.util.Map;

import com.tosan.logman.LoggerConfigurerFacade;
import com.tosan.logman.shell.Shell;

/**
 * @author mjafari
 *
 */
public class CommandFactory {
	private LoggerConfigurerFacade facade = new LoggerConfigurerFacade();
	private Map<String, Command> commands = null;

	public CommandFactory(Shell shell) {
		commands = new HashMap<String, Command>();
		commands.put("reload", new ReloadConfigFileCommand(facade, shell));
		commands.put("load", new LoadConfigFileCommand(facade, shell));
		commands.put("level", new SetLevelCommand(facade, shell));
		commands.put("conn", new ConnectCommand(facade, shell));
		commands.put("exit", new ExitCommand(facade, shell));
		commands.put("list", new ListJavaProcessesCommand(facade, shell));
		HelpCommand helpCommand = new HelpCommand(shell);
		commands.put("help", helpCommand);

		helpCommand.setHelpText(getCommandComments());
	}

	public String getCommandComments() {
		StringBuffer result = new StringBuffer();
		for(String key : commands.keySet()){
			result.append(key + "\t\t" + commands.get(key).comment());
			result.append("\n");
		}
		return result.toString();
	}


	/**
	 *	The factory method of class.
	 * @param strCommand is string form of requested {@code Command} object
	 * @return corresponding {@code Command} of strCommand
	 */
	public Command getCommand(String strCommand){
		String normalStrCommand = strCommand.trim().toLowerCase();
		return commands.get(normalStrCommand);
	}
}
