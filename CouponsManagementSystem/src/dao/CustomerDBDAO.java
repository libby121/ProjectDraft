package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import beans.Customer;

public class CustomerDBDAO implements CustomerDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExist(String email, String password)
			throws SQLException {
		Connection con = pool.getConnection();// get one connection from the
		// pool
		try {

			PreparedStatement stmt = con
					.prepareStatement("select * from customers");
			ResultSet et = stmt.executeQuery();
			while (et.next()) {
				if (et.getString("email").equals(email)
						&& et.getString("password").equals(password))
					return true;
				else
					System.out.println("no such customer!");

			}

		} finally {
			pool.restoreConnection(con);
		}
		return false;

	}

	@Override
	public void addCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("insert into customers (first_name,last_name,email,password) values(?,?,?,?)");
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			stmt.execute();
			System.out.println("customer added!");
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();// check if i can change id//
		// add a method for updating only part of details
		try {
			PreparedStatement stmt = con
					.prepareStatement("update customers set first_name=?, last_name=?, email=?, password=? where id="+customer.getId());
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getEmail());
			stmt.setString(4, customer.getPassword());
			stmt.execute();
			System.out.println("customer updated!");
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCustomer(int id) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("delete from customers where id=" + id);
			stmt.execute();
			System.out.println("customer deleted!");//what about the customer vs coupon table?

		} finally {
			pool.restoreConnection(con);
		}
	}
	
	public void deleteCustomerPurchase(int id) throws SQLException{//In order to delete i need both queries
		Connection con=pool.getConnection();
		try{
			PreparedStatement stmt=con.prepareStatement("delete from customers_vs_coupons where customerID=" +id);
			stmt.execute();
		}finally{pool.restoreConnection(con);
	}
		}

	@Override
	public ArrayList<Customer> getAllcustomers() throws SQLException {
		Connection con = pool.getConnection();//what if some of the details are missing for a customer?
		try {
			PreparedStatement stmt = con
					.prepareStatement("select * from customers ");
			ResultSet et = stmt.executeQuery();
			ArrayList<Customer>customers=new ArrayList<Customer>();
			while (et.next()) {
				customers.add(new Customer(et.getInt("id"),et.getString("first_name"),et.getString("last_name"),et.getString("email"),et.getString("password")));

			}
			return customers;
		} finally {
			pool.restoreConnection(con);
		}

	}
//get all customers of a company->in couponDAO
	@Override
	public Customer getOneCustmer(int id) throws SQLException {
		Connection con= pool.getConnection();
		try{
			PreparedStatement stmt = con.prepareStatement("select* from customers where id= "+id);

			ResultSet st= stmt.executeQuery();
			if(st.first()){
				Customer customer = new Customer(st.getInt("id"),st.getString("first_name"),st.getString("last_name"),st.getString("email"),st.getString("password"));
				return customer;
			}
			System.out.println("no such customer!");
			return null;
			
		}finally{pool.restoreConnection(con);}
	}

}
