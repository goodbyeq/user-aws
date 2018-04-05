package com.beatus.goodbyeq.users.service.api;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import com.beatus.goodbyeq.users.exception.ResponseEntityException;
import com.beatus.goodbyeq.users.model.ItemDetailsDTO;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;
import com.beatus.goodbyeq.users.validation.exception.GoodByeQValidationException;


public interface ItemsService {
	
	public ItemDetailsDTO fetchItemDetails(String addUserStr) throws GoodByeQValidationException, ClassNotFoundException, SQLException ;
	public String createBill(String scannedItemsStr) throws GoodByeQCompanyServiceException, 
	ResponseEntityException, ClassNotFoundException, JSONException, IOException, SQLException;
}
