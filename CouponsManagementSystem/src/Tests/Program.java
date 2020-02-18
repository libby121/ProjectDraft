package Tests;

import java.sql.Date;
import java.sql.SQLException;

import dao.ConnectionPool;
import dao.CouponDBDAO;
import jobThread.CouponExpirationDailyJob;
import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.AccessDeniedException;
import exceptions.AdminAccessDeniedException;
import exceptions.CompanyAccessDeniedException;
import exceptions.CompanyDoesntExistException;
import exceptions.CompanyExistsException;
import exceptions.CouponDeleteException;
import exceptions.CouponExpiredException;
import exceptions.CouponNotFoundException;
import exceptions.CouponTitleDupliactionException;
import exceptions.CustomerAccessDeniedException;
import exceptions.NoSuchCouponsCategoryException;
import exceptions.couponOutOfStockException;
import exceptions.couponPurchaseDuplication;
import exceptions.deleteCouponException;
import exceptions.maxPriceException;
import exceptions.unchangableCompanyIdException;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;
import facades.Facade;
import Login.ClientType;
import Login.LoginManager;

public class Program {

	public static void main(String[] args) {


		testAll();




	}

	public static void testAll() {
		ConnectionPool pool=ConnectionPool.getInstance();
		CouponExpirationDailyJob Thread = new CouponExpirationDailyJob();
		try{
			Thread.start();

			System.out.println("test");
			CouponDBDAO coupDBD = new CouponDBDAO();


			LoginManager loginManager = LoginManager.getInstance();

			Facade facade = loginManager.login("com.admin@admin", "admain",
					ClientType.Administrator);
			if (facade instanceof AdminFacade) {
				AdminFacade admin = ((AdminFacade) facade);
				System.out.println("arrived to first facade");
				admin.addCompany(new Company("company", "company@new",
						"12333"));
				System.out.println("added");
				admin.deleteCompany(25);
				System.out.println(admin.getOneCompany(21));//check again
				for (Company c : admin.getAllCompanies()) {//check abut
					//coupons sostring
					System.out.println(c);
				}}
				Facade facade2 = loginManager.login("hi", "hi",
						ClientType.Company);
				if (facade2 instanceof CompanyFacade) {
					CompanyFacade companyFacade = ((CompanyFacade) facade2);
					System.out.println("arrived to second");
					companyFacade.deleteCoupon(93);
					companyFacade.addCoupon(new Coupon(1, Category.spa,
							"sparare23223", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2020-02-15"), 200, 234.6, "photo"));
					companyFacade.addCoupon(new Coupon(2, Category.spa,
							"spaare121", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2020-02-15"), 200, 234.6, "photo"));
					companyFacade.addCoupon(new Coupon(3, Category.spa,
							"spaare1321", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2020-01-15"), 200, 234.6, "photo"));
					companyFacade.addCoupon(new Coupon(4, Category.spa,
							"spaare1223", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2020-01-14"), 200, 234.6, "photo"));
					companyFacade.addCoupon(new Coupon(1, Category.spa,
							"spaare11121", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2020-01-15"), 200, 234.6, "photo"));
					companyFacade.addCoupon(new Coupon(1, Category.spa,
							"spaare90920", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2019-09-15"), 200, 234.6, "photo"));
					companyFacade.addCoupon(new Coupon(1, Category.spa,
							"spaare892", "nice spa", Date.valueOf("2020-02-15"),
							Date.valueOf("2019-2-15"), 200, 234.6, "photo"));

					System.out.println(companyFacade.getCompanyDetails());
					for (Coupon coupon : companyFacade.getCompanyCoupons()) {
						System.out.println(coupon);
					}
					companyFacade.deleteCoupon(68); //allows me to delete from
					//different company. what if coupon is deleted?
					companyFacade.updateCoupon(new Coupon(91, 1, Category.spa,
							"good night", "description", Date.valueOf("2020-03-25"),
							Date.valueOf("2020-03-28"), 789, 23.4,"image"));

					for (Coupon coupon :
						companyFacade.getCompanyCoupons(Category.spa)) {
						System.out.println(coupon);
					}

					for (Coupon coupon : companyFacade.getCompanyCoupons(250)) {
						System.out.println(coupon);
					}
				}
					Facade facade3 = loginManager.login("@yaffa.com", "1567",
							ClientType.Customer);
					if (facade3 instanceof CustomerFacade) {
						CustomerFacade customerFacade = ((CustomerFacade) facade3);
						System.out.println("arrived to third");
						customerFacade.purchaseCoupon(coupDBD.getOneCoupn(95));
						for (Coupon coupon :
							customerFacade.getCustomerCoupons()) {
							System.out.println(coupon);

							for (Coupon coup :
								customerFacade.getCustomerCoupons(Category.spa)) {
								System.out.println(coup);
							}

							for (Coupon cou : customerFacade
									.getCustomerCoupons(10.5)) {
								System.out.println(cou);// check why doesnt show the
								// message

								System.out.println(customerFacade
										.getCustomerDetails());
							}
						}
					}

					
					Thread.JobStop();


					pool.closeAllConnections();


				} catch (SQLException | AdminAccessDeniedException
						| CompanyDoesntExistException | CompanyAccessDeniedException
						| AccessDeniedException | CustomerAccessDeniedException
						| deleteCouponException | CouponNotFoundException | CompanyExistsException | maxPriceException | unchangableCompanyIdException | couponPurchaseDuplication | couponOutOfStockException | CouponExpiredException | NoSuchCouponsCategoryException | CouponTitleDupliactionException e) {

				System.err.println(e.getMessage());
				}//finally{pool.closeAllConnections();}







}}


