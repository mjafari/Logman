/**
 *
 */
package com.tosan.logman.shell;

import java.util.Arrays;
import java.util.Scanner;

import com.tosan.logman.command.Command;
import com.tosan.logman.command.CommandFactory;
import com.tosan.logman.exception.ShellCommandException;

/**
 * @author mjafari
 *
 */
public class Shell {
	private String prompt = "logman>";
	private Scanner scanner = new Scanner(System.in);
	private boolean runCompleted = false;
	private CommandFactory commandFactory = new CommandFactory(this);
	private String errorMessage;
	private String successMessage;


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public boolean isRunCompleted() {
		return runCompleted;
	}

	public void setRunCompleted(boolean runCompleted) {
		this.runCompleted = runCompleted;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.processProgramArgs(args);
		shell.listenForConmmands();
	}

	protected void processProgramArgs(String[] args) {
		if (args.length >= 1) {
				runCommand(new String[] { "conn ", args[0] });
				runCompleted = runCommand(Arrays.copyOfRange(args, 1, args.length));
		}
	}

	/**
	 *
	 * @param commandElements is a string array that represent a command and its parameters separated by space(s).
	 * @return true if commandElements contains a command and its parameters that run successfully.
	 */
	private boolean runCommand(String[] commandElements) {
		errorMessage = null;
		//setting default success message.
		successMessage = "done.";

		if (commandElements.length == 0) {
			echo("No command entered.");
			return false;
		}
		Command command = commandFactory.getCommand(commandElements[0].trim());
		if (command == null) {
			echo("Invalid command entered.");
			return false;
		}
		try {
			command.setParams(Arrays.copyOfRange(commandElements, 1,
					commandElements.length));
			command.execute();
		} catch (ShellCommandException e) {
			echo(errorMessage);
			return false;
		}
		echo(successMessage);
		return true;
	}

	protected void listenForConmmands() {
		String[] commandArray = null;
		while (!runCompleted) {
			commandArray = catchCommand();
			runCommand(commandArray);
		}
	}

	private String[] catchCommand() {
		System.out.print(prompt);
		String command = scanner.nextLine();
		return command.split(" ");
	}

	private static void echo(String text) {
		// System.out.println();
		System.out.println(text);
	}
}
