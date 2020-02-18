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

	private ConnectionPool pool = ConnectionPool.getInstance();

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
		System.out.println("added!");

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
					.prepareStatement("update companies set name=?, email=?,password=? where id="
							+ company.getId());
			stmt.setString(1, company.getName());
			stmt.setString(2, company.getEmail());
			stmt.setString(3, company.getPassword());

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
		System.out.println("deleted");
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
			//System.out.println("test");
			
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
				
				return company;
			
			}else return null; 
			
		}finally{pool.restoreConnection(con);}//why in object i dont need to return anything after the finally,but in boolean i do?
		
	}
//		public Company getOneCompanyCoupons(int id) throws SQLException{
//			//an additional method for the help of the facade
//			Connection con=pool.getConnection();
//			try{
//				PreparedStatement stmt= con.prepareStatement("select companies.name, companies.email, companies.password, coupons. where id="+id);
//				ResultSet et= stmt.executeQuery();
//				if(et.last()){
//					Company company=new Company(id,et.getString("name"),et.getString("email"),et.getString("password"));
//				
//					return company;
//					
//				}else return null; 
//				
//			}finally{pool.restoreConnection(con);}//why in object i dont need to return anything after the finally,but in boolean i do?
//			
	
//			
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

	
}}

	
