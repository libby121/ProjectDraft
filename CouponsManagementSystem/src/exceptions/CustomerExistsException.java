package exceptions;

public class CustomerExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerExistsException(){
		System.out.println("customer is already registered!");
	}
}
