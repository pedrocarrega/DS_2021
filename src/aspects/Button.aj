package aspects;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet.EventReceiver;

import core.Client;
import core.Contacts;
import events.ButtonEvent;
import i18n.I18N;
import i18n.Messages;

public aspect Button {

	private static String Client.emergencyContact;
	
	after(): execution(* core.Device.inicializeMenu()){
		core.Device.menu.put(I18N.getString(Messages.BUTTON_OPTION), (i) -> pressButton(core.Device.bezirk));
	}
	
	after(): execution(* core.Client.inicializeMenu()){
		core.Client.menu.put(I18N.getString(Messages.ADD_EMERGENCY_CONTACT), (i) -> addEmergencyContact(core.Client.contacts));
	}

	private String addEmergencyContact(Contacts contacts) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println(I18N.getString(Messages.ADD_CONTACT));
		String name = sc.nextLine();

		if(!contacts.containsContact(name)) {
			System.out.println(I18N.getString(Messages.INSERT_NUMBER));
			int phoneNumber = Integer.parseInt(sc.nextLine());
			if(!contacts.containsContact(phoneNumber))
				contacts.addContacts(name, phoneNumber);
		}
		sc.close();
		
		Client.emergencyContact = name;

		return I18N.getString(Messages.EMERGENCY_CONTACT_SUCCESS);
	}


	private void pressButton(Bezirk bezirk) {
		bezirk.sendEvent(new ButtonEvent(true));
	}

	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(ButtonEvent.class);
	}

	
	after(EventReceiver event) : call(* com.bezirk.middleware.messages.EventSet.setEventReceiver(EventReceiver)) && target(event){
		if (event instanceof ButtonEvent)
			System.out.println(I18N.getString(Messages.BUTTON_ALERT) + LocalDateTime.now().toString());
	}
}