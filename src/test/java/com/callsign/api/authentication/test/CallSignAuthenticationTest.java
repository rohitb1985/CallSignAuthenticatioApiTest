package com.callsign.api.authentication.test;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.callsign.api.authentication.CallSignAuthenticationApplication;
import com.callsign.api.authentication.beans.UserCredentials;
import com.callsign.api.authentication.exceptions.UserAuthenticationException;
import com.callsign.api.authentication.service.CallSignAuthenticateService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Test class for integration test cases.
 * @author Rohit
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = CallSignAuthenticationApplication.class)
public class CallSignAuthenticationTest {

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Autowired
	CallSignAuthenticateService service;
	
	@BeforeClass
    public static void setupHeadlessMode() {
       // if (Boolean.getBoolean("headless")) {
           
            System.setProperty("java.awt.headless", "false");
       // }
    }
	
	/**
	 * Method to verify the valid credentials
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void authenticateUserTest_with_valid_credentials(){
		String validUserName = "dXNlcm5hbWUx";
		String validPassword = "cGFzc3dvcmQx";
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername(validUserName);
		credentials.setPassword(validPassword);
		
		String response = service.authenticateUser(credentials);
		JsonElement jelement = new JsonParser().parse(response);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    String message = jobject.get("message").getAsString();
	    Assert.assertEquals("User Authenticated", message);
		
	}
	
	/**
	 * Method to verify the invalid credentials 
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void authenticateUserTest_with_invalid_credentials(){
		String invalidUserName = "dXNlcm5hbWUxOnBhc3N3b3JkMQ==";
		String invalidPassword = "cGFzc3dvcmQx";
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername(invalidUserName);
		credentials.setPassword(invalidPassword);
		try{
			String response = service.authenticateUser(credentials);
			JsonElement jelement = new JsonParser().parse(response);
		    JsonObject  jobject = jelement.getAsJsonObject(); 
		}catch(UserAuthenticationException ua){
			Assert.assertNotSame("User Authenticated", ua.getLocalizedMessage());
		}catch(Exception ex){
			Assert.assertTrue(true);
		}

	}
}
