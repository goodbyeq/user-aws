package com.beatus.goodbyeq.users.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.users.model.UserDTO;
import com.beatus.goodbyeq.users.validation.exception.GoodByeQClientValidationException;
@Component("usersValidator")
public class UsersValidator {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersValidator.class);

	public boolean validateCompanyDTO(UserDTO users) throws GoodByeQClientValidationException{
		LOGGER.info("Validating User Data " + users);

		/*if(users == null || StringUtils.isBlank(String.valueOf(users.getCompanyID()))){
			throw new GoodByeQClientValidationException("usersID","CompanyData, the usersId field is not available");

		}
		if(StringUtils.isBlank(String.valueOf(users.getCompanyName()))){
			throw new GoodByeQClientValidationException("usersName","CompanyData, the usersName field is not available for users with usersId = " + users.getCompanyID());

		}
		if(StringUtils.isBlank(String.valueOf(users.getAddressLine1()))){
			throw new GoodByeQClientValidationException("addressLine1","CompanyData, the addressLine1 is not availablefor users with usersId = " + users.getCompanyID());

		}
		if(StringUtils.isBlank(String.valueOf(users.getCity()))){
			throw new GoodByeQClientValidationException("city","CompanyData, the city field is not available for users with usersId = " + users.getCompanyID());

		}
		if(StringUtils.isBlank(String.valueOf(users.getZipCode()))){
			throw new GoodByeQClientValidationException("zipcode","CompanyData, the zipcode field is not available for users with usersId = " + users.getCompanyID());

		}
		if(StringUtils.isBlank(String.valueOf(users.getEmail()))){
			throw new GoodByeQClientValidationException("Email","CompanyData, the Email field is not available for users with usersId = " + users.getCompanyID());

		}
		if(StringUtils.isBlank(String.valueOf(users.getPhoneNumber()))){
			throw new GoodByeQClientValidationException("phoneNumber","CompanyData, the phoneNumber field is not available for users with usersId = " + users.getCompanyID());

		}*/
		return false;
	}

}
