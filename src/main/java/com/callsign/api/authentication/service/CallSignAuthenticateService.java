package com.callsign.api.authentication.service;

import com.callsign.api.authentication.beans.UserCredentials;

/**
 * Interface for the authenticate service
 * @author Rohit
 *
 */
public interface CallSignAuthenticateService {

	public String authenticateUser(UserCredentials credentials);
}
