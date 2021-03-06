package com.leqcar.timetracking.dtos;

import java.util.List;

/**
 * Created by jongtenerife on 10/12/2016.
 */
public class ItemDTO {

    private String itemCode;
    
    private String itemDescription;
    private String status;
    private String note;
    
    private List<ItemDetailDTO> itemDetailVOs;
    
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<ItemDetailDTO> getItemDetailVOs() {
		return itemDetailVOs;
	}
	public void setItemDetailVOs(List<ItemDetailDTO> itemDetailVOs) {
		this.itemDetailVOs = itemDetailVOs;
	}
    
    
}
