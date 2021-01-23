package features.light;

public class TurnLightOnEvent  extends LightEvent {

	private static final long serialVersionUID = 1L;

	public TurnLightOnEvent(Light light) {
		super(light);
	}
}
