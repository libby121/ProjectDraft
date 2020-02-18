package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Coupon;

public interface CouponDAO {

	void addCoupon(Coupon coupon)throws SQLException;
	void updateCoupon(Coupon coupon)throws SQLException;
	void deleteCoupon(int id)throws SQLException;
	ArrayList<Coupon>getAllcoupons()throws SQLException;
	Coupon getOneCoupn(int id)throws SQLException;
	void addCouponPurchase(int CustomerId,int CouponId)throws SQLException;
	void deleteCouponPurchase(int CustomerId,int CouponId)throws SQLException;
	void deleteCouponPurchase(int id) throws SQLException;
	
	
}
