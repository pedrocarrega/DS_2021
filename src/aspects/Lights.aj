package aspects;
import i18n.I18N;
import i18n.Messages;

public aspect Lights {
	
	after() : call(void java.io.PrintStream.println(..)){
		System.out.println(I18N.getString(Messages.LIGHT) +"\n");
	}
}
