package facades;

import java.sql.SQLException;

import dao.CompanyDAO;
import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exceptions.CompanyDoesntExistException;

public abstract class Facade {
	
	protected CompanyDAO compDBD = new CompanyDBDAO();//does it matter if its dao or dbdao.
	//why not private-> because it should be inherited
	protected CouponDBDAO coupDBD = new CouponDBDAO();
	protected CustomerDBDAO custDBD = new CustomerDBDAO();

	
	//how to change the exception from superclass
	public abstract boolean login(String email,String password) throws SQLException, CompanyDoesntExistException;//method will be overridden in each case separately
	//why exception?
	
}
