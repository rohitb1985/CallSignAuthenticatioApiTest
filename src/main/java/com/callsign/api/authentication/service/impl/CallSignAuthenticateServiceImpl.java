package com.callsign.api.authentication.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callsign.api.authentication.beans.AuthenticationInformationBean;
import com.callsign.api.authentication.beans.UserCredentials;
import com.callsign.api.authentication.dao.UserDao;
import com.callsign.api.authentication.exceptions.UserAuthenticationException;
import com.callsign.api.authentication.model.User;
import com.callsign.api.authentication.service.CallSignAuthenticateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.messaging.saaj.util.Base64;

@Service
public class CallSignAuthenticateServiceImpl implements
CallSignAuthenticateService {

	@Autowired
	UserDao user;

	/**
	 * MEthod to authenticate the user
	 */
	public String authenticateUser(UserCredentials credentials) throws UserAuthenticationException{
		
		String username = null, password = null;
		byte[] bytes = null;
		try {
			bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(credentials.getUsername());
			username = new String(bytes, StandardCharsets.UTF_8);
			bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(credentials.getPassword());
			password = new String(bytes, StandardCharsets.UTF_8);
		}catch(Exception ex){
			//ex.printStackTrace();
		}
		if(null == username || (null == password)){
			throw new UserAuthenticationException("User Details not found");
		}else{
			/**
			 * Get the user details from the database
			 */
			User userDetails = user.getUserDetails(username);
			if(null != userDetails && userDetails.getPassword().equalsIgnoreCase(password)){
				Gson gson = new GsonBuilder().enableComplexMapKeySerialization().disableHtmlEscaping()
						.setPrettyPrinting().create();
				AuthenticationInformationBean authInfo = new AuthenticationInformationBean();
				authInfo.setMessage("User Authenticated");
				authInfo.setAuthToken(generateToken(username));
				return gson.toJson(authInfo);
			}else{
				throw new UserAuthenticationException("Invalid Credentials");
			}
		}
	}
	
	/**
	 * Method to generate the token in base64 encoding
	 * @param credentials
	 * @return
	 */
	private String generateToken(String credentials){
		String encodedString = null;
		try {
			credentials = credentials + new Date();
			byte[] bytes = Base64.encode(credentials.getBytes());
			encodedString = new String(bytes, StandardCharsets.UTF_8);
			System.out.println(encodedString);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return encodedString;
	}

}
