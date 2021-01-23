package core;

import com.bezirk.middleware.messages.Event;

public class AlertEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	public String alert;

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}
	
	public String toString() {
        return String.format("Alerta: ", alert);
    }
}
