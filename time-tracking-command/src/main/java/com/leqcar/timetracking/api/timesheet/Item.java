package com.leqcar.timetracking.api.timesheet;

import java.util.List;

public class Item {
	
	private String itemCode;
	private String itemDescription;
	List<ItemDetail> itemDetails;
	
	public Item(String itemCode, String itemDescription, List<ItemDetail> itemDetails) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemDetails = itemDetails;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public List<ItemDetail> getItemDetails() {
		return itemDetails;
	}
	
}
