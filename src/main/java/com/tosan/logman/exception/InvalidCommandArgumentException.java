package com.tosan.logman.exception;


public class InvalidCommandArgumentException extends ShellCommandException{

	public InvalidCommandArgumentException() {
		super();
	}

	public InvalidCommandArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCommandArgumentException(String message) {
		super(message);
	}

	public InvalidCommandArgumentException(Throwable cause) {
		super(cause);
	}

}
