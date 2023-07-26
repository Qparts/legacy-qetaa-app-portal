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
import qetaa.jsf.portal.model.cart.PartCollectionItem;

@Named
@ViewScoped
public class PartsOrderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<PartCollectionItem> collections;
	private PartCollectionItem selectedCollection;
	
	@Inject
	private LoginBean loginBean;
	@Inject 
	private Requester reqs;
	
	@PostConstruct
	private void init() {
		initCollections();
		selectedCollection = new PartCollectionItem();
	}


	private void initCollections() {
		collections = new ArrayList<>();
		Response r = reqs.getSecuredRequest(AppConstants.getPartsItems(loginBean.getUserHolder().getVendorUser().getVendor().getId()));
		if(r.getStatus() == 200) {
			this.collections = r.readEntity(new GenericType<List<PartCollectionItem>>() {
			});
		}
		else {
			
		}	
	}
	
	public void prepareItem(PartCollectionItem collection) {
		collection.setPreparedBy(loginBean.getUserHolder().getVendorUser().getId());
		Response r = reqs.putSecuredRequest(AppConstants.PUT_PREPARE_ITEM, collection);
		if(r.getStatus() == 201) {
			init();
			Helper.addInfoMessage("Item is ready for collection");
		}
		else {
			
		}
	}


	public List<PartCollectionItem> getCollections() {
		return collections;
	}


	public void setCollections(List<PartCollectionItem> collections) {
		this.collections = collections;
	}


	public PartCollectionItem getSelectedCollection() {
		return selectedCollection;
	}


	public void setSelectedCollection(PartCollectionItem selectedCollection) {
		this.selectedCollection = selectedCollection;
	}
	
}
