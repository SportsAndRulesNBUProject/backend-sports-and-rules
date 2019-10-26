package com.nbu.sportsandrules.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

// Uncomment line below if you dont want to connect to database
//@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ExcludeDatabaseConfig {
}
