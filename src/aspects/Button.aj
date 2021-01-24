package aspects;

import core.Client;
import i18n.I18N;
import i18n.Messages;

public aspect Button {
	
	//pointcut main() : execution(* core.Client.main(..));
	
	after() : execution(* *.inicializeBezirk(..)) {
		Client.sendButtonEvent();
		System.out.println(I18N.getString(Messages.DANGER) +"\n");
	}
	
	//void around(Event event, ZirkEndPoint sender) : receiveEvents() && target(sender) && args(event) {
	//	//new AlertAssistantZirk();
    //    System.err.println("Button");
	//}
}