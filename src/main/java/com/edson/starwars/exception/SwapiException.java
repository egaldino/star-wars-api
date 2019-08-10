package com.edson.starwars.exception;

public class SwapiException extends Exception {

	private static final long serialVersionUID = 1L;


	public SwapiException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SwapiException(String message) {
		super(message);
	}

	public SwapiException(Throwable throwable) {
		super(throwable);
	}
	
}
