package dao;
//atomic actions  of data base communication
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Company;

public interface CompanyDAO {//all methods are automatically public->interface

	boolean isCompanyExists(String email, String password)throws SQLException;

	void addCompany(Company company) throws SQLException;

	void updateCompany(Company company)throws SQLException;
	void updateCompany(int id)throws SQLException;

	void deleteCompany(int id)throws SQLException;

	ArrayList<Company> getAllCompanies()throws SQLException;// dont we take companies

	Company getOneCompany(int id)throws SQLException;

	
}
