package jobThread;

import java.sql.SQLException;
import java.util.Calendar;

import beans.Coupon;
import dao.CouponDAO;
import dao.CouponDBDAO;

public class CouponExpirationDailyJob extends Thread {

	// private Calendar cal=Calendar.getInstance();
	private CouponDAO coupDAO = new CouponDBDAO();// downcasting
	private boolean quit;

	// public CouponExpirationDailyJob() {//defaultic- no t needed?
	// super();
	// }
	public void run() {

		while (!quit) {
			Calendar time = Calendar.getInstance();
			try {
				for (Coupon coup : coupDAO.getAllcoupons()) {
					if (time.getTime().after((coup.getEndDate()))) {
						System.out.println("coupon " + coup.getId()
								+ " has expired and deleted ");

						coupDAO.deleteCouponPurchase(coup.getId());
						coupDAO.deleteCoupon(coup.getId());


						//}System.out.println(coup.getId()+ " :" +coup.isSalePrice()+" : "+coup.getPrice());

					}
					if ((coup.getAmount() <= 100 && coup.getAmount()>10)
							&& 
							(coup.isSalePrice()==false)) {
						//System.out.println(coup.isIsSalePrice());

						coup.setPrice(coup.getPrice() * 0.8);//each time reduces the price

						coup.setIsSalePrice(true);
//						System.out.println("test2");
						coupDAO.updateCoupon(coup);
						//System.out.println(coup.isIsSalePrice());
						//error
						// go through the differences in coupon
						System.out
						.println("**limited time sale** 20% off on coupon number "
								+ coup.getId()+" ** "+coup.getTitle()+"**  of company: "+coup.getCompanyID());
					}
					//System.out.println(coup.isIsSalePrice());
					//}

				} }catch (SQLException e) {
					System.out.println("error!" + e.getMessage());
				}
			try {
				Thread.sleep(60*60);
			} catch (InterruptedException e1) {
			}
		}

	}

	public void JobStop() {
		quit = true;
		interrupt();
	}
}
