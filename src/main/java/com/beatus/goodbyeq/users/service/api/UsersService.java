package com.beatus.goodbyeq.users.service.api;

import java.sql.SQLException;

import com.beatus.goodbyeq.users.validation.exception.GoodByeQValidationException;


public interface UsersService {
	
	public String addUser(String addUserStr) throws GoodByeQValidationException, ClassNotFoundException, SQLException;
}
