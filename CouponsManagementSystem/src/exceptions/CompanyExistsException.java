package exceptions;

public class CompanyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyExistsException(){
		System.out.println("company already exists in dataBase");//should i print here?
		
	}

}
