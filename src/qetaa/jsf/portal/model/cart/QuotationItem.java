package qetaa.jsf.portal.model.cart;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import qetaa.jsf.portal.model.vehicle.ModelYear;

public class QuotationItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private long cartId;
	private long quotationId;
	private long quotationItemId;
	private int vendorId;
	private int quotationQuantity;
	private int vendorQuantity;
	private String itemNumber;
	private String itemDesc;
	private Double itemCostPrice;
	private Date created;
	private Date responded;
	private Integer respondedBy;
	private char status;
	private String vin;
	private int makeId;
	private int modelYearId;
	private ModelYear modelYear; 
	
	
	@JsonIgnore
	public int[] getQuantityArray() {
		int[] quantityArray = new int[quotationQuantity];
		for (int i = 0; i < quantityArray.length; i++) {
			quantityArray[i] = i + 1;
		}
		return quantityArray;
	}


	public QuotationItem() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public long getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(long quotationId) {
		this.quotationId = quotationId;
	}
	public long getQuotationItemId() {
		return quotationItemId;
	}
	public void setQuotationItemId(long quotationItemId) {
		this.quotationItemId = quotationItemId;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public int getQuotationQuantity() {
		return quotationQuantity;
	}
	public void setQuotationQuantity(int quotationQuantity) {
		this.quotationQuantity = quotationQuantity;
	}
	public int getVendorQuantity() {
		return vendorQuantity;
	}
	public void setVendorQuantity(int vendorQuantity) {
		this.vendorQuantity = vendorQuantity;
	}
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public Double getItemCostPrice() {
		return itemCostPrice;
	}
	public void setItemCostPrice(Double itemCostPrice) {
		this.itemCostPrice = itemCostPrice;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getResponded() {
		return responded;
	}
	public void setResponded(Date responded) {
		this.responded = responded;
	}
	public Integer getRespondedBy() {
		return respondedBy;
	}
	public void setRespondedBy(Integer respondedBy) {
		this.respondedBy = respondedBy;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public int getMakeId() {
		return makeId;
	}
	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}
	public int getModelYearId() {
		return modelYearId;
	}
	public void setModelYearId(int modelYearId) {
		this.modelYearId = modelYearId;
	}

	public ModelYear getModelYear() {
		return modelYear;
	}

	public void setModelYear(ModelYear modelYear) {
		this.modelYear = modelYear;
	}
}
