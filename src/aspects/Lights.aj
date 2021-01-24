          package aspects;
import java.util.List;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet.EventReceiver;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import events.LightEvent;
import i18n.I18N;
import i18n.Messages;

public aspect Lights {
	
	after(Bezirk bezirk) : call(void java.io.PrintStream.println(..)) && target(bezirk){
		String words = I18N.getString(Messages.LIGHT) + "\n";
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		voice.allocate();
		System.out.print(I18N.getString(Messages.LIGHT) + "\n");
		voice.speak(words);
		bezirk.sendEvent(new LightEvent());
	}
	
	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(LightEvent.class);
	}
	
	after(EventReceiver event) : call(* com.bezirk.middleware.messages.EventSet.setEventReceiver(EventReceiver)) && target(event){
		if (event instanceof LightEvent)
			System.out.println("");
	}
}
