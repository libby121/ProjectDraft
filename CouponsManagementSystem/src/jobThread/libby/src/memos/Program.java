package memos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		ArrayList<Reminder> tasks = new ArrayList<Reminder>();
		Calendar caly= Calendar.getInstance();
		caly.set(Calendar.YEAR, 2020);
		caly.set(Calendar.MONTH, 0);
		caly.set(Calendar.DATE, 20);
		caly.set(Calendar.HOUR_OF_DAY, 12);
		caly.set(Calendar.MINUTE, 50);
		
		tasks.add(new Reminder("feeding the rabbit", caly, false));
		ReminderThread ted = new ReminderThread(tasks);
		ted.start();
		int userChoice;
		//System.out.println("enter your choice:1 for adding a reminder, 2 for watching your list 3 to exit ");
		//Scanner sc = new Scanner(System.in);
		//int userChoice = sc.nextInt();
		Calendar taskTime = Calendar.getInstance();

		do {
			System.out.println("enter your choice:1> adding a reminder, 2>  watching your list, 3> exit ");
			Scanner sc = new Scanner(System.in);
			 userChoice = sc.nextInt();
			switch (userChoice) {
			case 1:
				System.out.println("you chose to add a task! whats your task name? ");
				sc.nextLine();
				String name = sc.nextLine();

				System.out.println("choose a dueDate\nfirst choose the year: ");
				int year = sc.nextInt();

				System.out.println("choose the month : **remember that the months start with 0");
				int month = sc.nextInt();

				System.out.println(" choose the day : ");
				int day = sc.nextInt();

				System.out.println(" choose the hour :  ");
				int hour = sc.nextInt();

				System.out.println("and the minutes : ");
				int minutes = sc.nextInt();

				System.out.println("is your task super importatnt?- true for super important, false for the negative");
				sc.nextLine();
				boolean imp = sc.nextBoolean();
				

				taskTime.set(year, month, day, hour, minutes);
				tasks.add(new Reminder(name, taskTime, imp));
				System.out.println("a task reminder added to your list. great!");
				break;

			case 2:
				System.out.println(tasks);
				break;

			case 3:
				System.out.println("and now get to work:)");
				break;
			default:
				System.out.println("please enter a valid number. 1-3");
			break;
			}
		} while (userChoice != 3);
		

	}
}
