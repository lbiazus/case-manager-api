package br.com.biazus.casemanager.exception;

public class InvalidCourtCaseException extends Exception {
	
	private static final long serialVersionUID = -7299296189567783372L;

	public InvalidCourtCaseException() {
		super("Invalid Case. Fill in all the required fields.");
	}
	
	public InvalidCourtCaseException(String message) {
		super(message);
	}
	
	public InvalidCourtCaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidCourtCaseException(Throwable cause) {
		super(cause);
	}
}
