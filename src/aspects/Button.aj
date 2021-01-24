package aspects;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.Bezirk;

import events.ButtonEvent;
import i18n.I18N;
import i18n.Messages;
import core.Device;
import core.Client;
import core.Contacts;

public aspect Button {

	private String Client.emergencyContact;

	after(HashMap<String, Consumer<String>> menu, Bezirk bezirk): execution(* Device.initializeMenu()) && args(menu, bezirk){

		menu.put(I18N.getString(Messages.BUTTON_OPTION), (i) -> pressButton(bezirk));
	}

	after(HashMap<String, Consumer<String>> menu, Contacts contacts): execution(* Client.initializeMenu()) && args(menu, contacts){

		menu.put(I18N.getString(Messages.BUTTON_OPTION), (i) -> addEmergencyContact(contacts));
		menu.put(I18N.getString(Messages.BUTTON_OPTION), (i) -> addEmergencyContact(contacts));
		menu.put(I18N.getString(Messages.BUTTON_OPTION), (i) -> addEmergencyContact(contacts));
	}

	private String addEmergencyContact(Contacts contacts) {



		return null;
	}

	private String removeEmergencyContact(Contacts contacts) {



		return null;
	}


	private void pressButton(Bezirk bezirk) {
		bezirk.sendEvent(new ButtonEvent(true));
	}

	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(ButtonEvent.class);
	}

	after(Event event) : execution(void *.receiveEvent(..)) && target(event) {
		if (event instanceof ButtonEvent)
			System.out.println(I18N.getString(Messages.BUTTON_ALERT) + LocalDateTime.now().toString());
	}
}