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
import qetaa.jsf.portal.helpers.PojoRequester;
import qetaa.jsf.portal.model.cart.PartsOrderItemApproved;
import qetaa.jsf.portal.model.financial.PurchaseOrder;

@Named
@ViewScoped
public class OutstandingPurchaseOrdesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<PurchaseOrder> outstandingPOs;
	private List<PurchaseOrder> uninvoicedPOs;
	private PurchaseOrder selectedPO;
	
	@Inject
	private Requester reqs;
	@Inject
	private LoginBean loginBean;
	
	@PostConstruct
	private void init() {
		this.selectedPO = new PurchaseOrder();
		initUninvoiced();
		initPOs();
		try {
			initVariables();
		} catch (InterruptedException e) {
		
		}
	}
	
	private void initUninvoiced() {
		Response r = reqs.getSecuredRequest(AppConstants.getUninvoicedPurchaseOrders(loginBean.getUserHolder().getVendorUser().getVendor().getId()));
		if(r.getStatus() == 200) {
			this.uninvoicedPOs = r.readEntity(new GenericType<List<PurchaseOrder>>() {});
		}
		else {
			this.uninvoicedPOs = new ArrayList<>();
		}
	}
	
	private void initVariables() throws InterruptedException  {
		String header = reqs.getSecurityHeader();
		Thread[] mainThreads = new Thread[outstandingPOs.size()];
		int index = 0;
		for (PurchaseOrder po: outstandingPOs) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Thread[] threads = new Thread[1];
					Thread t1 = initApprovedItems(po, header);
					threads[0] = t1;
					t1.start();
					for (int i = 0; i < threads.length; i++)
						try {
							threads[i].join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			});
			mainThreads[index] = t;
			index++;
		}
		for (int i = 0; i < mainThreads.length; i++) {
			mainThreads[i].start();
			mainThreads[i].join();
		}
	}

	private Thread initApprovedItems(PurchaseOrder po, String header) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try { 
					Response r = PojoRequester.getSecuredRequest(AppConstants.getFullApprovedItems(po.getCartId(), po.getVendorId()),
							header);
					if (r.getStatus() == 200) {
						List<PartsOrderItemApproved> approved = r
								.readEntity(new GenericType<List<PartsOrderItemApproved>>() {
								});
						if (approved == null) {
							po.setApproved(new ArrayList<>());
						} else {
							po.setApproved(approved);
						}
					}

				} catch (Exception ex) {

				}
			}
		});
		return thread;
	}



	private void initPOs() {
		Response r = reqs.getSecuredRequest(AppConstants.getOutstandingPurchaseOrders(loginBean.getUserHolder().getVendorUser().getVendor().getId()));
		if(r.getStatus() == 200) {
			this.outstandingPOs = r.readEntity(new GenericType<List<PurchaseOrder>>() {});
		}
		else {
			this.outstandingPOs = new ArrayList<>();
		}
	}



	public List<PurchaseOrder> getOutstandingPOs() {
		return outstandingPOs;
	}



	public void setOutstandingPOs(List<PurchaseOrder> outstandingPOs) {
		this.outstandingPOs = outstandingPOs;
	}



	public PurchaseOrder getSelectedPO() {
		return selectedPO;
	}



	public void setSelectedPO(PurchaseOrder selectedPO) {
		this.selectedPO = selectedPO;
	}

	public List<PurchaseOrder> getUninvoicedPOs() {
		return uninvoicedPOs;
	}

	public void setUninvoicedPOs(List<PurchaseOrder> uninvoicedPOs) {
		this.uninvoicedPOs = uninvoicedPOs;
	}


	
	
	
	
	
	
	
}
