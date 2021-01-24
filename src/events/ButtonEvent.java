package events;

import com.bezirk.middleware.messages.Event;

public class ButtonEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	public boolean on;

	public ButtonEvent(boolean on) {
		super();
		this.on = on;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}
}
