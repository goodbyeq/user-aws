package com.beatus.goodbyeq.users.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beatus.goodbyeq.users.model.JSendResponse;
import com.beatus.goodbyeq.users.model.StoresListDTO;
import com.beatus.goodbyeq.users.service.api.StoreService;
import com.beatus.goodbyeq.users.service.exception.GoodByeQCompanyServiceException;
import com.beatus.goodbyeq.users.utils.GoodByeQMediaType;
import com.beatus.goodbyeq.users.utils.UsersConstants;
import com.beatus.goodbyeq.users.validation.exception.GoodByeQClientValidationException;

@Controller
public class StoreController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(StoreController.class);

	@Resource(name = "storeService")
	private StoreService storeService;
	
	private JSendResponse<StoresListDTO> jsend(StoresListDTO storesListDTO) {
		if(storesListDTO == null){
	        return new JSendResponse<StoresListDTO>(UsersConstants.FAILURE, storesListDTO);
		}else {
			return new JSendResponse<StoresListDTO>(UsersConstants.SUCCESS, storesListDTO);
		}
	}
	
	/**
	 * Searches the stores through Google API for the given area details
	 * 
	 * @param searchString
	 *            - Input area (Ex: 08854, Hyderabad or Ameerpet etc.)
	 */
	@RequestMapping(value = "/searchStoresByArea", method = RequestMethod.GET, produces = { GoodByeQMediaType.APPLICATION_JSON })
	public @ResponseBody
	JSendResponse<StoresListDTO> searchStoresByArea(
			@RequestParam(UsersConstants.INPUT_AREA) String searchString,
			@RequestParam(UsersConstants.RADIUS) String radius,
			HttpServletRequest request, HttpServletResponse response)
			throws GoodByeQClientValidationException {
		String METHOD_NAME = "searchStoresByArea()";
		long startTime = System.nanoTime();
		StoresListDTO storesListDTO = null;
		logger.info(METHOD_NAME + "::searchString loaded:- " + searchString);
		try {
			storesListDTO = storeService.searchStoresByArea(searchString, radius);
		} catch (Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to "
					+ exception.getLocalizedMessage());
		}
		long endTime = System.nanoTime();
		logger.info(METHOD_NAME + "::completed in " + (endTime - startTime)
				/ 1000000 + " ms");
		return jsend(storesListDTO);
	}

	/**
	 * Searches the stores through Google API for the given coordinates
	 * 
	 * @param CoordinatesDTO
	 *            - coordinates object
	 */
	@RequestMapping(value = "/searchStoresByCoordinates", method = RequestMethod.GET, produces = { GoodByeQMediaType.APPLICATION_JSON })
	public @ResponseBody
	JSendResponse<StoresListDTO> searchStoresByCoordinates(
			@RequestParam(UsersConstants.LATITTUDE) String latittude,
			@RequestParam(UsersConstants.LONGITUDE) String longitude,
			@RequestParam(UsersConstants.RADIUS) String radius,
			HttpServletRequest request, HttpServletResponse response)
			throws GoodByeQClientValidationException,
			GoodByeQCompanyServiceException {
		String METHOD_NAME = "searchStoresByCoordinates()";
		long startTime = System.nanoTime();
		StoresListDTO searchResultsDTO = null;

		try {
			searchResultsDTO = storeService.searchStoresByCoordinates(latittude, longitude, radius);
		} catch (Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to "
					+ exception.getLocalizedMessage());
		}
		long endTime = System.nanoTime();
		logger.info(METHOD_NAME + "::completed in " + (endTime - startTime)
				/ 1000000 + " ms");
		return jsend(searchResultsDTO);
	}

	/**
	 * Searches the stores through Google API for the given area details
	 * 
	 * @param searchString - Input area (Ex: 08854, Hyderabad or Ameerpet etc.)
	 * @param radius - Ex: 5000 i.e. 5 KMs
	 */
	@RequestMapping(value = "/searchStoresByAreaFromGoogleAPI", method = RequestMethod.GET, 
			produces = { GoodByeQMediaType.APPLICATION_JSON })
	public @ResponseBody
	JSendResponse<StoresListDTO> searchStoresByAreaFromGoogleAPI(
			@RequestParam(UsersConstants.INPUT_AREA) String searchString,
			@RequestParam(UsersConstants.RADIUS) String radius,
			HttpServletRequest request, HttpServletResponse response)
			throws GoodByeQClientValidationException {
		String METHOD_NAME = "searchStoresByAreaFromGoogleAPI()";
		long startTime = System.nanoTime();
		StoresListDTO storesListDTO = null;
		logger.info(METHOD_NAME + "::Searching for stores of " + searchString + " within " + radius + " radius from Google API");
		
		try {
			storesListDTO = storeService.searchStoresByAreaFromGoogleAPI(searchString, radius);
		} catch (Exception exception) {
			logger.error(METHOD_NAME + "::Exception has occurred due to "
					+ exception.getLocalizedMessage());
		}
		long endTime = System.nanoTime();
		logger.info(METHOD_NAME + "::completed in " + (endTime - startTime)
				/ 1000000 + " ms");
		return jsend(storesListDTO);
	}
	
}