package com.beatus.goodbyeq.users.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.users.exception.ResponseEntityException;
import com.beatus.goodbyeq.users.model.SearchResultsDTO;
import com.beatus.goodbyeq.users.model.StoresListDTO;
import com.beatus.goodbyeq.users.utils.UsersConstants;

/**
 * Contains the Google API calls to fetch near by stores for the given input area or coordinates
 * @author Pavan Kumar Poduri
 */
@Component("googleAPI")
public class GoogleAPI {
	final static Logger logger = LoggerFactory.getLogger(GoogleAPI.class);
	static String CLASS_NAME = "GoogleAPIUtility";

	@Value(value = "${googleAPI.key:localhost}")
	private String googleAPIKey;
	/**
	 * Searches the near by stores for the given latitude and longitude
	 * @param lat - latitude
	 * @param lng - longitude
	 * @return - List of near by stores
	 * @throws ResponseEntityException
	 * @throws IOException
	 * @throws JSONException
	 */
	public ArrayList<SearchResultsDTO> findNearestStores(double lat, double lng, int radius) throws ResponseEntityException, IOException, JSONException{
		String METHOD_NAME = "findNearestStores()";
	    ArrayList<SearchResultsDTO> resultList = null;
	    StringBuilder jsonResults = new StringBuilder();
	    
        StringBuilder sb = new StringBuilder(UsersConstants.PLACES_API_BASE);
        sb.append(UsersConstants.TYPE_NEAR_BY_SEARCH);
        sb.append(UsersConstants.OUT_JSON);
        sb.append("?location=" + String.valueOf(lat) + "," + String.valueOf(lng));
        sb.append("&radius=" + radius);
        sb.append("&key=" + googleAPIKey);
        sb.append("&type=grocery_or_supermarket");
        logger.info(CLASS_NAME + "::" + METHOD_NAME + "::URL:-\n"+String.valueOf(sb));
        
        jsonResults = fetchJSONResults(sb);
        // Create a JSON object hierarchy from the results
        JSONObject jsonObj = new JSONObject(jsonResults.toString());
        JSONArray predsJsonArray = jsonObj.getJSONArray("results");
        
        // Extract the Place descriptions from the results
        resultList = new ArrayList<SearchResultsDTO>(predsJsonArray.length());
        logger.info(CLASS_NAME + "::" + METHOD_NAME + "::Results:-\n");
        for (int i = 0; i < predsJsonArray.length(); i++) {
            SearchResultsDTO place = new SearchResultsDTO();
            place.vicinity = predsJsonArray.getJSONObject(i).getString("vicinity");
            //place.formatted_address = getFormattedAddress(place.vicinity); 
            place.name = predsJsonArray.getJSONObject(i).getString("name");
            JSONObject locationObj = predsJsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
            place.longitude = locationObj.getDouble("lng");
            place.latitude = locationObj.getDouble("lat");
            try{
            	place.rating = String.valueOf(predsJsonArray.getJSONObject(i).getInt("rating"));	
            }
            catch(JSONException jsonException){
            	place.rating = "No";
            }
            logger.info(place.name + ", " + place.vicinity + " with " + place.rating + " rating.");
            resultList.add(place);
        }
        if(resultList.size() == 0){
        	logger.error("No Search Results found!\n");
        }
	 
	    return resultList;
	}

	/**
	 * Fetches the results from Google API for the given URL
	 * @param sb - StringBuffer object of API URL
	 * @return - JSON response for the given input
	 * @throws IOException
	 */
	private StringBuilder fetchJSONResults(StringBuilder urlStringBufferObj) throws IOException {
		StringBuilder jsonResults =  new StringBuilder();
		HttpURLConnection conn = null;
		URL url = new URL(urlStringBufferObj.toString());
	     conn = (HttpURLConnection) url.openConnection();
	     InputStreamReader in = new InputStreamReader(conn.getInputStream());
	
	     int read;
	     char[] buff = new char[1024];
	     while ((read = in.read(buff)) != -1) {
	         jsonResults.append(buff, 0, read);
	     }
	     
	     if (conn != null) {
	         conn.disconnect();
	     }
	     
		return jsonResults;
	}
	
	/**
	 * Fetches the stores details for the given input location through Google API
	 * @param inputLocation - input area details
	 * @return - List of near by stores
	 * @throws ResponseEntityException
	 * @throws IOException
	 * @throws JSONException
	 */
	public StoresListDTO getCoordinates(String inputLocation, String radius) throws ResponseEntityException ,IOException, JSONException {
		String METHOD_NAME = "getCoordinates()";
		StoresListDTO storesListDTO = null;
		ArrayList<SearchResultsDTO> searchResultsList = null;
		Double latitude = 0.0;
		Double longitude = 0.0;
		StringBuilder jsonResults = new StringBuilder();
        StringBuilder sb = new StringBuilder(UsersConstants.PLACES_API_BASE);
        sb.append(UsersConstants.TYPE_TEXT_SEARCH);
        sb.append(UsersConstants.OUT_JSON);
        sb.append("?query=" + inputLocation);
        sb.append("&key=" + googleAPIKey);
        
        logger.info(METHOD_NAME + "::" +"getCoordinates()::Google API URL:- " + sb.toString());
        jsonResults = fetchJSONResults(sb);
    
        // Create a JSON object hierarchy from the results
        JSONObject jsonObj = new JSONObject(jsonResults.toString());
        JSONArray predsJsonArray = jsonObj.getJSONArray("results");

        // Extract the Place descriptions from the results
        for (int i = 0; i < predsJsonArray.length(); i++) {
            JSONObject locationObj = predsJsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location");
            longitude = locationObj.getDouble("lng");
            latitude = locationObj.getDouble("lat");
            logger.info(METHOD_NAME + "::" +"Coordinates of " + inputLocation + " is " + latitude + "," + longitude);
        }
    
        searchResultsList = findNearestStores(latitude, longitude, Integer.parseInt(radius));
        storesListDTO =  new StoresListDTO(String.valueOf(latitude), String.valueOf(longitude), searchResultsList);
        return storesListDTO;
	}
	
	
}