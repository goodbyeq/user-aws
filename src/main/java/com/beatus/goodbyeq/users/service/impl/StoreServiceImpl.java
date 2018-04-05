package com.beatus.goodbyeq.users.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.users.dac.UsersDBService;
import com.beatus.goodbyeq.users.exception.ResponseEntityException;
import com.beatus.goodbyeq.users.model.StoreDetailsDTO;
import com.beatus.goodbyeq.users.model.StoresListDTO;
import com.beatus.goodbyeq.users.service.api.StoreService;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;
import com.beatus.goodbyeq.users.validation.StoreValidator;

@Component("storeService")
public class StoreServiceImpl implements StoreService {

	@Resource(name = "storeValidator")
	private StoreValidator storeValidator;
	
	@Resource(name = "usersDBService")
	private UsersDBService usersDBService;
	
	@Resource(name = "googleAPI")
	private GoogleAPI googleAPI;

	private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
	
	public StoresListDTO searchStoresByArea(String searchString, String radius)
			throws GoodByeQCompanyServiceException, ResponseEntityException, JSONException, IOException, ClassNotFoundException, SQLException {
		String METHOD_NAME = "searchStoresByArea()";
		StoresListDTO storesFromGoogleAPI = null;
		ArrayList<StoreDetailsDTO> registeredStoresListFromDB = null;
		StoresListDTO storesListDTO = null; 
		String lattitude = null;
		String longitude = null;
		
		storesFromGoogleAPI = googleAPI.getCoordinates(searchString, radius);
		lattitude = storesFromGoogleAPI.getLatitude();
		longitude = storesFromGoogleAPI.getLongitude();
		logger.info(METHOD_NAME + "lattitude:- " + lattitude + " longitude:- " + longitude + " radius- " + radius);
		registeredStoresListFromDB = usersDBService.checkDBForRegisteredStores(lattitude, longitude, radius);
		storesListDTO = new StoresListDTO(registeredStoresListFromDB, lattitude, longitude);
		
		return storesListDTO;
	}

	public StoresListDTO searchStoresByAreaFromGoogleAPI(String searchString, String radius)
			throws GoodByeQCompanyServiceException, ResponseEntityException, JSONException, IOException {
		StoresListDTO storesListDTO = googleAPI.getCoordinates(searchString, radius);
		return storesListDTO;
	}
	
	public StoresListDTO searchStoresByCoordinates(String latittude, String longitude, String radius)
			throws GoodByeQCompanyServiceException, ResponseEntityException, JSONException, IOException, ClassNotFoundException, SQLException {
		StoresListDTO storesListDTO = null;
		ArrayList<StoreDetailsDTO> registeredStoresListFromDB = null;
		logger.info("searchStoresByCoordinates()::" + "latittude:- " + latittude + 
				" longitude:- " + longitude + " radius- " + radius);
		registeredStoresListFromDB = usersDBService.checkDBForRegisteredStores(latittude, longitude, radius);
		storesListDTO = new StoresListDTO(registeredStoresListFromDB, latittude, longitude);
		
		return storesListDTO;
	}	
}
