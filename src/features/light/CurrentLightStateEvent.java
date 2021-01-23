package features.light;

public class CurrentLightStateEvent extends LightEvent {

	private static final long serialVersionUID = 1L;
	
	private String lightStage;
	
	

	public CurrentLightStateEvent(Light light, String lightStage) {
		super(light);
		this.lightStage = lightStage;
	}

	public String getLightStage() {
		return lightStage;
	}



	public void setLightStage(String lightStage) {
		this.lightStage = lightStage;
	}



	public CurrentLightStateEvent(Light light) {
		super(light);
	}
	

}
