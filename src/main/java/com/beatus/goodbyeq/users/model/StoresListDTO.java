package com.beatus.goodbyeq.users.model;

import java.util.ArrayList;
import java.util.List;

public class StoresListDTO {
	public List<SearchResultsDTO> searchResultsList = new ArrayList<SearchResultsDTO>();
	public List<StoreDetailsDTO> storeDetailsDTO = new ArrayList<StoreDetailsDTO>();
	private String latitude;
	private String longitude;
	
	public List<StoreDetailsDTO> getStoreDetailsDTO() {
		return storeDetailsDTO;
	}
	public void setStoreDetailsDTO(List<StoreDetailsDTO> storeDetailsDTO) {
		this.storeDetailsDTO = storeDetailsDTO;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public StoresListDTO(List<StoreDetailsDTO> storeDetailsDTO, String latitude, String longitude) {
		super();
		this.storeDetailsDTO = storeDetailsDTO;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public List<SearchResultsDTO> getSearchResultsList() {
		return searchResultsList;
	}
	public void setSearchResultsList(List<SearchResultsDTO> searchResultsList) {
		this.searchResultsList = searchResultsList;
	}
	public StoresListDTO(String latitude, String longitude, List<SearchResultsDTO> searchResultsList) {
		this.searchResultsList = searchResultsList;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}