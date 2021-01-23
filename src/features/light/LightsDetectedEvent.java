package features.light;

import java.util.Set;

import com.bezirk.middleware.messages.Event;

public class LightsDetectedEvent extends Event {

	private static final long serialVersionUID = 1L;
	
	private Set<Light> lights;

	public LightsDetectedEvent(Set<Light> lights) {
		super();
		this.lights = lights;
	}

	public Set<Light> getLights() {
		return lights;
	}

	public void setLights(Set<Light> lights) {
		this.lights = lights;
	}
	
	

}
