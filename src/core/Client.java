package core;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;
import com.sun.imageio.plugins.common.I18N;

import i18n.Messages;

public class Client {
	
	private static HashMap<String, Function> menu;
	private static Contacts contacts;
	private static Scanner sc;
	
	public static void main(String[] args) {
		
		//System.out.println("Hello World");
		
		contacts = new Contacts();
		inicializeMenu();
		inicializeBezirk();
		
		
	}

	private static void inicializeMenu() {
		// TODO Auto-generated method stub
		menu = new HashMap<>();
		sc = new Scanner(System.in);
		menu.put(I18N.getString(Messages.ADD_CONTACT), (sc) -> contacts.createContact((Scanner) sc));
		menu.put(I18N.getString(Messages.CHANGE_CONTACT), (sc) -> contacts.createContact((Scanner) sc));
		
	}

	private static void inicializeBezirk() {
		
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Device Client Zirk");
		
		//TODO how could we do this?
		final EventSet events = new EventSet();
		
		events.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				// TODO should be treated by aspects
			}
		});
		bezirk.subscribe(events);
	}

}
