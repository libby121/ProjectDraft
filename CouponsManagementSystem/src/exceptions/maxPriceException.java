package exceptions;

public class maxPriceException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public maxPriceException(){
		System.out.println(" coupons prices are all higher than givan price!");
	}
}
