package com.callsign.api.authentication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * This is the starting class for the spring boot application.
 * To run the application we just need to run this class as a Java Application. 
 * Once application is started we can access the application on localhost port 8080.
 * Extending the SpringBootServletInitializer so that the application can be deployed as a war file also in any web application container like tomcat
 * @author Rohit
 *
 */
@SpringBootApplication
public class CallSignAuthenticationApplication extends SpringBootServletInitializer{

	public static void main(String[] args){
		SpringApplicationBuilder builder = new SpringApplicationBuilder(CallSignAuthenticationApplication.class);
        builder.headless(false).run(args);
	}
	
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(CallSignAuthenticationApplication.class);
	}
}
