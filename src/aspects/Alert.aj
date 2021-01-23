package aspects;

import features.alert.AlertAssistantZirk;

public aspect Alert {
	//TODO Mudar para o main da aplicação
	before() : execution(* *.main(..)) {
		new AlertAssistantZirk();
        System.err.println("This product has an Alert Assistant");
	}
}