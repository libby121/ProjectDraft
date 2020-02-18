package beans;

import java.sql.Date;

public class Coupon {

	private int id;
	private int companyID;
	private Category category;//i can get in the ctor a category from the specific list only
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;
	private boolean IsSalePrice;




	public Coupon(int id, int companyID, Category category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image, boolean isSalePrice) {
		super();
		this.id = id;
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		IsSalePrice = isSalePrice;
	}



	public Coupon(int companyID, Category category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		super();		
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}



	public Coupon(int id, int companyID, Category category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image) {		
		this.id = id;
		this.companyID = companyID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;

	}

	public int getCompanyID() {
		return companyID;
	}


	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyID + ", title="
				+ title + ", description=" + description + ", startDate="
				+ startDate + ", endDate=" + endDate + ", amount=" + amount
				+ ", price=" + price + ", image=" + image + ",Category="+category +"]";
	}


	public String toPrint(){//?same signature
		//a short version of toString
		return String.format("id=%s, Category=%s,title=%s", id,category,title);
	}



	public boolean isSalePrice() {
		return IsSalePrice;
	}



	public void setIsSalePrice(boolean isSalePrice) {
		IsSalePrice = isSalePrice;
	}



	public int getId() {
		return id;
	}









}



