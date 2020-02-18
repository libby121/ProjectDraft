package exceptions;

public class NoSuchCouponsCategoryException extends Exception {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public	NoSuchCouponsCategoryException() {
	super("no such category exists. please check again the inserted category name");
}
	
}
