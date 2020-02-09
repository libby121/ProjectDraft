package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customer;

public interface CustomerDAO {

	boolean isCustomerExist(String email,String password)throws SQLException;
	void addCustomer(Customer customer)throws SQLException;
	void updateCustomer(Customer customer)throws SQLException;
	void deleteCustomer(int id)throws SQLException;
	ArrayList<Customer>getAllcustomers()throws SQLException;
	Customer getOneCustmer (int id)throws SQLException;
}
