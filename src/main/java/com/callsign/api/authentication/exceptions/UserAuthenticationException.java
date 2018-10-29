package com.callsign.api.authentication.exceptions;

/**
 * User Exception class
 * @author Rohit
 *
 */
public class UserAuthenticationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserAuthenticationException(String exceptionMessage){
		super(exceptionMessage);
	}
}
