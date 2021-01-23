package features.light;

import javafx.scene.paint.Color;

public class Light {
	
	private String id;
	
	private String on_off;
	
	private Color color;

	public String getOn_off() {
		return on_off;
	}

	public void setOn_off(String on_off) {
		this.on_off = on_off;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Light(String id, String on_off, Color color) {
		super();
		this.id = id;
		this.on_off = on_off;
		this.color = color;
	}
	
}
