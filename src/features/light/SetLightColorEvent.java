package features.light;

import javafx.scene.paint.Color;

public class SetLightColorEvent extends LightEvent {
	
	private Color colorEvent;

	private static final long serialVersionUID = 1L;

	public Color getColorEvent() {
		return colorEvent;
	}

	public void setColorEvent(Color colorEvent) {
		this.colorEvent = colorEvent;
	}

	public SetLightColorEvent(Light light, Color colorEvent) {
		super(light);
		this.colorEvent = colorEvent;
	}

	
}