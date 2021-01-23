package features.light;

public class GetLightStateEvent extends LightEvent {

	private static final long serialVersionUID = 1L;

	public GetLightStateEvent(Light light) {
		super(light);
	}
	
}
