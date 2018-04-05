package com.beatus.goodbyeq.users.model;

public class ItemDetailsDTO {
	private String itemId;
	private String itemName;
	private String hsnCode;
	private String brand;
	private String price;
	private String unitMRP;
	private String unitDiscount;
	private String unitDiscountType;
	private String buyQuantity;
	private String getQuantity;
	private String sgst;
	private String cgst;
	private String igst;
	private String itemQuantity;
	private String itemTaxAmount;
	private String itemDiscount;
	private String itemStatus;
	
	public ItemDetailsDTO(String itemId, String itemName, String brand, String unitMRP, String unitDiscount,
			String unitDiscountType, String buyQuantity, String getQuantity) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.brand = brand;
		this.unitMRP = unitMRP;
		this.unitDiscount = unitDiscount;
		this.unitDiscountType = unitDiscountType;
		this.buyQuantity = buyQuantity;
		this.getQuantity = getQuantity;
	}
	
	public ItemDetailsDTO(String itemId, String itemName, String hsnCode, String price, String sgst, String cgst,
			String igst, String itemQuantity, String itemTaxAmount, String itemDiscount) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.hsnCode = hsnCode;
		this.price = price;
		this.sgst = sgst;
		this.cgst = cgst;
		this.igst = igst;
		this.itemQuantity = itemQuantity;
		this.itemTaxAmount = itemTaxAmount;
		this.itemDiscount = itemDiscount;
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnitMRP() {
		return unitMRP;
	}
	public void setUnitMRP(String unitMRP) {
		this.unitMRP = unitMRP;
	}
	public String getUnitDiscount() {
		return unitDiscount;
	}
	public void setUnitDiscount(String unitDiscount) {
		this.unitDiscount = unitDiscount;
	}
	public String getUnitDiscountType() {
		return unitDiscountType;
	}
	public void setUnitDiscountType(String unitDiscountType) {
		this.unitDiscountType = unitDiscountType;
	}
	public String getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(String buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public String getGetQuantity() {
		return getQuantity;
	}
	public void setGetQuantity(String getQuantity) {
		this.getQuantity = getQuantity;
	}
	public String getSgst() {
		return sgst;
	}
	public void setSgst(String sgst) {
		this.sgst = sgst;
	}
	public String getCgst() {
		return cgst;
	}
	public void setCgst(String cgst) {
		this.cgst = cgst;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getIgst() {
		return igst;
	}
	public void setIgst(String igst) {
		this.igst = igst;
	}
	public String getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getItemTaxAmount() {
		return itemTaxAmount;
	}
	public void setItemTaxAmount(String itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}
	public String getItemDiscount() {
		return itemDiscount;
	}
	public void setItemDiscount(String itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public ItemDetailsDTO(String itemStatus) {
		this.itemStatus = itemStatus;
	}
}