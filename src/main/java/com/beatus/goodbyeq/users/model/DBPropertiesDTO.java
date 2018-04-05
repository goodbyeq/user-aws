package com.beatus.goodbyeq.users.model;

public class DBPropertiesDTO {
	
	private String driver;
	private String dbHost;
	private String dbPort;
	private String dbSchema;
	private String dbUsername;
	private String dbPassword;
	
	public String getDbHost() {
		return dbHost;
	}
	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}
	public String getDbPort() {
		return dbPort;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDbSchema() {
		return dbSchema;
	}
	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}
	
	public DBPropertiesDTO(String driver, String dbHost, String dbPort, String dbSchema, String dbUsername, String dbPassword) {
		this.dbHost = dbHost;
		this.dbPort = dbPort;
		this.dbSchema = dbSchema;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.driver = driver;
	}
}