package com.leqcar.timetracking.resource;

public class CannotCreateTimeSheetException extends RuntimeException {
	
	private static final long serialVersionUID = -2880584909769984831L;
	
	public CannotCreateTimeSheetException(String message) {
		super(message);
	}

}
