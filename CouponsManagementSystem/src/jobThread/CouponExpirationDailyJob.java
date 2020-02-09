package jobThread;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import beans.Coupon;
import dao.CouponDAO;
import dao.CouponDBDAO;
import facades.CompanyFacade;

public class CouponExpirationDailyJob extends Thread {

	private Calendar cal=Calendar.getInstance();
	private CouponDAO coupDAO=new CouponDBDAO();//downcasting
	private boolean quit;

	

	public CouponExpirationDailyJob() {
		super();
	}

//	public CouponExpirationDailyJob(CouponDAO coupDAO, boolean quit) {
//
//		this.coupDAO = coupDAO;
//		this.quit = quit;//default is false in boolean
//	}
//
//	public CouponExpirationDailyJob() {
//		// TODO Auto-generated constructor stub
//	}

	public void run(){
		while(!quit){
			Calendar time=cal.getInstance();//Calendar.getInstance?
			try {//WHY ONLY CATCH HERE?
				for (Coupon coup : coupDAO.getAllcoupons()) {

					//				cal.setTime(coup.getEndDate());
					//				int month = cal.get(Calendar.MONTH);
					//				int day = cal.get(Calendar.DAY_OF_MONTH);
					//				int year = cal.get(Calendar.YEAR);



					//	String str=coup.getEndDate;
					if(coup.getEndDate().after(cal.getTime())){

						//if(((Date.valueOf(str).after(cal.getTime()))){
						//if(time.after(coup.getEndDate())){
						///coupDAO.deleteCoupon(coup.getId());
						CompanyFacade fac=new CompanyFacade();//here? new? why not above
						fac.deleteCoupon(coup.getId());

					}
				}


			}
			catch (SQLException e) { System.out.println("error!"+e.getMessage());

			try {
				Thread.sleep(86_400_00);
			} catch (InterruptedException e1) {
			}
			}
		}


	}

	public void JobStop(){
		quit=true;
		interrupt();//the method belongs this class.no need new
//		if(quit){//if someone changed the var to true
//			CouponExpirationDailyJob t1=new CouponExpirationDailyJob(); //? 
//
//			t1.interrupt();//?
//			quit=false;//in case someone will restart the thread
//
//		}
	}}


