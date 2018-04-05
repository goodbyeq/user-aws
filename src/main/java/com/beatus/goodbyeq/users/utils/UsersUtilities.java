package com.beatus.goodbyeq.users.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beatus.goodbyeq.users.model.ItemDetailsDTO;
import com.beatus.goodbyeq.users.model.SearchResultsDTO;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UsersUtilities {

	private static final Logger LOG = LoggerFactory.getLogger(UsersUtilities.class);
	
	public static String createStoresJSONObject(ArrayList<SearchResultsDTO> searchResultsByCoordinates) throws GoodByeQCompanyServiceException, JSONException {
		String jsonResponse  = "";
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		
		for(SearchResultsDTO searchResultsDTO : searchResultsByCoordinates) {

			item.put("name", searchResultsDTO.getName());
			item.put("address", searchResultsDTO.getVicinity());
			item.put("rating", searchResultsDTO.getRating());
			item.put("latitude", searchResultsDTO.getLatitude());
			item.put("longitude", searchResultsDTO.getLongitude());
			array.put(item);
			item = new JSONObject();
			
			json.put("results", array);
		}
		
		jsonResponse = json.toString();
		
		return jsonResponse;
	}
	
	public static String createItemsJSONObject(ItemDetailsDTO itemsDTO) throws GoodByeQCompanyServiceException, JSONException {
		String jsonResponse  = "";
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		
		item.put("itemId", itemsDTO.getBrand());
		item.put("itemName", itemsDTO.getItemName());
		item.put("discount", itemsDTO.getItemDiscount());
		item.put("price", itemsDTO.getPrice());
		item.put("sgst", itemsDTO.getSgst());
		item.put("cgst", itemsDTO.getCgst());
		item.put("brand", itemsDTO.getBrand());
		array.put(item);
		item = new JSONObject();
		
		json.put("results", array);
		jsonResponse = json.toString();
		
		return jsonResponse;
	}
	
	public static String createStrJSONObject(String inputStr) throws GoodByeQCompanyServiceException, JSONException {
		String jsonResponse  = "";
		JSONObject json = new JSONObject();
		
		json.put("results", inputStr);
		jsonResponse = json.toString();
		
		return jsonResponse;
	}
	
	public static String readJSONObject(String inputJSONStr, String key) throws GoodByeQCompanyServiceException, JSONException {
		String jsonObjectValue = "";
		
		// Create a JSON object hierarchy from the results
        JSONObject jsonObj = new JSONObject(inputJSONStr);
        jsonObjectValue = jsonObj.getString(key);
        return jsonObjectValue;
	}
	
	public static String generateRandomKey(final int numberOfCharacters) {
    	String	randomNumber = RandomStringUtils.randomAlphanumeric(numberOfCharacters);
		return randomNumber;
	}
    
    private static final ObjectMapper serializeMapper = new ObjectMapper();
    private static final ObjectMapper deserializeMapper = new ObjectMapper();
	static{
	    synchronized (serializeMapper) {
	    	// Map only non null values
	    	serializeMapper.setSerializationInclusion(Include.NON_EMPTY);
	    	serializeMapper.setSerializationInclusion(Include.NON_NULL);
	    	serializeMapper.setSerializationInclusion(Include.NON_DEFAULT);
	    	serializeMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	    }
	    synchronized (deserializeMapper) {
			deserializeMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
	}
	
	public static String convertJavaToJson(Object data) {
    	// Map only non null values
    	serializeMapper.setSerializationInclusion(Include.NON_EMPTY);
    	serializeMapper.setSerializationInclusion(Include.NON_NULL);
    	serializeMapper.setSerializationInclusion(Include.NON_DEFAULT);
    	serializeMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
    	String json = null;
    	try {
    		LOG.debug("FromJava: {}", data.toString());
    		json = serializeMapper.writeValueAsString(data);
    	} catch (IOException ioe) {
    		LOG.error("IOException: {}", ioe);
    	}
    	LOG.debug("ToJson: {}", json);
    	return json;
    }

	public static String generateRandomString() {
		String randomStr = null;
		UUID random = UUID.randomUUID();
		randomStr = random.toString();
		randomStr = randomStr.replaceAll("-", "");
		String timeStampStr = new SimpleDateFormat("hhmmss").format(new Date());
		randomStr = timeStampStr+randomStr;
		System.out.println( randomStr );
		
		return randomStr;
	}
	
	public static ArrayList<ItemDetailsDTO> readItemsListJSONObject(String inputItemsListJSONStr) throws GoodByeQCompanyServiceException, JSONException {
		ArrayList<ItemDetailsDTO> itemDetailsDTOList = new ArrayList<ItemDetailsDTO>();
		
		// Create a JSON object hierarchy from the results
        JSONObject jsonObj = new JSONObject(inputItemsListJSONStr);
        JSONArray itemsListJsonArray = jsonObj.getJSONArray("itemsList");
        
        for (int i = 0; i < itemsListJsonArray.length(); i++) {
            SearchResultsDTO place = new SearchResultsDTO();
            place.vicinity = itemsListJsonArray.getJSONObject(i).getString("productId");
            //place.formatted_address = getFormattedAddress(place.vicinity); 
            place.name = itemsListJsonArray.getJSONObject(i).getString("name");
            JSONObject locationObj = itemsListJsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
            place.longitude = locationObj.getDouble("lng");
            place.latitude = locationObj.getDouble("lat");
            try{
            	place.rating = String.valueOf(itemsListJsonArray.getJSONObject(i).getInt("rating"));	
            }
            catch(JSONException jsonException){
            	place.rating = "No";
            }
            LOG.debug(place.name + ", " + place.vicinity + " with " + place.rating + " rating.");
            //itemDetailsDTOList.add(new ItemDetailsDTO(id, name, price, sgst, cgst, discount));
        }
        
        return itemDetailsDTOList;
	}
}