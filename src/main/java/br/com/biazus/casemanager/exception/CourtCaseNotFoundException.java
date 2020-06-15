package br.com.biazus.casemanager.exception;

public class CourtCaseNotFoundException extends Exception {
	
	private static final long serialVersionUID = -7299296189567783372L;

	public CourtCaseNotFoundException() {
		super("Case not found.");
	}
	
	public CourtCaseNotFoundException(String message) {
		super(message);
	}
	
	public CourtCaseNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CourtCaseNotFoundException(Throwable cause) {
		super(cause);
	}
}
