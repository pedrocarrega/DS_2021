package features.light;

import java.util.HashSet;
import java.util.Set;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import i18n.I18N;
import javafx.scene.paint.Color;

public class LightController {
	
	private Bezirk bezirk;
	
	 public LightController() {
	        BezirkMiddleware.initialize();
	        bezirk = BezirkMiddleware.registerZirk("Light Controller");
	    }


    public Set<Light> findLights() {
        final Set<Light> foundLights = new HashSet<>();
        Light light1 = new Light("Light1", "OFF", Color.TRANSPARENT);
        foundLights.add(light1);
        Light light2 = new Light("Light2", "OFF", Color.TRANSPARENT);
        foundLights.add(light2);
        Light light3 = new Light("Light3", "OFF", Color.TRANSPARENT);
        foundLights.add(light3);
        return foundLights;
    }

    public CurrentLightStateEvent getLightState(Light light) {
    	return new CurrentLightStateEvent(light, light.getOn_off());
    }

    public void turnLightOn(Light light) {
    	light.setOn_off("ON");
    }

    public void turnLightOff(Light light) {
    	light.setOn_off("OFF");
    }

    public void setLightColor(Light light, Color color) {
    	light.setColor(color);
    }
    
    public void sendTurnLightOn(Light light) {
    	final TurnLightOnEvent turnLightOnEvent = new TurnLightOnEvent(light);
    	bezirk.sendEvent(turnLightOnEvent);
    	System.out.println("Send Turn On Event");

    }
    
    public static void main(String args[]) throws InterruptedException {
    	LightController lightController = new LightController();
        System.err.println("This product has lights");
        System.err.println(I18N.getString(i18n.Messages.DEVICE_RUNNING, "Light"));
        
       // lightController.sendTurnLightOn("1", );

     }

}
