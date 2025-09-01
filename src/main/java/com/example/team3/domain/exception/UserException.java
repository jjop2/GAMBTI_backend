package com.example.team3.domain.exception;

public class UserException extends RuntimeException {
	public UserException(String message) {
		super(message);
	}
	public UserException(String message, Throwable cause) {
		super(message,cause);
	}

}
