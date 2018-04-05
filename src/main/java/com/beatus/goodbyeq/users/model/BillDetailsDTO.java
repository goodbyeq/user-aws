package com.beatus.goodbyeq.users.model;

public class BillDetailsDTO {
	
	private String billId;
	private String userId;
	private String storeId;
	private String companyId;
	private String itemQuantity;
	private String totalQuantity;
	private String totalAmount;
	private String totalTax;
	private String totalCGST;
	private String totalSGST;
	private String totalIGST;

	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}
	public String getTotalCGST() {
		return totalCGST;
	}
	public void setTotalCGST(String totalCGST) {
		this.totalCGST = totalCGST;
	}
	public String getTotalSGST() {
		return totalSGST;
	}
	public void setTotalSGST(String totalSGST) {
		this.totalSGST = totalSGST;
	}
	public String getTotalIGST() {
		return totalIGST;
	}
	public void setTotalIGST(String totalIGST) {
		this.totalIGST = totalIGST;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public BillDetailsDTO(String billId, String userId, String storeId, String companyId, String itemQuantity,
			String totalQuantity, String totalAmount, String totalTax, String totalCGST, String totalSGST,
			String totalIGST) {
		super();
		this.billId = billId;
		this.userId = userId;
		this.storeId = storeId;
		this.companyId = companyId;
		this.itemQuantity = itemQuantity;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
		this.totalTax = totalTax;
		this.totalCGST = totalCGST;
		this.totalSGST = totalSGST;
		this.totalIGST = totalIGST;
	}	
}