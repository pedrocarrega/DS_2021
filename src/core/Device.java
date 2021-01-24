package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import events.AlertEvent;

public class Device {
	
	private static HashMap<String, Consumer<String>> menu;
	private static Scanner sc;
	
	public static void main(String[] args) {
		
		inicializeMenu();
		inicializeBezirk();
		if(!menu.isEmpty())
			start();
		
	}

	private static void start() {
		
		do {
			System.out.println(printMenu());
			int input = Integer.parseInt(sc.nextLine());
			int x = 1;
			for(Consumer<String> f : menu.values()) {
				if(input == x++) {
					f.accept("");
					break;
				}
			}
		} while (true);	
	}

	public static void inicializeMenu() {
				
		menu = new HashMap<>();
		sc = new Scanner(System.in);
		
	}

	private static void inicializeBezirk() {
		
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Device Client Zirk");
		
		List<Class<? extends Event>> listenEvents = new ArrayList<>();
		listenEvents.add(AlertEvent.class);
		
		@SuppressWarnings("unchecked")
		final EventSet events = new EventSet((Class<? extends Event>[]) listenEvents.toArray());
		
		events.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				//TODO should be treated by aspects, unless its core event
				if (event instanceof AlertEvent) {
					AlertEvent temp = (AlertEvent) event;
					if(temp.getContact() != 0) {
						//assumindo que este não é o numero para o qual nao foi fowarded aqui fazia foward
						//sendAlertToNumber(temp);
					}
					System.out.println(temp.toString());
					
				}
			}
		});
		bezirk.subscribe(events);
	}

	private static String printMenu() {
		
		StringBuilder sb = new StringBuilder();
		int x = 1;
		
		for (String s : menu.keySet()) {
			sb.append(x++ + " - " + s);
		}

		return sb.toString();
	}

}