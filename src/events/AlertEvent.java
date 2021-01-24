package events;

import com.bezirk.middleware.messages.Event;

import i18n.I18N;
import i18n.Messages;

public class AlertEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	public String alert;
	public int alertContact;

	public AlertEvent (String alert) {
		this.alert = alert;
		this.alertContact = 0;
	}
	
	public AlertEvent (String alert, int phone) {
		this.alert = alert;
		this.alertContact = phone;
	}
	
	public String getAlert() {
		return alert;
	}

	public int getContact() {
		return alertContact;
	}
	
	public String toString() {
		if(alertContact == 0)
			return alert;
		else
			return alert + I18N.getString(Messages.ALERT_FOWARD) + alertContact;
			
    }
}
