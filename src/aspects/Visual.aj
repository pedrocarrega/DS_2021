package aspects;

public aspect Visual {
	
	
	after() : call(void java.io.PrintStream.println(..)){
		
	}
}