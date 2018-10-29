package com.callsign.api.authentication.config.db;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Class to start the database
 * @author Rohit
 *
 */
@ComponentScan({"com.callsign"})
@Configuration
public class SpringRootConfig {

	@Autowired
	DataSource datasource;
	
	@Bean
	public JdbcTemplate getJdbcTemplate(){
		return new JdbcTemplate(datasource);
	}
	
	/**
	 * This will launch the database manager tool to see the table structure and data
	 */
	@PostConstruct
	public void startDBManager() {
		
		//hsqldb
		//DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

		//derby
		//DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

		//h2
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false", "--user", "sa", "--password", "" });

	}
}
