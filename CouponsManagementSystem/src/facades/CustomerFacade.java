package facades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import exceptions.CompanyDoesntExistException;
import exceptions.CouponExpiredException;
import exceptions.couponOutOfStockException;
import exceptions.couponPurchaseDuplication;
import exceptions.maxPriceException;
import exceptions.unchangableCompanyIdException;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

public class CustomerFacade extends Facade {

	private int id;

	public CustomerFacade() {

	}

	@Override
	public boolean login(String email, String password) throws SQLException,
	CompanyDoesntExistException {
		//if i add here the is exists validation- do i need the exception?
		for (Customer cust : custDBD.getAllcustomers()) {
			if(cust.getEmail().equalsIgnoreCase(email)&&cust.getPassword().equals(password)){
				id=cust.getId();
				return true;
			}//else throw new Exception
		}return false;
	}


	public void purchaseCoupon(Coupon coup) throws SQLException, couponPurchaseDuplication, couponOutOfStockException, CouponExpiredException{
		for (Coupon coupon : coupDBD.getCouponsByCustomerId(id)) {
			Calendar cal=Calendar.getInstance();

			if(coupon.getId()==coup.getId())throw new couponPurchaseDuplication();
			if(coup.getAmount()==0)throw new couponOutOfStockException();

			if(coup.getEndDate().before(cal.getTime()))throw new CouponExpiredException();

		} coupDBD.addCouponPurchase(id, coup.getId());//?ask check method if's
		coup.setAmount(coup.getAmount()-1);// i updated the coup amount  
		coupDBD.updateCoupon(coup);//null pointer with ?


		System.out.println("db: "+coupDBD.getOneCoupn(coup.getId()).getAmount());
		System.out.println(coup.getAmount());



		Company c=compDBD.getOneCompany(coup.getCompanyID());//supposed to be in customer facade?
		compDBD.getOneCompany(c.getId()).setEmail("emailtest?");
		//		c.setEmail //why only this way?
		//		//.setBalance(balance+coup.getPrice());
		compDBD.updateCompany(c);


	}




	public ArrayList<Coupon>getCustomerCoupons() throws SQLException{

		return coupDBD.getCouponsByCustomerId(id);
	}

	public ArrayList<Coupon>getCustomerCoupons(Category category) throws SQLException{
		ArrayList<Coupon>couponsByCategory=new ArrayList<Coupon>();
		for (Coupon coup : getCustomerCoupons()) {
			if(coupDBD.getOneCoupn(coup.getId()).getCategory().equals(category)){//?
				couponsByCategory.add(coupDBD.getOneCoupn(coup.getId()));
			}

		}
		return couponsByCategory;
	}
	public ArrayList<Coupon>getCustomerCoupons(double maxPrice) throws maxPriceException, SQLException{	
		ArrayList<Coupon>couponsUpToMaxPrice=new ArrayList<Coupon>();
		for (Coupon coupon : getCustomerCoupons()) {
			if(coupon.getPrice()<=maxPrice)
				couponsUpToMaxPrice.add(coupDBD.getOneCoupn(coupon.getId()));

		}
		if(!couponsUpToMaxPrice.isEmpty())
			return couponsUpToMaxPrice;
		else System.out.println("eroor");
		throw new maxPriceException();
	}

	public Customer getCustomerDetails() throws SQLException{
		Customer cust=custDBD.getOneCustmer(id);
		cust.setCoupons(cust.getCoupons());
		return cust;
	}
}