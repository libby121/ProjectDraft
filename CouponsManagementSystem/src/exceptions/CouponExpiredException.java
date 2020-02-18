package exceptions;

public class CouponExpiredException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponExpiredException(){
		super("curent date is after expiration date ");
	}

}
