package qetaa.jsf.portal.model.test.promo;

import java.io.Serializable;
import java.util.Date;

public class TestWorkshopPromotion implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private int workshopId;
	private int cityId;
	private long customerId;
	private long cartId;
	private String promoCode;
	private Date created;
	private Date used;
	private Date expire;
	private char status;
	private Double laborAmount;
	private Date checked;

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUsed() {
		return used;
	}

	public void setUsed(Date used) {
		this.used = used;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public int getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(int workshopId) {
		this.workshopId = workshopId;
	}

	public Double getLaborAmount() {
		return laborAmount;
	}

	public void setLaborAmount(Double laborAmount) {
		this.laborAmount = laborAmount;
	}

	public Date getChecked() {
		return checked;
	}

	public void setChecked(Date checked) {
		this.checked = checked;
	}
	
	
}
