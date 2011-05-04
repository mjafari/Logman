/**
 *
 */
package com.tosan.logman.command;

import com.tosan.logman.exception.InvalidCommandArgumentException;
import com.tosan.logman.exception.ShellCommandException;

/**
 * @author mjafari
 *
 */
public interface Command {
	public void execute() throws ShellCommandException;
	public void setParams(String[] params) throws InvalidCommandArgumentException;
	public String comment();
}
