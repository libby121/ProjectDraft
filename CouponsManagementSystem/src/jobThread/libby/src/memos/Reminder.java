package memos;

import java.util.Calendar;

public class Reminder {
	
	private String name;
	private Calendar dueDate;
	private boolean isImportant;
	private boolean isPoped;
	
	
	public Reminder(String name, Calendar cal, boolean isImportant) {
	
		this.name = name;
		this.dueDate = cal;
		this.isImportant = isImportant;
		//this.isPoped = isPoped;
	}


	@Override
	public String toString() {
		return "Reminder [name=" + name + ", cal=" + dueDate.getTime() + ", isImportant=" + isImportant + ", isAlreadyPoped?=" + isPoped
				+ "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Calendar getDueDate() {
		return dueDate;
	}


	public void setCal(Calendar cal) {
		this.dueDate = cal;
	}


	public boolean isImportant() {
		return isImportant;
	}


	public void setImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}


	public boolean isPoped() {
		return isPoped;
	}


	public void setPoped(boolean isPoped) {
		this.isPoped = isPoped;
	}


	
	
	
	

}
