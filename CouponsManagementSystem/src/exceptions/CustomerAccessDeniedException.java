package exceptions;

public class CustomerAccessDeniedException extends Exception {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAccessDeniedException(){ 
		System.out.println("access denied to customer client, wrong email or password");
	}

}
