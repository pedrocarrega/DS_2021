package aspects;
import core.Client;
import i18n.I18N;
import i18n.Messages;

public aspect Lights {
	
	after() : execution(* *.inicializeBezirk(..)) {
		Client.sendLightEvent();
		System.out.println(I18N.getString(Messages.LIGHT) +"\n");
	}
}
