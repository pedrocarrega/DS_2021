package events;

import java.time.LocalTime;

import com.bezirk.middleware.messages.Event;

public class MovementEvent extends Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LocalTime time;

	public LocalTime getTime() {
		return time;
	}

	public MovementEvent(String time) {
		this.time = LocalTime.parse(time);
	}
	
	@Override
	public String toString() {
		return time.toString();
	}

}
