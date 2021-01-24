package events;

import java.util.Random;

import com.bezirk.middleware.messages.Event;

import i18n.I18N;
import i18n.Messages;

public class PulsationEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	public int heartRate;

	public PulsationEvent () {
		this.heartRate = new Random().nextInt(120);
	}

	public int getHeartRate() {
		return heartRate;
	}
	
	public String toString() {
		return I18N.getString(Messages.HEART_RATE) + heartRate;
			
    }
}
