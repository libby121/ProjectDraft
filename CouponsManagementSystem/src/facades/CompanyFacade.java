package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.CompanyDoesntExistException;
import exceptions.CouponTitleDupliactionException;
import exceptions.maxPriceException;
import exceptions.unchangableCompanyIdException;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

public class CompanyFacade extends Facade {

	private int id;



	public CompanyFacade(int id) {

		this.id = id;
	}
	public CompanyFacade( ) {


	}
	@Override
	public boolean login(String email, String password) throws SQLException, CompanyDoesntExistException {
		if(compDBD.isCompanyExists(email, password)){//why?
			for (Company comp : compDBD.getAllCompanies()) {
				if(comp.getEmail().equals(email)&&comp.getPassword().equals(password)){
					id=comp.getId();
					return true;
				}

			}
		}//else throw  new CompanyDoesntExistException();//difference between throw and new. printing matters?
		//here?
		return false;
	}
	//what if i try to add to not existing company id
	//can a company go through all coupons table
	//do i need to go through all coupons?	
	public void addCoupon(Coupon coup) throws SQLException, CouponTitleDupliactionException{//run through one company's coupons and through all companies coupons
		for (Coupon coupon : coupDBD.getCouponsByCompanyId(id)) {//how can i call a method from this class
			//? if(coupon.getCompanyId==(coup.getCompanyID())){//comdbd.getid

			if((coupon.getTitle().equals(coup.getTitle()))){
				throw new CouponTitleDupliactionException();


			} 
			
		}coupDBD.addCoupon(coup);
	}

	public void updateCoupon(Coupon coup) throws SQLException, unchangableCompanyIdException{
		if(coup.getCompanyID()==id)
		coupDBD.updateCoupon(coup);//do i need while loop here
		
		else throw new unchangableCompanyIdException();

		}

	

	public void deleteCoupon(int couponId) throws SQLException{

		coupDBD.deleteCoupon(couponId);
		//is it needed/allowed to check all customers or only the company customers?
		for (Customer customer : custDBD.getAllcustomers()) {
			for (Coupon coupon : customer.getCoupons()) {
				if(couponId==coupon.getId()){
					coupDBD.deleteCouponPurchase(customer.getId(), couponId);
				}
			}

		}

	}


	public ArrayList<Coupon> getCompanyCoupons() throws SQLException{
		//is it better here or in dbdao
		return coupDBD.getCouponsByCompanyId(id);
	}

	public ArrayList<Coupon>getCompanyCoupons(Category category) throws SQLException{//overloading.//method signature=name+parameters
		ArrayList<Coupon>couponsByCategory=new ArrayList<Coupon>();
		for (Coupon coupon : coupDBD.getCouponsByCompanyId(id)) {
			if((coupon.getCategory().compareTo(category))==0){
				couponsByCategory.add(coupon);
			}

		}
		return couponsByCategory;
	}

	public ArrayList<Coupon>getCompanyCoupons(double maxPrice) throws SQLException, maxPriceException{
		ArrayList<Coupon>maxPriceCoupons=new ArrayList<Coupon>();
		for (Coupon coupon : coupDBD.getCouponsByCompanyId(id)) {
			if(coupon.getPrice()<=maxPrice){
				maxPriceCoupons.add(coupon);
			}
			if(maxPriceCoupons.isEmpty())throw new maxPriceException();//why here
			return null;	
		}

		return maxPriceCoupons;


	}

	public Company getCompanyDetails() throws SQLException{
		Company comp= compDBD.getOneCompany(id);
		comp.setCoupons(coupDBD.getCouponsByCompanyId(id));
		return comp;
		//return coupDBD.getCouponsByCompanyId(id);//cannot be two returns
		//return compDBD.getOneCompany(id);

	}





}	
