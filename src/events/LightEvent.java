package events;

import com.bezirk.middleware.messages.Event;

public class LightEvent extends Event {

	private static final long serialVersionUID = 1L;

	private String light;

	public String getLight() {
		return light;
	}

	public LightEvent(String light) {
		this.light = light;
	}
	
	@Override
	public String toString() {
		return light;
	}

}