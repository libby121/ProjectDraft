package beans;

import java.util.ArrayList;


public class Company {

	private int id;// no set for id
	private String name;
	private String email;
	private String password;
	//private double balance; //company's balance column gets updated with a customer's purchase. 
	//add balance to tostring
//	public double getBalance() {
//		return balance;
//	}

	private ArrayList<Coupon>coupons;
	
	
	public Company(int id, String name, String email) {
		//In case admin facade is not adding the password to a new company
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Company(String name, String email, String password) { 
		//a CTOR for adding a new company to sql table(AI won't allow me to add id)
		this.name = name;
		this.email = email;
		this.password = password;
		
	}

	public Company(int id, String name, String email, String password) {
		//a CTOR for reading a company from sql table
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {//?
		this.password = password;
	}

	
	
	public String info() {
		//an optional method for reading all about the company//?
		return "Company [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons+ "]";
	}

	public String toString() {
		return "Company info [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + "]";
	}

}
