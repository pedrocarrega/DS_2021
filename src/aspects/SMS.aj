package aspects;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.messages.Event;

import core.Client;
import events.ButtonEvent;
import events.SMSEvent;
import i18n.I18N;
import i18n.Messages;

public aspect SMS {
	
	after(HashMap<String, Consumer<String>> menu, Bezirk bezirk): execution(* Device.initializeMenu()) && args(menu, bezirk){

		menu.put(I18N.getString(Messages.SMS_OPTION), (i) -> sendSMS(bezirk));
	}
	
	
	private void sendSMS(Bezirk bezirk) {
		bezirk.sendEvent(new SMSEvent(I18N.getString(Messages.SMS_TEXT)));
	}

	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(SMSEvent.class);
	}
	
	after(Event event) : execution(void *.receiveEvent(..)) && target(event) {
		if (event instanceof SMSEvent)
			System.out.println(I18N.getString(Messages.SMS_SENT) + LocalDateTime.now().toString());
	}
}