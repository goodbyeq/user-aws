package com.beatus.goodbyeq.users.service.api;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONException;

import com.beatus.goodbyeq.users.exception.ResponseEntityException;
import com.beatus.goodbyeq.users.model.StoresListDTO;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;

public interface StoreService {
	
	public StoresListDTO searchStoresByArea(String searchString, String radius)
			throws GoodByeQCompanyServiceException, ResponseEntityException, JSONException, IOException, ClassNotFoundException, SQLException;
	public StoresListDTO searchStoresByAreaFromGoogleAPI(String searchString, String radius) 
			throws GoodByeQCompanyServiceException, ResponseEntityException, JSONException, IOException;
	public StoresListDTO searchStoresByCoordinates(String latittude, String longitude, String radius)
			throws GoodByeQCompanyServiceException, ResponseEntityException, JSONException, IOException, ClassNotFoundException, SQLException;	
}
