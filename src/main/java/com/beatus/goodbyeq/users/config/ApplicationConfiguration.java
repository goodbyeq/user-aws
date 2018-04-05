package com.beatus.goodbyeq.users.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

;

/**
 * Root class for the application's Spring configuration. This class is
 * annotated with {@link ComponentScan} which will look for other classes in
 * this package with the {@link Configuration} annotation. It will not component
 * scan for any other Spring Stereotypes.
 * 
 * @author Abhinav Akey
 * @since 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.beatus.goodbyeq", useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(Configuration.class),
		@ComponentScan.Filter(Controller.class),
		@ComponentScan.Filter(Component.class),
		@ComponentScan.Filter(Service.class) })
@PropertySource(name = "applicationProperties", value = "classpath:properties/application-dev.properties", ignoreResourceNotFound = false)
public class ApplicationConfiguration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationConfiguration.class);

	// ******************************************************************//
	// Properties Configuration
	// ******************************************************************//

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {

		LOGGER.info("Loading the properties");
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

		pspc.setLocalOverride(true);

		return pspc;
	}
}
