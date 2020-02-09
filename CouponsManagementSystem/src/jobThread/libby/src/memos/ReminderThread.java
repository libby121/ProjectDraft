package memos;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderThread extends Thread {

	private ArrayList<Reminder> reminders;

	public ReminderThread(ArrayList<Reminder> reminders) {

		this.reminders = reminders;
	}

	
	public boolean isEqual(Reminder r) {
		Calendar caly = Calendar.getInstance();

		int year1 = caly.get(Calendar.YEAR);
		int year = r.getDueDate().get(Calendar.YEAR);
		int month1 = caly.get(Calendar.MONTH);
		int month = r.getDueDate().get(Calendar.MONTH);
		int day1 = caly.get(Calendar.DATE);
		int day = r.getDueDate().get(Calendar.DATE);
		int hour1 = caly.get(Calendar.HOUR_OF_DAY);
		int hour = r.getDueDate().get(Calendar.HOUR_OF_DAY);
		int minute1 = caly.get(Calendar.MINUTE);
		int minute = r.getDueDate().get(Calendar.MINUTE);

		if ((year1 == year) && (month1 == month) && (day1 == day) && (hour1 == hour) && (minute1 == minute)) {
			return true;
		}
		return false;

	}

	public void run() 
	{
		while (true) {
			Calendar cal = Calendar.getInstance();
			for (Reminder reminder : reminders) {
//				if (isEqual(reminder)) {
//					System.out.println("it's time for " + reminder.getName());
//					reminder.setPoped(true);
//				}

				if (reminder.isImportant() && isEqual(reminder)) {
					for (int i = 0; i < 3; i++) {

						System.out.println("it's time for : " + reminder.getName());
						reminder.setPoped(true);
						try {
							Thread.sleep(60_000);
						} catch (InterruptedException e) {

							System.out.println("still tired..");
						}

					}

				} else if (isEqual(reminder)) {
					System.out.println("it's time for " + reminder.getName());
					reminder.setPoped(true);
					
				}

			}
			try {
				Thread.sleep(60_000);
			} catch (InterruptedException e) {
			}

		}
	}
}
