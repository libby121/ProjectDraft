package exceptions;

public class AccessDeniedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AccessDeniedException(){
		System.out.println("wrong password or email");
	}
}
