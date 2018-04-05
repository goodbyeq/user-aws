package com.beatus.goodbyeq.users.dac;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.users.model.AddressDTO;
import com.beatus.goodbyeq.users.model.StoreDetailsDTO;

@Component("usersDBService")
public class UsersDBService {

	private static final Logger logger = LoggerFactory.getLogger(UsersDBService.class);
	
	@Value(value = "${db.dbname:localhost}")
	private String dbSchema;
	
	@Resource(name = "usersDBUtils")
	private UsersDBUtils usersDBUtils;
	public ArrayList<StoreDetailsDTO> checkDBForRegisteredStores(String latitude, String longitude,
			String radius) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		StoreDetailsDTO storesListDTO = null;
		ArrayList<StoreDetailsDTO> storesList = new ArrayList<StoreDetailsDTO>();  
		dbConnection = usersDBUtils.getRemoteConnection();
		statement = dbConnection.createStatement();
		String fetchItemDetailsSQL = "SELECT * FROM(" +
			    "SELECT *,(((acos(sin(("+latitude+"*pi()/180)) * sin((GEO_LATITUDE*pi()/180))+cos(("+latitude+"*pi()/180)) * cos((GEO_LATITUDE*pi()/180)) * cos((("+longitude+" - GEO_LONGITUDE)*pi()/180))))*180/pi())*60*1.1515*1.609344) as distance "
			    		+ "FROM " + dbSchema + ".GBQ_STORE) t " +
				"WHERE distance <= " + radius;
		logger.info("Stores list query:- " + fetchItemDetailsSQL);
		ResultSet rs = statement.executeQuery(fetchItemDetailsSQL);
		while (rs.next()) {
			storesListDTO = new StoreDetailsDTO(rs.getString("GEO_LATITUDE"), rs.getString("GEO_LONGITUDE"),
					rs.getString("STORE_ID"), rs.getString("COMPANY_ID"), rs.getString("STORE_NAME"),
					new AddressDTO(rs.getString("ADDRESS"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("ZIPCODE")));
			storesList.add(storesListDTO);
		}		
		return storesList;
	}
}