package aspects;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import com.bezirk.middleware.messages.Event;

import core.Client;
import events.MovementEvent;
import i18n.I18N;
import i18n.Messages;

public aspect Movement {
	
	private static LocalTime Client.movePatternStartTime;
	private static LocalTime Client.movePatternEndTime;
	private static String Client.movePatternPlace;
	
	
	after(HashMap<String, Consumer<String>> menu): execution(* *.initializeMenu()) && args(menu){

		menu.put(I18N.getString(Messages.MOVEMENT_OPTION), (i) -> createPattern());
		
	}
	
	private String createPattern() {
		Scanner sc = new Scanner(System.in);
		System.out.println(I18N.getString(Messages.START_TIME));
		String startTime = sc.nextLine();
		System.out.println(I18N.getString(Messages.END_TIME));
		String endTime = sc.nextLine();
		
		Client.movePatternStartTime = LocalTime.parse(startTime);
		Client.movePatternEndTime = LocalTime.parse(endTime);
		

		System.out.println(I18N.getString(Messages.LOCATION));
		Client.movePatternPlace = sc.nextLine();
		
		sc.close();
		
		return I18N.getString(Messages.MOVEMENT_PATTERN_CREATED);
	}
	
	after(List<Class<? extends Event>> events): initialization(java.util.ArrayList+.new(..)) && args(events){
		events.add(MovementEvent.class);
	}
	
	after(Event event) : execution(void *.receiveEvent(..)) && target(event) {
		if (event instanceof MovementEvent) {
			MovementEvent temp = (MovementEvent) event;
			if(temp.getTime().isBefore(Client.movePatternStartTime) && temp.getTime().isAfter(Client.movePatternEndTime)) {
				System.out.println(I18N.getString(Messages.MOVEMENT_ALERT) + temp.toString());
			}
		}
	}
}