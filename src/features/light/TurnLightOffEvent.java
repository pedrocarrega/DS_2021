package features.light;

public class TurnLightOffEvent extends LightEvent {

	private static final long serialVersionUID = 1L;

	public TurnLightOffEvent(Light light) {
		super(light);
	}
}
