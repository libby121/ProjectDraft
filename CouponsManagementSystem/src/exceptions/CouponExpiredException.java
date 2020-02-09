package exceptions;

public class CouponExpiredException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponExpiredException(){
		System.out.println("curent date is after expiration date ");
	}

}
