package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.CompanyDoesntExistException;
import exceptions.CouponNotFoundException;
import exceptions.CouponTitleDupliactionException;
import exceptions.NoSuchCouponsCategoryException;
import exceptions.deleteCouponException;
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

	public CompanyFacade() {

	}

	@Override
	public boolean login(String email, String password) throws SQLException,
	CompanyDoesntExistException {
		if (compDBD.isCompanyExists(email, password)) {// why?
			for (Company comp : compDBD.getAllCompanies()) {
				if (comp.getEmail().equals(email)
						&& comp.getPassword().equals(password)) {
					id = comp.getId();
					return true;
				}

			}
		}
		return false;
	}

	public void addCoupon(Coupon coup) throws SQLException,
	CouponTitleDupliactionException {
		for (Coupon coupon : coupDBD.getCouponsByCompanyId(id)) {
			if ((coupon.getTitle().equals(coup.getTitle()))) {
				throw new CouponTitleDupliactionException();

			}

		}
		coupDBD.addCoupon(coup);
	}

	public void updateCoupon(Coupon coup) throws SQLException,
	unchangableCompanyIdException {
		if (coup.getCompanyID() == id)
			coupDBD.updateCoupon(coup);

		else
			throw new unchangableCompanyIdException();

	}

	public void deleteCoupon(int couponId) throws deleteCouponException, SQLException, CouponNotFoundException {
		for (Customer customer : custDBD.getAllcustomers()) {//check
			for (Coupon coupon : coupDBD.getCouponsByCustomerId(customer
					.getId())) {
				
				if (couponId == coupon.getId())
				
				{
					coupDBD.deleteCouponPurchase(customer.getId(), couponId);
				}
			}

		}
		coupDBD.deleteCoupon(couponId);

	}

	public ArrayList<Coupon> getCompanyCoupons() throws SQLException {
		return coupDBD.getCouponsByCompanyId(id);
	}

	public ArrayList<Coupon> getCompanyCoupons(Category category)
			throws SQLException, NoSuchCouponsCategoryException {// overloading.//method
		// signature=name+parameters
		ArrayList<Coupon> couponsByCategory = new ArrayList<Coupon>();//?ask+customer category
		for (Coupon coupon : coupDBD.getCouponsByCompanyId(id)) {
			if (((coupDBD.getOneCoupn(coupon.getId()).getCategory().name()).compareTo(category.name())) == 0) {
				couponsByCategory.add(coupDBD.getOneCoupn(coupon.getId()));// add
				
			}

		}
		 if(couponsByCategory.size()==0)throw new NoSuchCouponsCategoryException();
		 else return couponsByCategory;
	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice)
			throws SQLException, maxPriceException {
		ArrayList<Coupon> maxPriceCoupons = new ArrayList<Coupon>();
		for (Coupon coupon : coupDBD.getCouponsByCompanyId(id)) {
			if (coupDBD.getOneCoupn(coupon.getId()).getPrice() <= maxPrice) {//why??
				maxPriceCoupons.add(coupDBD.getOneCoupn(coupon.getId()));
			}
			 
		}
		if (maxPriceCoupons.isEmpty()) {
			throw new maxPriceException();
		} else
			return maxPriceCoupons;

	}

	public Company getCompanyDetails() throws SQLException {
		Company comp = compDBD.getOneCompany(id);
		comp.setCoupons(coupDBD.getCouponsByCompanyId(id));
		return compDBD.getOneCompany(comp.getId());//ask?why coupons are null here?
		//check with adminFacade

	}

}
