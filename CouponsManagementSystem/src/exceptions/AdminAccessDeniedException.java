package exceptions;

public class AdminAccessDeniedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public AdminAccessDeniedException(){
	System.out.println("access denied to admin client");
}

}

