package com.beatus.goodbyeq.users.dac;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beatus.goodbyeq.users.model.BillDetailsDTO;
import com.beatus.goodbyeq.users.model.ItemDetailsDTO;

@Component("itemsDBService")
public class ItemsDBService {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemsDBService.class);
	
	@Value(value = "${db.dbname:localhost}")
	private String dbSchema;
	
	@Resource(name = "usersDBUtils")
	private UsersDBUtils usersDBUtils;
	
	public ItemDetailsDTO fetchItemDetails(String itemCode) throws ClassNotFoundException, SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ItemDetailsDTO itemDetailsDTO = null;
		
		String fetchItemDetailsSQL = "SELECT * "
				+ "FROM "+dbSchema+".GBQ_ITEM "
				+ "WHERE ITEM_ID = '" + itemCode + "'";
		logger.info("fetchItemDetails()::Query:- " + fetchItemDetailsSQL);
		
		dbConnection = usersDBUtils.getRemoteConnection();
		logger.info("fetchItemDetails()::dbConnection:- " + dbConnection);
		statement = dbConnection.createStatement();
 
		ResultSet rs = statement.executeQuery(fetchItemDetailsSQL);

		while (rs.next()) {
			itemDetailsDTO = new ItemDetailsDTO(rs.getString("ITEM_ID"), rs.getString("ITEM_NAME"), rs.getString("BRAND"), 
					rs.getString("UNIT_MRP"), rs.getString("UNIT_DISCOUNT"), rs.getString("UNIT_DISCOUNT_TYPE"), 
					rs.getString("BUY_QUANTITY"), rs.getString("GET_QUANTITY"));
		}
		
		statement.close();
		dbConnection.close();
		return itemDetailsDTO;
	}
	
	/**
	 * Inserts user details into DB
	 * @param userDTO - UserDTO object
	 * @return - User creation status
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	public String createBill(BillDetailsDTO billDetailsDTO, ArrayList<ItemDetailsDTO> itemDetailDTOList) throws ClassNotFoundException, SQLException {
		String statusMessage = "Bill generated!";
		Connection dbConnection = null;
		int billInsertionStatus = 0;
		ArrayList<Integer> itemsInstertionCodesList = null;
		//int itemsInsertionStatus = 0;
		
		dbConnection = usersDBUtils.getRemoteConnection();
			
		billInsertionStatus = insertIntoBillTable(billDetailsDTO, dbConnection);
		if(billInsertionStatus == 1) {
			itemsInstertionCodesList = insertIntoBillItemsTable(billDetailsDTO.getBillId(), itemDetailDTOList, dbConnection);
			/*for(int itemInsertionCode : itemsInstertionCodesList) {
				itemsInsertionStatus +=itemInsertionCode;
			}
			if(itemsInsertionStatus == itemDetailDTOList.size()) {
				logger.info(billDetailsDTO.getBillId() + " is inserted into GBQ_BILL_ITEM table"); 				
			}*/
			/*logger.info(billDetailsDTO.getBillId() + " is inserted into GBQ_BILL_ITEM table");*/
			logger.info("createBill():: itemsInstertionCodesList:- " + itemsInstertionCodesList);
		}
		dbConnection.close();
		return statusMessage;
	}
	
	private ArrayList<Integer> insertIntoBillItemsTable(String billId, ArrayList<ItemDetailsDTO> itemDetailDTOList, 
			Connection dbConnection) throws SQLException {
		PreparedStatement preparedStatement = null;
		ArrayList<Integer> statusCodes = new ArrayList<Integer>();
		int statusCode = 0;
        Date date = new Date();
        
		for(ItemDetailsDTO itemDetailsDTO: itemDetailDTOList) {
			preparedStatement = dbConnection.prepareStatement("INSERT INTO "+dbSchema+".GBQ_BILL_ITEM "
					+ "(BILL_ID, ITEM_ID, HSN_CODE, ITEM_NAME, PRODUCT_PRICE, ITEM_CREATE_TS, "
					+ "ITEM_UPDATE_TS, ITEM_QUANTITY, ITEM_TAX_AMOUNT, "
					+ "ITEM_TAX_SGST, ITEM_TAX_CGST, ITEM_TAX_IGST, ITEM_DISCOUNT) " + 
					"VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, billId);
			preparedStatement.setString(2, itemDetailsDTO.getItemId());
			preparedStatement.setString(3, itemDetailsDTO.getHsnCode());
			preparedStatement.setString(4, itemDetailsDTO.getItemName());
			preparedStatement.setString(5, itemDetailsDTO.getPrice());
			preparedStatement.setTimestamp(6, new Timestamp(date.getTime()));
			preparedStatement.setTimestamp(7, new Timestamp(date.getTime()));
			preparedStatement.setString(8, itemDetailsDTO.getItemQuantity());
			preparedStatement.setString(9, itemDetailsDTO.getItemTaxAmount());
			preparedStatement.setString(10, itemDetailsDTO.getSgst());
			preparedStatement.setString(11, itemDetailsDTO.getCgst());
			preparedStatement.setString(12, itemDetailsDTO.getIgst());
			preparedStatement.setString(13, itemDetailsDTO.getItemDiscount());
			// execute insert SQL statement
			statusCode = preparedStatement.executeUpdate();
			statusCodes.add(statusCode);
			logger.info(itemDetailsDTO.getItemId() + " is inserted into GBQ_BILL_ITEM table for bill# " + billId);
		}		
		return statusCodes;
	}


	private int insertIntoBillTable(BillDetailsDTO billDetailsDTO, Connection dbConnection) throws SQLException {
		PreparedStatement preparedStatement = null;
        Date date = new Date();
        
		preparedStatement = dbConnection.prepareStatement("INSERT INTO "+dbSchema+".GBQ_BILL "
				+ "(BILL_ID, USER_ID, STORE_ID, COMPANY_ID, ITEM_QTY, TOTAL_QTY, TOTAL_AMT, "
				+ "TOTAL_TAX, BILL_CREATE_TIMESTAMP, BILL_UPDATE_TIMESTAMP, TOTAL_SGST, TOTAL_CGST, TOTAL_IGST) " + 
				"VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		preparedStatement.setString(1, billDetailsDTO.getBillId());
		preparedStatement.setString(2, billDetailsDTO.getUserId());
		preparedStatement.setString(3, billDetailsDTO.getStoreId());
		preparedStatement.setString(4, billDetailsDTO.getCompanyId());
		preparedStatement.setString(5, billDetailsDTO.getItemQuantity());
		preparedStatement.setString(6, billDetailsDTO.getTotalQuantity());
		preparedStatement.setString(7, billDetailsDTO.getTotalAmount());
		preparedStatement.setString(8, billDetailsDTO.getTotalTax());
		preparedStatement.setTimestamp(9, new Timestamp(date.getTime()));
		preparedStatement.setTimestamp(10, new Timestamp(date.getTime()));
		preparedStatement.setString(11, billDetailsDTO.getTotalSGST());
		preparedStatement.setString(12, billDetailsDTO.getTotalCGST());
		preparedStatement.setString(13, billDetailsDTO.getTotalIGST());		
		// execute insert SQL statement
		int statusCode = preparedStatement.executeUpdate();
		logger.info("Record is inserted into GBQ_BILL table!" + statusCode);
		return statusCode;
	}
}