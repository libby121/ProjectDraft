package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.CompanyDBDAO;
import exceptions.CompanyExistsException;
import exceptions.CustomerExistsException;

public class AdminFacade extends Facade {
	
	public AdminFacade(){//why do i need ctor?
	}
	

	// private CompanyDBDAO compDBD = new CompanyDBDAO();


	//whos the admin?


	public boolean login(String email, String password) throws SQLException {
		if (email.equals("com.admin@admin") && password.equals("admain")) {
			
			return true;
		}
		return false;
	}

	public void addCompany(Company company) throws SQLException,
	CompanyExistsException {

		ArrayList<Company> companies = new ArrayList<Company>();// how do they
		// all know
		// company..
		// because dbdao uses this bean?
		for (Company compa : companies) {
			if ((compa.getName().equals(company.getName()))

					|| compa.getEmail().equalsIgnoreCase(company.getEmail())) {
				throw new CompanyExistsException();
			} else
				compDBD.addCompany(company);// identifies the refernce
		}

	}



	public void updateCompany(Company company)throws SQLException{//why cant i update id?
//		Company temp=compDBD.getOneCompany(company.getId());
//		String pass=temp.getPassword();
//		company.setPassword(pass);
//		compDBD.updateCompany(company);
		//		int id=company.getId();
		//		compDBD.updateCompany(company);
		//		
		//		if(id!=company.getId()){
		//			throw UnchangableIdException
		//		}
	compDBD.updateCompany(company);
		
	}

	public void deleteCompany(int id) throws SQLException {
		compDBD.deleteCompany(id);

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCompanyID() == id) {
				coupDBD.deleteCoupon(coupon.getId());

			}
			ArrayList<Customer> customers = new ArrayList<Customer>();
			for (Customer customer : customers) {
				for (Coupon coup : customer.getCoupons()) {//?
					if (coup.getCompanyID() == id) {
						coupDBD.deleteCouponPurchase(customer.getId(),
								coup.getId());

					}

				}

			}
		}

	}


	public ArrayList<Company>getAllCompanies() throws SQLException{
		//ArrayList<Company>companie=new ArrayList<Company>();//why do i need that line?
		return compDBD.getAllCompanies();

	}



	public Company getOneCompany(int id) throws SQLException{

		return	compDBD.getOneCompany(id);

	}

	public void addCustomer(Customer customr) throws SQLException, CustomerExistsException{
		ArrayList<Customer>customers=new ArrayList<Customer>();
		for (Customer customer1 : customers) {
			if(customer1.getEmail().equals(customer1.getEmail())){
				throw new CustomerExistsException();
			}else custDBD.addCustomer(customr);
		}


	}
	public void updateCustomer(Customer customer) throws SQLException{

		custDBD.updateCustomer(customer);
	}

	public ArrayList<Customer> getAllCustomers() throws SQLException{
		return custDBD.getAllcustomers();}}
//		ArrayList<Customer>allCustomers=new ArrayList<Customer>();
//		for (Company company : compDBD.getAllCompanies()) {//DO I GO BY COMPANY OR BY CUSTDB
//			for (Customer customer : custDBD.getAllcustomers()) {//is it connected to the external for
//				allCustomers.add(customer);

	

//get customers by company



