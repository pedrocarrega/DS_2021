package aspects;

public aspect Alert {
	//TODO Mudar para o main da aplica��o
	before() : execution(* *.main(..)) {
		//new AlertAssistantZirk();
        System.err.println("This product has an Alert Assistant");
	}
}