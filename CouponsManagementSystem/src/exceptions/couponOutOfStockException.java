package exceptions;

public class couponOutOfStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public couponOutOfStockException(){
		System.out.println("this coupon is currently out of stock!");
	}
}
