package events;

import com.bezirk.middleware.messages.Event;

public class AlertEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	public String alert;

	public AlertEvent (String alert) {
		this.alert = alert;
	}
	
	public String getAlert() {
		return alert;
	}
	
	public String toString() {
        return alert;
    }
}
