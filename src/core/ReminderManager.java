package core;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import i18n.I18N;
import i18n.Messages;

public class ReminderManager {

	private static HashMap<String, Reminder> reminder;

	public ReminderManager() {
		reminder = new HashMap<>();
	}


	public String createReminder(Scanner sc) {

		System.out.println(I18N.getString(Messages.INSERT_REMINDER_NAME));
		String name = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_START_TIME));
		String startTime = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_END_TIME));
		String endTime = sc.nextLine();
		System.out.println(I18N.getString(Messages.INSERT_PERIODICITY));
		int periodicity = Integer.parseInt(sc.nextLine());

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM");
			Date parsedDate = dateFormat.parse(startTime);
			Timestamp timestamp_startTime = new java.sql.Timestamp(parsedDate.getTime());
			parsedDate = dateFormat.parse(endTime);
			Timestamp timestamp_endTime = new java.sql.Timestamp(parsedDate.getTime());
			Reminder new_reminder = new Reminder(name, timestamp_startTime, timestamp_endTime, periodicity);
			if(!reminder.containsKey(name)) {
				reminder.put(name, new_reminder);
				return I18N.getString(Messages.CREATE_REMINDER_SUCCESS);
			} else {
				return I18N.getString(Messages.CREATE_REMINDER_FAILED);
			}
		} catch (Exception e) {
			return I18N.getString(Messages.CREATE_REMINDER_FAILED);
		}	
	}

	public String removeReminder(Scanner sc) {

		System.out.println(printReminders());
		System.out.println(I18N.getString(Messages.PICK_REMINDER));
		int picked = Integer.parseInt(sc.nextLine());
		int x = 1;

		for(String s : reminder.keySet()) {
			if(x++ == picked)
				reminder.remove(s);
		}
		if(x > picked)
			return I18N.getString(Messages.REMOVE_REMINDER_FAILURE);
		else
			return I18N.getString(Messages.REMOVE_REMINDER_SUCCESS);

	}

	private String printReminders() {

		StringBuilder sb = new StringBuilder();
		int x = 1;

		for (String s : reminder.keySet()) {
			sb.append(x++ + " - " + s);
		}

		return sb.toString();
	}

}
