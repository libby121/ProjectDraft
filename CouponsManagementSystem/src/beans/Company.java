package beans;

import java.util.ArrayList;


public class Company {

	private int id;// no set for id
	private String name;
	private String email;
	private String password;
	private ArrayList<Coupon>coupons;
	
	public Company(){};
	
	public Company(int id, String name, String email, String password,
			ArrayList<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Company(String name, String email, String password) {// when creating
																// new company.
																// its AI SO i
																// cant give an
																// id

		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Company(int id, String name, String email, String password) {//when i want to read i do want t see the id

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

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + "]";
	}

	public String getAllinformation() {
		return "Company info [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", coupons="+ coupons+"]";
	}

}
