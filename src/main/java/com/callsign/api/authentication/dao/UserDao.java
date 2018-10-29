package com.callsign.api.authentication.dao;

import com.callsign.api.authentication.model.User;

public interface UserDao {

	public User getUserDetails(String username);
}
