package exceptions;

public class CouponTitleDupliactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponTitleDupliactionException(){
		System.out.println("coupon title already exists!");
	}

}
