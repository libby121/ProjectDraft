package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Category;
import beans.Coupon;

public class CouponDBDAO implements CouponDAO {

	// public CouponDBDAO(){} how come i dont need a ctor?

	private ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image,afterDiscount)values(?,?,?,?,?,?,?,?,?,?)");
			stmt.setInt(1, coupon.getCompanyID());
			stmt.setInt(2, coupon.getCategory().ordinal() + 1);
			stmt.setString(3, coupon.getTitle());
			stmt.setString(4, coupon.getDescription());
			stmt.setDate(5, coupon.getStartDate());
			stmt.setDate(6, coupon.getEndDate());
			stmt.setInt(7, coupon.getAmount());
			stmt.setDouble(8, coupon.getPrice());
			stmt.setString(9, coupon.getImage());
			stmt.setInt(11, coupon.isSalePrice()==true?1:0);

			stmt.execute();

			System.out.println("coupon added!");
		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		Connection con = pool.getConnection();// cannot get the id from obj.
		// doesnt override the coupon-ok
		// to i need to keep it possible to change company id?
		try {
			String query="update coupons set company_id=?,category_id=?, title=?,description=?,start_date=?,end_date=?,amount=?,price=?,image=?,afterDiscount=? where id=?";
			PreparedStatement stmt = con
					.prepareStatement(query);
			stmt.setInt(1, coupon.getCompanyID());
			stmt.setInt(2, coupon.getCategory().ordinal() + 1);
			stmt.setString(3, coupon.getTitle());
			stmt.setString(4, coupon.getDescription());
			stmt.setDate(5, coupon.getStartDate());
			stmt.setDate(6, coupon.getEndDate());
			stmt.setInt(7, coupon.getAmount());
			stmt.setDouble(8, coupon.getPrice());
			stmt.setString(9, coupon.getImage());
			
			stmt.setInt(10, coupon.isSalePrice()==true?1:0);
			stmt.setInt(11, coupon.getId());

			stmt.execute();
			// System.out.println(coupon.getId());
			System.out.println("coupon updated!");
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCoupon(int id) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("delete from coupons where id= " + id);
			stmt.execute();

			 System.out.println("coupon deleted!");

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public ArrayList<Coupon> getAllcoupons() throws SQLException {
		Connection con = pool.getConnection();
		try {
			ArrayList<Coupon> coupns = new ArrayList<Coupon>();
			PreparedStatement stmt = con
					.prepareStatement("select * from coupons join Categories on coupons.category_id=categories.id ");
			ResultSet et = stmt.executeQuery();
			while (et.next()) {
				
				coupns.add(new Coupon(et.getInt("id"), et.getInt("COMPANY_ID"),
						Category.values()[et.getInt("CATEGORY_ID") - 1], et
								.getString("title"), et
								.getString("description"), et
								.getDate("start_date"), et.getDate("END_DATE"),
						et.getInt("amount"), et.getDouble("price"), et
								.getString("image"),et.getInt("afterDiscount")==1?true:false));
			}
			 
			return coupns;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public Coupon getOneCoupn(int id) throws SQLException {
		Connection con = pool.getConnection();
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from coupons where id=" + id);
			ResultSet et = stmt.executeQuery();
			if (et.last()) {
				Coupon coup = new Coupon(et.getInt("id"),
						et.getInt("company_id"),
						Category.values()[et.getInt("category_id") - 1],
						et.getString("title"), et.getString("description"),
						et.getDate("start_date"), et.getDate("end_date"),
						et.getInt("amount"), et.getDouble("price"),
						et.getString("image"));
				// System.out.println(coup);
				return coup;

			}

		} finally {
			pool.restoreConnection(con);
		}
		return null;

	}

	@Override
	public void addCouponPurchase(int CustomerId, int CouponId)
			throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into customers_vs_coupons (customerID,couponID) value(?,?) ");
			stmt.setInt(1, CustomerId);
			stmt.setInt(2, CouponId);
			stmt.execute();
			System.out.println("a purchase added to table!");
			// amount+balance
			// d coupon amount

		} finally {
			pool.restoreConnection(con);
		}

	}



	// must it be in the interface? does it belong here?
	public ArrayList<Coupon> getCouponsByCompanyId(int CompanyId)
			throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from coupons where COMPANY_ID="+CompanyId);
						
			ResultSet st = stmt.executeQuery();
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			while (st.next()) {

				coupons.add(new Coupon(st.getInt("ID"),
						st.getInt("company_Id"), Category.values()[st.getInt("category_id")-1], st.getString("title"), st
								.getString("description"), st
								.getDate("start_date"), st.getDate("end_date"),
						st.getInt("amount"), st.getDouble("price"), st
								.getString("image")));// is it correct?
			}

			return coupons;

		} finally {
			pool.restoreConnection(con);
		}

	}

	public ArrayList<Coupon> getCouponsByCustomerId(int customerId)
			throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from coupons join customers_vs_coupons on coupons.id=customers_vs_coupons.couponID where CustomerID="
							+ customerId);
			ResultSet st = stmt.executeQuery();
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			while (st.next()) {
				coupons.add(new Coupon(st.getInt("ID"),
						st.getInt("company_Id"), Category.values()[st
								.getInt("category_id") - 1], st
								.getString("title"), st
								.getString("description"), st
								.getDate("start_date"), st.getDate("end_date"),
						st.getInt("amount"), st.getDouble("price"), st
								.getString("image")));// is it correct?

			}
			// System.out.println(coupons);
			return coupons;
		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCouponPurchase(int CustomerId, int CouponId)
			throws SQLException {
		Connection con = pool.getConnection();
		try{
		PreparedStatement stmt = con
				.prepareStatement("delete from customers_vs_coupons where customerId="
						+ CustomerId + " and CouponId=" + CouponId);
		stmt.execute();
		System.out.println("a purchase deleted from table");
	}finally{pool.restoreConnection(con);

}}
	public void deleteCouponPurchase(int couponId) throws SQLException{	
	Connection con=pool.getConnection();
	try{
		PreparedStatement stmt=con.prepareStatement("delete from customers_vs_coupons where couponID="+couponId);
		stmt.execute();
		
	}finally{pool.restoreConnection(con);}
	}
	
	
	
	}
