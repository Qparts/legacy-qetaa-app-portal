package qetaa.jsf.portal.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import qetaa.jsf.portal.helpers.AppConstants;
import qetaa.jsf.portal.helpers.Helper;
import qetaa.jsf.portal.model.test.promo.TestWorkshopPromotion;

@Named
@ViewScoped
public class PromotionTestBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String promoString;
	private TestWorkshopPromotion wsPromo;
	private boolean expired;
	
	@Inject
	private Requester reqs;
	
	@PostConstruct
	private void init() {
		wsPromo = null;
		promoString = "";
		expired = false;
	}
	
	public void usePromoCode() {
		Response r = reqs.putSecuredRequest(AppConstants.PUT_UPDATE_TEST_PROMO, this.wsPromo);
		if(r.getStatus() == 201) {
			Helper.addInfoMessage("Promo Code Updated");
			init();
		}
	}
	
	public void findPromoCode() {
		Response r = reqs.getSecuredRequest(AppConstants.findPromotionCode(promoString));
		if(r.getStatus() == 200) {
			wsPromo = r.readEntity(TestWorkshopPromotion.class);
			expired = false;
		}
		else if(r.getStatus() == 498){
			wsPromo = r.readEntity(TestWorkshopPromotion.class);
			expired = true;
		}
		else {
			wsPromo = null;
			Helper.addErrorMessage("Promo code not found");
		}
	}

	public String getPromoString() {
		return promoString;
	}

	public void setPromoString(String promoString) {
		this.promoString = promoString;
	}

	public TestWorkshopPromotion getWsPromo() {
		return wsPromo;
	}

	public void setWsPromo(TestWorkshopPromotion wsPromo) {
		this.wsPromo = wsPromo;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
	
	
	
	
}
