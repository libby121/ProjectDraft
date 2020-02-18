package Tests;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CompanyDBDAO;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import exceptions.AccessDeniedException;
import exceptions.AdminAccessDeniedException;
import exceptions.CompanyAccessDeniedException;
import exceptions.CompanyDoesntExistException;
import exceptions.CompanyExistsException;
import exceptions.CouponExpiredException;
import exceptions.CouponTitleDupliactionException;
import exceptions.CustomerAccessDeniedException;
import exceptions.CustomerExistsException;
import exceptions.NoSuchCouponsCategoryException;
import exceptions.couponOutOfStockException;
import exceptions.couponPurchaseDuplication;
import exceptions.maxPriceException;
import exceptions.unchangableCompanyIdException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.Facade;
import Login.ClientType;
import Login.LoginManager;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

public class TempMain {

	public static void main(String[] args) throws SQLException, AdminAccessDeniedException, CompanyDoesntExistException, CompanyAccessDeniedException, CustomerAccessDeniedException, AccessDeniedException, CouponTitleDupliactionException, maxPriceException, NoSuchCouponsCategoryException, unchangableCompanyIdException, couponPurchaseDuplication, couponOutOfStockException, CouponExpiredException, CompanyExistsException, CustomerExistsException {
		
		CompanyDBDAO compDBD=new CompanyDBDAO();
		CouponDBDAO coupDBD=new CouponDBDAO();
		AdminFacade adminF=new AdminFacade();
		LoginManager lo=LoginManager.getInstance();
		Facade facade=lo.login("com.admin@admin", "admain", ClientType.Administrator);
		if(facade instanceof AdminFacade){
			if (facade instanceof AdminFacade) {
				AdminFacade admin = ((AdminFacade) facade);
			//	admin.addCompany(new Company("company17", "company17@", "12345"));
			
		//admin.addCompany(new Company("123company", "123company@", "12345"));//adding automatically password?
		//admin.addCustomer(new Customer("moshe", "cohen", "moshe@"));
			//admin.deleteCompany(3);
			//	compDBD.deleteCompany(id);
			//admin.addCustomer(new Customer("keren", "dovkin", "moshe@"));
				//admin.updateCompany(new Company(5, "febCompan", "feb@", "updated"));
//			for (Customer company : admin.getAllCustomers()) {
//				System.out.println(company);
//			}
				admin.addCompany(new Company("company", "company@new",
						"12333"));
				//System.out.println("added");
			//	System.out.println(admin.getOneCompany(1).getCoupons());//???
				
			}
			
			
		}}}
		
	
			
			
			
	