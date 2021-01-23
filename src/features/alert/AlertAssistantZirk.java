package features.alert;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class AlertAssistantZirk{
    public AlertAssistantZirk() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Alert Assistant Zirk");
        System.err.println("Got Bezirk instance");
        
        final EventSet alertEvents = new EventSet(AlertEvent.class);

        alertEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                if (event instanceof AlertEvent) {
                    final AlertEvent alertUpdate = (AlertEvent) event;
                    System.err.println("\nReceived alert update: " + alertUpdate.toString());
                    System.out.println(alertUpdate.getAlert());
                }
            }
        });
        bezirk.subscribe(alertEvents);
    }

    public static void main(String args[]) {
        new AlertAssistantZirk();
        System.err.println("This product has an Alert Assistant");
    }
}
