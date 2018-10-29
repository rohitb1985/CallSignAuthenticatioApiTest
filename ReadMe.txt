Authentication Exapmple:

The Application has been developed using Spring Boot and can be run as a Java Application.
The tomcat server has been added as a maven dependency and runs on port 8080.

To implement the Authentication service, following changes have been done.
A POST service operation has been created which accepts the username and password in Base64 encoded format.
On successful authentication an encrypted token is also returned.

Following username, password combinations have been created in H2 in memory database:
username1:password1 
username2:password2
username3:password3

The database scripts are added in the /src/main/resources folder to create the table and insert the above data

The Base64 encoding can be done online for testing purpose. 
For eg: dXNlcm5hbWUx = username1 and cGFzc3dvcmQx = password1
The above data can be used to verify username1 credentials.

For testing Postman has been used where the content-type is set as application/json and sample request body looks like:
{
"username" : "dXNlcm5hbWUx",
"password" : "cGFzc3dvcmQx"
}

The API URL will be as below:
http://localhost:8080/v1/api/authenticateuser

When user is authenticated successfully below message will be received.
{
"message": "User Authenticated",
"authToken": "dXNlcm5hbWUxRnJpIE9jdCAyNiAxNzo1NzozNSBCU1QgMjAxOA=="
}

Here the auth token is simply the username and current time concatenated in base 64 encoding.
When authentication is unsuccessful no token is returned, instead an exception message is returned with a user friendly message. 

Integration test cases have also been added for 2 scenarios i.e when valid user credentials are supplied and when 
invalid credentials are supplied in the request.