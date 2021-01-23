package aspects;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public aspect Visual {
	
	after(String words) : call(void java.io.PrintStream.println(..)) && args(words) {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		Voice voice = VoiceManager.getInstance().getVoice("kevin16");
		voice.allocate();
		voice.speak(words);
	}
}