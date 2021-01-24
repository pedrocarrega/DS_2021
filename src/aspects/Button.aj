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

public aspect Button {
	
	after(HashMap<String, Consumer<String>> menu, Bezirk bezirk): execution(* Device.initializeMenu()) && args(menu, bezirk){

		menu.put(I18N.getString(Messages.BUTTON_OPTION), (i) -> pressButton(bezirk));
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