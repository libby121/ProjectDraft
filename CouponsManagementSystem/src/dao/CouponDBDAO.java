package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Category;
import beans.Coupon;

public class CouponDBDAO implements CouponDAO {
	
	//public CouponDBDAO(){} how come i dont need a ctor?

	private ConnectionPool pool= ConnectionPool.getInstance();
	@Override
	public void addCoupon(Coupon coupon) throws SQLException{
	Connection con=pool.getConnection();
	try{
		PreparedStatement stmt = con.prepareStatement("insert into coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image)values(?,?,?,?,?,?,?,?,?)");
		stmt.setInt(1, coupon.getCompanyID());
		stmt.setInt(2, coupon.getCategory().ordinal()+1);
		stmt.setString(3, coupon.getTitle());
		stmt.setString(4, coupon.getDescription());
		stmt.setDate(5, coupon.getStartDate());
		stmt.setDate(6, coupon.getEndDate());
		stmt.setInt(7, coupon.getAmount());
		stmt.setDouble(8, coupon.getPrice());
		stmt.setString(9, coupon.getImage());
		
		stmt.execute();
		
		System.out.println("coupon added!");
	}finally{pool.restoreConnection(con);}
	}

	@Override
	public void updateCoupon(Coupon coupon)throws SQLException{
	Connection con= pool.getConnection();// cannot get the id from obj. doesnt override the coupon-ok
	//to i need to keep it possible to change company id?
	try{
		PreparedStatement stmt= con.prepareStatement("update coupons set company_id=?,category_id=?, title=?,description=?,start_date=?,end_date=?,amount=?,price=?,image=? where id=?");
		stmt.setInt(1,coupon.getCompanyID());
		stmt.setInt(2,coupon.getCategory().ordinal()+1);
		stmt.setString(3,coupon.getTitle());
		stmt.setString(4,coupon.getDescription());
		stmt.setDate(5,coupon.getStartDate());
		stmt.setDate(6,coupon.getEndDate());
		stmt.setInt(7,coupon.getAmount());
		stmt.setDouble(8,coupon.getPrice());
		stmt.setString(9,coupon.getImage());
		stmt.setInt(10,coupon.getId());
	
		stmt.execute();
	System.out.println(coupon.getId());
		System.out.println("coupon updated!");
	}finally{ pool.restoreConnection(con);}
	

	}

	@Override
	public void deleteCoupon(int id) throws SQLException{
		Connection con=pool.getConnection();
		try{
			PreparedStatement stmt= con.prepareStatement("delete from coupons where id= "+id);
			stmt.execute();
			
			System.out.println("coupon deleted!");
			
		}finally {pool.restoreConnection(con);}

	}

	@Override
	public ArrayList<Coupon> getAllcoupons() throws SQLException {
		Connection con=pool.getConnection();
		try{
			ArrayList<Coupon>coupns=new ArrayList<Coupon>();
			PreparedStatement stmt= con.prepareStatement("select * from coupons join Categories on coupons.category_id=categories.id ");
			ResultSet et= stmt.executeQuery();
			while(et.next()){
				coupns.add(new Coupon(et.getInt("id"), et.getInt("COMPANY_ID"),Category.values()[et.getInt("CATEGORY_ID")-1] , et.getString("title"), et.getString("description"), et.getDate("start_date"), et.getDate("END_DATE"), et.getInt("amount"), et.getDouble("price"), et.getString("image")));
			}
			System.out.println(coupns);
			return coupns;
			
			
		}finally{pool.restoreConnection(con);}//why doesnt he ask for return null
	}

	@Override
	public Coupon getOneCoupn(int id) throws SQLException{
	Connection con=pool.getConnection();
	try{
		
		PreparedStatement stmt= con.prepareStatement("select * from coupons where id="+id);
		ResultSet et =stmt.executeQuery();
		if(et.last()){
			Coupon coup = new Coupon(et.getInt("id"), et.getInt("company_id"), Category.values()[et.getInt("category_id")], et.getString("title"),et.getString("description"), et.getDate("start_date"), et.getDate("end_date"), et.getInt("amount"), et.getDouble("price"), et.getString("image"));
			System.out.println(coup);
			return coup;
			
		
		}
		
	}finally{pool.restoreConnection(con);}
		return null;
		
		
	}

	@Override
	public void addCouponPurchase(int CustomerId, int CouponId)throws SQLException {
		Connection con=pool.getConnection();
		try{
		PreparedStatement stmt=con.prepareStatement("insert into customers_vs_coupons (customerID,couponID) value(?,?) ");
		stmt.setInt(1, CustomerId);
		stmt.setInt(2, CouponId);
		stmt.execute();
		System.out.println("a purchase added to table!");
			
		
		}finally{pool.restoreConnection(con);}
		

		
			
		}
	//must it be in the interface? does it belong here?
	public ArrayList<Coupon>getCouponsByCompanyId(int CompanyId) throws SQLException{
		Connection con=pool.getConnection();
		try{
			PreparedStatement stmt= con.prepareStatement("select coupons.id , categories.name , coupons.title from coupons join categories on coupons.CATEGORY_ID=categories.id where COMPANY_ID="+CompanyId);
			ResultSet st= stmt.executeQuery();
		ArrayList<Coupon>coupons=new ArrayList<Coupon>();
			while(st.next()){
				
					coupons.add(new Coupon(st.getInt("ID"),Category.valueOf(st.getString("name")),st.getString("title")));//is it correct?
				}
			 for (Coupon coupon : coupons) {
				 System.out.println(coupon.toPrint());//can i do it differently?
				
			}
			return coupons;
				
		}finally{pool.restoreConnection(con);}
			
			
		}
		
	
	
	public ArrayList<Coupon>getCouponsByCustomerId(int customerId) throws SQLException{
		Connection con= pool.getConnection();
		try{
			PreparedStatement stmt= con.prepareStatement("select customers_vs_coupons.couponID,coupons.title,coupons.CATEGORY_ID from customers_vs_coupons join coupons on coupons.id=customers_vs_coupons.couponID where customerID= "+customerId);
			ResultSet st=stmt.executeQuery();
			ArrayList<Coupon>coupons=new ArrayList<Coupon>();
			while(st.next()){
				coupons.add(new Coupon(st.getInt("couponID"),Category.values()[(st.getInt("category_id"))],st.getString("title")));
			
			}
			System.out.println(coupons);
			return coupons;
		}finally{pool.restoreConnection(con);}
	}
	

	@Override
	public void deleteCouponPurchase(int CustomerId, int CouponId) throws SQLException{
		 Connection con= pool.getConnection();
		 PreparedStatement stmt= con.prepareStatement("delete from customers_vs_coupons where customerId="+CustomerId+" and CouponId="+CouponId);
		 stmt.execute();
		 System.out.println("a purchase deleted from table");
	}

}
