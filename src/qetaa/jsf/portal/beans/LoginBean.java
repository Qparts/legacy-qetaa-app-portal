package qetaa.jsf.portal.beans;

import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import qetaa.jsf.portal.helpers.AppConstants;
import qetaa.jsf.portal.helpers.Helper;
import qetaa.jsf.portal.model.vendor.UserHolder;
import qetaa.jsf.portal.model.vendor.VendorUser;

@Named
@SessionScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private UserHolder userHolder;
	
	
	@Inject
	private Requester reqs;
	
	@PostConstruct
	private void init(){
		userHolder = new UserHolder();
		userHolder.setVendorUser(new VendorUser());
	}
	
	
	public void login(){
		HashMap<String,Object> map = new HashMap<>();
		map.put("username", username);
		map.put("appSecret", AppConstants.APP_SECRET);
		map.put("language", 'E');
		map.put("code", password);
		Response r = reqs.postSecuredRequest(AppConstants.POST_LOGIN, map);
		if(r.getStatus() == 200){
			this.userHolder = r.readEntity(UserHolder.class);
			doLogin();
		}
		else {
			Helper.addErrorMessage("Incorrect login credintials");
		}
	}
	
	public boolean isLogged() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("vendor.access") != null;
	}
	
	private void doLogin() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("vendor.access", userHolder);
		Helper.redirect("/portal/port/home");
	}

	public void doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("vendor.access");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		Helper.redirect("/portal/login");
	}
	
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public UserHolder getUserHolder() {
		return userHolder;
	}

	public void setUserHolder(UserHolder userHolder) {
		this.userHolder = userHolder;
	}
	
}
