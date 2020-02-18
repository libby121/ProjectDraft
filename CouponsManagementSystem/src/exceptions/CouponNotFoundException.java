package exceptions;

public class CouponNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponNotFoundException(){
		super("please note: the coupon was already deleted successfully");
	}
}
