package qetaa.jsf.portal.helpers;

public class AppConstants {
	public final static String APP_HOST = "http://localhost:8080";
	public final static String SERVICE_HOST = "http://localhost:8080";
	
	private final static String VENDOR_SERVICE = SERVICE_HOST +"/service-qetaa-vendor/rest/";
	private final static String CART_SERVICE = SERVICE_HOST +"/service-qetaa-cart/rest/";
	private final static String PAYMENT_SERVICE = SERVICE_HOST +"/service-qetaa-payment/rest/";
	private final static String PROMOTION_SERVICE = SERVICE_HOST  + "/service-qetaa-vendor/rest/promotion/";
	
	public final static String POST_LOGIN = VENDOR_SERVICE + "login";
	
	public final static String APP_SECRET = "093X3b*y&iWu4U&F181X#3ZE0)%9374";
	
	public final static String PUT_QUOTATION_ITEM = CART_SERVICE + "quotation-vendor-item";
	public final static String PUT_PREPARE_ITEM = CART_SERVICE + "vendor-prepare-item";
	
	public final static String PUT_UPDATE_TEST_PROMO = PROMOTION_SERVICE + "use-test-workshop-promo-code";
	
	public final static String getPartsItems(int vendorId) {
		return CART_SERVICE + "vendor-parts-items/vendor/" + vendorId;	
	}
	
	public final static String findPromotionCode(String code) {
		return PROMOTION_SERVICE + "find-test-workshop-promo-code/code/" + code;
	}
	
	
	public final static String getQuotationNotification(int vendorId) {
		return CART_SERVICE + "vendor-quotation-notification/vendor/" + vendorId;
	}
	
	public final static String getPartsNotification(int vendorId) {
		return CART_SERVICE + "vendor-parts-notification/vendor/" + vendorId;
	}
	
	public final static String getWaitingQuotationItems(int vendorId) {
		return CART_SERVICE + "waiting-vendor-quotation-items/vendor/" + vendorId;
	}
	
	public final static String getOutstandingPurchaseOrders(int vendorId) {
		return PAYMENT_SERVICE + "outstanding-purchase-orders/vendor/" + vendorId;
	}
	
	public final static String getUninvoicedPurchaseOrders(int vendorId) {
		return PAYMENT_SERVICE + "uninvoiced-purchase-orders/vendor/" + vendorId;
	}
	
	public final static String getFullApprovedItems(long cartId, int vendorId) {
		return CART_SERVICE + "full-parts-approved-items/cart/"+cartId+"/vendor/" + vendorId;
	}
	
}
