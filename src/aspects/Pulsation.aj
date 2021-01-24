package aspects;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.Bezirk;

import events.PulsationEvent;
import i18n.I18N;
import i18n.Messages;
import core.Device;

public aspect Pulsation {
	
	after(HashMap<String, Consumer<String>> menu, Bezirk bezirk): execution(* Device.initializeMenu()) && args(menu, bezirk){

		menu.put(I18N.getString(Messages.HEART_RATE_OPTION), (i) -> sendHeartRate(bezirk));
	}
	
	
	private void sendHeartRate(Bezirk bezirk) {
		bezirk.sendEvent(new PulsationEvent());
	}

	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(PulsationEvent.class);
	}
	
	after(Event event) : execution(void *.receiveEvent(..)) && target(event) {
		if (event instanceof PulsationEvent) {
			PulsationEvent temp = (PulsationEvent) event;
			System.out.println(temp.toString());
		}
	}
}