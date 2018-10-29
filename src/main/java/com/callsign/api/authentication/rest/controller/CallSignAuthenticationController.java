package com.callsign.api.authentication.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callsign.api.authentication.beans.UserCredentials;
import com.callsign.api.authentication.service.CallSignAuthenticateService;

/**
 * Controller class to accept the authentication request
 * @author Rohit
 *
 */
@RestController
@RequestMapping("/v1/api")
public class CallSignAuthenticationController {

	@Autowired
	CallSignAuthenticateService authService;
	
	/**
	 * Service operation accepting the authentication request
	 * @param credentials
	 * @return
	 */
	@RequestMapping(value="/authenticateuser", method=RequestMethod.POST)
	public String authenticateUser(@RequestBody UserCredentials credentials){
		return authService.authenticateUser(credentials);
	}
}
