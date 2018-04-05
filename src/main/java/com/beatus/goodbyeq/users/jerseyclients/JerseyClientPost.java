package com.beatus.goodbyeq.users.jerseyclients;

import com.beatus.goodbyeq.users.model.CoordinatesDTO;

//import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {
		//final Logger logger = Logger.getLogger(JerseyClientPost.class);
		
		try {

			Client client = Client.create();

			/*WebResource webResource = client.resource("http://localhost:8085/users/searchStoresByCoordinates");
			String inputCoordinates = "{\"latittude\":\"40.5514931\",\"longitude\":\"-74.534133\"}";
			CoordinatesDTO coordinatesDTO = new CoordinatesDTO("40.5514931", "-74.534133");*/
			
			/*WebResource webResource = client.resource("http://localhost:8085/users/createUsers");
			String inputUserDetails = "{\"firstName\":\"Manoj"
					+ "\",\"lastName\":\"Kamuju"
					+ "\",\"userID\":\"ssandeepkamuju"
					+ "\",\"email\":\"manoj.kamuju@gmail.com"
					+ "\",\"phoneNumber\":\"46456456"
					+ "\",\"dateOfBirth\":\"1994-12-28"
					+ "\",\"gender\":\"M"
					+ "\",\"salt\":\"testSalt"
					+ "\",\"password\":\"test@password123"
					+ "\",\"latittude\":\"38.9769793"
					+ "\",\"longitude\":\"-77.3921876"
					+ "\",\"address\":\"Apt 304, 2152 San Moritz Cir"
					+ "\",\"city\":\"Herndon"
					+ "\",\"state\":\"VA"
					+ "\"}";*/

			/*WebResource webResource = client.resource("http://localhost:8085/users/searchStoresByArea");
			String inputArea = "{\"inputArea\":\"Kakinada\"}";*/
			
			/*WebResource webResource = client.resource("http://localhost:8085/users/searchProductDetails");
			String inputItemCode = "{\"itemCode\":\"Product2\"}";*/
			
/*WebResource webResource = client.resource("http://localhost:8080/users/createBill");
			String inputItemDetailsDetails = 

					"{" + 
			    			"	\"userId\": \"anudeepakey\"," +
			    			"	\"storeId\": \"Store2\"," +
			    			"	\"companyId\": \"Company2\"," +
			    			"	\"itemQuantity\": \"3\"," +
			    			"	\"totalQuantity\": \"10\"," +
			    			"	\"totalAmount\": \"1000\"," +
			    			"	\"totalTax\": \"10\"," +
			    			"	\"totalCGST\": \"10\"," +
			    			"	\"totalSGST\": \"10\"," +
			    			"	\"totalIGST\": \"10\"," +
			    			"	\"itemsList\": " +
			    			"		[{" + 
			    			"		\"itemId\": \"Product1\"," +  
			    			"		\"itemName\": \"Maida\"," + 
			    			"		\"hsnCode\": \"1234227\"," +
			    			"		\"itemQuantity\": \"1\"," +
			    			"		\"itemPrice\": \"35\"," +
			    			"		\"itemTaxAmount\": \"2.10\"," +
			    			"		\"itemCGST\": \"10\"," +
			    			"		\"itemSGST\": \"10\"," +
			    			"		\"itemIGST\": \"10\"," +
			    			"		\"itemDiscount\": \"12.5\"," +
			    			"    	}, {" + 
			    			"		\"itemId\": \"Product5\"," +  
			    			"		\"itemName\": \"Garlic\"," + 
			    			"		\"hsnCode\": \"2123522\"," +
			    			"		\"itemQuantity\": \"2\"," +
			    			"		\"itemPrice\": \"15\"," +
			    			"		\"itemTaxAmount\": \"10\"," +
			    			"		\"itemCGST\": \"11\"," +
			    			"		\"itemSGST\": \"11\"," +
			    			"		\"itemIGST\": \"10\"," +
			    			"		\"itemDiscount\": \"10.5\"," +
			    			"		}]" +
			    			"}";*/
			
			WebResource webResource = client.resource("http://localhost:8080/users/createBill");
			String inputItemDetailsDetails = 

					"{" + 
			    			"	\"userId\": \"ppavan569\"," +
			    			"	\"storeId\": \"Store4\"," +
			    			"	\"companyId\": \"Company5\"," +
			    			"	\"itemQuantity\": \"3\"," +
			    			"	\"totalQuantity\": \"10\"," +
			    			"	\"totalAmount\": \"1000\"," +
			    			"	\"totalTax\": \"10\"," +
			    			"	\"totalCGST\": \"10\"," +
			    			"	\"totalSGST\": \"10\"," +
			    			"	\"totalIGST\": \"10\"," +
			    			"	\"itemsList\": " +
			    			"		[{" + 
			    			"		\"itemId\": \"Product1\"," +  
			    			"		\"itemName\": \"Maida\"," + 
			    			"		\"hsnCode\": \"1234227\"," +
			    			"		\"itemQuantity\": \"1\"," +
			    			"		\"itemPrice\": \"35\"," +
			    			"		\"itemTaxAmount\": \"2.10\"," +
			    			"		\"itemCGST\": \"10\"," +
			    			"		\"itemSGST\": \"10\"," +
			    			"		\"itemIGST\": \"10\"," +
			    			"		\"itemDiscount\": \"12.5\"" +
			    			"    	}, {" + 
			    			"		\"itemId\": \"Product5\"," +  
			    			"		\"itemName\": \"Garlic\"," + 
			    			"		\"hsnCode\": \"2123522\"," +
			    			"		\"itemQuantity\": \"2\"," +
			    			"		\"itemPrice\": \"15\"," +
			    			"		\"itemTaxAmount\": \"10\"," +
			    			"		\"itemCGST\": \"11\"," +
			    			"		\"itemSGST\": \"11\"," +
			    			"		\"itemIGST\": \"10\"," +
			    			"		\"itemDiscount\": \"10.5\"" +
			    			"		}]" +
			    			"}";
			
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, inputItemDetailsDetails);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server:\n" + output);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}