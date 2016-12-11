package com.leqcar.timetracking.api.timesheet;

import java.util.List;

public class ItemCommand {
	
	private String itemCode;
	private String itemDescription;
	private List<ItemDetail> itemDetails;
	private String status;
	private String note;

	public ItemCommand(String itemCode, String itemDescription, List<ItemDetail> itemDetails, String status, String note) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.itemDetails = itemDetails;
		this.status = status;
		this.note = note;
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

	public String getStatus() {
		return status;
	}

	public String getNote() {
		return note;
	}
}
