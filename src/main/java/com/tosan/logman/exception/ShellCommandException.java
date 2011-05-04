package com.tosan.logman.exception;

/**
 * A ShellCommandException is thrown when a problem in running a logman shell command exists.
 * @author mjafari
 *
 */
public class ShellCommandException extends Exception {

	public ShellCommandException() {
	}

	public ShellCommandException(String message) {
		super(message);
	}

	public ShellCommandException(Throwable cause) {
		super(cause);
	}

	public ShellCommandException(String message, Throwable cause) {
		super(message, cause);
	}

}
