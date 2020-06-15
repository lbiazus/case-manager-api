package br.com.biazus.casemanager.exception;

public class InvalidAccessTypeException extends Exception {
	
	private static final long serialVersionUID = -7299296189567783372L;

	public InvalidAccessTypeException() {
		super("AccessType Not Found.");
	}
	
	public InvalidAccessTypeException(String message) {
		super(message);
	}
	
	public InvalidAccessTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidAccessTypeException(Throwable cause) {
		super(cause);
	}
	
}
