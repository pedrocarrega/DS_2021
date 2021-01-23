package features.light;

import com.bezirk.middleware.messages.Event;

public class LightEvent extends Event {

	private static final long serialVersionUID = 1L;

	private Light light;

	public Light getLight() {
		return light;
	}

	public void setLight(Light light) {
		this.light = light;
	}

	
	public LightEvent(Light light) {
		super();
		this.light = light;
	}

}