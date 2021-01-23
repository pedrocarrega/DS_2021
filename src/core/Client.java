package core;

import java.util.HashMap;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import examples.AirQualityUpdateEvent;

public class Client {
	
	public static void main(String[] args) {
		
		//System.out.println("Hello World");
		
		HashMap<String, Contacts> contacts;
		
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Device Client Zirk");
		
		//TODO how could we do this?
		final EventSet events = new EventSet();
		
		events.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				// TODO should be treated by aspects
				if (event instanceof Contacts) {
					Contacts contact = (Contacts) event;
					if (contact.isEmergencyContact()) {
						//TODO Does it go here?
					}
				}
			}
		});
		bezirk.subscribe(events);
	}

}
