package com.callsign.api.authentication.beans;

/**
 * bean class for response object
 * @author Rohit
 *
 */
public class AuthenticationInformationBean {

	private String message;
	private String authToken;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
	
}
