package features.light;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import javafx.scene.paint.Color;

public class LightZirkTest {

    public static void main(String[] args) {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Light Assistant");

        final EventSet lightEvents = new EventSet(LightsDetectedEvent.class, CurrentLightStateEvent.class);

        lightEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof LightsDetectedEvent) {
                    Set<Light> lights = ((LightsDetectedEvent) event).getLights();

                    for (final Light light : lights) {
                    	System.out.println("Found light: " + light.getId());
                     
                        bezirk.sendEvent(new GetLightStateEvent(light));
                        bezirk.sendEvent(new TurnLightOnEvent(light));
                        bezirk.sendEvent(new SetLightColorEvent(light, Color.TRANSPARENT));

                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                bezirk.sendEvent(new TurnLightOffEvent(light));
                            }
                        }, 2000);
                    }
                } else if (event instanceof CurrentLightStateEvent) {
                    CurrentLightStateEvent lightState = (CurrentLightStateEvent) event;
                    System.out.println("Light Stage: " + lightState.getLight().getOn_off());
                }
            }
        });

        bezirk.subscribe(lightEvents);
    }
}
