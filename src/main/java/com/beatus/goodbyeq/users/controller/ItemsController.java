package com.beatus.goodbyeq.users.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.goodbyeq.users.model.ItemDetailsDTO;
import com.beatus.goodbyeq.users.model.JSendResponse;
import com.beatus.goodbyeq.users.service.api.ItemsService;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;
import com.beatus.goodbyeq.users.utils.Constants;
import com.beatus.goodbyeq.users.utils.GoodByeQMediaType;
import com.beatus.goodbyeq.users.validation.exception.GoodByeQClientValidationException;

@Controller
public class ItemsController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ItemsController.class);
	
	@Value(value = "${db.host:localhost}")
	private String defaultDb;
	
	@Resource(name = "itemsService")
	private ItemsService itemsService;
	
	private JSendResponse<ItemDetailsDTO> jsend(ItemDetailsDTO itemDetailsDTO) {
		if(itemDetailsDTO == null){
	        return new JSendResponse<ItemDetailsDTO>(Constants.FAILURE, itemDetailsDTO);
		}else {
			return new JSendResponse<ItemDetailsDTO>(Constants.SUCCESS, itemDetailsDTO);
		}
	}
	
	/**
	 * Search the scanned product details which includes discount, price, tax from the database.
	 * @param searchItemString - Scanned item bar code	
	 */
	@RequestMapping(value = "/getItemById", method = RequestMethod.GET, produces = {GoodByeQMediaType.APPLICATION_JSON})
	public @ResponseBody JSendResponse<ItemDetailsDTO> searchProductDetails(@RequestParam(Constants.ITEM_ID) String itemID, HttpServletRequest request,
			HttpServletResponse response) throws GoodByeQClientValidationException, GoodByeQCompanyServiceException {
		String METHOD_NAME = "searchProductDetails()";
		long startTime = System.nanoTime();
		ItemDetailsDTO itemData = null;
		logger.info("defaultDb " + defaultDb);
		logger.info(METHOD_NAME + "::searchItemString loaded:- " +  itemID);
		try {		
			itemData = itemsService.fetchItemDetails(itemID);
		}catch(Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to " + exception.getMessage()); 
		}
		long endTime = System.nanoTime();
		logger.info(METHOD_NAME + "::completed in " +(endTime - startTime)/1000000 + " ms");
		return jsend(itemData);
	}	
	
	/**
	 * Search the scanned product details which includes discount, price, tax from the database.
	 * @param searchItemString - Scanned item bar code	
	 */
	@RequestMapping(value = "/createBill", method = RequestMethod.POST, 
			consumes = {GoodByeQMediaType.APPLICATION_JSON}, produces = {GoodByeQMediaType.APPLICATION_JSON})
	public @ResponseBody JSendResponse<String> createBill(@RequestBody String scannedItemsString, HttpServletRequest request,
			HttpServletResponse response) throws GoodByeQClientValidationException, GoodByeQCompanyServiceException {
		String METHOD_NAME = "createBill()";
		long startTime = System.nanoTime();
		String responseMessage = null;
		logger.info(METHOD_NAME + "::scanned items loaded:- " + scannedItemsString);
		try {		
			responseMessage = itemsService.createBill(scannedItemsString);
		}catch(Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to " + exception.getMessage()); 
		}
		long endTime = System.nanoTime();
		logger.info(METHOD_NAME + "::completed in " +(endTime - startTime)/1000000 + " ms");
		return jsend(responseMessage);
	}	
}
