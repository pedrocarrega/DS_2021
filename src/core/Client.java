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

import i18n.I18N;
import i18n.Messages;

public class Client {
	
	private static HashMap<String, Consumer<String>> menu;
	private static Contacts contacts;
	private static Scanner sc;
	
	public static void main(String[] args) {
		
		contacts = new Contacts();
		inicializeMenu();
		inicializeBezirk();
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
		menu.put(I18N.getString(Messages.ADD_CONTACT), (i) -> contacts.createContact(sc));
		menu.put(I18N.getString(Messages.EDIT_CONTACT), (i) -> contacts.editContact(sc));
		menu.put(I18N.getString(Messages.REMOVE_CONTACT), (i) -> contacts.removeContact(sc));
		
	}

	private static void inicializeBezirk() {
		
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Device Client Zirk");
		
		//TODO how could we do this?
		List<Class<? extends Event>> listenEvents = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		final EventSet events = new EventSet((Class<? extends Event>[]) listenEvents.toArray());
		
		events.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				//TODO should be treated by aspects, unless its core event
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
