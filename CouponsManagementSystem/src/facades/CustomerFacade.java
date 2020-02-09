package facades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import exceptions.CompanyDoesntExistException;
import exceptions.CouponExpiredException;
import exceptions.couponOutOfStockException;
import exceptions.couponPurchaseDuplication;
import exceptions.maxPriceException;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

public class CustomerFacade extends Facade {

	private int id;

	Calendar cal=Calendar.getInstance();



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
			if(coupon.getId()==coup.getId())throw new couponPurchaseDuplication();
			if(coup.getAmount()==0)throw new couponOutOfStockException();//else if

			if(coup.getEndDate().after(cal.getTime()))throw new CouponExpiredException();

			else coupDBD.addCouponPurchase(id, coup.getId());//here?

		}


	}

	public ArrayList<Coupon>getCustomerCoupons() throws SQLException{

		return coupDBD.getCouponsByCustomerId(id);
	}

	public ArrayList<Coupon>getCustomerCoupons(Category category) throws SQLException{//i cant call to the same method name?
		ArrayList<Coupon>couponsByCategory=new ArrayList<Coupon>();
		for (Coupon coup : getCustomerCoupons()) {//whats that??compare to spring @bean jbt
			if(coup.getCategory().equals(category)){//?
				couponsByCategory.add(coup);
			}

		}
		return couponsByCategory;
	}
	public ArrayList<Coupon>getCustomerCoupons(double maxPrice) throws maxPriceException{	
		ArrayList<Coupon>couponsUpToMaxPrice=new ArrayList<Coupon>();
		for (Coupon coupon : couponsUpToMaxPrice) {
			if(coupon.getPrice()<=maxPrice)
				couponsUpToMaxPrice.add(coupon);
			
		}
		if(!couponsUpToMaxPrice.isEmpty())
		return couponsUpToMaxPrice;
		else throw new maxPriceException();
	}
	
	public Customer getCustomerDetails() throws SQLException{
		Customer cust=custDBD.getOneCustmer(id);
		cust.setCoupons(cust.getCoupons());
		return cust;
	}
}