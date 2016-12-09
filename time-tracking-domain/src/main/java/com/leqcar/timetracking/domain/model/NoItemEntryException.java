package com.leqcar.timetracking.domain.model;

public class NoItemEntryException extends RuntimeException {
	
	private static final long serialVersionUID = 7931871917323996509L;

	public NoItemEntryException() {
		super();
	}

	public NoItemEntryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoItemEntryException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoItemEntryException(String message) {
		super(message);
	}

	public NoItemEntryException(Throwable cause) {
		super(cause);
	}

	
}
