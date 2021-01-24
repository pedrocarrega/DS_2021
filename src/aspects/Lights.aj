package aspects;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import i18n.I18N;
import i18n.Messages;

public aspect Lights {
	
	after() : call(void java.io.PrintStream.println(..)){
		String words = I18N.getString(Messages.LIGHT) + "\n";
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		voice.allocate();
		System.out.print(I18N.getString(Messages.LIGHT) + "\n");
		voice.speak(words);
	}
}
