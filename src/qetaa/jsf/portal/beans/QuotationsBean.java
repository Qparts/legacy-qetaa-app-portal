package qetaa.jsf.portal.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import qetaa.jsf.portal.helpers.AppConstants;
import qetaa.jsf.portal.helpers.Helper;
import qetaa.jsf.portal.model.cart.QuotationItem;

@Named
@ViewScoped
public class QuotationsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<QuotationItem> quotationItems;

	@Inject
	private Requester reqs;

	@Inject
	private LoginBean loginBean;
	
	@Inject 
	private NotificationBean notificationBean;

	@PostConstruct
	private void init() {
		quotationItems = new ArrayList<>();
		Response r = reqs.getSecuredRequest(
				AppConstants.getWaitingQuotationItems(loginBean.getUserHolder().getVendorUser().getVendor().getId()));
		if (r.getStatus() == 200) {
			this.quotationItems = r.readEntity(new GenericType<List<QuotationItem>>() {
			});
		} else {
		}
	}

	public void submitQuotationItem(QuotationItem qitem) {
		try {
			if (qitem.getVendorQuantity() == 0) {

			} else if (qitem.getItemNumber().trim().length() == 0 || qitem.getItemCostPrice() == null
					|| !(qitem.getItemCostPrice() > 0)) {
				throw new Exception();
			}
			qitem.setRespondedBy(this.loginBean.getUserHolder().getVendorUser().getId());
			Response r = this.reqs.putSecuredRequest(AppConstants.PUT_QUOTATION_ITEM, qitem);
			if(r.getStatus() == 200) {
				this.quotationItems.remove(qitem);
				Helper.addInfoMessage("Item Submitted");
				this.notificationBean.initQuotations();
			}
			else if (r.getStatus() == 409) {
				Helper.addErrorMessage("This quotation has been submitted by another user");
				init();
			}
			else {
				Helper.addErrorMessage("An error occured");
			}

		} catch (Exception ex) {
			if (qitem.getItemNumber().trim().length() == 0) {
				Helper.addErrorMessage("Item Number is not set");
			} else if (qitem.getItemCostPrice() == null || !(qitem.getItemCostPrice() > 0)) {
				Helper.addErrorMessage("Price is not set");
			}
		}
	}

	public List<QuotationItem> getQuotationItems() {
		return this.quotationItems;
	}

}
