package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Category;
import beans.Company;
import beans.Coupon;

public class CompanyDBDAO implements CompanyDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();// general var
	// for the class

	@Override
	public void addCompany(Company company) throws SQLException {// dont know
		// yet how ill handle the exception. treatment will be in main
		Connection con = pool.getConnection();// call the method to get one
		// connection from list(wait if needed)
			try {
			PreparedStatement stmt = con 
					.prepareStatement("insert into companies(name, email,password)values(?,?,?)");
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());

			stmt.execute();

		} finally {
			pool.restoreConnection(con);// after i use the connection i restore
			// it to the list for further usage
		}

	}

	@Override
	public void updateCompany(Company company) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con//cannot update the name of company
					.prepareStatement("update companies set email=?,password=? where id="
							+ company.getId());
			stmt.setString(1, company.getEmail());
			stmt.setString(2, company.getPassword());

			stmt.execute();
			System.out.println("updated company");
		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCompany(int id) throws SQLException {
	Connection con= pool.getConnection();
	try{
		PreparedStatement stmt= con.prepareStatement("delete from companies where id="+id);
		stmt.execute();
		
	}finally{pool.restoreConnection(con);}
	}

	@Override
	public ArrayList<Company> getAllCompanies() throws SQLException {
		Connection con = pool.getConnection();
		try {
			ArrayList<Company> companies = new ArrayList<Company>();
			PreparedStatement stmt = con
					.prepareStatement("select * from companies");
			ResultSet et = stmt.executeQuery();
			while (et.next()) {
				companies.add(new Company(et.getInt("id"),
						et.getString("name"), et.getString("email"), et
						.getString("password")));

			}
			return companies;

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public Company getOneCompany(int id) throws SQLException{
		Connection con=pool.getConnection();
		try{
			PreparedStatement stmt= con.prepareStatement("select * from companies where id="+id);
			ResultSet et= stmt.executeQuery();
			if(et.last()){
				Company company=new Company(id,et.getString("name"),et.getString("email"),et.getString("password"));
				System.out.println(company);
				return company;
			
			}else return null;//print will be in main
			
		}finally{pool.restoreConnection(con);}//why in object i dont need to return anything after the finally,but in boolean i do?
		
	}	
		public Company getOneCompanyCoupons(int id) throws SQLException{
			Connection con=pool.getConnection();
			try{
				PreparedStatement stmt= con.prepareStatement("select companies.name,companies.email companies.password,coupons. where id="+id);
				ResultSet et= stmt.executeQuery();
				if(et.last()){
					Company company=new Company(id,et.getString("name"),et.getString("email"),et.getString("password"));
					System.out.println(company);
					return company;
					
				}else return null; 
				
			}finally{pool.restoreConnection(con);}//why in object i dont need to return anything after the finally,but in boolean i do?
			
			
	}
		public  Company getCouponsByCompanyId(int CompanyId) throws SQLException{//cannot resolve in the same table
			Connection con=pool.getConnection();
			Company company=new Company();
			try{
				PreparedStatement stmt= con.prepareStatement("select companies.name, companies.email, companies.password, coupons.id , categories.name , coupons.title from coupons join categories on coupons.CATEGORY_ID=categories.id where COMPANY_ID="+CompanyId);
				ResultSet st= stmt.executeQuery();
				ArrayList<Coupon>coupons=new ArrayList<Coupon>();
				while(st.next()){
					System.out.println("ye");
					company=new Company(CompanyId,st.getString("name"),st.getString("email"),st.getString("password"),coupons);
				coupons.add(new Coupon(st.getInt("coupons.id"),Category.valueOf("name"),st.getString("title")));
				}	System.out.println(company);
			
					return company;
					
				  
				
			}finally{pool.restoreConnection(con);
			}//why
			}
		

//	public  Array getCompanyInfoAndCoupons(int id) throws SQLException{
//		Connection con=pool.getConnection();
//		try{
//			PreparedStatement stmt= con.prepareStatement("select * from coupons where company_id="+id);
//			ResultSet et =stmt.executeQuery();
//			if(et.first()){
//				Array ids;
//				ids=et.getArray(1);
//				System.out.println(ids);
//				return ids;
//				
//			}
//		}finally{pool.restoreConnection(con);}
//		return null;
//	}
	
	
	@Override
	public boolean isCompanyExists(String email, String password)
			throws SQLException {//exception here?
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from companies");
			ResultSet et = stmt.executeQuery();
			while (et.next()) {
				if (et.getString("email").equals(email)
						&& et.getString("password").equals(password))
					//break;no meaning to break. return breaks the loop
				return true;///?

			}

		} finally {
			pool.restoreConnection(con);

		}return false;

	
}

	@Override
	public void updateCompany(int id) throws SQLException {
		Connection con=pool.getConnection();
		try{
			
			PreparedStatement stmt = con.prepareStatement("update companies set name=?,email=?,password=? where id=?");
		stmt.setString(1,"hi" );
		stmt.setString(2,"hi" );
		stmt.setString(3,"hi" );
		stmt.setInt(4,id );
			stmt.execute();
			
		}finally{pool.restoreConnection(con);
		
	}}}
