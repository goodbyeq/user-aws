package com.beatus.goodbyeq.users.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.users.dac.ItemsDBService;
import com.beatus.goodbyeq.users.exception.ResponseEntityException;
import com.beatus.goodbyeq.users.model.BillDetailsDTO;
import com.beatus.goodbyeq.users.model.ItemDetailsDTO;
import com.beatus.goodbyeq.users.service.api.ItemsService;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;
import com.beatus.goodbyeq.users.utils.UsersConstants;
import com.beatus.goodbyeq.users.utils.UsersUtilities;
import com.beatus.goodbyeq.users.validation.ItemsValidator;
import com.beatus.goodbyeq.users.validation.exception.GoodByeQValidationException;

@Component("itemsService")
public class ItemsServiceImpl implements ItemsService{
	
	@Resource(name = "itemsValidator")
	private ItemsValidator itemsValidator;
	
	@Resource(name = "itemsDBService")
	private ItemsDBService itemsDBService;
	
	private static final Logger logger = LoggerFactory.getLogger(ItemsServiceImpl.class);
	
	public ItemDetailsDTO fetchItemDetails(String itemCode) throws GoodByeQValidationException, ClassNotFoundException, SQLException{
		logger.info("Item Code:- " + itemCode);
		ItemDetailsDTO itemDetailsDTO = null; 
				
		itemDetailsDTO = itemsDBService.fetchItemDetails(itemCode);
		if(itemDetailsDTO != null) {
			logger.info("Item Name:- " + itemDetailsDTO.getItemName());
			itemDetailsDTO.setItemStatus(UsersConstants.ITEM_FOUND);
		}	
		else {
			itemDetailsDTO = new ItemDetailsDTO(UsersConstants.ITEM_NOT_FOUND);
		}
		return itemDetailsDTO;
	}

	public String createBill(String scannedItemsStr) throws GoodByeQCompanyServiceException, 
		ResponseEntityException, ClassNotFoundException, JSONException, IOException, SQLException {
		ArrayList<ItemDetailsDTO> itemDetailsList = new ArrayList<ItemDetailsDTO>();
		String responseMessage = null;
		String itemId = null;
		String hsnCode = null;
		String itemName = null;
		String itemQuantity = null;
		String itemPrice = null;
		String itemTaxAmount = null;
		String itemCGST = null;
		String itemSGST = null;
		String itemIGST = null;
		String itemDiscount = null;
				
		JSONObject itemDetailsJSONObj = new JSONObject(scannedItemsStr);
		String userId = UsersUtilities.readJSONObject(scannedItemsStr, "userId");
		String storeId = UsersUtilities.readJSONObject(scannedItemsStr, "storeId");
		String companyId = UsersUtilities.readJSONObject(scannedItemsStr, "companyId");
		logger.info("userId:- " + userId + " storeId:- " + storeId);
		String billItemQuantity = UsersUtilities.readJSONObject(scannedItemsStr, "itemQuantity");
		String totalQuantity = UsersUtilities.readJSONObject(scannedItemsStr, "totalQuantity");
		String totalAmount = UsersUtilities.readJSONObject(scannedItemsStr, "totalAmount");
		String totalTax = UsersUtilities.readJSONObject(scannedItemsStr, "totalTax");
		String totalCGST = UsersUtilities.readJSONObject(scannedItemsStr, "totalCGST");
		String totalSGST = UsersUtilities.readJSONObject(scannedItemsStr, "totalSGST");
		String totalIGST = UsersUtilities.readJSONObject(scannedItemsStr, "totalIGST");	
		
		BillDetailsDTO billDetailsDTO = new BillDetailsDTO(UsersUtilities.generateRandomString(), userId, storeId, companyId, 
				billItemQuantity, totalQuantity, totalAmount, totalTax, totalCGST, totalSGST,
				totalIGST);
		
		JSONArray itemDetailsJSONArray = itemDetailsJSONObj.getJSONArray("itemsList");
		int itemsCount = itemDetailsJSONArray.length();
		for (int i = 0; i < itemsCount; i++) {
			 itemId  = itemDetailsJSONArray.getJSONObject(i).getString("itemId");
			 itemName  = itemDetailsJSONArray.getJSONObject(i).getString("itemName");
			 hsnCode  = itemDetailsJSONArray.getJSONObject(i).getString("hsnCode");
			 itemQuantity  = itemDetailsJSONArray.getJSONObject(i).getString("itemQuantity");
			 itemPrice  = itemDetailsJSONArray.getJSONObject(i).getString("itemPrice");
			 itemTaxAmount  = itemDetailsJSONArray.getJSONObject(i).getString("itemTaxAmount");
			 itemCGST  = itemDetailsJSONArray.getJSONObject(i).getString("itemCGST");
			 itemSGST  = itemDetailsJSONArray.getJSONObject(i).getString("itemSGST");
			 itemIGST  = itemDetailsJSONArray.getJSONObject(i).getString("itemIGST");
			 itemDiscount  = itemDetailsJSONArray.getJSONObject(i).getString("itemDiscount");
			 
			 itemDetailsList.add(new ItemDetailsDTO(itemId, itemName, hsnCode, itemPrice, itemSGST, itemCGST, itemIGST,
					 itemQuantity, itemTaxAmount, itemDiscount));
		}
		 
		responseMessage = itemsDBService.createBill(billDetailsDTO, itemDetailsList); 
		return responseMessage;
	}
}