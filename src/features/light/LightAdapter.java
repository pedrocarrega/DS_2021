package features.light;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class LightAdapter {

    private final LightController lightController;

    public LightAdapter(final Bezirk bezirk) {
        lightController = new LightController();
        
        final EventSet lightEventSet = new EventSet(TurnLightOnEvent.class, TurnLightOffEvent.class, SetLightColorEvent.class, GetLightStateEvent.class);

        lightEventSet.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof LightEvent) {
                    final Light light = ((LightEvent) event).getLight();

                    if (event instanceof TurnLightOnEvent) {
                        lightController.turnLightOn(light);
                    } else if (event instanceof TurnLightOffEvent) {
                        lightController.turnLightOff(light);
                    } else if (event instanceof SetLightColorEvent) {
                        lightController.setLightColor(light, ((SetLightColorEvent) event).getColorEvent());

                    } else if (event instanceof CurrentLightStateEvent) {
                        final CurrentLightStateEvent lightStateEvent =
                                lightController.getLightState(light);
                        bezirk.sendEvent(sender, lightStateEvent);
                    }
                }
            }
        });

        bezirk.subscribe(lightEventSet);

        bezirk.sendEvent(new LightsDetectedEvent(lightController.findLights()));
    }
}