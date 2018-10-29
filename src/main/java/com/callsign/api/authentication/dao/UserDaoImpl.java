package com.callsign.api.authentication.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callsign.api.authentication.model.User;

/**
 * Implementation class for Database operation
 * @author Rohit
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public User getUserDetails(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		User result = null;
		try{
			result = jdbcTemplate.queryForObject("Select * from USER where username=?", new Object[] {username},
					new BeanPropertyRowMapper<User>(User.class));
		}catch(Exception ex){
			result = null;
		}
		return result;
	}

}
