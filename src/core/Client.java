package core;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import events.AlertEvent;
import i18n.I18N;
import i18n.Messages;

public class Client {

	public static LinkedHashMap<String, Consumer<String>> menu;
	public static Contacts contacts;
	private static Scanner sc;
	private static ReminderManager reminders;

	public static void main(String[] args) {
		
		reminders = new ReminderManager();
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

	private static void inicializeMenu() {
		
		menu = new LinkedHashMap<>();
		sc = new Scanner(System.in);
		menu.put(I18N.getString(Messages.ADD_CONTACT), (i) -> contacts.createContact(sc));
		menu.put(I18N.getString(Messages.EDIT_CONTACT), (i) -> contacts.editContact(sc));
		menu.put(I18N.getString(Messages.REMOVE_CONTACT), (i) -> contacts.removeContact(sc));
		menu.put(I18N.getString(Messages.ADD_REMINDER), (i) -> reminders.createReminder(sc));
		menu.put(I18N.getString(Messages.REMOVE_REMINDER), (i) -> reminders.removeReminder(sc));
	}

	private static void inicializeBezirk() {

		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Device Client Zirk");

		List<Class<? extends Event>> listenEvents = new ArrayList<>();
		listenEvents.add(AlertEvent.class);

		@SuppressWarnings("unchecked")
		Class<? extends Event>[] array = new Class[listenEvents.size()];
		listenEvents.toArray(array);
		
		final EventSet events = new EventSet(array);
		
		events.setEventReceiver(new EventSet.EventReceiver() {

			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
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
			sb.append(x++ + " - " + s + "\n");
		}

		return sb.toString();
	}

	/*
	public static void sendButtonEvent() {
        final ButtonEvent buttonEvent = new ButtonEvent(true);
        //sends the event
        bezirk.sendEvent(buttonEvent);
        System.err.println("Button event sent.");
    }
	
	public static void sendLightEvent() {
        final LightEvent lightEvent = new LightEvent("ON");
        //sends the event
        bezirk.sendEvent(lightEvent);
        System.err.println("Light event sent.");
    }
*/
}
