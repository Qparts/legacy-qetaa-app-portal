package qetaa.jsf.portal.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import qetaa.jsf.portal.helpers.AppConstants;

@Named
@ViewScoped
public class NotificationBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int quotations;
	private int parts;
	
	@Inject
	private Requester reqs;
	
	@Inject
	private LoginBean loginBean;
	
	@PostConstruct
	private void init(){
		initQuotations();
		initParts();
	}
	
	public void initQuotations() {
		Response r = reqs.getSecuredRequest(AppConstants.getQuotationNotification(loginBean.getUserHolder().getVendorUser().getVendor().getId()));
		if(r.getStatus() == 200) {
			this.quotations = r.readEntity(Integer.class);
		}
	}
	
	public void initParts() {
		Response r = reqs.getSecuredRequest(AppConstants.getPartsNotification(loginBean.getUserHolder().getVendorUser().getVendor().getId()));
		if(r.getStatus() == 200) {
			this.parts = r.readEntity(Integer.class);
		}
	}
	
	public int getQuotations() {
		return quotations;
	}

	public void setQuotations(int quotations) {
		this.quotations = quotations;
	}

	public int getParts() {
		return parts;
	}

	public void setParts(int parts) {
		this.parts = parts;
	}

	
	

}
