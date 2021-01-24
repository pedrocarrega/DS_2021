package core;

import java.sql.Timestamp;

public class Reminder {
	
	private Timestamp currentTime;
	private String person_name;
	private String reminder_name;
	private Timestamp startTime;
	private Timestamp endTime;
	private int periodicity;
	
	public Reminder (String reminder_name, Timestamp startTime, Timestamp endTime, int periodicity) {
		this.reminder_name = reminder_name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.periodicity = periodicity;
	}

	@Override
	public String toString() {
		return "Reminder [currentTime=" + currentTime + ", person_name=" + person_name + ", reminder_name="
				+ reminder_name + ", startTime=" + startTime + ", endTime=" + endTime + ", periodicity=" + periodicity
				+ "]";
	}

	public Timestamp getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Timestamp currentTime) {
		this.currentTime = currentTime;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}

	public String getReminder_name() {
		return reminder_name;
	}

	public void setReminder_name(String reminder_name) {
		this.reminder_name = reminder_name;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}
	
	
}
