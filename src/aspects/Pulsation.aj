package aspects;

import java.util.List;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet.EventReceiver;

import events.PulsationEvent;
import i18n.I18N;
import i18n.Messages;

public aspect Pulsation {
	
	after(): call(* core.Device.inicializeMenu()){
		core.Device.menu.put(I18N.getString(Messages.HEART_RATE_OPTION), (i) -> sendHeartRate(core.Device.bezirk));
	}
	
	
	private void sendHeartRate(Bezirk bezirk) {
		bezirk.sendEvent(new PulsationEvent());
	}

	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(PulsationEvent.class);
	}
	
	after(EventReceiver event) : call(* com.bezirk.middleware.messages.EventSet.setEventReceiver(EventReceiver)) && target(event){
		if (event instanceof PulsationEvent) {
			PulsationEvent temp = (PulsationEvent) event;
			System.out.println(temp.toString());
		}
	}
}