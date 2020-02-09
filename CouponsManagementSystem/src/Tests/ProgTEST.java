package Tests;



import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import dao.CompanyDBDAO;
import dao.ConnectionPool;
import dao.CouponDBDAO;
import dao.CustomerDBDAO;
import beans.Category;
import beans.Company;
import beans.Coupon;

public class ProgTEST {
	
	public static void main(String[] args) {
		
		
//		CustomerDBDAO custDBD=new CustomerDBDAO();
//		try {
//			//System.out.println(custDBD.getOneCustmer(20).getCoupons());
//		} catch (SQLException e) {System.out.println("error!"+e.getMessage());
//		}
		
		CouponDBDAO coupDBD= new CouponDBDAO();
		try {
			//
			//System.out.println(coupDBD.getCouponsByCustomerId(20));
			for (Coupon coup : coupDBD.getCouponsByCustomerId(20)) {
				System.out.println(coup);
			}
		} catch (SQLException e) {System.out.println("error!"+e.getMessage());
		}
//		Coupon coup=new Coupon(12, Category.clothes, "dresses");
//		String str="2025-2-8";
//		//Date date=new Date(2025-2-8);
//		
//		String date = "2015-04-12";
//		java.sql.Date dat = java.sql.Date.valueOf(date);
//		//create calander instance and get required params
//		Calendar cal = Calendar.getInstance();
//		//cal.sett;
//		cal.setTime(dat);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DAY_OF_MONTH);
//		int year = cal.get(Calendar.YEAR);
//		System.out.println(year);
//		
////		Calendar cal=Calendar.getInstance();
////		java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());
////	    System.out.println(sqlDate);
	
		//cal.setTime(date);
		//System.out.println(cal);


	 
		//Coupon coup=new Coupon(12, Category.clothes, "dresses");
		//String str="2025-2-8";
	//	Calendar cal=Calendar.getInstance();
		//coup.setEndDate(Date.valueOf(str));
		//cal.getTimeInMillis()
		
		//System.out.println(coup.getEndDate().gett.after(cal.getTimeInMillis()))));
		//cal.getTime().
		//System.out.println((Date.valueOf(str).after(cal.getTime())));
		//System.out.println(((cal.getTime().after(Date.valueOf(str)))));//same day-treats it as after the current day
		//System.out.println(coup.getEndDate().after
			//System.out.println((Date.valueOf(str).after(cal.getTimeInMillis())));
		
//		Date date= new Date(
//		 Date fromSqlTime = new Date(Time.valueOf("17:00:00").getTime());

		
//		public boolean isEqual(Reminder r) {
//			Calendar caly = Calendar.getInstance();
//
//			int year1 = caly.get(Calendar.YEAR);
//			int year = r.getDueDate().get(Calendar.YEAR);
//			int month1 = caly.get(Calendar.MONTH);
//			int month = r.getDueDate().get(Calendar.MONTH);
//			int day1 = caly.get(Calendar.DATE);
//			int day = r.getDueDate().get(Calendar.DATE);
//			int hour1 = caly.get(Calendar.HOUR_OF_DAY);
//			int hour = r.getDueDate().get(Calendar.HOUR_OF_DAY);
//			int minute1 = caly.get(Calendar.MINUTE);
//			int minute = r.getDueDate().get(Calendar.MINUTE);
//
//			if ((year1 == year) && (month1 == month) && (day1 == day) && (hour1 == hour) && (minute1 == minute)) {
//				return true;
//			}
//			return false;
//
//		}
//
//		public void run() 
//		{
//			while (true) {
//				Calendar cal = Calendar.getInstance();
//				for (Reminder reminder : reminders) {
////					if (isEqual(reminder)) {
////						System.out.println("it's time for " + reminder.getName());
////						reminder.setPoped(true);
////					}
//
//					if (reminder.isImportant() && isEqual(reminder)) {
//						for (int i = 0; i < 3; i++) {
//
//							System.out.println("it's time for : " + reminder.getName());
//							reminder.setPoped(true);
//							try {
//								Thread.sleep(60_000);
//							} catch (InterruptedException e) {
//
//								System.out.println("still tired..");
//							}
//
//						}
//
//					} else if (isEqual(reminder)) {
//						System.out.println("it's time for " + reminder.getName());
//						reminder.setPoped(true);
//						
//					}
//
//				}
//				try {
//					Thread.sleep(60_000);
//				} catch (InterruptedException e) {
//				}
//
//			}
	}}